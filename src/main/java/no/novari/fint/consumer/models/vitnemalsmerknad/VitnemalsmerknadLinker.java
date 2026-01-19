package no.novari.fint.consumer.models.vitnemalsmerknad;

import no.novari.fint.model.resource.utdanning.kodeverk.VitnemalsmerknadResource;
import no.novari.fint.model.resource.utdanning.kodeverk.VitnemalsmerknadResources;
import no.novari.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class VitnemalsmerknadLinker extends FintLinker<VitnemalsmerknadResource> {

    public VitnemalsmerknadLinker() {
        super(VitnemalsmerknadResource.class);
    }

    public void mapLinks(VitnemalsmerknadResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public VitnemalsmerknadResources toResources(Collection<VitnemalsmerknadResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public VitnemalsmerknadResources toResources(Stream<VitnemalsmerknadResource> stream, int offset, int size, int totalItems) {
        VitnemalsmerknadResources resources = new VitnemalsmerknadResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(VitnemalsmerknadResource vitnemalsmerknad) {
        return getAllSelfHrefs(vitnemalsmerknad).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(VitnemalsmerknadResource vitnemalsmerknad) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(vitnemalsmerknad.getSystemId()) && !isEmpty(vitnemalsmerknad.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(vitnemalsmerknad.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(VitnemalsmerknadResource vitnemalsmerknad) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(vitnemalsmerknad.getSystemId()) && !isEmpty(vitnemalsmerknad.getSystemId().getIdentifikatorverdi())) {
            builder.add(vitnemalsmerknad.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

