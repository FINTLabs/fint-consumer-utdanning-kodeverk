package no.fint.consumer.models.fagstatus;

import no.novari.fint.model.resource.utdanning.kodeverk.FagstatusResource;
import no.novari.fint.model.resource.utdanning.kodeverk.FagstatusResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class FagstatusLinker extends FintLinker<FagstatusResource> {

    public FagstatusLinker() {
        super(FagstatusResource.class);
    }

    public void mapLinks(FagstatusResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public FagstatusResources toResources(Collection<FagstatusResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public FagstatusResources toResources(Stream<FagstatusResource> stream, int offset, int size, int totalItems) {
        FagstatusResources resources = new FagstatusResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(FagstatusResource fagstatus) {
        return getAllSelfHrefs(fagstatus).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(FagstatusResource fagstatus) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(fagstatus.getSystemId()) && !isEmpty(fagstatus.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(fagstatus.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(FagstatusResource fagstatus) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(fagstatus.getSystemId()) && !isEmpty(fagstatus.getSystemId().getIdentifikatorverdi())) {
            builder.add(fagstatus.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

