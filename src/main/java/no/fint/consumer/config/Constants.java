package no.fint.consumer.config;

public enum Constants {
;

    public static final String COMPONENT = "utdanning-kodeverk";
    public static final String COMPONENT_CONSUMER = COMPONENT + " consumer";
    public static final String CACHE_SERVICE = "CACHE_SERVICE";

    
    public static final String CACHE_INITIALDELAY_AVBRUDDSARSAK = "${fint.consumer.cache.initialDelay.avbruddsarsak:900000}";
    public static final String CACHE_FIXEDRATE_AVBRUDDSARSAK = "${fint.consumer.cache.fixedRate.avbruddsarsak:900000}";
    
    public static final String CACHE_INITIALDELAY_EKSAMENSFORM = "${fint.consumer.cache.initialDelay.eksamensform:1000000}";
    public static final String CACHE_FIXEDRATE_EKSAMENSFORM = "${fint.consumer.cache.fixedRate.eksamensform:900000}";
    
    public static final String CACHE_INITIALDELAY_ELEVKATEGORI = "${fint.consumer.cache.initialDelay.elevkategori:1100000}";
    public static final String CACHE_FIXEDRATE_ELEVKATEGORI = "${fint.consumer.cache.fixedRate.elevkategori:900000}";
    
    public static final String CACHE_INITIALDELAY_FAGMERKNAD = "${fint.consumer.cache.initialDelay.fagmerknad:1200000}";
    public static final String CACHE_FIXEDRATE_FAGMERKNAD = "${fint.consumer.cache.fixedRate.fagmerknad:900000}";
    
    public static final String CACHE_INITIALDELAY_FAGSTATUS = "${fint.consumer.cache.initialDelay.fagstatus:1300000}";
    public static final String CACHE_FIXEDRATE_FAGSTATUS = "${fint.consumer.cache.fixedRate.fagstatus:900000}";
    
    public static final String CACHE_INITIALDELAY_FRAVARSTYPE = "${fint.consumer.cache.initialDelay.fravarstype:1400000}";
    public static final String CACHE_FIXEDRATE_FRAVARSTYPE = "${fint.consumer.cache.fixedRate.fravarstype:900000}";
    
    public static final String CACHE_INITIALDELAY_KARAKTERSKALA = "${fint.consumer.cache.initialDelay.karakterskala:1500000}";
    public static final String CACHE_FIXEDRATE_KARAKTERSKALA = "${fint.consumer.cache.fixedRate.karakterskala:900000}";
    
    public static final String CACHE_INITIALDELAY_KARAKTERSTATUS = "${fint.consumer.cache.initialDelay.karakterstatus:1600000}";
    public static final String CACHE_FIXEDRATE_KARAKTERSTATUS = "${fint.consumer.cache.fixedRate.karakterstatus:900000}";
    
    public static final String CACHE_INITIALDELAY_OTENHET = "${fint.consumer.cache.initialDelay.otenhet:1700000}";
    public static final String CACHE_FIXEDRATE_OTENHET = "${fint.consumer.cache.fixedRate.otenhet:900000}";
    
    public static final String CACHE_INITIALDELAY_OTSTATUS = "${fint.consumer.cache.initialDelay.otstatus:1800000}";
    public static final String CACHE_FIXEDRATE_OTSTATUS = "${fint.consumer.cache.fixedRate.otstatus:900000}";
    
    public static final String CACHE_INITIALDELAY_SKOLEAR = "${fint.consumer.cache.initialDelay.skolear:1900000}";
    public static final String CACHE_FIXEDRATE_SKOLEAR = "${fint.consumer.cache.fixedRate.skolear:900000}";
    
    public static final String CACHE_INITIALDELAY_SKOLEEIERTYPE = "${fint.consumer.cache.initialDelay.skoleeiertype:2000000}";
    public static final String CACHE_FIXEDRATE_SKOLEEIERTYPE = "${fint.consumer.cache.fixedRate.skoleeiertype:900000}";
    
    public static final String CACHE_INITIALDELAY_TERMIN = "${fint.consumer.cache.initialDelay.termin:2100000}";
    public static final String CACHE_FIXEDRATE_TERMIN = "${fint.consumer.cache.fixedRate.termin:900000}";
    
    public static final String CACHE_INITIALDELAY_TILRETTELEGGING = "${fint.consumer.cache.initialDelay.tilrettelegging:2200000}";
    public static final String CACHE_FIXEDRATE_TILRETTELEGGING = "${fint.consumer.cache.fixedRate.tilrettelegging:900000}";
    

}
