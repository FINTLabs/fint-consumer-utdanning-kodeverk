package no.fint.consumer.models.elevkategori;

import no.fint.model.utdanning.kodeverk.Elevkategori;
import no.fint.model.relation.FintResource;
import no.fint.relations.FintResourceAssembler;
import no.fint.relations.FintResourceSupport;
import org.springframework.stereotype.Component;

@Component
public class ElevkategoriAssembler extends FintResourceAssembler<Elevkategori> {

    public ElevkategoriAssembler() {
        super(ElevkategoriController.class);
    }


    @Override
    public FintResourceSupport assemble(Elevkategori elevkategori , FintResource<Elevkategori> fintResource) {
        return createResourceWithId(elevkategori.getSystemId().getIdentifikatorverdi(), fintResource, "systemid");
    }
    
    
    
}

