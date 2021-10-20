package com.example.pa_usptu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Layout;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.LayoutRes;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.gson.Gson;

import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import de.hdodenhof.circleimageview.CircleImageView;


public class Second extends ParentNavigationActivity{
    private String username;
    private String password;

    LinearLayout News;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        Bundle arguments = getIntent().getExtras();
        username = arguments.get("username").toString();
        password = arguments.get("password").toString();

        SharedPreferences Account;
        SharedPreferences OAuth;
        OAuth = getSharedPreferences(APP_PREFERENCES_OAuth, Context.MODE_PRIVATE);
        Account = getSharedPreferences(APP_PREFERENCES_Account, Context.MODE_PRIVATE);
        editor_oauth = OAuth.edit();
        editor_account = Account.edit();

        if (Account.contains(APP_PREFERENCES_FAMILY)) {
            TextView name_menu = (TextView) findViewById(R.id.button_account_name);
            TextView email_menu = (TextView) findViewById(R.id.button_account_email);

            TextView name = (TextView) findViewById(R.id.name);
            TextView email = (TextView) findViewById(R.id.mail);
            TextView name_button = (TextView) findViewById(R.id.button_account_name);
            TextView email_button = (TextView) findViewById(R.id.button_account_email);
            TextView phone = (TextView) findViewById(R.id.phone);
            TextView nomzachet = (TextView) findViewById(R.id.nomzachet);
            TextView gruppa = (TextView) findViewById(R.id.gruppa);
            TextView nspc = (TextView) findViewById(R.id.nspc);
            TextView rait = (TextView) findViewById(R.id.rait);

            ProgressBar loadingInfo = (ProgressBar) findViewById(R.id.LoadingInfo);

            loadingInfo.setVisibility(View.GONE);

            name.setVisibility(View.VISIBLE);
            email.setVisibility(View.VISIBLE);
            phone.setVisibility(View.VISIBLE);

            name.setText(Account.getString(APP_PREFERENCES_FAMILY, "")+" "+Account.getString(APP_PREFERENCES_NAME, ""));
            email.setText(Account.getString(APP_PREFERENCES_EMAIL, ""));
            name_button.setText(Account.getString(APP_PREFERENCES_FAMILY, "")+" "+Account.getString(APP_PREFERENCES_NAME, ""));
            email_button.setText(Account.getString(APP_PREFERENCES_EMAIL, ""));
            phone.setText(Account.getString(APP_PREFERENCES_MOBILEPHONE, ""));
            nomzachet.setText(Account.getString(APP_PREFERENCES_NOMZACHET, ""));
            gruppa.setText(Account.getString(APP_PREFERENCES_GRUPPA, ""));
            nspc.setText(Account.getString(APP_PREFERENCES_NSPC, ""));
            rait.append(Account.getString(APP_PREFERENCES_RAIT, ""));
        }

        //MENU
        News =(LinearLayout) findViewById(R.id.news);
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

