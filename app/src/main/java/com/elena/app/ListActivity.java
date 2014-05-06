package com.elena.app;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;
import android.content.Intent;


import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by elena on 26/04/14.
 */
public class ListActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list);
        Gson gson =new Gson();
        String json=null;
        try {
            InputStream is = getAssets().open("supermercado.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        ListView list= (ListView)findViewById(R.id.prova);
        Tienda[] t = gson.fromJson(json, Tienda[].class);
        // Tienda t = gson.fromJson(json, Tienda.class);
        Intent intent=getIntent();
        String pkg=getPackageName();

        int prod=intent.getIntExtra(pkg+".but",0);

        if (prod==0){
            CustomAdapterpan adapter = new CustomAdapterpan(this, R.layout.row, t);
            list.setAdapter(adapter);
        }else{
            CustomAdapter adapter = new CustomAdapter(this, R.layout.row, t);
            list.setAdapter(adapter);
        }


    }
}
