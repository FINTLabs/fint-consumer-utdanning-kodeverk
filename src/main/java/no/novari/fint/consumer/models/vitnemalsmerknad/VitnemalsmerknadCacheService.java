package no.novari.fint.consumer.models.vitnemalsmerknad;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.extern.slf4j.Slf4j;

import no.fint.cache.CacheService;
import no.fint.cache.model.CacheObject;
import no.novari.fint.consumer.config.Constants;
import no.novari.fint.consumer.config.ConsumerProps;
import no.novari.fint.consumer.event.ConsumerEventUtil;
import no.fint.event.model.Event;
import no.fint.event.model.ResponseStatus;
import no.novari.fint.relations.FintResourceCompatibility;
import no.novari.fint.model.resource.FintLinks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import no.novari.fint.model.utdanning.kodeverk.Vitnemalsmerknad;
import no.novari.fint.model.resource.utdanning.kodeverk.VitnemalsmerknadResource;
import no.novari.fint.model.utdanning.kodeverk.KodeverkActions;
import no.novari.fint.model.felles.kompleksedatatyper.Identifikator;

@Slf4j
@Service
@ConditionalOnProperty(name = "fint.consumer.cache.disabled.vitnemalsmerknad", havingValue = "false", matchIfMissing = true)
public class VitnemalsmerknadCacheService extends CacheService<VitnemalsmerknadResource> {

    public static final String MODEL = Vitnemalsmerknad.class.getSimpleName().toLowerCase();

    @Value("${fint.consumer.compatibility.fintresource:true}")
    private boolean checkFintResourceCompatibility;

    @Autowired
    private FintResourceCompatibility fintResourceCompatibility;

    @Autowired
    private ConsumerEventUtil consumerEventUtil;

    @Autowired
    private ConsumerProps props;

    @Autowired
    private VitnemalsmerknadLinker linker;

    private JavaType javaType;

    private ObjectMapper objectMapper;

    public VitnemalsmerknadCacheService() {
        super(MODEL, KodeverkActions.GET_ALL_VITNEMALSMERKNAD, KodeverkActions.UPDATE_VITNEMALSMERKNAD);
        objectMapper = new ObjectMapper();
        javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, VitnemalsmerknadResource.class);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    @PostConstruct
    public void init() {
        props.getAssets().forEach(this::createCache);
    }

    @Scheduled(initialDelayString = Constants.CACHE_INITIALDELAY_VITNEMALSMERKNAD, fixedRateString = Constants.CACHE_FIXEDRATE_VITNEMALSMERKNAD)
    public void populateCacheAll() {
        props.getAssets().forEach(this::populateCache);
    }

    public void rebuildCache(String orgId) {
		flush(orgId);
		populateCache(orgId);
	}

    @Override
    public void populateCache(String orgId) {
		log.info("Populating Vitnemalsmerknad cache for {}", orgId);
        Event event = new Event(orgId, Constants.COMPONENT, KodeverkActions.GET_ALL_VITNEMALSMERKNAD, Constants.CACHE_SERVICE);
        consumerEventUtil.send(event);
    }


    public Optional<VitnemalsmerknadResource> getVitnemalsmerknadBySystemId(String orgId, String systemId) {
        return getOne(orgId, systemId.hashCode(),
            (resource) -> Optional
                .ofNullable(resource)
                .map(VitnemalsmerknadResource::getSystemId)
                .map(Identifikator::getIdentifikatorverdi)
                .map(systemId::equals)
                .orElse(false));
    }


	@Override
    public void onAction(Event event) {
        List<VitnemalsmerknadResource> data;
        if (checkFintResourceCompatibility && fintResourceCompatibility.isFintResourceData(event.getData())) {
            log.info("Compatibility: Converting FintResource<VitnemalsmerknadResource> to VitnemalsmerknadResource ...");
            data = fintResourceCompatibility.convertResourceData(event.getData(), VitnemalsmerknadResource.class);
        } else {
            data = objectMapper.convertValue(event.getData(), javaType);
        }
        data.forEach(resource -> {
            linker.mapLinks(resource);
            linker.resetSelfLinks(resource);
        });
        if (KodeverkActions.valueOf(event.getAction()) == KodeverkActions.UPDATE_VITNEMALSMERKNAD) {
            if (event.getResponseStatus() == ResponseStatus.ACCEPTED || event.getResponseStatus() == ResponseStatus.CONFLICT) {
                List<CacheObject<VitnemalsmerknadResource>> cacheObjects = data
                    .stream()
                    .map(i -> new CacheObject<>(i, linker.hashCodes(i)))
                    .collect(Collectors.toList());
                addCache(event.getOrgId(), cacheObjects);
                log.info("Added {} cache objects to cache for {}", cacheObjects.size(), event.getOrgId());
            } else {
                log.debug("Ignoring payload for {} with response status {}", event.getOrgId(), event.getResponseStatus());
            }
        } else {
            List<CacheObject<VitnemalsmerknadResource>> cacheObjects = data
                    .stream()
                    .map(i -> new CacheObject<>(i, linker.hashCodes(i)))
                    .collect(Collectors.toList());
            updateCache(event.getOrgId(), cacheObjects);
            log.info("Updated cache for {} with {} cache objects", event.getOrgId(), cacheObjects.size());
        }
    }
}
