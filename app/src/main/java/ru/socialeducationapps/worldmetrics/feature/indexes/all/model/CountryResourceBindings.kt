package ru.socialeducationapps.worldmetrics.feature.indexes.all.model

import ru.socialeducationapps.worldmetrics.R

/** Provides relevant data about countries*/
class CountryResourceBindings private constructor() {
    companion object {
        /**
         * Gets localized country name by it's ISO code
         *
         * @param countryCode ISO code of a country
         */
        fun getNameIdByCode(countryCode: String): Int? =
            CODES_TO_NAMES[countryCode.lowercase()]

        fun getAlpha3Code(alpha2: String): String =
            ALPHA_2_TO_ALPHA_3_CODES.getOrDefault(alpha2.lowercase(), "")

        fun getAlpha2Code(alpha3: String): String =
            ALPHA_3_TO_ALPHA_2_CODES.getOrDefault(alpha3.lowercase(), "")

        fun getAllCountryCodes(): Collection<String> = CODES_TO_NAMES.keys

        private val ALPHA_2_TO_ALPHA_3_CODES: Map<String, String>
        private val ALPHA_3_TO_ALPHA_2_CODES: Map<String, String>

        private val CODES_TO_NAMES = mapOf(
            "abw" to R.string.country_name_aruba,
            "afg" to R.string.country_name_afghanistan,
            "ago" to R.string.country_name_angola,
            "aia" to R.string.country_name_anguilla,
            "alb" to R.string.country_name_albania,
            "and" to R.string.country_name_andorra,
            "ant" to R.string.country_name_netherlands_antilles,
            "are" to R.string.country_name_united_arab_emirates,
            "arg" to R.string.country_name_argentina,
            "arm" to R.string.country_name_armenia,
            "asm" to R.string.country_name_american_samoa,
            "ata" to R.string.country_name_antarctica,
            "atf" to R.string.country_name_french_southern_territories,
            "atg" to R.string.country_name_antigua_and_barbuda,
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
            "blm" to R.string.country_name_saint_barthelemy,
            "blr" to R.string.country_name_belarus,
            "blz" to R.string.country_name_belize,
            "bmu" to R.string.country_name_bermuda,
            "bol" to R.string.country_name_bolivia,
            "bra" to R.string.country_name_brazil,
            "brb" to R.string.country_name_barbados,
            "brn" to R.string.country_name_brunei,
            "btn" to R.string.country_name_bhutan,
            "bvt" to R.string.country_name_bouvet_island,
            "bwa" to R.string.country_name_botswana,
            "caf" to R.string.country_name_central_african_republic,
            "can" to R.string.country_name_canada,
            "cck" to R.string.country_name_cocos_keeling_islands,
            "che" to R.string.country_name_switzerland,
            "chl" to R.string.country_name_chile,
            "chn" to R.string.country_name_china,
            "civ" to R.string.country_name_cote_d_ivoire,
            "cmr" to R.string.country_name_cameroon,
            "cod" to R.string.country_name_congo_the_democratic_republic_of_the,
            "cog" to R.string.country_name_congo,
            "cok" to R.string.country_name_cook_islands,
            "col" to R.string.country_name_colombia,
            "com" to R.string.country_name_comoros,
            "cpv" to R.string.country_name_cape_verde,
            "cri" to R.string.country_name_costa_rica,
            "cub" to R.string.country_name_cuba,
            "cxr" to R.string.country_name_christmas_island,
            "cym" to R.string.country_name_cayman_islands,
            "cyp" to R.string.country_name_cyprus,
            "cze" to R.string.country_name_czech_republic,
            "deu" to R.string.country_name_germany,
            "dji" to R.string.country_name_djibouti,
            "dma" to R.string.country_name_dominica,
            "dnk" to R.string.country_name_denmark,
            "dom" to R.string.country_name_dominican_republic,
            "dza" to R.string.country_name_algeria,
            "ecu" to R.string.country_name_ecuador,
            "egy" to R.string.country_name_egypt,
            "eri" to R.string.country_name_eritrea,
            "esh" to R.string.country_name_western_sahara,
            "esp" to R.string.country_name_spain,
            "est" to R.string.country_name_estonia,
            "eth" to R.string.country_name_ethiopia,
            "fin" to R.string.country_name_finland,
            "fji" to R.string.country_name_fiji,
            "flk" to R.string.country_name_falkland_islands_malvinas,
            "fra" to R.string.country_name_france,
            "fro" to R.string.country_name_faroe_islands,
            "fsm" to R.string.country_name_micronesia,
            "gab" to R.string.country_name_gabon,
            "gbr" to R.string.country_name_united_kingdom,
            "geo" to R.string.country_name_georgia,
            "ggy" to R.string.country_name_guernsey,
            "gha" to R.string.country_name_ghana,
            "gib" to R.string.country_name_gibraltar,
            "gin" to R.string.country_name_guinea,
            "glp" to R.string.country_name_guadeloupe,
            "gmb" to R.string.country_name_gambia,
            "gnb" to R.string.country_name_guinea_bissau,
            "gnq" to R.string.country_name_equatorial_guinea,
            "grc" to R.string.country_name_greece,
            "grd" to R.string.country_name_grenada,
            "grl" to R.string.country_name_greenland,
            "gtm" to R.string.country_name_guatemala,
            "guf" to R.string.country_name_french_guiana,
            "gum" to R.string.country_name_guam,
            "guy" to R.string.country_name_guyana,
            "hkg" to R.string.country_name_hong_kong,
            "hmd" to R.string.country_name_heard_island_and_mcdonald_islands,
            "hnd" to R.string.country_name_honduras,
            "hrv" to R.string.country_name_croatia,
            "hti" to R.string.country_name_haiti,
            "hun" to R.string.country_name_hungary,
            "idn" to R.string.country_name_indonesia,
            "imn" to R.string.country_name_isle_of_man,
            "ind" to R.string.country_name_india,
            "iot" to R.string.country_name_british_indian_ocean_territory,
            "irl" to R.string.country_name_ireland,
            "irn" to R.string.country_name_iran,
            "irq" to R.string.country_name_iraq,
            "isl" to R.string.country_name_iceland,
            "isr" to R.string.country_name_israel,
            "ita" to R.string.country_name_italy,
            "jam" to R.string.country_name_jamaica,
            "jey" to R.string.country_name_jersey,
            "jor" to R.string.country_name_jordan,
            "jpn" to R.string.country_name_japan,
            "kaz" to R.string.country_name_kazakhstan,
            "ken" to R.string.country_name_kenya,
            "kgz" to R.string.country_name_kyrgyzstan,
            "khm" to R.string.country_name_cambodia,
            "kir" to R.string.country_name_kiribati,
            "kna" to R.string.country_name_saint_kitts_and_nevis,
            "kor" to R.string.country_name_south_korea,
            "kwt" to R.string.country_name_kuwait,
            "lao" to R.string.country_name_lao,
            "lbn" to R.string.country_name_lebanon,
            "lbr" to R.string.country_name_liberia,
            "lby" to R.string.country_name_libya,
            "lca" to R.string.country_name_saint_lucia,
            "lie" to R.string.country_name_liechtenstein,
            "lka" to R.string.country_name_sri_lanka,
            "lso" to R.string.country_name_lesotho,
            "ltu" to R.string.country_name_lithuania,
            "lux" to R.string.country_name_luxembourg,
            "lva" to R.string.country_name_latvia,
            "mac" to R.string.country_name_macao,
            "maf" to R.string.country_name_saint_martin_french_part,
            "mar" to R.string.country_name_morocco,
            "mco" to R.string.country_name_monaco,
            "mda" to R.string.country_name_moldova,
            "mdg" to R.string.country_name_madagascar,
            "mdv" to R.string.country_name_maldives,
            "mex" to R.string.country_name_mexico,
            "mhl" to R.string.country_name_marshall_islands,
            "mkd" to R.string.country_name_macedonia,
            "mli" to R.string.country_name_mali,
            "mlt" to R.string.country_name_malta,
            "mmr" to R.string.country_name_myanmar,
            "mne" to R.string.country_name_montenegro,
            "mng" to R.string.country_name_mongolia,
            "mnp" to R.string.country_name_northern_mariana_islands,
            "moz" to R.string.country_name_mozambique,
            "mrt" to R.string.country_name_mauritania,
            "msr" to R.string.country_name_montserrat,
            "mtq" to R.string.country_name_martinique,
            "mus" to R.string.country_name_mauritius,
            "mwi" to R.string.country_name_malawi,
            "mys" to R.string.country_name_malaysia,
            "myt" to R.string.country_name_mayotte,
            "nam" to R.string.country_name_namibia,
            "ncl" to R.string.country_name_new_caledonia,
            "ner" to R.string.country_name_niger,
            "nfk" to R.string.country_name_norfolk_island,
            "nga" to R.string.country_name_nigeria,
            "nic" to R.string.country_name_nicaragua,
            "niu" to R.string.country_name_niue,
            "nld" to R.string.country_name_netherlands,
            "nor" to R.string.country_name_norway,
            "npl" to R.string.country_name_nepal,
            "nru" to R.string.country_name_nauru,
            "nzl" to R.string.country_name_new_zealand,
            "omn" to R.string.country_name_oman,
            "pak" to R.string.country_name_pakistan,
            "pan" to R.string.country_name_panama,
            "pcn" to R.string.country_name_pitcairn,
            "per" to R.string.country_name_peru,
            "phl" to R.string.country_name_philippines,
            "plw" to R.string.country_name_palau,
            "png" to R.string.country_name_papua_new_guinea,
            "pol" to R.string.country_name_poland,
            "pri" to R.string.country_name_puerto_rico,
            "prk" to R.string.country_name_north_korea,
            "prt" to R.string.country_name_portugal,
            "pry" to R.string.country_name_paraguay,
            "pse" to R.string.country_name_palestine,
            "pyf" to R.string.country_name_french_polynesia,
            "qat" to R.string.country_name_qatar,
            "reu" to R.string.country_name_reunion,
            "rou" to R.string.country_name_romania,
            "rus" to R.string.country_name_russia,
            "rwa" to R.string.country_name_rwanda,
            "sau" to R.string.country_name_saudi_arabia,
            "sdn" to R.string.country_name_sudan,
            "sen" to R.string.country_name_senegal,
            "sgp" to R.string.country_name_singapore,
            "sgs" to R.string.country_name_south_georgia_and_the_south_sandwich_islands,
            "shn" to R.string.country_name_saint_helena_ascension_and_tristan_da_cunha,
            "sjm" to R.string.country_name_svalbard_and_jan_mayen,
            "slb" to R.string.country_name_solomon_islands,
            "sle" to R.string.country_name_sierra_leone,
            "slv" to R.string.country_name_el_salvador,
            "smr" to R.string.country_name_san_marino,
            "som" to R.string.country_name_somalia,
            "spm" to R.string.country_name_saint_pierre_and_miquelon,
            "srb" to R.string.country_name_serbia,
            "ssd" to R.string.country_name_south_sudan,
            "stp" to R.string.country_name_sao_tome_and_principe,
            "sur" to R.string.country_name_suriname,
            "svk" to R.string.country_name_slovakia,
            "svn" to R.string.country_name_slovenia,
            "swe" to R.string.country_name_sweden,
            "swz" to R.string.country_name_eswatini,
            "syc" to R.string.country_name_seychelles,
            "syr" to R.string.country_name_syrian_arab_republic,
            "tca" to R.string.country_name_turks_and_caicos_islands,
            "tcd" to R.string.country_name_chad,
            "tgo" to R.string.country_name_togo,
            "tha" to R.string.country_name_thailand,
            "tjk" to R.string.country_name_tajikistan,
            "tkl" to R.string.country_name_tokelau,
            "tkm" to R.string.country_name_turkmenistan,
            "tls" to R.string.country_name_timor_leste,
            "ton" to R.string.country_name_tonga,
            "tto" to R.string.country_name_trinidad_and_tobago,
            "tun" to R.string.country_name_tunisia,
            "tur" to R.string.country_name_turkey,
            "tuv" to R.string.country_name_tuvalu,
            "twn" to R.string.country_name_taiwan,
            "tza" to R.string.country_name_tanzania,
            "uga" to R.string.country_name_uganda,
            "ukr" to R.string.country_name_ukraine,
            "umi" to R.string.country_name_united_states_minor_outlying_islands,
            "ury" to R.string.country_name_uruguay,
            "usa" to R.string.country_name_united_states,
            "uzb" to R.string.country_name_uzbekistan,
            "vat" to R.string.country_name_holy_see_vatican_city_state,
            "vct" to R.string.country_name_saint_vincent_and_the_grenadines,
            "ven" to R.string.country_name_venezuela,
            "vgb" to R.string.country_name_virgin_islands_british,
            "vir" to R.string.country_name_virgin_islands_us,
            "vnm" to R.string.country_name_viet_nam,
            "vut" to R.string.country_name_vanuatu,
            "wlf" to R.string.country_name_wallis_and_futuna,
            "wsm" to R.string.country_name_samoa,
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
            addCodes("ad", "and")
            addCodes("ae", "are")
            addCodes("af", "afg")
            addCodes("ag", "atg")
            addCodes("ai", "aia")
            addCodes("al", "alb")
            addCodes("am", "arm")
            addCodes("an", "ant")
            addCodes("ao", "ago")
            addCodes("aq", "ata")
            addCodes("ar", "arg")
            addCodes("as", "asm")
            addCodes("at", "aut")
            addCodes("au", "aus")
            addCodes("aw", "abw")
            addCodes("az", "aze")
            addCodes("ba", "bih")
            addCodes("bb", "brb")
            addCodes("bd", "bgd")
            addCodes("be", "bel")
            addCodes("bf", "bfa")
            addCodes("bg", "bgr")
            addCodes("bh", "bhr")
            addCodes("bi", "bdi")
            addCodes("bj", "ben")
            addCodes("bl", "blm")
            addCodes("bm", "bmu")
            addCodes("bn", "brn")
            addCodes("bo", "bol")
            addCodes("br", "bra")
            addCodes("bs", "bhs")
            addCodes("bt", "btn")
            addCodes("bv", "bvt")
            addCodes("bw", "bwa")
            addCodes("by", "blr")
            addCodes("bz", "blz")
            addCodes("ca", "can")
            addCodes("cc", "cck")
            addCodes("cd", "cod")
            addCodes("cf", "caf")
            addCodes("cg", "cog")
            addCodes("ch", "che")
            addCodes("ci", "civ")
            addCodes("ck", "cok")
            addCodes("cl", "chl")
            addCodes("cm", "cmr")
            addCodes("cn", "chn")
            addCodes("co", "col")
            addCodes("cr", "cri")
            addCodes("cu", "cub")
            addCodes("cv", "cpv")
            addCodes("cx", "cxr")
            addCodes("cy", "cyp")
            addCodes("cz", "cze")
            addCodes("de", "deu")
            addCodes("dj", "dji")
            addCodes("dk", "dnk")
            addCodes("dm", "dma")
            addCodes("do", "dom")
            addCodes("dz", "dza")
            addCodes("ec", "ecu")
            addCodes("ee", "est")
            addCodes("eg", "egy")
            addCodes("eh", "esh")
            addCodes("er", "eri")
            addCodes("es", "esp")
            addCodes("et", "eth")
            addCodes("fi", "fin")
            addCodes("fj", "fji")
            addCodes("fk", "flk")
            addCodes("fm", "fsm")
            addCodes("fo", "fro")
            addCodes("fr", "fra")
            addCodes("ga", "gab")
            addCodes("gb", "gbr")
            addCodes("gd", "grd")
            addCodes("ge", "geo")
            addCodes("gf", "guf")
            addCodes("gg", "ggy")
            addCodes("gh", "gha")
            addCodes("gi", "gib")
            addCodes("gl", "grl")
            addCodes("gm", "gmb")
            addCodes("gn", "gin")
            addCodes("gp", "glp")
            addCodes("gq", "gnq")
            addCodes("gr", "grc")
            addCodes("gs", "sgs")
            addCodes("gt", "gtm")
            addCodes("gu", "gum")
            addCodes("gw", "gnb")
            addCodes("gy", "guy")
            addCodes("hk", "hkg")
            addCodes("hm", "hmd")
            addCodes("hn", "hnd")
            addCodes("hr", "hrv")
            addCodes("ht", "hti")
            addCodes("hu", "hun")
            addCodes("id", "idn")
            addCodes("ie", "irl")
            addCodes("il", "isr")
            addCodes("im", "imn")
            addCodes("in", "ind")
            addCodes("io", "iot")
            addCodes("iq", "irq")
            addCodes("ir", "irn")
            addCodes("is", "isl")
            addCodes("it", "ita")
            addCodes("je", "jey")
            addCodes("jm", "jam")
            addCodes("jo", "jor")
            addCodes("jp", "jpn")
            addCodes("ke", "ken")
            addCodes("kg", "kgz")
            addCodes("kh", "khm")
            addCodes("ki", "kir")
            addCodes("km", "com")
            addCodes("kn", "kna")
            addCodes("kp", "prk")
            addCodes("kr", "kor")
            addCodes("kw", "kwt")
            addCodes("ky", "cym")
            addCodes("kz", "kaz")
            addCodes("la", "lao")
            addCodes("lb", "lbn")
            addCodes("lc", "lca")
            addCodes("li", "lie")
            addCodes("lk", "lka")
            addCodes("lr", "lbr")
            addCodes("ls", "lso")
            addCodes("lt", "ltu")
            addCodes("lu", "lux")
            addCodes("lv", "lva")
            addCodes("ly", "lby")
            addCodes("ma", "mar")
            addCodes("mc", "mco")
            addCodes("md", "mda")
            addCodes("me", "mne")
            addCodes("mf", "maf")
            addCodes("mg", "mdg")
            addCodes("mh", "mhl")
            addCodes("mk", "mkd")
            addCodes("ml", "mli")
            addCodes("mm", "mmr")
            addCodes("mn", "mng")
            addCodes("mo", "mac")
            addCodes("mp", "mnp")
            addCodes("mq", "mtq")
            addCodes("mr", "mrt")
            addCodes("ms", "msr")
            addCodes("mt", "mlt")
            addCodes("mu", "mus")
            addCodes("mv", "mdv")
            addCodes("mw", "mwi")
            addCodes("mx", "mex")
            addCodes("my", "mys")
            addCodes("mz", "moz")
            addCodes("na", "nam")
            addCodes("nc", "ncl")
            addCodes("ne", "ner")
            addCodes("nf", "nfk")
            addCodes("ng", "nga")
            addCodes("ni", "nic")
            addCodes("nl", "nld")
            addCodes("no", "nor")
            addCodes("np", "npl")
            addCodes("nr", "nru")
            addCodes("nu", "niu")
            addCodes("nz", "nzl")
            addCodes("om", "omn")
            addCodes("pa", "pan")
            addCodes("pe", "per")
            addCodes("pf", "pyf")
            addCodes("pg", "png")
            addCodes("ph", "phl")
            addCodes("pk", "pak")
            addCodes("pl", "pol")
            addCodes("pm", "spm")
            addCodes("pn", "pcn")
            addCodes("pr", "pri")
            addCodes("ps", "pse")
            addCodes("pt", "prt")
            addCodes("pw", "plw")
            addCodes("py", "pry")
            addCodes("qa", "qat")
            addCodes("re", "reu")
            addCodes("ro", "rou")
            addCodes("rs", "srb")
            addCodes("ru", "rus")
            addCodes("rw", "rwa")
            addCodes("sa", "sau")
            addCodes("sb", "slb")
            addCodes("sc", "syc")
            addCodes("sd", "sdn")
            addCodes("se", "swe")
            addCodes("sg", "sgp")
            addCodes("sh", "shn")
            addCodes("si", "svn")
            addCodes("sj", "sjm")
            addCodes("sk", "svk")
            addCodes("sl", "sle")
            addCodes("sm", "smr")
            addCodes("sn", "sen")
            addCodes("so", "som")
            addCodes("sr", "sur")
            addCodes("ss", "ssd")
            addCodes("st", "stp")
            addCodes("sv", "slv")
            addCodes("sy", "syr")
            addCodes("sz", "swz")
            addCodes("tc", "tca")
            addCodes("td", "tcd")
            addCodes("tf", "atf")
            addCodes("tg", "tgo")
            addCodes("th", "tha")
            addCodes("tj", "tjk")
            addCodes("tk", "tkl")
            addCodes("tl", "tls")
            addCodes("tm", "tkm")
            addCodes("tn", "tun")
            addCodes("to", "ton")
            addCodes("tr", "tur")
            addCodes("tt", "tto")
            addCodes("tv", "tuv")
            addCodes("tw", "twn")
            addCodes("tz", "tza")
            addCodes("ua", "ukr")
            addCodes("ug", "uga")
            addCodes("um", "umi")
            addCodes("us", "usa")
            addCodes("uy", "ury")
            addCodes("uz", "uzb")
            addCodes("va", "vat")
            addCodes("vc", "vct")
            addCodes("ve", "ven")
            addCodes("vg", "vgb")
            addCodes("vi", "vir")
            addCodes("vn", "vnm")
            addCodes("vu", "vut")
            addCodes("wf", "wlf")
            addCodes("ws", "wsm")
            addCodes("xk", "xko")
            addCodes("ye", "yem")
            addCodes("yt", "myt")
            addCodes("za", "zaf")
            addCodes("zm", "zmb")
            addCodes("zw", "zwe")
            return a2to3 to a3to2
        }

        init {
            val codeMaps = initCodeMaps()
            ALPHA_2_TO_ALPHA_3_CODES = codeMaps.first
            ALPHA_3_TO_ALPHA_2_CODES = codeMaps.second
        }
    }
}