package no.fint.consumer.models.fullfortkode;

import no.novari.fint.model.resource.utdanning.kodeverk.FullfortkodeResource;
import no.novari.fint.model.resource.utdanning.kodeverk.FullfortkodeResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class FullfortkodeLinker extends FintLinker<FullfortkodeResource> {

    public FullfortkodeLinker() {
        super(FullfortkodeResource.class);
    }

    public void mapLinks(FullfortkodeResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public FullfortkodeResources toResources(Collection<FullfortkodeResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public FullfortkodeResources toResources(Stream<FullfortkodeResource> stream, int offset, int size, int totalItems) {
        FullfortkodeResources resources = new FullfortkodeResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(FullfortkodeResource fullfortkode) {
        return getAllSelfHrefs(fullfortkode).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(FullfortkodeResource fullfortkode) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(fullfortkode.getSystemId()) && !isEmpty(fullfortkode.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(fullfortkode.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(FullfortkodeResource fullfortkode) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(fullfortkode.getSystemId()) && !isEmpty(fullfortkode.getSystemId().getIdentifikatorverdi())) {
            builder.add(fullfortkode.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

