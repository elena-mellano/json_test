package com.elena.app;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.content.Intent;
import android.widget.TextView;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;

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
            InputStream is = openFileInput("supermercado.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Tienda[] t = gson.fromJson(json, Tienda[].class);
        // Tienda t = gson.fromJson(json, Tienda.class);
        Intent intent=getIntent();
        String pkg=getPackageName();
        ListView list= (ListView)findViewById(R.id.prova);

        int prod=intent.getIntExtra(pkg+".but",0);

        if (prod==0){
            setTitle("Pan");
            CustomAdapterpan adapter = new CustomAdapterpan(this, R.layout.row, t);
            list.setAdapter(adapter);
        }else{
            setTitle("Manzana");
            CustomAdapter adapter = new CustomAdapter(this, R.layout.row, t);
            list.setAdapter(adapter);
        }
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3){
                TextView item =(TextView)((LinearLayout) arg1).getChildAt(0);
                Gson gson =new Gson();
                String json=null;
                String totale=item.getText().toString();
                String del = ":";
                String[] palabrasSeparadas = totale.split(del);

                try {
                    InputStream is = openFileInput("supermercado.json");
                    int size = is.available();
                    byte[] buffer = new byte[size];
                    is.read(buffer);
                    is.close();
                    json = new String(buffer);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                int f=0;
                Type collectionType = new TypeToken<ArrayList<Tienda>>(){}.getType();
                ArrayList<Tienda> tott = gson.fromJson(json, collectionType);

                for (int i=0;i<tott.size();i++){
                    if ( tott.get(i).getSupermercado().equals(palabrasSeparadas[0])){
                        tott.remove(i);
                        f=1;
                    }
                }
                if (f==1){
                    try {
                        FileOutputStream file = openFileOutput("supermercado.json",MODE_PRIVATE);
                        json=gson.toJson(tott,collectionType);
                        file.write(json.getBytes());
                        file.flush();
                        file.close();
                        finish();
                        startActivity(getIntent());
                    }catch(Exception e){
                        System.out.print(e.toString());
                    }
                }

            }

        });

    }


}
