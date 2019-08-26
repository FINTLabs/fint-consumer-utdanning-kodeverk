package no.fint.consumer.models.elevkategori;

import no.fint.model.resource.Link;
import no.fint.model.resource.utdanning.kodeverk.ElevkategoriResource;
import no.fint.model.resource.utdanning.kodeverk.ElevkategoriResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;

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
        if (!isNull(elevkategori.getSystemId()) && !isEmpty(elevkategori.getSystemId().getIdentifikatorverdi())) {
            return createHrefWithId(elevkategori.getSystemId().getIdentifikatorverdi(), "systemid");
        }
        
        return null;
    }
    
}

