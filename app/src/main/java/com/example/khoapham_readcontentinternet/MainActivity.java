package com.example.khoapham_readcontentinternet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new ReadContentInternet().execute("https://khoapham.vn");
    }
    class ReadContentInternet extends AsyncTask<String,String,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder= new StringBuilder();
            try {

                URL url  = new URL(strings[0]);
                URLConnection connection= url.openConnection();
                InputStream inputStream= connection.getInputStream();
                InputStreamReader inputStreamReader= new InputStreamReader(inputStream);
                BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
                String line="";
                while ((line=bufferedReader.readLine())!=null)
                {
                    stringBuilder.append(line+"\n");
                    publishProgress(line);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
           return stringBuilder.toString();
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            Toast.makeText(MainActivity.this,values+"",Toast.LENGTH_LONG).show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this,"ReadCompletely",Toast.LENGTH_LONG).show();
            Log.d("BBB",s);

        }
    }
}
