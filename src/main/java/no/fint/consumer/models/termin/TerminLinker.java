package no.fint.consumer.models.termin;

import no.fint.model.resource.Link;
import no.fint.model.resource.utdanning.kodeverk.TerminResource;
import no.fint.model.resource.utdanning.kodeverk.TerminResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class TerminLinker extends FintLinker<TerminResource> {

    public TerminLinker() {
        super(TerminResource.class);
    }

    public void mapLinks(TerminResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public TerminResources toResources(Collection<TerminResource> collection) {
        TerminResources resources = new TerminResources();
        collection.stream().map(this::toResource).forEach(resources::addResource);
        resources.addSelf(Link.with(self()));
        return resources;
    }

    @Override
    public String getSelfHref(TerminResource termin) {
        return getAllSelfHrefs(termin).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(TerminResource termin) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(termin.getSystemId()) && !isEmpty(termin.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(termin.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(TerminResource termin) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(termin.getSystemId()) && !isEmpty(termin.getSystemId().getIdentifikatorverdi())) {
            builder.add(termin.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

