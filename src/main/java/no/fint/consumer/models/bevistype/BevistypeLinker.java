package no.fint.consumer.models.bevistype;

import no.novari.fint.model.resource.utdanning.kodeverk.BevistypeResource;
import no.novari.fint.model.resource.utdanning.kodeverk.BevistypeResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class BevistypeLinker extends FintLinker<BevistypeResource> {

    public BevistypeLinker() {
        super(BevistypeResource.class);
    }

    public void mapLinks(BevistypeResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public BevistypeResources toResources(Collection<BevistypeResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public BevistypeResources toResources(Stream<BevistypeResource> stream, int offset, int size, int totalItems) {
        BevistypeResources resources = new BevistypeResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(BevistypeResource bevistype) {
        return getAllSelfHrefs(bevistype).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(BevistypeResource bevistype) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(bevistype.getSystemId()) && !isEmpty(bevistype.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(bevistype.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(BevistypeResource bevistype) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(bevistype.getSystemId()) && !isEmpty(bevistype.getSystemId().getIdentifikatorverdi())) {
            builder.add(bevistype.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

