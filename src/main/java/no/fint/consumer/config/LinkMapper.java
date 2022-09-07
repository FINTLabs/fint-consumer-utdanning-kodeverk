package no.fint.consumer.config;

import no.fint.consumer.utils.RestEndpoints;
import java.util.Map;
import com.google.common.collect.ImmutableMap;
import no.fint.model.utdanning.kodeverk.Avbruddsarsak;
import no.fint.model.utdanning.kodeverk.Eksamensform;
import no.fint.model.utdanning.kodeverk.Elevkategori;
import no.fint.model.utdanning.kodeverk.Fagmerknad;
import no.fint.model.utdanning.kodeverk.Fravarstype;
import no.fint.model.utdanning.kodeverk.Karakterskala;
import no.fint.model.utdanning.kodeverk.Karakterstatus;
import no.fint.model.utdanning.kodeverk.Skolear;
import no.fint.model.utdanning.kodeverk.Skoleeiertype;
import no.fint.model.utdanning.kodeverk.Termin;
import no.fint.model.utdanning.kodeverk.Tilrettelegging;

public class LinkMapper {

    public static Map<String, String> linkMapper(String contextPath) {
        return ImmutableMap.<String,String>builder()
            .put(Avbruddsarsak.class.getName(), contextPath + RestEndpoints.AVBRUDDSARSAK)
            .put(Eksamensform.class.getName(), contextPath + RestEndpoints.EKSAMENSFORM)
            .put(Elevkategori.class.getName(), contextPath + RestEndpoints.ELEVKATEGORI)
            .put(Fagmerknad.class.getName(), contextPath + RestEndpoints.FAGMERKNAD)
            .put(Fravarstype.class.getName(), contextPath + RestEndpoints.FRAVARSTYPE)
            .put(Karakterskala.class.getName(), contextPath + RestEndpoints.KARAKTERSKALA)
            .put(Karakterstatus.class.getName(), contextPath + RestEndpoints.KARAKTERSTATUS)
            .put(Skolear.class.getName(), contextPath + RestEndpoints.SKOLEAR)
            .put(Skoleeiertype.class.getName(), contextPath + RestEndpoints.SKOLEEIERTYPE)
            .put(Termin.class.getName(), contextPath + RestEndpoints.TERMIN)
            .put(Tilrettelegging.class.getName(), contextPath + RestEndpoints.TILRETTELEGGING)
            .put("no.fint.model.utdanning.vurdering.Karakterverdi", "/utdanning/vurdering/karakterverdi")
            /* .put(TODO,TODO) */
            .build();
    }

}
