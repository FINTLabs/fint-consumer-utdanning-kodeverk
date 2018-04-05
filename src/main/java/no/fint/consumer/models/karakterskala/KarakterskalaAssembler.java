package no.fint.consumer.models.karakterskala;

import no.fint.model.utdanning.kodeverk.Karakterskala;
import no.fint.model.relation.FintResource;
import no.fint.relations.FintResourceAssembler;
import no.fint.relations.FintResourceSupport;
import org.springframework.stereotype.Component;

@Component
public class KarakterskalaAssembler extends FintResourceAssembler<Karakterskala> {

    public KarakterskalaAssembler() {
        super(KarakterskalaController.class);
    }


    @Override
    public FintResourceSupport assemble(Karakterskala karakterskala , FintResource<Karakterskala> fintResource) {
        return createResourceWithId(karakterskala.getSystemId().getIdentifikatorverdi(), fintResource, "systemid");
    }
    
    
    
}

