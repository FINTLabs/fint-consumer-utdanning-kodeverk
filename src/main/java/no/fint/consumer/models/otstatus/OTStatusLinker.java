package no.fint.consumer.models.otstatus;

import no.fint.model.resource.utdanning.kodeverk.OTStatusResource;
import no.fint.model.resource.utdanning.kodeverk.OTStatusResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class OTStatusLinker extends FintLinker<OTStatusResource> {

    public OTStatusLinker() {
        super(OTStatusResource.class);
    }

    public void mapLinks(OTStatusResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public OTStatusResources toResources(Collection<OTStatusResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public OTStatusResources toResources(Stream<OTStatusResource> stream, int offset, int size, int totalItems) {
        OTStatusResources resources = new OTStatusResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(OTStatusResource otstatus) {
        return getAllSelfHrefs(otstatus).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(OTStatusResource otstatus) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(otstatus.getSystemId()) && !isEmpty(otstatus.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(otstatus.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(OTStatusResource otstatus) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(otstatus.getSystemId()) && !isEmpty(otstatus.getSystemId().getIdentifikatorverdi())) {
            builder.add(otstatus.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

