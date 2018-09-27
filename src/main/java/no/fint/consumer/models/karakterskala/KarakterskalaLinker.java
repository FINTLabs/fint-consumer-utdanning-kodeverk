package no.fint.consumer.models.karakterskala;

import no.fint.model.resource.Link;
import no.fint.model.resource.utdanning.kodeverk.KarakterskalaResource;
import no.fint.model.resource.utdanning.kodeverk.KarakterskalaResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;

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
        KarakterskalaResources resources = new KarakterskalaResources();
        collection.stream().map(this::toResource).forEach(resources::addResource);
        resources.addSelf(Link.with(self()));
        return resources;
    }

    @Override
    public String getSelfHref(KarakterskalaResource karakterskala) {
        if (!isNull(karakterskala.getSystemId()) && !isEmpty(karakterskala.getSystemId().getIdentifikatorverdi())) {
            return createHrefWithId(karakterskala.getSystemId().getIdentifikatorverdi(), "systemid");
        }
        
        return null;
    }
    
}

