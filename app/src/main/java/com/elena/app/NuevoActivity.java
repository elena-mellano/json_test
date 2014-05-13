package com.elena.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.app.AlertDialog;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileWriter;
import java.util.ArrayList;
import java.lang.reflect.Type;
/**
 * Created by elena on 28/04/14.
 */
public class NuevoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nuevo);

    }
    public void onClickana(View v) {
        EditText tienda= (EditText)findViewById(R.id.editText);
        String nombretienda= tienda.getText().toString();
        if (nombretienda.compareTo("")==0 || nombretienda.compareTo("Tienda")==0){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("inserir un nombre para la tienda");
            AlertDialog dialog = builder.create();
            dialog.show();
        }else{
            EditText pan= (EditText)findViewById(R.id.editText2);
            String span= pan.getText().toString();
            Double valorpan=0.0;
            try{
                valorpan = Double.parseDouble(span);
            }catch(NumberFormatException ex){
                System.out.println("Value at TextView is not a valid integer");
            }
            EditText man= (EditText)findViewById(R.id.editText3);
            String sman= man.getText().toString();
            double valorman=0.0;
            try{
                valorman = Double.parseDouble(sman);
            }catch(NumberFormatException ex){
                System.out.println("Value at TextView is not a valid integer");
            }

            Tienda t=new Tienda();
            t.setSupermercado(nombretienda);
            t.setManzana(valorman);
            t.setPan(valorpan);
            Gson gson =new Gson();
            String json=null;
            String json2=null;
            try {
                InputStream is = openFileInput("supermercado.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json2 = new String(buffer);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            Type collectionType = new TypeToken<ArrayList<Tienda>>(){}.getType();
            ArrayList<Tienda> tott = gson.fromJson(json2, collectionType);
            int f=0;
            for (int i=0;i<tott.size();i++){
                if ( tott.get(i).getSupermercado().equals(t.getSupermercado())){
                    tott.get(i).setManzana(t.getManzana());
                    tott.get(i).setPan(t.getPan());
                    f=1;
                }
            }
            if (f==0){
                tott.add(t);
            }

            try {
                FileOutputStream file = openFileOutput("supermercado.json",MODE_PRIVATE);
                json=gson.toJson(tott,collectionType);
                file.write(json.getBytes());
                file.flush();
                file.close();
                Intent start=new Intent(NuevoActivity.this, MainActivity.class);
                startActivity(start); //

            } catch (Exception e) {
                json=gson.toJson(tott,collectionType);
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage(json);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
    }
}
