package no.fint.consumer.config;

import com.google.common.collect.ImmutableMap;
import no.fint.consumer.utils.RestEndpoints;
import no.fint.model.utdanning.kodeverk.Elevkategori;
import no.fint.model.utdanning.kodeverk.Fravarstype;
import no.fint.model.utdanning.kodeverk.Karakterskala;
import no.fint.model.utdanning.kodeverk.Skoleeiertype;
import no.fint.model.utdanning.vurdering.Karakterverdi;

import java.util.Map;

public class LinkMapper {

    public static Map<String, String> linkMapper(String contextPath) {
        return ImmutableMap.<String, String>builder()
                .put(Elevkategori.class.getName(), contextPath + RestEndpoints.ELEVKATEGORI)
                .put(Fravarstype.class.getName(), contextPath + RestEndpoints.FRAVARSTYPE)
                .put(Karakterskala.class.getName(), contextPath + RestEndpoints.KARAKTERSKALA)
                .put(Skoleeiertype.class.getName(), contextPath + RestEndpoints.SKOLEEIERTYPE)
				.put(Karakterverdi.class.getName(), "/utdanning/vurdering/karakterverdi")
                .build();
    }

}
