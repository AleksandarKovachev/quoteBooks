package com.example.pc.login.MySql;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class Downloader extends AsyncTask<Void, Void, String> {

    Context c;
    String urlAddress;
    RecyclerView recyclerView;
    String type;

    public Downloader(Context c, String urlAddress, RecyclerView recyclerView, String type) {
        this.c = c;
        this.urlAddress = urlAddress;
        this.recyclerView = recyclerView;
        this.type = type;
    }

    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);

        if (result.startsWith("Error")) {
            Toast.makeText(c, "Unsuccessful " + result, Toast.LENGTH_SHORT).show();
        } else {
            new DataParser(c, result, recyclerView, type).execute();
        }
    }

    @Override
    protected String doInBackground(Void... voids) {
        return this.downloadData();
    }

    private String downloadData() {

        Object connection = Connector.connect(urlAddress);
        if (connection.toString().startsWith("Error")) {
            return connection.toString();
        }

        try {
            HttpURLConnection con = (HttpURLConnection) connection;
            InputStream is = new BufferedInputStream(con.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuffer jsonData=new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                jsonData.append(line+"\n");
            }
            br.close();
            is.close();
            return jsonData.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error " + e.getMessage();
        }
    }
}
