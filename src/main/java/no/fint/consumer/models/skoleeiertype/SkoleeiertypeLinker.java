package no.fint.consumer.models.skoleeiertype;

import no.novari.fint.model.resource.utdanning.kodeverk.SkoleeiertypeResource;
import no.novari.fint.model.resource.utdanning.kodeverk.SkoleeiertypeResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class SkoleeiertypeLinker extends FintLinker<SkoleeiertypeResource> {

    public SkoleeiertypeLinker() {
        super(SkoleeiertypeResource.class);
    }

    public void mapLinks(SkoleeiertypeResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public SkoleeiertypeResources toResources(Collection<SkoleeiertypeResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public SkoleeiertypeResources toResources(Stream<SkoleeiertypeResource> stream, int offset, int size, int totalItems) {
        SkoleeiertypeResources resources = new SkoleeiertypeResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(SkoleeiertypeResource skoleeiertype) {
        return getAllSelfHrefs(skoleeiertype).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(SkoleeiertypeResource skoleeiertype) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(skoleeiertype.getSystemId()) && !isEmpty(skoleeiertype.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(skoleeiertype.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(SkoleeiertypeResource skoleeiertype) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(skoleeiertype.getSystemId()) && !isEmpty(skoleeiertype.getSystemId().getIdentifikatorverdi())) {
            builder.add(skoleeiertype.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

