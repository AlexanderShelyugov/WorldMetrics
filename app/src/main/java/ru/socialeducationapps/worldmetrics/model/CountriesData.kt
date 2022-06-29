package ru.socialeducationapps.worldmetrics.model

import ru.socialeducationapps.worldmetrics.R
import ru.socialeducationapps.worldmetrics.global.ContextAccess

/** Provides relevant data about countries*/
class CountriesData private constructor() {
    companion object {
        /**
         * Gets localized country name by it's ISO code
         *
         * @param countryCode ISO code of a country
         */
        fun getNameByCode(countryCode: String): String {
            val strId = CODES_TO_NAMES[countryCode.lowercase()] ?: -1
            return if (strId < 0) "" else ContextAccess.context.getString(strId)
        }

        fun getAlpha3Code(alpha2: String): String =
            ALPHA_2_TO_ALPHA_3_CODES.getOrDefault(alpha2.lowercase(), "")

        fun getAlpha2Code(alpha3: String): String =
            ALPHA_3_TO_ALPHA_2_CODES.getOrDefault(alpha3.lowercase(), "")

        val ALPHA_2_TO_ALPHA_3_CODES: Map<String, String>
        val ALPHA_3_TO_ALPHA_2_CODES: Map<String, String>

        val CODES_TO_NAMES = mapOf(
            "afg" to R.string.country_name_afghanistan,
            "ago" to R.string.country_name_angola,
            "alb" to R.string.country_name_albania,
            "and" to R.string.country_name_andorra,
            "are" to R.string.country_name_united_arab_emirates,
            "arg" to R.string.country_name_argentina,
            "arm" to R.string.country_name_armenia,
            "aus" to R.string.country_name_australia,
            "aut" to R.string.country_name_austria,
            "aze" to R.string.country_name_azerbaijan,
            "bdi" to R.string.country_name_burundi,
            "bel" to R.string.country_name_belgium,
            "ben" to R.string.country_name_benin,
            "bfa" to R.string.country_name_burkina_faso,
            "bgd" to R.string.country_name_bangladesh,
            "bgr" to R.string.country_name_bulgaria,
            "bhr" to R.string.country_name_bahrain,
            "bhs" to R.string.country_name_bahamas,
            "bih" to R.string.country_name_bosnia_and_herzegovina,
            "blr" to R.string.country_name_belarus,
            "blz" to R.string.country_name_belize,
            "bol" to R.string.country_name_bolivia,
            "bra" to R.string.country_name_brazil,
            "brb" to R.string.country_name_barbados,
            "brn" to R.string.country_name_brunei,
            "btn" to R.string.country_name_bhutan,
            "bwa" to R.string.country_name_botswana,
            "caf" to R.string.country_name_central_african_republic,
            "can" to R.string.country_name_canada,
            "che" to R.string.country_name_switzerland,
            "chl" to R.string.country_name_chile,
            "chn" to R.string.country_name_china,
            "civ" to R.string.country_name_cote_d_ivoire,
            "cmr" to R.string.country_name_cameroon,
            "cod" to R.string.country_name_congo,
            "cog" to R.string.country_name_congo,
            "col" to R.string.country_name_colombia,
            "com" to R.string.country_name_comoros,
            "cpv" to R.string.country_name_cape_verde,
            "cri" to R.string.country_name_costa_rica,
            "ctu" to R.string.country_name_cyprus_north,
            "cub" to R.string.country_name_cuba,
            "cyp" to R.string.country_name_cyprus,
            "cze" to R.string.country_name_czech_republic,
            "deu" to R.string.country_name_germany,
            "dji" to R.string.country_name_djibouti,
            "dnk" to R.string.country_name_denmark,
            "dom" to R.string.country_name_dominican_republic,
            "dma" to R.string.country_name_dominica,
            "dza" to R.string.country_name_algeria,
            "ecu" to R.string.country_name_ecuador,
            "egy" to R.string.country_name_egypt,
            "eri" to R.string.country_name_eritrea,
            "esp" to R.string.country_name_spain,
            "est" to R.string.country_name_estonia,
            "eth" to R.string.country_name_ethiopia,
            "fin" to R.string.country_name_finland,
            "fji" to R.string.country_name_fiji,
            "fra" to R.string.country_name_france,
            "gab" to R.string.country_name_gabon,
            "gbr" to R.string.country_name_united_kingdom,
            "geo" to R.string.country_name_georgia,
            "gha" to R.string.country_name_ghana,
            "gin" to R.string.country_name_guinea,
            "gmb" to R.string.country_name_gambia,
            "gnb" to R.string.country_name_guinea_bissau,
            "gnq" to R.string.country_name_equatorial_guinea,
            "grc" to R.string.country_name_greece,
            "grd" to R.string.country_name_grenada,
            "gtm" to R.string.country_name_guatemala,
            "guy" to R.string.country_name_guyana,
            "hkg" to R.string.country_name_hong_kong,
            "hnd" to R.string.country_name_honduras,
            "hrv" to R.string.country_name_croatia,
            "hti" to R.string.country_name_haiti,
            "hun" to R.string.country_name_hungary,
            "idn" to R.string.country_name_indonesia,
            "ind" to R.string.country_name_india,
            "irl" to R.string.country_name_ireland,
            "irn" to R.string.country_name_iran,
            "irq" to R.string.country_name_iraq,
            "isl" to R.string.country_name_iceland,
            "isr" to R.string.country_name_israel,
            "ita" to R.string.country_name_italy,
            "jam" to R.string.country_name_jamaica,
            "jor" to R.string.country_name_jordan,
            "jpn" to R.string.country_name_japan,
            "kaz" to R.string.country_name_kazakhstan,
            "ken" to R.string.country_name_kenya,
            "kir" to R.string.country_name_kiribati,
            "kgz" to R.string.country_name_kyrgyz_republic,
            "khm" to R.string.country_name_cambodia,
            "kor" to R.string.country_name_south_korea,
            "kwt" to R.string.country_name_kuwait,
            "lao" to R.string.country_name_lao,
            "lbn" to R.string.country_name_lebanon,
            "lbr" to R.string.country_name_liberia,
            "lby" to R.string.country_name_libya,
            "lie" to R.string.country_name_liechtenstein,
            "lca" to R.string.country_name_saint_lucia,
            "lka" to R.string.country_name_sri_lanka,
            "lso" to R.string.country_name_lesotho,
            "ltu" to R.string.country_name_lithuania,
            "lux" to R.string.country_name_luxembourg,
            "lva" to R.string.country_name_latvia,
            "mac" to R.string.country_name_macao,
            "mar" to R.string.country_name_morocco,
            "mda" to R.string.country_name_moldova,
            "mdg" to R.string.country_name_madagascar,
            "mdv" to R.string.country_name_maldives,
            "mex" to R.string.country_name_mexico,
            "mkd" to R.string.country_name_macedonia,
            "mli" to R.string.country_name_mali,
            "mlt" to R.string.country_name_malta,
            "mmr" to R.string.country_name_myanmar,
            "mne" to R.string.country_name_montenegro,
            "mng" to R.string.country_name_mongolia,
            "moz" to R.string.country_name_mozambique,
            "mrt" to R.string.country_name_mauritania,
            "mus" to R.string.country_name_mauritius,
            "mwi" to R.string.country_name_malawi,
            "mys" to R.string.country_name_malaysia,
            "nam" to R.string.country_name_namibia,
            "ner" to R.string.country_name_niger,
            "nga" to R.string.country_name_nigeria,
            "nic" to R.string.country_name_nicaragua,
            "nld" to R.string.country_name_netherlands,
            "nor" to R.string.country_name_norway,
            "npl" to R.string.country_name_nepal,
            "nzl" to R.string.country_name_new_zealand,
            "omn" to R.string.country_name_oman,
            "pak" to R.string.country_name_pakistan,
            "pan" to R.string.country_name_panama,
            "per" to R.string.country_name_peru,
            "phl" to R.string.country_name_philippines,
            "png" to R.string.country_name_papua_new_guinea,
            "pol" to R.string.country_name_poland,
            "pri" to R.string.country_name_puerto_rico,
            "prk" to R.string.country_name_north_korea,
            "prt" to R.string.country_name_portugal,
            "pry" to R.string.country_name_paraguay,
            "pse" to R.string.country_name_palestine,
            "qat" to R.string.country_name_qatar,
            "rou" to R.string.country_name_romania,
            "rus" to R.string.country_name_russia,
            "rwa" to R.string.country_name_rwanda,
            "sau" to R.string.country_name_saudi_arabia,
            "sdn" to R.string.country_name_sudan,
            "sen" to R.string.country_name_senegal,
            "sgp" to R.string.country_name_singapore,
            "slb" to R.string.country_name_solomon_islands,
            "sle" to R.string.country_name_sierra_leone,
            "slv" to R.string.country_name_el_salvador,
            "som" to R.string.country_name_somalia,
            "srb" to R.string.country_name_serbia,
            "ssd" to R.string.country_name_south_sudan,
            "stp" to R.string.country_name_sao_tome_and_principe,
            "sur" to R.string.country_name_suriname,
            "svk" to R.string.country_name_slovak_republic,
            "svn" to R.string.country_name_slovenia,
            "swe" to R.string.country_name_sweden,
            "swz" to R.string.country_name_swaziland,
            "syc" to R.string.country_name_seychelles,
            "syr" to R.string.country_name_syria,
            "tcd" to R.string.country_name_chad,
            "tgo" to R.string.country_name_togo,
            "tha" to R.string.country_name_thailand,
            "tjk" to R.string.country_name_tajikistan,
            "tkm" to R.string.country_name_turkmenistan,
            "tls" to R.string.country_name_timor_leste,
            "ton" to R.string.country_name_tonga,
            "tto" to R.string.country_name_trinidad_and_tobago,
            "tun" to R.string.country_name_tunisia,
            "tur" to R.string.country_name_turkey,
            "twn" to R.string.country_name_taiwan,
            "tza" to R.string.country_name_tanzania,
            "uga" to R.string.country_name_uganda,
            "ukr" to R.string.country_name_ukraine,
            "ury" to R.string.country_name_uruguay,
            "usa" to R.string.country_name_united_states,
            "uzb" to R.string.country_name_uzbekistan,
            "vct" to R.string.country_name_saint_vincent_and_the_grenadines,
            "ven" to R.string.country_name_venezuela,
            "vnm" to R.string.country_name_vietnam,
            "vut" to R.string.country_name_vanuatu,
            "wsm" to R.string.country_name_samoa,
            "xcd" to R.string.country_name_oecs,
            "xko" to R.string.country_name_kosovo,
            "yem" to R.string.country_name_yemen,
            "zaf" to R.string.country_name_south_africa,
            "zmb" to R.string.country_name_zambia,
            "zwe" to R.string.country_name_zimbabwe,
        )


        private fun initCodeMaps(): Pair<Map<String, String>, Map<String, String>> {
            val a2to3 = mutableMapOf<String, String>()
            val a3to2 = mutableMapOf<String, String>()
            val addCodes: (String, String) -> Unit = { alpha2, alpha3 ->
                a2to3[alpha2.lowercase()] = alpha3.lowercase()
                a3to2[alpha3.lowercase()] = alpha2.lowercase()
            }
            addCodes("AF", "AFG")
            addCodes("AX", "ALA")
            addCodes("AL", "ALB")
            addCodes("DZ", "DZA")
            addCodes("AS", "ASM")
            addCodes("AD", "AND")
            addCodes("AO", "AGO")
            addCodes("AI", "AIA")
            addCodes("AQ", "ATA")
            addCodes("AG", "ATG")
            addCodes("AR", "ARG")
            addCodes("AM", "ARM")
            addCodes("AW", "ABW")
            addCodes("AU", "AUS")
            addCodes("AT", "AUT")
            addCodes("AZ", "AZE")
            addCodes("BS", "BHS")
            addCodes("BH", "BHR")
            addCodes("BD", "BGD")
            addCodes("BB", "BRB")
            addCodes("BY", "BLR")
            addCodes("BE", "BEL")
            addCodes("BZ", "BLZ")
            addCodes("BJ", "BEN")
            addCodes("BM", "BMU")
            addCodes("BT", "BTN")
            addCodes("BO", "BOL")
            addCodes("BA", "BIH")
            addCodes("BW", "BWA")
            addCodes("BV", "BVT")
            addCodes("BR", "BRA")
            addCodes("VG", "VGB")
            addCodes("IO", "IOT")
            addCodes("BN", "BRN")
            addCodes("BG", "BGR")
            addCodes("BF", "BFA")
            addCodes("BI", "BDI")
            addCodes("KH", "KHM")
            addCodes("CM", "CMR")
            addCodes("CA", "CAN")
            addCodes("CV", "CPV")
            addCodes("KY", "CYM")
            addCodes("CF", "CAF")
            addCodes("TD", "TCD")
            addCodes("CL", "CHL")
            addCodes("CN", "CHN")
            addCodes("HK", "HKG")
            addCodes("MO", "MAC")
            addCodes("CX", "CXR")
            addCodes("CC", "CCK")
            addCodes("CO", "COL")
            addCodes("KM", "COM")
            addCodes("CG", "COG")
            addCodes("CD", "COD")
            addCodes("CK", "COK")
            addCodes("CR", "CRI")
            addCodes("CI", "CIV")
            addCodes("HR", "HRV")
            addCodes("CU", "CUB")
            addCodes("CY", "CYP")
            addCodes("CZ", "CZE")
            addCodes("DK", "DNK")
            addCodes("DJ", "DJI")
            addCodes("DM", "DMA")
            addCodes("DO", "DOM")
            addCodes("EC", "ECU")
            addCodes("EG", "EGY")
            addCodes("SV", "SLV")
            addCodes("GQ", "GNQ")
            addCodes("ER", "ERI")
            addCodes("EE", "EST")
            addCodes("ET", "ETH")
            addCodes("FK", "FLK")
            addCodes("FO", "FRO")
            addCodes("FJ", "FJI")
            addCodes("FI", "FIN")
            addCodes("FR", "FRA")
            addCodes("GF", "GUF")
            addCodes("PF", "PYF")
            addCodes("TF", "ATF")
            addCodes("GA", "GAB")
            addCodes("GM", "GMB")
            addCodes("GE", "GEO")
            addCodes("DE", "DEU")
            addCodes("GH", "GHA")
            addCodes("GI", "GIB")
            addCodes("GR", "GRC")
            addCodes("GL", "GRL")
            addCodes("GD", "GRD")
            addCodes("GP", "GLP")
            addCodes("GU", "GUM")
            addCodes("GT", "GTM")
            addCodes("GG", "GGY")
            addCodes("GN", "GIN")
            addCodes("GW", "GNB")
            addCodes("GY", "GUY")
            addCodes("HT", "HTI")
            addCodes("HM", "HMD")
            addCodes("VA", "VAT")
            addCodes("HN", "HND")
            addCodes("HU", "HUN")
            addCodes("IS", "ISL")
            addCodes("IN", "IND")
            addCodes("ID", "IDN")
            addCodes("IR", "IRN")
            addCodes("IQ", "IRQ")
            addCodes("IE", "IRL")
            addCodes("IM", "IMN")
            addCodes("IL", "ISR")
            addCodes("IT", "ITA")
            addCodes("JM", "JAM")
            addCodes("JP", "JPN")
            addCodes("JE", "JEY")
            addCodes("JO", "JOR")
            addCodes("KZ", "KAZ")
            addCodes("KE", "KEN")
            addCodes("KI", "KIR")
            addCodes("KP", "PRK")
            addCodes("KR", "KOR")
            addCodes("KW", "KWT")
            addCodes("KG", "KGZ")
            addCodes("LA", "LAO")
            addCodes("LV", "LVA")
            addCodes("LB", "LBN")
            addCodes("LS", "LSO")
            addCodes("LR", "LBR")
            addCodes("LY", "LBY")
            addCodes("LI", "LIE")
            addCodes("LT", "LTU")
            addCodes("LU", "LUX")
            addCodes("MK", "MKD")
            addCodes("MG", "MDG")
            addCodes("MW", "MWI")
            addCodes("MY", "MYS")
            addCodes("MV", "MDV")
            addCodes("ML", "MLI")
            addCodes("MT", "MLT")
            addCodes("MH", "MHL")
            addCodes("MQ", "MTQ")
            addCodes("MR", "MRT")
            addCodes("MU", "MUS")
            addCodes("YT", "MYT")
            addCodes("MX", "MEX")
            addCodes("FM", "FSM")
            addCodes("MD", "MDA")
            addCodes("MC", "MCO")
            addCodes("MN", "MNG")
            addCodes("ME", "MNE")
            addCodes("MS", "MSR")
            addCodes("MA", "MAR")
            addCodes("MZ", "MOZ")
            addCodes("MM", "MMR")
            addCodes("NA", "NAM")
            addCodes("NR", "NRU")
            addCodes("NP", "NPL")
            addCodes("NL", "NLD")
            addCodes("AN", "ANT")
            addCodes("NC", "NCL")
            addCodes("NZ", "NZL")
            addCodes("NI", "NIC")
            addCodes("NE", "NER")
            addCodes("NG", "NGA")
            addCodes("NU", "NIU")
            addCodes("NF", "NFK")
            addCodes("MP", "MNP")
            addCodes("NO", "NOR")
            addCodes("OM", "OMN")
            addCodes("PK", "PAK")
            addCodes("PW", "PLW")
            addCodes("PS", "PSE")
            addCodes("PA", "PAN")
            addCodes("PG", "PNG")
            addCodes("PY", "PRY")
            addCodes("PE", "PER")
            addCodes("PH", "PHL")
            addCodes("PN", "PCN")
            addCodes("PL", "POL")
            addCodes("PT", "PRT")
            addCodes("PR", "PRI")
            addCodes("QA", "QAT")
            addCodes("RE", "REU")
            addCodes("RO", "ROU")
            addCodes("RU", "RUS")
            addCodes("RW", "RWA")
            addCodes("BL", "BLM")
            addCodes("SH", "SHN")
            addCodes("KN", "KNA")
            addCodes("LC", "LCA")
            addCodes("MF", "MAF")
            addCodes("PM", "SPM")
            addCodes("VC", "VCT")
            addCodes("WS", "WSM")
            addCodes("SM", "SMR")
            addCodes("ST", "STP")
            addCodes("SA", "SAU")
            addCodes("SN", "SEN")
            addCodes("RS", "SRB")
            addCodes("SC", "SYC")
            addCodes("SL", "SLE")
            addCodes("SG", "SGP")
            addCodes("SK", "SVK")
            addCodes("SI", "SVN")
            addCodes("SB", "SLB")
            addCodes("SO", "SOM")
            addCodes("ZA", "ZAF")
            addCodes("GS", "SGS")
            addCodes("SS", "SSD")
            addCodes("ES", "ESP")
            addCodes("LK", "LKA")
            addCodes("SD", "SDN")
            addCodes("SR", "SUR")
            addCodes("SJ", "SJM")
            addCodes("SZ", "SWZ")
            addCodes("SE", "SWE")
            addCodes("CH", "CHE")
            addCodes("SY", "SYR")
            addCodes("TW", "TWN")
            addCodes("TJ", "TJK")
            addCodes("TZ", "TZA")
            addCodes("TH", "THA")
            addCodes("TL", "TLS")
            addCodes("TG", "TGO")
            addCodes("TK", "TKL")
            addCodes("TO", "TON")
            addCodes("TT", "TTO")
            addCodes("TN", "TUN")
            addCodes("TR", "TUR")
            addCodes("TM", "TKM")
            addCodes("TC", "TCA")
            addCodes("TV", "TUV")
            addCodes("UG", "UGA")
            addCodes("UA", "UKR")
            addCodes("AE", "ARE")
            addCodes("GB", "GBR")
            addCodes("US", "USA")
            addCodes("UM", "UMI")
            addCodes("UY", "URY")
            addCodes("UZ", "UZB")
            addCodes("VU", "VUT")
            addCodes("VE", "VEN")
            addCodes("VN", "VNM")
            addCodes("VI", "VIR")
            addCodes("WF", "WLF")
            addCodes("EH", "ESH")
            addCodes("YE", "YEM")
            addCodes("ZM", "ZMB")
            addCodes("ZW", "ZWE")
            return a2to3 to a3to2
        }

        init {
            val codeMaps = initCodeMaps()
            ALPHA_2_TO_ALPHA_3_CODES = codeMaps.first
            ALPHA_3_TO_ALPHA_2_CODES = codeMaps.second
        }
    }
}