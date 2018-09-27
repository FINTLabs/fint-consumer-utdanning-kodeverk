package no.fint.consumer.models.fravarstype;

import no.fint.model.resource.Link;
import no.fint.model.resource.utdanning.kodeverk.FravarstypeResource;
import no.fint.model.resource.utdanning.kodeverk.FravarstypeResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;


@Component
public class FravarstypeLinker extends FintLinker<FravarstypeResource> {

    public FravarstypeLinker() {
        super(FravarstypeResource.class);
    }

    public void mapLinks(FravarstypeResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public FravarstypeResources toResources(Collection<FravarstypeResource> collection) {
        FravarstypeResources resources = new FravarstypeResources();
        collection.stream().map(this::toResource).forEach(resources::addResource);
        resources.addSelf(Link.with(self()));
        return resources;
    }

    @Override
    public String getSelfHref(FravarstypeResource fravarstype) {
        if (!isNull(fravarstype.getSystemId()) && !isEmpty(fravarstype.getSystemId().getIdentifikatorverdi())) {
            return createHrefWithId(fravarstype.getSystemId().getIdentifikatorverdi(), "systemid");
        }
        
        return null;
    }
    
}

