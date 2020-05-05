package no.fint.consumer.models.skolear;

import no.fint.model.resource.Link;
import no.fint.model.resource.utdanning.kodeverk.SkolearResource;
import no.fint.model.resource.utdanning.kodeverk.SkolearResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class SkolearLinker extends FintLinker<SkolearResource> {

    public SkolearLinker() {
        super(SkolearResource.class);
    }

    public void mapLinks(SkolearResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public SkolearResources toResources(Collection<SkolearResource> collection) {
        SkolearResources resources = new SkolearResources();
        collection.stream().map(this::toResource).forEach(resources::addResource);
        resources.addSelf(Link.with(self()));
        return resources;
    }

    @Override
    public String getSelfHref(SkolearResource skolear) {
        return getAllSelfHrefs(skolear).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(SkolearResource skolear) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(skolear.getSystemId()) && !isEmpty(skolear.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(skolear.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(SkolearResource skolear) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(skolear.getSystemId()) && !isEmpty(skolear.getSystemId().getIdentifikatorverdi())) {
            builder.add(skolear.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

