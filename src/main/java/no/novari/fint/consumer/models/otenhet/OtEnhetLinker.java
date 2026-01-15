package no.novari.fint.consumer.models.otenhet;

import no.novari.fint.model.resource.utdanning.kodeverk.OtEnhetResource;
import no.novari.fint.model.resource.utdanning.kodeverk.OtEnhetResources;
import no.novari.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class OtEnhetLinker extends FintLinker<OtEnhetResource> {

    public OtEnhetLinker() {
        super(OtEnhetResource.class);
    }

    public void mapLinks(OtEnhetResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public OtEnhetResources toResources(Collection<OtEnhetResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public OtEnhetResources toResources(Stream<OtEnhetResource> stream, int offset, int size, int totalItems) {
        OtEnhetResources resources = new OtEnhetResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(OtEnhetResource otenhet) {
        return getAllSelfHrefs(otenhet).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(OtEnhetResource otenhet) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(otenhet.getSystemId()) && !isEmpty(otenhet.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(otenhet.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(OtEnhetResource otenhet) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(otenhet.getSystemId()) && !isEmpty(otenhet.getSystemId().getIdentifikatorverdi())) {
            builder.add(otenhet.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

