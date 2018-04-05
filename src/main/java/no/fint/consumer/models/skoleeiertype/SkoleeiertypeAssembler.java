package no.fint.consumer.models.skoleeiertype;

import no.fint.model.utdanning.kodeverk.Skoleeiertype;
import no.fint.model.relation.FintResource;
import no.fint.relations.FintResourceAssembler;
import no.fint.relations.FintResourceSupport;
import org.springframework.stereotype.Component;

@Component
public class SkoleeiertypeAssembler extends FintResourceAssembler<Skoleeiertype> {

    public SkoleeiertypeAssembler() {
        super(SkoleeiertypeController.class);
    }


    @Override
    public FintResourceSupport assemble(Skoleeiertype skoleeiertype , FintResource<Skoleeiertype> fintResource) {
        return createResourceWithId(skoleeiertype.getSystemId().getIdentifikatorverdi(), fintResource, "systemid");
    }
    
    
    
}

