package no.fint.consumer.models.skoleeiertype;

import no.fint.model.resource.Link;
import no.fint.model.resource.utdanning.kodeverk.SkoleeiertypeResource;
import no.fint.model.resource.utdanning.kodeverk.SkoleeiertypeResources;
import no.fint.relations.FintLinker;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static java.util.Objects.isNull;
import static org.springframework.util.StringUtils.isEmpty;


@Component
public class SkoleeiertypeLinker extends FintLinker<SkoleeiertypeResource> {

    public SkoleeiertypeLinker() {
        super(SkoleeiertypeResource.class);
    }

    public void mapLinks(SkoleeiertypeResource resource) {
        super.mapLinks(resource);
    }

    @Override
    public SkoleeiertypeResources toResources(Collection<SkoleeiertypeResource> collection) {
        SkoleeiertypeResources resources = new SkoleeiertypeResources();
        collection.stream().map(this::toResource).forEach(resources::addResource);
        resources.addSelf(Link.with(self()));
        return resources;
    }

    @Override
    public String getSelfHref(SkoleeiertypeResource skoleeiertype) {
        if (!isNull(skoleeiertype.getSystemId()) && !isEmpty(skoleeiertype.getSystemId().getIdentifikatorverdi())) {
            return createHrefWithId(skoleeiertype.getSystemId().getIdentifikatorverdi(), "systemid");
        }

        return null;
    }

}

