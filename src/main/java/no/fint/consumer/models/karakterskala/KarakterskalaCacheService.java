package no.fint.consumer.models.karakterskala;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import lombok.extern.slf4j.Slf4j;

import no.fint.cache.CacheService;
import no.fint.consumer.config.Constants;
import no.fint.consumer.config.ConsumerProps;
import no.fint.consumer.event.ConsumerEventUtil;
import no.fint.event.model.Event;
import no.fint.event.model.ResponseStatus;
import no.fint.model.felles.kompleksedatatyper.Identifikator;
import no.fint.relations.FintResourceCompatibility;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import no.fint.model.utdanning.kodeverk.Karakterskala;
import no.fint.model.resource.utdanning.kodeverk.KarakterskalaResource;
import no.fint.model.utdanning.kodeverk.KodeverkActions;

@Slf4j
@Service
public class KarakterskalaCacheService extends CacheService<KarakterskalaResource> {

    public static final String MODEL = Karakterskala.class.getSimpleName().toLowerCase();

    @Value("${fint.consumer.compatibility.fintresource:true}")
    private boolean checkFintResourceCompatibility;

    @Autowired
    private FintResourceCompatibility fintResourceCompatibility;

    @Autowired
    private ConsumerEventUtil consumerEventUtil;

    @Autowired
    private ConsumerProps props;

    @Autowired
    private KarakterskalaLinker linker;

    private JavaType javaType;

    private ObjectMapper objectMapper;

    public KarakterskalaCacheService() {
        super(MODEL, KodeverkActions.GET_ALL_KARAKTERSKALA, KodeverkActions.UPDATE_KARAKTERSKALA);
        objectMapper = new ObjectMapper();
        javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, KarakterskalaResource.class);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }

    @PostConstruct
    public void init() {
        Arrays.stream(props.getOrgs()).forEach(this::createCache);
    }

    @Scheduled(initialDelayString = ConsumerProps.CACHE_INITIALDELAY_KARAKTERSKALA, fixedRateString = ConsumerProps.CACHE_FIXEDRATE_KARAKTERSKALA)
    public void populateCacheAll() {
        Arrays.stream(props.getOrgs()).forEach(this::populateCache);
    }

    public void rebuildCache(String orgId) {
		flush(orgId);
		populateCache(orgId);
	}

    private void populateCache(String orgId) {
		log.info("Populating Karakterskala cache for {}", orgId);
        Event event = new Event(orgId, Constants.COMPONENT, KodeverkActions.GET_ALL_KARAKTERSKALA, Constants.CACHE_SERVICE);
        consumerEventUtil.send(event);
    }


    public Optional<KarakterskalaResource> getKarakterskalaBySystemId(String orgId, String systemId) {
        return getOne(orgId, (resource) -> Optional
                .ofNullable(resource)
                .map(KarakterskalaResource::getSystemId)
                .map(Identifikator::getIdentifikatorverdi)
                .map(_id -> _id.equals(systemId))
                .orElse(false));
    }


	@Override
    public void onAction(Event event) {
        List<KarakterskalaResource> data;
        if (checkFintResourceCompatibility && fintResourceCompatibility.isFintResourceData(event.getData())) {
            log.info("Compatibility: Converting FintResource<KarakterskalaResource> to KarakterskalaResource ...");
            data = fintResourceCompatibility.convertResourceData(event.getData(), KarakterskalaResource.class);
        } else {
            data = objectMapper.convertValue(event.getData(), javaType);
        }
        data.forEach(linker::mapLinks);
        if (KodeverkActions.valueOf(event.getAction()) == KodeverkActions.UPDATE_KARAKTERSKALA) {
            if (event.getResponseStatus() == ResponseStatus.ACCEPTED || event.getResponseStatus() == ResponseStatus.CONFLICT) {
                add(event.getOrgId(), data);
                log.info("Added {} elements to cache for {}", data.size(), event.getOrgId());
            } else {
                log.debug("Ignoring payload for {} with response status {}", event.getOrgId(), event.getResponseStatus());
            }
        } else {
            update(event.getOrgId(), data);
            log.info("Updated cache for {} with {} elements", event.getOrgId(), data.size());
        }
    }
}
