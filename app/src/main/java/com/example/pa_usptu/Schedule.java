package com.example.pa_usptu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.gson.Gson;

import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;


public class Schedule extends ParentNavigationActivity{
    private String username;
    private String password;

    LinearLayout News;
    TextView Schedule_Text;
    LinearLayout Education;
    LinearLayout Personal_Track;
    LinearLayout Achievements;
    LinearLayout My_Works;
    LinearLayout Payouts;
    LinearLayout Tuition_Fees;
    LinearLayout Hostel;
    LinearLayout Workaround;
    LinearLayout Electronic_Reception;
    LinearLayout Resources;
    LinearLayout Logout;

    public static final String APP_PREFERENCES_OAuth = "OAuth";
    public static final String APP_PREFERENCES_Account = "Account";
    public static final String APP_PREFERENCES_FAMILY = "Family";
    public static final String APP_PREFERENCES_NAME = "Name";
    public static final String APP_PREFERENCES_FATHER = "Father";
    public static final String APP_PREFERENCES_GRUPPA = "Gruppa";
    public static final String APP_PREFERENCES_NOMZACHET = "Nomzachet";
    public static final String APP_PREFERENCES_WORKNOMER = "Worknomer";
    public static final String APP_PREFERENCES_EMAIL = "Email";
    public static final String APP_PREFERENCES_MOBILEPHONE = "Mobilephone";
    public static final String APP_PREFERENCES_CHECKPASP = "Checkpasp";
    public static final String APP_PREFERENCES_CHECKDROZ = "Checkdroz";
    public static final String APP_PREFERENCES_CHECKADDR = "Checkaddr";
    public static final String APP_PREFERENCES_KAPRIK = "Kaprik";
    public static final String APP_PREFERENCES_DID = "Did";
    public static final String APP_PREFERENCES_KONT_ID = "Kont_id";
    public static final String APP_PREFERENCES_OTHERDOC_ADDRREGISTR2 = "Otherdoc_addrregistr2";
    public static final String APP_PREFERENCES_$CSS = "$css";
    public static final String APP_PREFERENCES_DATAK = "Datak";
    public static final String APP_PREFERENCES_DATAN = "Datan";
    public static final String APP_PREFERENCES_DATAPRIKAZ = "Dataprikaz";
    public static final String APP_PREFERENCES_DATAZAP = "Datazap";
    public static final String APP_PREFERENCES_GOSLINIA = "Goslinia";
    public static final String APP_PREFERENCES_ID = "Id";
    public static final String APP_PREFERENCES_KOD_OKCO = "Kod_okco";
    public static final String APP_PREFERENCES_KURS = "Kurs";
    public static final String APP_PREFERENCES_NFAKA = "Nfaka";
    public static final String APP_PREFERENCES_NFOB = "Nfob";
    public static final String APP_PREFERENCES_NKAPRIKAZ = "Nkaprikaz";
    public static final String APP_PREFERENCES_NNARP = "Nnarp";
    public static final String APP_PREFERENCES_NOMERPRIKAZ = "Nomerprikaz";
    public static final String APP_PREFERENCES_NSPC = "Nspc";
    public static final String APP_PREFERENCES_NSPECYA = "Nspecya";
    public static final String APP_PREFERENCES_NVIDPRIKAZ = "Nvidprikaz";
    public static final String APP_PREFERENCES_PRIMECH = "Primech";
    public static final String APP_PREFERENCES_PROVEDENO = "Provedeno";
    public static final String APP_PREFERENCES_RAIT = "Rait";
    public static final String APP_PREFERENCES_TITUL_ID = "Titul_id";
    public static final String APP_PREFERENCES_USERS = "Users";
    public static final String APP_PREFERENCES_WORK = "Work";
    private SharedPreferences.Editor editor_oauth;
    private SharedPreferences.Editor editor_account;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_activity);

        Bundle arguments = getIntent().getExtras();
        username = arguments.get("username").toString();
        password = arguments.get("password").toString();

        SharedPreferences Account;
        SharedPreferences OAuth;
        OAuth = getSharedPreferences(APP_PREFERENCES_OAuth, Context.MODE_PRIVATE);
        Account = getSharedPreferences(APP_PREFERENCES_Account, Context.MODE_PRIVATE);
        editor_oauth = OAuth.edit();
        editor_account = Account.edit();

        //MENU
        News =(LinearLayout) findViewById(R.id.news);
        Schedule_Text =(TextView) findViewById(R.id.schedule_text);
        Education =(LinearLayout) findViewById(R.id.education);
        Personal_Track = (LinearLayout) findViewById(R.id.personal_track);
        Achievements =(LinearLayout) findViewById(R.id.achievements);
        My_Works =(LinearLayout) findViewById(R.id.my_works);
        Payouts =(LinearLayout) findViewById(R.id.payouts);
        Tuition_Fees =(LinearLayout) findViewById(R.id.tuition_fees);
        Hostel =(LinearLayout) findViewById(R.id.hostel);
        Workaround =(LinearLayout) findViewById(R.id.workaround);
        Electronic_Reception =(LinearLayout) findViewById(R.id.electronic_reception);
        Resources =(LinearLayout) findViewById(R.id.resources);
        Logout =(LinearLayout) findViewById(R.id.ok);

        Schedule_Text.setTextColor(getResources().getColor(R.color.choised));

        CircleImageView button_account = (CircleImageView) findViewById(R.id.button_account);

        button_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Schedule.this, Second.class);
                intent.putExtra("username", username);
                intent.putExtra("password", password);
                startActivity(intent);
                finish();
            }
        });
        News.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Schedule.this, News.class);
                intent.putExtra("username", username);
                intent.putExtra("password", password);
                startActivity(intent);
                finish();
            }
        });
        Education.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Не работает...",Toast.LENGTH_SHORT).show();
            }
        });
        Personal_Track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Не работает...",Toast.LENGTH_SHORT).show();
            }
        });
        Achievements.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Не работает...",Toast.LENGTH_SHORT).show();
            }
        });
        My_Works.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Не работает...",Toast.LENGTH_SHORT).show();
            }
        });
        Payouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Не работает...",Toast.LENGTH_SHORT).show();
            }
        });
        Tuition_Fees.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Не работает...",Toast.LENGTH_SHORT).show();
            }
        });
        Hostel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Не работает...",Toast.LENGTH_SHORT).show();
            }
        });
        Workaround.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Не работает...",Toast.LENGTH_SHORT).show();
            }
        });
        Electronic_Reception.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Не работает...",Toast.LENGTH_SHORT).show();
            }
        });
        Resources.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Не работает...",Toast.LENGTH_SHORT).show();
            }
        });
        Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor_oauth.clear();
                editor_account.clear();
                editor_oauth.apply();
                editor_account.apply();

                Intent intent = new Intent(Schedule.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        ImageButton imageButton = (ImageButton) findViewById(R.id.menu);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.Drawer);
                drawerLayout.openDrawer(5);
            }
        });

        GetStudInfo getStudInfo = new GetStudInfo();
        getStudInfo.execute();
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

    class GetStudInfo extends AsyncTask<Void, Void, Full_Info_Student> {
        Connection.Response response1;
        Boolean test;

        @Override
        protected Full_Info_Student doInBackground(Void... voids) {
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

            Full_Info_Student student = new Full_Info_Student();

            student.$css = Stud_Info_Study.$css;
            student.family = Stud_Info.family;
            student.name = Stud_Info.name;
            student.father = Stud_Info.father;
            student.gruppa = Stud_Info.gruppa;
            student.nomzachet = Stud_Info.nomzachet;
            student.worknomer = Stud_Info.worknomer;
            student.email = Stud_Info.email;
            student.mobilephone = "+"+Stud_Info.mobilephone;
            student.checkpasp = Stud_Info.checkpasp;
            student.checkdroz = Stud_Info.checkdroz;
            student.checkaddr = Stud_Info.checkaddr;
            student.kaprik = Stud_Info.kaprik;
            student.did = Stud_Info.did;
            student.kont_id = Stud_Info.kont_id;
            student.otherdoc_addrregistr2 = Stud_Info.otherdoc_addrregistr2;
            student.datak = Stud_Info_Study.datak;
            student.datan = Stud_Info_Study.datan;
            student.dataprikaz = Stud_Info_Study.dataprikaz;
            student.datazap = Stud_Info_Study.datazap;
            student.goslinia = Stud_Info_Study.goslinia;
            student.id = Stud_Info_Study.id;
            student.kod_okco = Stud_Info_Study.kod_okco;
            student.kurs = Stud_Info_Study.kurs;
            student.nfaka = Stud_Info_Study.nfaka;
            student.nfob = Stud_Info_Study.nfob;
            student.nkaprikaz = Stud_Info_Study.nkaprikaz;
            student.nnarp = Stud_Info_Study.nnarp;
            student.nomerprikaz = Stud_Info_Study.nomerprikaz;
            student.nspc = Stud_Info_Study.nspc;
            student.nspecya = Stud_Info_Study.nspecya;
            student.nvidprikaz = Stud_Info_Study.nvidprikaz;
            student.primech = Stud_Info_Study.primech;
            student.provedeno = Stud_Info_Study.provedeno;
            student.rait = Stud_Info_Study.rait;
            student.titul_id = Stud_Info_Study.titul_id;
            student.users = Stud_Info_Study.users;
            student.work = Stud_Info_Study.work;

            return student;
        }

        @Override
        protected void onPostExecute(Full_Info_Student student) {
            super.onPostExecute(student);
        }
    }

    public class Full_Info_Student {
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
        private String $css;
        private String datak;
        private String datan;
        private String dataprikaz;
        private String datazap;
        private String goslinia;
        private String id;
        private String kod_okco;
        private String kurs;
        private String nfaka;
        private String nfob;
        private String nkaprikaz;
        private String nnarp;
        private String nomerprikaz;
        private String nspc;
        private String nspecya;
        private String nvidprikaz;
        private String primech;
        private String provedeno;
        private String rait;
        private String titul_id;
        private String users;
        private String work;
    }

    public class Data_stud_info {
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

    public class Data_stud_info_study {
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