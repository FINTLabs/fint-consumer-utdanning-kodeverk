package no.fint.consumer.models.otenhet;

import no.fint.model.resource.utdanning.kodeverk.OTEnhetResource;
import no.fint.model.resource.utdanning.kodeverk.OTEnhetResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class OTEnhetLinker extends FintLinker<OTEnhetResource> {

    public OTEnhetLinker() {
        super(OTEnhetResource.class);
    }

    public void mapLinks(OTEnhetResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public OTEnhetResources toResources(Collection<OTEnhetResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public OTEnhetResources toResources(Stream<OTEnhetResource> stream, int offset, int size, int totalItems) {
        OTEnhetResources resources = new OTEnhetResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(OTEnhetResource otenhet) {
        return getAllSelfHrefs(otenhet).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(OTEnhetResource otenhet) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(otenhet.getSystemId()) && !isEmpty(otenhet.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(otenhet.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(OTEnhetResource otenhet) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(otenhet.getSystemId()) && !isEmpty(otenhet.getSystemId().getIdentifikatorverdi())) {
            builder.add(otenhet.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

