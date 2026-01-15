package no.novari.fint.consumer.models.avbruddsarsak;

import no.novari.fint.model.resource.utdanning.kodeverk.AvbruddsarsakResource;
import no.novari.fint.model.resource.utdanning.kodeverk.AvbruddsarsakResources;
import no.novari.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class AvbruddsarsakLinker extends FintLinker<AvbruddsarsakResource> {

    public AvbruddsarsakLinker() {
        super(AvbruddsarsakResource.class);
    }

    public void mapLinks(AvbruddsarsakResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public AvbruddsarsakResources toResources(Collection<AvbruddsarsakResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public AvbruddsarsakResources toResources(Stream<AvbruddsarsakResource> stream, int offset, int size, int totalItems) {
        AvbruddsarsakResources resources = new AvbruddsarsakResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(AvbruddsarsakResource avbruddsarsak) {
        return getAllSelfHrefs(avbruddsarsak).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(AvbruddsarsakResource avbruddsarsak) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(avbruddsarsak.getSystemId()) && !isEmpty(avbruddsarsak.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(avbruddsarsak.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(AvbruddsarsakResource avbruddsarsak) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(avbruddsarsak.getSystemId()) && !isEmpty(avbruddsarsak.getSystemId().getIdentifikatorverdi())) {
            builder.add(avbruddsarsak.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