        News.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Second.this, News.class);
                intent.putExtra("username", username);
                intent.putExtra("password", password);
                startActivity(intent);
                finish();
            }
        });
        Schedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Second.this, Schedule.class);
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

                Intent intent = new Intent(Second.this, MainActivity.class);
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

    class GetStudInfo extends AsyncTask<Void, Void, Student_Info> {
        Connection.Response response1;
        Boolean test;

        @Override
        protected Student_Info doInBackground(Void... voids) {


            Student_Info student = Get_Student_Info.Get_Student_Info(username, password);

            editor_account.putString(APP_PREFERENCES_FAMILY, student.getFamily());
            editor_account.putString(APP_PREFERENCES_$CSS, student.get$css());
            editor_account.putString(APP_PREFERENCES_NAME, student.getName());
            editor_account.putString(APP_PREFERENCES_FATHER, student.getFather());
            editor_account.putString(APP_PREFERENCES_GRUPPA, student.getGruppa());
            editor_account.putString(APP_PREFERENCES_NOMZACHET, student.getNomzachet());
            editor_account.putString(APP_PREFERENCES_WORKNOMER, student.getWorknomer());
            editor_account.putString(APP_PREFERENCES_EMAIL, student.getEmail());
            editor_account.putString(APP_PREFERENCES_MOBILEPHONE, student.getMobilephone());
            editor_account.putString(APP_PREFERENCES_CHECKPASP, student.getCheckpasp());
            editor_account.putString(APP_PREFERENCES_CHECKDROZ, student.getCheckdroz());
            editor_account.putString(APP_PREFERENCES_CHECKADDR, student.getCheckaddr());
            editor_account.putString(APP_PREFERENCES_KAPRIK, student.getKaprik());
            editor_account.putString(APP_PREFERENCES_DID, student.getDid());
            editor_account.putString(APP_PREFERENCES_KONT_ID, student.getKont_id());
            editor_account.putString(APP_PREFERENCES_OTHERDOC_ADDRREGISTR2, student.getOtherdoc_addrregistr2());
            editor_account.putString(APP_PREFERENCES_DATAK, student.getDatak());
            editor_account.putString(APP_PREFERENCES_DATAN, student.getDatan());
            editor_account.putString(APP_PREFERENCES_DATAPRIKAZ, student.getDataprikaz());
            editor_account.putString(APP_PREFERENCES_DATAZAP, student.getDatazap());
            editor_account.putString(APP_PREFERENCES_GOSLINIA, student.getGoslinia());
            editor_account.putString(APP_PREFERENCES_ID, student.getId());
            editor_account.putString(APP_PREFERENCES_KOD_OKCO, student.getKod_okco());
            editor_account.putString(APP_PREFERENCES_KURS, student.getKurs());
            editor_account.putString(APP_PREFERENCES_NFAKA, student.getNfaka());
            editor_account.putString(APP_PREFERENCES_NFOB, student.getNfob());
            editor_account.putString(APP_PREFERENCES_NKAPRIKAZ, student.getNkaprikaz());
            editor_account.putString(APP_PREFERENCES_NNARP, student.getNnarp());
            editor_account.putString(APP_PREFERENCES_NOMERPRIKAZ, student.getNomerprikaz());
            editor_account.putString(APP_PREFERENCES_NSPC, student.getNspc());
            editor_account.putString(APP_PREFERENCES_NSPECYA, student.getNspecya());
            editor_account.putString(APP_PREFERENCES_NVIDPRIKAZ, student.getNvidprikaz());
            editor_account.putString(APP_PREFERENCES_PRIMECH, student.getPrimech());
            editor_account.putString(APP_PREFERENCES_PROVEDENO, student.getProvedeno());
            editor_account.putString(APP_PREFERENCES_RAIT, student.getRait());
            editor_account.putString(APP_PREFERENCES_TITUL_ID, student.getTitul_id());
            editor_account.putString(APP_PREFERENCES_USERS, student.getUsers());
            editor_account.putString(APP_PREFERENCES_WORK, student.getWork());
            editor_account.apply();

            return student;
        }

        @Override
        protected void onPostExecute(Student_Info student) {
            super.onPostExecute(student);

            TextView name = (TextView) findViewById(R.id.name);
            TextView email = (TextView) findViewById(R.id.mail);
            TextView name_button = (TextView) findViewById(R.id.button_account_name);
            TextView email_button = (TextView) findViewById(R.id.button_account_email);
            TextView phone = (TextView) findViewById(R.id.phone);
            TextView nomzachet = (TextView) findViewById(R.id.nomzachet);
            TextView gruppa = (TextView) findViewById(R.id.gruppa);
            TextView nspc = (TextView) findViewById(R.id.nspc);
            TextView rait = (TextView) findViewById(R.id.rait);

            ProgressBar loadingInfo = (ProgressBar) findViewById(R.id.LoadingInfo);

            loadingInfo.setVisibility(View.GONE);

            name.setVisibility(View.VISIBLE);
            email.setVisibility(View.VISIBLE);
            phone.setVisibility(View.VISIBLE);

            name.setText(student.getFamily()+" "+student.getName());
            email.setText(student.getEmail());
            name_button.setText(student.getFamily()+" "+student.getName());
            email_button.setText(student.getEmail());
            phone.setText(student.getMobilephone());
            nomzachet.setText(student.getNomzachet());
            gruppa.setText(student.getGruppa());
            nspc.setText(student.getNspc());
            rait.setText("Успеваемость: "+student.getRait());
        }
    }


}