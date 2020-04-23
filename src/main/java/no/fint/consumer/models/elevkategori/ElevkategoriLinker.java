package no.fint.consumer.models.elevkategori;

import no.fint.model.resource.Link;
import no.fint.model.resource.utdanning.kodeverk.ElevkategoriResource;
import no.fint.model.resource.utdanning.kodeverk.ElevkategoriResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class ElevkategoriLinker extends FintLinker<ElevkategoriResource> {

    public ElevkategoriLinker() {
        super(ElevkategoriResource.class);
    }

    public void mapLinks(ElevkategoriResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public ElevkategoriResources toResources(Collection<ElevkategoriResource> collection) {
        ElevkategoriResources resources = new ElevkategoriResources();
        collection.stream().map(this::toResource).forEach(resources::addResource);
        resources.addSelf(Link.with(self()));
        return resources;
    }

    @Override
    public String getSelfHref(ElevkategoriResource elevkategori) {
        return getAllSelfHrefs(elevkategori).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(ElevkategoriResource elevkategori) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(elevkategori.getSystemId()) && !isEmpty(elevkategori.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(elevkategori.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(ElevkategoriResource elevkategori) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(elevkategori.getSystemId()) && !isEmpty(elevkategori.getSystemId().getIdentifikatorverdi())) {
            builder.add(elevkategori.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

