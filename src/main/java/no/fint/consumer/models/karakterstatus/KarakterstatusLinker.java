package no.fint.consumer.models.karakterstatus;

import no.novari.fint.model.resource.utdanning.kodeverk.KarakterstatusResource;
import no.novari.fint.model.resource.utdanning.kodeverk.KarakterstatusResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class KarakterstatusLinker extends FintLinker<KarakterstatusResource> {

    public KarakterstatusLinker() {
        super(KarakterstatusResource.class);
    }

    public void mapLinks(KarakterstatusResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public KarakterstatusResources toResources(Collection<KarakterstatusResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public KarakterstatusResources toResources(Stream<KarakterstatusResource> stream, int offset, int size, int totalItems) {
        KarakterstatusResources resources = new KarakterstatusResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(KarakterstatusResource karakterstatus) {
        return getAllSelfHrefs(karakterstatus).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(KarakterstatusResource karakterstatus) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(karakterstatus.getSystemId()) && !isEmpty(karakterstatus.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(karakterstatus.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(KarakterstatusResource karakterstatus) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(karakterstatus.getSystemId()) && !isEmpty(karakterstatus.getSystemId().getIdentifikatorverdi())) {
            builder.add(karakterstatus.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

