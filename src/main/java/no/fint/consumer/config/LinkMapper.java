package no.fint.consumer.config;

import no.fint.consumer.utils.RestEndpoints;
import java.util.Map;
import com.google.common.collect.ImmutableMap;
import no.novari.fint.model.utdanning.kodeverk.Avbruddsarsak;
import no.novari.fint.model.utdanning.kodeverk.Betalingsstatus;
import no.novari.fint.model.utdanning.kodeverk.Bevistype;
import no.novari.fint.model.utdanning.kodeverk.Brevtype;
import no.novari.fint.model.utdanning.kodeverk.Eksamensform;
import no.novari.fint.model.utdanning.kodeverk.Elevkategori;
import no.novari.fint.model.utdanning.kodeverk.Fagmerknad;
import no.novari.fint.model.utdanning.kodeverk.Fagstatus;
import no.novari.fint.model.utdanning.kodeverk.Fravarstype;
import no.novari.fint.model.utdanning.kodeverk.Fullfortkode;
import no.novari.fint.model.utdanning.kodeverk.Karakterskala;
import no.novari.fint.model.utdanning.kodeverk.Karakterstatus;
import no.novari.fint.model.utdanning.kodeverk.OtEnhet;
import no.novari.fint.model.utdanning.kodeverk.OtStatus;
import no.novari.fint.model.utdanning.kodeverk.Provestatus;
import no.novari.fint.model.utdanning.kodeverk.Skolear;
import no.novari.fint.model.utdanning.kodeverk.Skoleeiertype;
import no.novari.fint.model.utdanning.kodeverk.Termin;
import no.novari.fint.model.utdanning.kodeverk.Tilrettelegging;
import no.novari.fint.model.utdanning.kodeverk.Varseltype;
import no.novari.fint.model.utdanning.kodeverk.Vitnemalsmerknad;

public class LinkMapper {

    public static Map<String, String> linkMapper(String contextPath) {
        return ImmutableMap.<String,String>builder()
            .put(Avbruddsarsak.class.getName(), contextPath + RestEndpoints.AVBRUDDSARSAK)
            .put(Betalingsstatus.class.getName(), contextPath + RestEndpoints.BETALINGSSTATUS)
            .put(Bevistype.class.getName(), contextPath + RestEndpoints.BEVISTYPE)
            .put(Brevtype.class.getName(), contextPath + RestEndpoints.BREVTYPE)
            .put(Eksamensform.class.getName(), contextPath + RestEndpoints.EKSAMENSFORM)
            .put(Elevkategori.class.getName(), contextPath + RestEndpoints.ELEVKATEGORI)
            .put(Fagmerknad.class.getName(), contextPath + RestEndpoints.FAGMERKNAD)
            .put(Fagstatus.class.getName(), contextPath + RestEndpoints.FAGSTATUS)
            .put(Fravarstype.class.getName(), contextPath + RestEndpoints.FRAVARSTYPE)
            .put(Fullfortkode.class.getName(), contextPath + RestEndpoints.FULLFORTKODE)
            .put(Karakterskala.class.getName(), contextPath + RestEndpoints.KARAKTERSKALA)
            .put(Karakterstatus.class.getName(), contextPath + RestEndpoints.KARAKTERSTATUS)
            .put(OtEnhet.class.getName(), contextPath + RestEndpoints.OTENHET)
            .put(OtStatus.class.getName(), contextPath + RestEndpoints.OTSTATUS)
            .put(Provestatus.class.getName(), contextPath + RestEndpoints.PROVESTATUS)
            .put(Skolear.class.getName(), contextPath + RestEndpoints.SKOLEAR)
            .put(Skoleeiertype.class.getName(), contextPath + RestEndpoints.SKOLEEIERTYPE)
            .put(Termin.class.getName(), contextPath + RestEndpoints.TERMIN)
            .put(Tilrettelegging.class.getName(), contextPath + RestEndpoints.TILRETTELEGGING)
            .put(Varseltype.class.getName(), contextPath + RestEndpoints.VARSELTYPE)
            .put(Vitnemalsmerknad.class.getName(), contextPath + RestEndpoints.VITNEMALSMERKNAD)
            .put("no.novari.fint.model.utdanning.vurdering.Karakterverdi", "/model/utdanning/vurdering/karakterverdi")
            .put("no.novari.fint.model.felles.kodeverk.Kommune", "/model/felles/kodeverk/kommune")
            /* .put(TODO,TODO) */
            .build();
    }

}
