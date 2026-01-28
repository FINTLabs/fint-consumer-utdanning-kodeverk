package no.fint.consumer.models.betalingsstatus;

import no.novari.fint.model.resource.utdanning.kodeverk.BetalingsstatusResource;
import no.novari.fint.model.resource.utdanning.kodeverk.BetalingsstatusResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class BetalingsstatusLinker extends FintLinker<BetalingsstatusResource> {

    public BetalingsstatusLinker() {
        super(BetalingsstatusResource.class);
    }

    public void mapLinks(BetalingsstatusResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public BetalingsstatusResources toResources(Collection<BetalingsstatusResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public BetalingsstatusResources toResources(Stream<BetalingsstatusResource> stream, int offset, int size, int totalItems) {
        BetalingsstatusResources resources = new BetalingsstatusResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(BetalingsstatusResource betalingsstatus) {
        return getAllSelfHrefs(betalingsstatus).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(BetalingsstatusResource betalingsstatus) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(betalingsstatus.getSystemId()) && !isEmpty(betalingsstatus.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(betalingsstatus.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(BetalingsstatusResource betalingsstatus) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(betalingsstatus.getSystemId()) && !isEmpty(betalingsstatus.getSystemId().getIdentifikatorverdi())) {
            builder.add(betalingsstatus.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

