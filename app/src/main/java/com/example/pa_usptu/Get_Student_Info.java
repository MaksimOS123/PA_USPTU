package com.example.pa_usptu;

import com.google.gson.Gson;

import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Get_Student_Info {

    public static Student_Info Get_Student_Info(String username, String password) {
        String typelogin = "1";
        String dataLogin = "LData={\"typelogin\":"+ typelogin +",\"username\":\""+ username +"\",\"password\":\""+ password +"\"}";
        String dataReload = "typelogin="+ typelogin +"&username="+ username + "&password=" + password;
        String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36";

        String urlgetvar = "https://ams.rusoil.net/pcs/getvar";
        String urlstud_info = "https://ams.rusoil.net/pcs/getfunc?obj=get_stud_info";
        String urlkont_dvig = "https://ams.rusoil.net/pcs/getfunc?obj=get_kont_dvig";
        String urllogin = "https://ams.rusoil.net/oau/login";
        String urlreload = "https://ams.rusoil.net/oau/reload";
        String urlget = "https://ams.rusoil.net/oau/Get_profile";

        Connection connection1 = HttpConnection.connect(urllogin)
                .ignoreHttpErrors(true)
                .userAgent(userAgent)
                .header("Host", "ams.rusoil.net")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept-Language", "ru,en;q=0.9,en-GB;q=0.8,en-US;q=0.7")
                .header("Connection", "keep-alive")
                .header("Referer", "https://ams.rusoil.net/oau/aut")
                .header("Origin", "https://ams.rusoil.net")
                .followRedirects(true)
                .requestBody(dataLogin)
                .maxBodySize(1_000_000*30)
                .timeout(0)
                .method(Connection.Method.POST);
        Connection.Response response1 = null;
        try {
            response1 = connection1.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String PHPSESSID = response1.cookies().toString().substring(11, response1.cookies().toString().length()-1);
        Map<String, String> cookiess = new HashMap<>();
        cookiess.put("PHPSESSID", PHPSESSID);
        cookiess.put("tekform", "w_first");

        Connection connection1289 = HttpConnection.connect("https://ams.rusoil.net/oau/authorize?response_type=code&client_id=pcs_cli_serv772&redirect_uri=https://ams.rusoil.net/pcs/route_oau_resp&state%3D="+ PHPSESSID +"&scope=email+profile")
                .userAgent(userAgent)
                .followRedirects(true)
                .cookies(response1.cookies())
                .ignoreHttpErrors(true);

        try {
            Connection.Response response1289 = connection1289.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Connection connection2 = connection1.url(urlreload)
                .cookies(response1.cookies())
                .userAgent(userAgent)
                .ignoreHttpErrors(true)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Accept-Language", "ru,en;q=0.9,en-GB;q=0.8,en-US;q=0.7")
                .header("Connection", "keep-alive")
                .header("Upgrade-Insecure-Requests", "1")
                .header("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
                .header("Cache-Control", "max-age=0")
                .header("Host", "ams.rusoil.net")
                .header("Origin", "https://ams.rusoil.net")
                .referrer("https://ams.rusoil.net/oau/authorize?response_type=code&client_id=pcs_cli_serv772&redirect_uri=https://ams.rusoil.net/pcs/route_oau_resp&state%3D="+ PHPSESSID +"&scope=email+profile")
                .header("Upgrade-Insecure-Requests", "1")
                .header("sec-ch-ua", "\"Chromium\";v=\"92\", \" Not A;Brand\";v=\"99\", \"Google Chrome\";v=\"92\"")
                .header("sec-ch-ua-mobile", "?0")
                .header("sec-ch-ua-platform","\"Windows\"")
                .header("Sec-Fetch-Dest","document")
                .header("Sec-Fetch-Mode","navigate")
                .header("Sec-Fetch-Site","same-origin")
                .header("Sec-Fetch-User","?1")
                .followRedirects(true)
                .requestBody(dataReload)
                .timeout(0)
                .method(Connection.Method.POST);


        Connection connection4 = connection2.url(urlgetvar)
                .cookies(cookiess)
                .followRedirects(true)
                .userAgent(userAgent)
                .method(Connection.Method.POST)
                .header("Accept-Encoding", "gzip, deflate, br")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .header("Accept-Language", "ru,en;q=0.9,en-GB;q=0.8,en-US;q=0.7")
                .header("Connection", "keep-alive")
                .header("Accept","*/*")
                .header("Host", "ams.rusoil.net")
                .header("Origin", "https://ams.rusoil.net")
                .referrer("https://ams.rusoil.net/pcs/s?w_first")
                .requestBody("LData={\"namevar\":\"KONT_ID\"}");

        Connection.Response response4 = null;
        try {
            response4 = connection4.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Document tt4 = null;
        try {
            tt4 = response4.parse();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String KONT_ID = tt4.body().text().substring(20, tt4.body().text().length()-2);


        Connection connection3 = connection2.url(urlstud_info)
                .cookies(cookiess)
                .followRedirects(true)
                .userAgent(userAgent)
                .method(Connection.Method.POST)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .ignoreContentType(true)
                .referrer("https://ams.rusoil.net/pcs/s?w_first")
                .requestBody("kont_id=0"+KONT_ID);

        Connection.Response response3 = null;
        try {
            response3 = connection3.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String tt3 = null;
        try {
            tt3 = response3.parse().body().text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            tt3 = UnicodeToText(tt3);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Gson g = new Gson();
        Data_stud_info Stud_Info = g.fromJson(tt3.substring(10, tt3.length()-2), Data_stud_info.class);

        Connection connection5 = connection2.url(urlkont_dvig)
                .cookies(cookiess)
                .followRedirects(true)
                .userAgent(userAgent)
                .method(Connection.Method.POST)
                .header("Content-Type", "application/x-www-form-urlencoded")
                .ignoreContentType(true)
                .referrer("https://ams.rusoil.net/pcs/s?w_first")
                .requestBody("kont_id=0"+KONT_ID);

        Connection.Response response5 = null;
        try {
            response5 = connection5.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String tt5 = null;
        try {
            tt5 = response5.parse().body().text();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            tt5 = UnicodeToText(tt5);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Data_stud_info_study Stud_Info_Study = g.fromJson(tt5.substring(10, tt5.length()-2), Data_stud_info_study.class);

        Student_Info student = new Student_Info();

        student.set$css(Stud_Info_Study.$css);
        student.setFamily(Stud_Info.family);
        student.setName(Stud_Info.name);
        student.setFather(Stud_Info.father);
        student.setGruppa(Stud_Info.gruppa);
        student.setNomzachet(Stud_Info.nomzachet);
        student.setWorknomer(Stud_Info.worknomer);
        student.setEmail(Stud_Info.email);
        student.setMobilephone("+"+Stud_Info.mobilephone);
        student.setCheckpasp(Stud_Info.checkpasp);
        student.setCheckdroz(Stud_Info.checkdroz);
        student.setCheckaddr ( Stud_Info.checkaddr);
        student.setKaprik ( Stud_Info.kaprik);
        student.setDid ( Stud_Info.did);
        student.setKont_id ( Stud_Info.kont_id);
        student.setOtherdoc_addrregistr2 ( Stud_Info.otherdoc_addrregistr2);
        student.setDatak ( Stud_Info_Study.datak);
        student.setDatan ( Stud_Info_Study.datan);
        student.setDataprikaz ( Stud_Info_Study.dataprikaz);
        student.setDatazap ( Stud_Info_Study.datazap);
        student.setGoslinia ( Stud_Info_Study.goslinia);
        student.setId ( Stud_Info_Study.id);
        student.setKod_okco ( Stud_Info_Study.kod_okco);
        student.setKurs ( Stud_Info_Study.kurs);
        student.setNfaka ( Stud_Info_Study.nfaka);
        student.setNfob ( Stud_Info_Study.nfob);
        student.setNkaprikaz ( Stud_Info_Study.nkaprikaz);
        student.setNnarp ( Stud_Info_Study.nnarp);
        student.setNomerprikaz ( Stud_Info_Study.nomerprikaz);
        student.setNspc ( Stud_Info_Study.nspc);
        student.setNspecya ( Stud_Info_Study.nspecya);
        student.setNvidprikaz ( Stud_Info_Study.nvidprikaz);
        student.setPrimech ( Stud_Info_Study.primech);
        student.setProvedeno ( Stud_Info_Study.provedeno);
        student.setRait ( Stud_Info_Study.rait);
        student.setTitul_id ( Stud_Info_Study.titul_id);
        student.setUsers ( Stud_Info_Study.users);
        student.setWork ( Stud_Info_Study.work);
        student.setNnarp ( Stud_Info_Study.nnarp);

        return student;
    }

    private static String UnicodeToText(String text) throws IOException {
        Set<String> hexItems = new HashSet<>();

        Matcher m = Pattern.compile("\\\\u[a-fA-f0-9]{4}").matcher(text);
        while (m.find()) {
            hexItems.add(m.group());
        }

        for (String unicodeHex : hexItems) {
            int hexVal = Integer.parseInt(unicodeHex.substring(2), 16);
            text = text.replace(unicodeHex, "" + ((char) hexVal));
        }

        return text;
    }

    private class Data_stud_info {
        private String family;
        private String name;
        private String father;
        private String gruppa;
        private String nomzachet;
        private String worknomer;
        private String email;
        private String mobilephone;
        private String checkpasp;
        private String checkdroz;
        private String checkaddr;
        private String kaprik;
        private String did;
        private String kont_id;
        private String otherdoc_addrregistr2;

        public String getFamily(){
            return family;
        }
    }

    private class Data_stud_info_study {
        private String $css;
        private String datak;
        private String datan;
        private String dataprikaz;
        private String datazap;
        private String goslinia;
        private String gruppa;
        private String id;
        private String kod_okco;
        private String kurs;
        private String nfaka;
        private String nfob;
        private String nkaprikaz;
        private String nnarp;
        private String nomerprikaz;
        private String nomzachet;
        private String nspc;
        private String nspecya;
        private String nvidprikaz;
        private String primech;
        private String provedeno;
        private String rait;
        private String titul_id;
        private String users;
        private String work;
        private String worknomer;

    }
}
