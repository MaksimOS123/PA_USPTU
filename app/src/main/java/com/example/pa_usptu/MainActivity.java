package com.example.pa_usptu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import org.jsoup.Connection;
import org.jsoup.helper.HttpConnection;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    private SharedPreferences OAuth;
    private EditText username;
    private EditText password;
    private Button login;
    private TextView loginLocked;
    private TextView attempts;
    private TextView numberOfAttempts;

    public static final String APP_PREFERENCES = "OAuth";
    public static final String APP_PREFERENCES_LOGIN = "Login";
    public static final String APP_PREFERENCES_PASSWORD = "Password";

    private SharedPreferences.Editor editor;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        OAuth = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        if(OAuth.contains(APP_PREFERENCES_LOGIN)) {
            Intent intent = new Intent(MainActivity.this, News.class);
            intent.putExtra("username", OAuth.getString(APP_PREFERENCES_LOGIN, ""));
            intent.putExtra("password", OAuth.getString(APP_PREFERENCES_PASSWORD, ""));
            startActivity(intent);
            finish();
        }

        editor = OAuth.edit();

        Button butt = (Button) findViewById(R.id.gos_in);
        String string = "<font color='#292D32'>Войти с помощью </font>" + "<font color='#0500FF'>Гос</font>" + "<font color='#FF0000'>Услуг</font>";
        butt.setText(Html.fromHtml(string));

        //Auth
        username = (EditText) findViewById(R.id.Login);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.sign_in);
    }

    public void Login (View view) throws IOException {
        PostResponse postResponse = new PostResponse();
        postResponse.execute();
    }

    class PostResponse extends AsyncTask<Void, Boolean, Boolean> {
        String response1;
        Boolean test;

        @Override
        protected Boolean doInBackground(Void... voids) {
            String typelogin = "1";
            String dataLogin = "LData={\"typelogin\":"+ typelogin +",\"username\":\""+ username.getText().toString() +"\",\"password\":\""+ password.getText().toString() +"\"}";
            String userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.159 Safari/537.36";

            String urllogin = "https://ams.rusoil.net/oau/login";

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
                response1 = connection1.execute().body();
                test = response1.indexOf("\"R\":\"Ok\"") != -1;
            } catch (IOException e) {
                e.printStackTrace();
            }

            return test;
        }

        @Override
        protected void onPostExecute(Boolean response1) {
            super.onPostExecute(response1);

            if (response1){
                Toast.makeText(getApplicationContext(), "Вход выполнен!",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this,Second.class);
                intent.putExtra("username", username.getText().toString());
                intent.putExtra("password", password.getText().toString());

                editor.putString(APP_PREFERENCES_LOGIN, username.getText().toString());
                editor.putString(APP_PREFERENCES_PASSWORD, password.getText().toString());
                editor.apply();

                startActivity(intent);
                finish();
            } else {
                Toast.makeText(getApplicationContext(), "Неправильные данные!",Toast.LENGTH_SHORT).show();
            }

        }
    }
}