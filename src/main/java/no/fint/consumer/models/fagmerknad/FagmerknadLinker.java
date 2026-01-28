package no.fint.consumer.models.fagmerknad;

import no.novari.fint.model.resource.utdanning.kodeverk.FagmerknadResource;
import no.novari.fint.model.resource.utdanning.kodeverk.FagmerknadResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class FagmerknadLinker extends FintLinker<FagmerknadResource> {

    public FagmerknadLinker() {
        super(FagmerknadResource.class);
    }

    public void mapLinks(FagmerknadResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public FagmerknadResources toResources(Collection<FagmerknadResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public FagmerknadResources toResources(Stream<FagmerknadResource> stream, int offset, int size, int totalItems) {
        FagmerknadResources resources = new FagmerknadResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(FagmerknadResource fagmerknad) {
        return getAllSelfHrefs(fagmerknad).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(FagmerknadResource fagmerknad) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(fagmerknad.getSystemId()) && !isEmpty(fagmerknad.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(fagmerknad.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(FagmerknadResource fagmerknad) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(fagmerknad.getSystemId()) && !isEmpty(fagmerknad.getSystemId().getIdentifikatorverdi())) {
            builder.add(fagmerknad.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

