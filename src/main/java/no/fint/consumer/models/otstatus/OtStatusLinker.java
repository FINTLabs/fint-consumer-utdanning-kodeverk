package no.fint.consumer.models.otstatus;

import no.novari.fint.model.resource.utdanning.kodeverk.OtStatusResource;
import no.novari.fint.model.resource.utdanning.kodeverk.OtStatusResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class OtStatusLinker extends FintLinker<OtStatusResource> {

    public OtStatusLinker() {
        super(OtStatusResource.class);
    }

    public void mapLinks(OtStatusResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public OtStatusResources toResources(Collection<OtStatusResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public OtStatusResources toResources(Stream<OtStatusResource> stream, int offset, int size, int totalItems) {
        OtStatusResources resources = new OtStatusResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(OtStatusResource otstatus) {
        return getAllSelfHrefs(otstatus).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(OtStatusResource otstatus) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(otstatus.getSystemId()) && !isEmpty(otstatus.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(otstatus.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(OtStatusResource otstatus) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(otstatus.getSystemId()) && !isEmpty(otstatus.getSystemId().getIdentifikatorverdi())) {
            builder.add(otstatus.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

