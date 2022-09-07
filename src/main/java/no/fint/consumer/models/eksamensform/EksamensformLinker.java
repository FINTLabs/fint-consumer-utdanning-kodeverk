package no.fint.consumer.models.eksamensform;

import no.fint.model.resource.utdanning.kodeverk.EksamensformResource;
import no.fint.model.resource.utdanning.kodeverk.EksamensformResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class EksamensformLinker extends FintLinker<EksamensformResource> {

    public EksamensformLinker() {
        super(EksamensformResource.class);
    }

    public void mapLinks(EksamensformResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public EksamensformResources toResources(Collection<EksamensformResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public EksamensformResources toResources(Stream<EksamensformResource> stream, int offset, int size, int totalItems) {
        EksamensformResources resources = new EksamensformResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(EksamensformResource eksamensform) {
        return getAllSelfHrefs(eksamensform).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(EksamensformResource eksamensform) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(eksamensform.getSystemId()) && !isEmpty(eksamensform.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(eksamensform.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(EksamensformResource eksamensform) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(eksamensform.getSystemId()) && !isEmpty(eksamensform.getSystemId().getIdentifikatorverdi())) {
            builder.add(eksamensform.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

