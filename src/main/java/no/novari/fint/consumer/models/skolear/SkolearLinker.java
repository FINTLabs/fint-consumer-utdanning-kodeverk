package no.novari.fint.consumer.models.skolear;

import no.novari.fint.model.resource.utdanning.kodeverk.SkolearResource;
import no.novari.fint.model.resource.utdanning.kodeverk.SkolearResources;
import no.novari.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class SkolearLinker extends FintLinker<SkolearResource> {

    public SkolearLinker() {
        super(SkolearResource.class);
    }

    public void mapLinks(SkolearResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public SkolearResources toResources(Collection<SkolearResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public SkolearResources toResources(Stream<SkolearResource> stream, int offset, int size, int totalItems) {
        SkolearResources resources = new SkolearResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(SkolearResource skolear) {
        return getAllSelfHrefs(skolear).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(SkolearResource skolear) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(skolear.getSystemId()) && !isEmpty(skolear.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(skolear.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(SkolearResource skolear) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(skolear.getSystemId()) && !isEmpty(skolear.getSystemId().getIdentifikatorverdi())) {
            builder.add(skolear.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

