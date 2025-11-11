package com.bd.api.biblioteca_crud.domain.shared.enums;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public enum Idioma {
    // Principais idiomas mundiais
    ENGLISH("EN", "English"),
    PORTUGUESE("PT", "Portugues"),
    PORTUGUESE_BRAZILIAN("PT-BR", "Portugues Brasileiro"),
    SPANISH("ES", "Espanol"),
    FRENCH("FR", "Francais"),
    GERMAN("DE", "Deutsch"),
    ITALIAN("IT", "Italiano"),
    RUSSIAN("RU", "Russian"),
    CHINESE("ZH", "Chinese"),
    CHINESE_SIMPLIFIED("ZH-CN", "Chinese Simplified"),
    CHINESE_TRADITIONAL("ZH-TW", "Chinese Traditional"),
    JAPANESE("JA", "Japanese"),
    KOREAN("KO", "Korean"),
    ARABIC("AR", "Arabic"),
    HINDI("HI", "Hindi"),

    // Idiomas europeus
    DUTCH("NL", "Nederlands"),
    POLISH("PL", "Polski"),
    SWEDISH("SV", "Svenska"),
    NORWEGIAN("NO", "Norsk"),
    DANISH("DA", "Dansk"),
    FINNISH("FI", "Suomi"),
    GREEK("EL", "Greek"),
    TURKISH("TR", "Turkish"),
    CZECH("CS", "Czech"),
    HUNGARIAN("HU", "Magyar"),
    ROMANIAN("RO", "Romanian"),
    BULGARIAN("BG", "Bulgarian"),
    UKRAINIAN("UK", "Ukrainian"),
    CROATIAN("HR", "Croatian"),
    SERBIAN("SR", "Serbian"),
    SLOVAK("SK", "Slovak"),
    SLOVENIAN("SL", "Slovenian"),
    LITHUANIAN("LT", "Lithuanian"),
    LATVIAN("LV", "Latvian"),
    ESTONIAN("ET", "Estonian"),
    IRISH("GA", "Irish"),
    WELSH("CY", "Welsh"),
    ICELANDIC("IS", "Icelandic"),
    MALTESE("MT", "Maltese"),

    // Idiomas asiáticos
    THAI("TH", "Thai"),
    VIETNAMESE("VI", "Vietnamese"),
    INDONESIAN("ID", "Indonesian"),
    MALAY("MS", "Malay"),
    TAGALOG("TL", "Tagalog"),
    BENGALI("BN", "Bengali"),
    URDU("UR", "Urdu"),
    PUNJABI("PA", "Punjabi"),
    TAMIL("TA", "Tamil"),
    TELUGU("TE", "Telugu"),
    MARATHI("MR", "Marathi"),
    GUJARATI("GU", "Gujarati"),
    KANNADA("KN", "Kannada"),
    MALAYALAM("ML", "Malayalam"),
    SINHALESE("SI", "Sinhalese"),
    BURMESE("MY", "Burmese"),
    KHMER("KM", "Khmer"),
    LAO("LO", "Lao"),
    NEPALI("NE", "Nepali"),
    MONGOLIAN("MN", "Mongolian"),

    // Idiomas do Oriente Médio
    HEBREW("HE", "Hebrew"),
    PERSIAN("FA", "Persian"),
    KURDISH("KU", "Kurdish"),
    PASHTO("PS", "Pashto"),

    // Idiomas africanos
    SWAHILI("SW", "Swahili"),
    HAUSA("HA", "Hausa"),
    YORUBA("YO", "Yoruba"),
    IGBO("IG", "Igbo"),
    ZULU("ZU", "Zulu"),
    XHOSA("XH", "Xhosa"),
    AFRIKAANS("AF", "Afrikaans"),
    AMHARIC("AM", "Amharic"),
    SOMALI("SO", "Somali"),

    // Idiomas das Américas
    SPANISH_MEXICAN("ES-MX", "Espanol Mexicano"),
    SPANISH_ARGENTINIAN("ES-AR", "Espanol Argentino"),
    SPANISH_COLOMBIAN("ES-CO", "Espanol Colombiano"),
    ENGLISH_US("EN-US", "English US"),
    ENGLISH_UK("EN-GB", "English UK"),
    ENGLISH_AUSTRALIAN("EN-AU", "English Australian"),
    ENGLISH_CANADIAN("EN-CA", "English Canadian"),
    FRENCH_CANADIAN("FR-CA", "Francais Canadien"),
    QUECHUA("QU", "Quechua"),
    GUARANI("GN", "Guarani"),
    AYMARA("AY", "Aymara"),
    NAHUATL("NAH", "Nahuatl"),

    // Idiomas clássicos e históricos
    LATIN("LA", "Latin"),
    ANCIENT_GREEK("GRC", "Ancient Greek"),
    SANSKRIT("SA", "Sanskrit"),

    // Outros idiomas
    ESPERANTO("EO", "Esperanto"),
    BASQUE("EU", "Basque"),
    CATALAN("CA", "Catalan"),
    GALICIAN("GL", "Galician"),
    ALBANIAN("SQ", "Albanian"),
    MACEDONIAN("MK", "Macedonian"),
    BOSNIAN("BS", "Bosnian"),
    ARMENIAN("HY", "Armenian"),
    GEORGIAN("KA", "Georgian"),
    AZERBAIJANI("AZ", "Azerbaijani"),
    KAZAKH("KK", "Kazakh"),
    UZBEK("UZ", "Uzbek"),
    TAJIK("TG", "Tajik"),
    TURKMEN("TK", "Turkmen"),
    KYRGYZ("KY", "Kyrgyz"),
    BELARUSIAN("BE", "Belarusian"),
    MALAGASY("MG", "Malagasy"),
    MAORI("MI", "Maori"),
    HAWAIIAN("HAW", "Hawaiian"),
    SAMOAN("SM", "Samoan"),
    TONGAN("TO", "Tongan"),
    FIJIAN("FJ", "Fijian");

    private final String code;
    private final String name;

    Idioma(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    //Método auxiliar para encontrar enum pelo código
    public static Idioma fromCode(String code) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.code.equalsIgnoreCase(code)) {
                return idioma;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Idioma desconhecido: " + code);
    }

    //Método auxiliar para listar todos os códigos
//    public static String[] getAllCodes() {
//        Idioma[] idioma = Idioma.values();
//        String[] codes = new String[idioma.length];
//        for (int i = 0; i < idioma.length; i++) {
//            codes[i] = idioma[i].code;
//        }
//        return codes;
//    }

    @Override
    public String toString() {
        return name + " (" + code + ")";
    }

}
