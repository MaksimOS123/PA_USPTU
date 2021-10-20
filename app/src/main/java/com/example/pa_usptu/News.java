package com.example.pa_usptu;

import android.animation.LayoutTransition;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.widget.NestedScrollView;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.gson.Gson;

import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;


public class News extends ParentNavigationActivity{
    private String username;
    private String password;

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
    private Student_Info student;
    private SharedPreferences Account;

    TextView News_Text;
    LinearLayout Schedule;
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);

        Bundle arguments = getIntent().getExtras();
        username = arguments.get("username").toString();
        password = arguments.get("password").toString();

        SharedPreferences OAuth;
        OAuth = getSharedPreferences(APP_PREFERENCES_OAuth, Context.MODE_PRIVATE);
        Account = getSharedPreferences(APP_PREFERENCES_Account, Context.MODE_PRIVATE);
        editor_oauth = OAuth.edit();
        editor_account = Account.edit();

        TextView name_button = (TextView) findViewById(R.id.button_account_name);
        TextView email_button = (TextView) findViewById(R.id.button_account_email);
        name_button.setText(Account.getString(APP_PREFERENCES_FAMILY, "")+" "+Account.getString(APP_PREFERENCES_NAME, ""));
        email_button.setText(Account.getString(APP_PREFERENCES_EMAIL, ""));

        //MENU
        News_Text =(TextView) findViewById(R.id.news_text);
        Schedule =(LinearLayout) findViewById(R.id.schedule);
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

        News_Text.setTextColor(getResources().getColor(R.color.choised));

        CircleImageView button_account = (CircleImageView) findViewById(R.id.button_account);

        button_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(News.this, Second.class);
                intent.putExtra("username", username);
                intent.putExtra("password", password);
                startActivity(intent);
                finish();
            }
        });
        Schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(News.this, Schedule.class);
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

                Intent intent = new Intent(News.this, MainActivity.class);
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

        GetStudInfo getStudInfo = new GetStudInfo(this);
        getStudInfo.execute();

    }

    class GetStudInfo extends AsyncTask<Void, Void, Document> {
        Connection.Response response1;
        Boolean test;
        Context context;

        public GetStudInfo(Context tt) {
            context = tt;
        }

        @Override
        protected Document doInBackground(Void... voids) {
            String typelogin = "1";
            String dataLogin = "LData={\"typelogin\":"+ typelogin +",\"username\":\""+ username +"\",\"password\":\""+ password +"\"}";
            String dataReload = "typelogin="+ typelogin +"&username="+ username + "&password=" + password;
            String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36";

            String urlnews = "https://ams.rusoil.net/pcs/getfunc?obj=getnewsN";
            String urllogin = "https://ams.rusoil.net/oau/login";
            String urlreload = "https://ams.rusoil.net/oau/reload";

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

            Connection connection6 = connection2.url(urlnews)
                    .cookies(cookiess)
                    .followRedirects(true)
                    .userAgent(userAgent)
                    .method(Connection.Method.POST)
                    .header("Content-Type", "application/x-www-form-urlencoded")
                    .ignoreContentType(true)
                    .referrer("https://ams.rusoil.net/pcs/s?w_first")
                    .requestBody("cat=s");

            Document tt6 = null;

            try {
                Connection.Response response6 = connection6.execute();
                tt6 = response6.parse();
            } catch (IOException e) {
                e.printStackTrace();
            }

            student = Get_Student_Info.Get_Student_Info(username, password);

            return tt6;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        protected void onPostExecute(Document tt6) {
            super.onPostExecute(tt6);

            Element table = tt6.select("table").get(0);
            Elements rows = table.select("td");

            for (int i = 0; i < rows.size(); i++) {

                String Date = rows.get(i).text().substring(0, rows.get(i).text().indexOf(" "));
                String Title = rows.get(i).text().substring(rows.get(i).text().indexOf(" ")+1, rows.get(i).text().indexOf("..."));
                String News_Text = rows.get(i).text().substring(rows.get(i).text().indexOf("...")+14, rows.get(i).text().indexOf("Скрыть")-1);

                LinearLayout main_linear = (LinearLayout) findViewById(R.id.Main_Linear);

                LinearLayout.LayoutParams linLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                LinearLayout.LayoutParams imageLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                LinearLayout.LayoutParams relLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                LinearLayout.LayoutParams dateLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                LinearLayout.LayoutParams scrollLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getResources().getDimensionPixelSize(R.dimen.size_scroll));
                LinearLayout.LayoutParams moreLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                if (i != rows.size()-1)
                    linLayoutParams.setMargins(0, getResources().getDimensionPixelSize(R.dimen.top_margin_card), 0, 0);
                else
                    linLayoutParams.setMargins(0, getResources().getDimensionPixelSize(R.dimen.top_margin_card), 0, getResources().getDimensionPixelSize(R.dimen.top_margin_card));
                dateLayoutParams.setMargins(getResources().getDimensionPixelSize(R.dimen.left_margin_date), getResources().getDimensionPixelSize(R.dimen.top_margin_date), 0, 0);
                textLayoutParams.setMargins(getResources().getDimensionPixelSize(R.dimen.left_margin_title), getResources().getDimensionPixelSize(R.dimen.top_margin_title), getResources().getDimensionPixelSize(R.dimen.left_margin_title), getResources().getDimensionPixelSize(R.dimen.bottom_margin_title));
                scrollLayoutParams.setMargins(getResources().getDimensionPixelSize(R.dimen.left_margin_scroll), getResources().getDimensionPixelSize(R.dimen.top_margin_scroll), getResources().getDimensionPixelSize(R.dimen.right_margin_scroll), getResources().getDimensionPixelSize(R.dimen.bottom_margin_scroll));
                moreLayoutParams.setMargins(0, 0, getResources().getDimensionPixelSize(R.dimen.right_margin_scroll), getResources().getDimensionPixelSize(R.dimen.top_margin_date));
                moreLayoutParams.gravity = Gravity.RIGHT;

                CardView cardView = new CardView(context);
                cardView.setCardBackgroundColor(Color.TRANSPARENT);
                cardView.setCardElevation(0);
                cardView.setLayoutTransition(new LayoutTransition());

                main_linear.addView(cardView, linLayoutParams);

                ImageView imageView = new ImageView(context);
                imageView.setImageResource(R.drawable.news_card);
                cardView.addView(imageView, imageLayoutParams);

                LinearLayout relativeLayout = new LinearLayout(context);
                relativeLayout.setOrientation(LinearLayout.VERTICAL);
                relativeLayout.setLayoutTransition(new LayoutTransition());
                cardView.addView(relativeLayout, relLayoutParams);

                TextView date_news = new TextView(context);
                date_news.setText(Date);
                date_news.setTextColor(getResources().getColor(R.color.date_blue));
                date_news.setTypeface(getResources().getFont(R.font.source_sans_pro_regular));
                date_news.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 10);

                relativeLayout.addView(date_news, dateLayoutParams);

                TextView title_news = new TextView(context);
                title_news.setText(Title);
                title_news.setTextColor(Color.BLACK);
                title_news.setTypeface(getResources().getFont(R.font.source_sans_pro_semi_bold));
                title_news.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);

                relativeLayout.addView(title_news, textLayoutParams);

                NestedScrollView scrollView = new NestedScrollView(context);
                scrollView.setVisibility(View.GONE);
                relativeLayout.addView(scrollView, scrollLayoutParams);

                TextView text_news = new TextView(context);
                text_news.setText(News_Text);
                text_news.setTextColor(getResources().getColor(R.color.text_black));
                text_news.setTypeface(getResources().getFont(R.font.source_sans_pro_regular));
                text_news.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);

                scrollView.addView(text_news);

                TextView more_button = new TextView(context);
                more_button.setText("Подробнее");
                more_button.setLayoutParams(moreLayoutParams);
                more_button.setTextColor(getResources().getColor(R.color.blue));
                more_button.setTypeface(getResources().getFont(R.font.source_sans_pro_regular));
                more_button.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 13);

                more_button.setOnClickListener(new View.OnClickListener() {
                    private Boolean click = Boolean.FALSE;

                    @Override
                    public void onClick(View view) {
                        if (click) {
                            scrollView.setVisibility(View.GONE);
                            more_button.setText("Подробнее");
                            click = !click;
                        } else {
                            scrollView.setVisibility(View.VISIBLE);
                            more_button.setText("Скрыть");
                            click = !click;
                        }
                    }
                });

                relativeLayout.addView(more_button);
            }

            if (Account.getString(APP_PREFERENCES_FAMILY, "") != student.getFamily() || Account.getString(APP_PREFERENCES_NAME, "") != student.getName() || Account.getString(APP_PREFERENCES_EMAIL, "") != student.getEmail()){
                TextView name_button = (TextView) findViewById(R.id.button_account_name);
                TextView email_button = (TextView) findViewById(R.id.button_account_email);
                name_button.setText(student.getFamily()+student.getName());
                email_button.setText(student.getEmail());

                editor_account.putString(APP_PREFERENCES_FAMILY, student.getFamily());
                editor_account.putString(APP_PREFERENCES_NAME, student.getName());
                editor_account.putString(APP_PREFERENCES_EMAIL, student.getEmail());
            }

            ProgressBar news_loading = (ProgressBar) findViewById(R.id.news_loading);
            news_loading.setVisibility(View.GONE);

        }
    }

}