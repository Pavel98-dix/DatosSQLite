package com.example.datossqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void registrarUsuario(View v)
    {
        Toast.makeText(getApplicationContext(),"Abriendo pagina, espere.....",Toast.LENGTH_LONG).show();
        Intent registrar= new Intent(this,registrarUsuario.class);
        startActivity(registrar);
    }
    public void consultarUsuario(View v)
    {
        Toast.makeText(getApplicationContext(),"Abriendo pagina, espere.....",Toast.LENGTH_LONG).show();
        Intent consultarUsuario= new Intent(this,consultarUsuario.class);
        startActivity(consultarUsuario);
    }
    public void consultarListView(View v)
    {
        Toast.makeText(getApplicationContext(),"Abriendo pagina, espere.....",Toast.LENGTH_LONG).show();
        Intent visualizarList= new Intent(this,visualizarListView.class);
        startActivity(visualizarList);
    }
}