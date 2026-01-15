package no.novari.fint.consumer.models.brevtype;

import no.novari.fint.model.resource.utdanning.kodeverk.BrevtypeResource;
import no.novari.fint.model.resource.utdanning.kodeverk.BrevtypeResources;
import no.novari.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class BrevtypeLinker extends FintLinker<BrevtypeResource> {

    public BrevtypeLinker() {
        super(BrevtypeResource.class);
    }

    public void mapLinks(BrevtypeResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public BrevtypeResources toResources(Collection<BrevtypeResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public BrevtypeResources toResources(Stream<BrevtypeResource> stream, int offset, int size, int totalItems) {
        BrevtypeResources resources = new BrevtypeResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(BrevtypeResource brevtype) {
        return getAllSelfHrefs(brevtype).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(BrevtypeResource brevtype) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(brevtype.getSystemId()) && !isEmpty(brevtype.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(brevtype.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(BrevtypeResource brevtype) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(brevtype.getSystemId()) && !isEmpty(brevtype.getSystemId().getIdentifikatorverdi())) {
            builder.add(brevtype.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

