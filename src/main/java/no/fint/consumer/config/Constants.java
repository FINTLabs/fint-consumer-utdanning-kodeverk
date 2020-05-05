
package no.fint.consumer.config;

public enum Constants {
;

    public static final String COMPONENT = "utdanning-kodeverk";
    public static final String COMPONENT_CONSUMER = COMPONENT + " consumer";
    public static final String CACHE_SERVICE = "CACHE_SERVICE";

    
    public static final String CACHE_INITIALDELAY_ELEVKATEGORI = "${fint.consumer.cache.initialDelay.elevkategori:900000}";
    public static final String CACHE_FIXEDRATE_ELEVKATEGORI = "${fint.consumer.cache.fixedRate.elevkategori:900000}";
    
    public static final String CACHE_INITIALDELAY_FRAVARSTYPE = "${fint.consumer.cache.initialDelay.fravarstype:960000}";
    public static final String CACHE_FIXEDRATE_FRAVARSTYPE = "${fint.consumer.cache.fixedRate.fravarstype:900000}";
    
    public static final String CACHE_INITIALDELAY_KARAKTERSKALA = "${fint.consumer.cache.initialDelay.karakterskala:1020000}";
    public static final String CACHE_FIXEDRATE_KARAKTERSKALA = "${fint.consumer.cache.fixedRate.karakterskala:900000}";
    
    public static final String CACHE_INITIALDELAY_SKOLEAR = "${fint.consumer.cache.initialDelay.skolear:1080000}";
    public static final String CACHE_FIXEDRATE_SKOLEAR = "${fint.consumer.cache.fixedRate.skolear:900000}";
    
    public static final String CACHE_INITIALDELAY_SKOLEEIERTYPE = "${fint.consumer.cache.initialDelay.skoleeiertype:1140000}";
    public static final String CACHE_FIXEDRATE_SKOLEEIERTYPE = "${fint.consumer.cache.fixedRate.skoleeiertype:900000}";
    
    public static final String CACHE_INITIALDELAY_TERMIN = "${fint.consumer.cache.initialDelay.termin:1200000}";
    public static final String CACHE_FIXEDRATE_TERMIN = "${fint.consumer.cache.fixedRate.termin:900000}";
    

}
