package no.fint.consumer.models.tilrettelegging;

import no.novari.fint.model.resource.utdanning.kodeverk.TilretteleggingResource;
import no.novari.fint.model.resource.utdanning.kodeverk.TilretteleggingResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class TilretteleggingLinker extends FintLinker<TilretteleggingResource> {

    public TilretteleggingLinker() {
        super(TilretteleggingResource.class);
    }

    public void mapLinks(TilretteleggingResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public TilretteleggingResources toResources(Collection<TilretteleggingResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public TilretteleggingResources toResources(Stream<TilretteleggingResource> stream, int offset, int size, int totalItems) {
        TilretteleggingResources resources = new TilretteleggingResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(TilretteleggingResource tilrettelegging) {
        return getAllSelfHrefs(tilrettelegging).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(TilretteleggingResource tilrettelegging) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(tilrettelegging.getSystemId()) && !isEmpty(tilrettelegging.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(tilrettelegging.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(TilretteleggingResource tilrettelegging) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(tilrettelegging.getSystemId()) && !isEmpty(tilrettelegging.getSystemId().getIdentifikatorverdi())) {
            builder.add(tilrettelegging.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

