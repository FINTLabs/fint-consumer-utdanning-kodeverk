package no.fint.consumer.models.karakterskala;

import no.fint.model.resource.utdanning.kodeverk.KarakterskalaResource;
import no.fint.model.resource.utdanning.kodeverk.KarakterskalaResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;

@Component
public class KarakterskalaLinker extends FintLinker<KarakterskalaResource> {

    public KarakterskalaLinker() {
        super(KarakterskalaResource.class);
    }

    public void mapLinks(KarakterskalaResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public KarakterskalaResources toResources(Collection<KarakterskalaResource> collection) {
        return toResources(collection.stream(), 0, 0, collection.size());
    }

    @Override
    public KarakterskalaResources toResources(Stream<KarakterskalaResource> stream, int offset, int size, int totalItems) {
        KarakterskalaResources resources = new KarakterskalaResources();
        stream.map(this::toResource).forEach(resources::addResource);
        addPagination(resources, offset, size, totalItems);
        return resources;
    }

    @Override
    public String getSelfHref(KarakterskalaResource karakterskala) {
        return getAllSelfHrefs(karakterskala).findFirst().orElse(null);
    }

    @Override
    public Stream<String> getAllSelfHrefs(KarakterskalaResource karakterskala) {
        Stream.Builder<String> builder = Stream.builder();
        if (!isNull(karakterskala.getSystemId()) && !isEmpty(karakterskala.getSystemId().getIdentifikatorverdi())) {
            builder.add(createHrefWithId(karakterskala.getSystemId().getIdentifikatorverdi(), "systemid"));
        }
        
        return builder.build();
    }

    int[] hashCodes(KarakterskalaResource karakterskala) {
        IntStream.Builder builder = IntStream.builder();
        if (!isNull(karakterskala.getSystemId()) && !isEmpty(karakterskala.getSystemId().getIdentifikatorverdi())) {
            builder.add(karakterskala.getSystemId().getIdentifikatorverdi().hashCode());
        }
        
        return builder.build().toArray();
    }

}

