package no.novari.fint.consumer.models.varseltype;

import no.novari.fint.model.resource.utdanning.kodeverk.VarseltypeResource;
import no.novari.fint.model.resource.utdanning.kodeverk.VarseltypeResources;
import no.novari.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class VarseltypeLinker extends FintLinker<VarseltypeResource> {

    public VarseltypeLinker() {
        super(VarseltypeResource.class);
    }

    public void mapLinks(VarseltypeResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public VarseltypeResources toResources(Collection<VarseltypeResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public VarseltypeResources toResources(Stream<VarseltypeResource> stream, int offset, int size, int totalItems) {
        VarseltypeResources resources = new VarseltypeResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(VarseltypeResource varseltype) {
        return getAllSelfHrefs(varseltype).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(VarseltypeResource varseltype) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(varseltype.getSystemId()) && !isEmpty(varseltype.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(varseltype.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(VarseltypeResource varseltype) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(varseltype.getSystemId()) && !isEmpty(varseltype.getSystemId().getIdentifikatorverdi())) {
            builder.add(varseltype.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

