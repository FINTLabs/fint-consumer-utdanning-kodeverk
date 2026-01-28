package no.fint.consumer.models.provestatus;

import no.novari.fint.model.resource.utdanning.kodeverk.ProvestatusResource;
import no.novari.fint.model.resource.utdanning.kodeverk.ProvestatusResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class ProvestatusLinker extends FintLinker<ProvestatusResource> {

    public ProvestatusLinker() {
        super(ProvestatusResource.class);
    }

    public void mapLinks(ProvestatusResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public ProvestatusResources toResources(Collection<ProvestatusResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public ProvestatusResources toResources(Stream<ProvestatusResource> stream, int offset, int size, int totalItems) {
        ProvestatusResources resources = new ProvestatusResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(ProvestatusResource provestatus) {
        return getAllSelfHrefs(provestatus).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(ProvestatusResource provestatus) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(provestatus.getSystemId()) && !isEmpty(provestatus.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(provestatus.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(ProvestatusResource provestatus) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(provestatus.getSystemId()) && !isEmpty(provestatus.getSystemId().getIdentifikatorverdi())) {
            builder.add(provestatus.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

