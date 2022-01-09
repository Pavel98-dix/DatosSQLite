package com.example.datossqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class visualizarListView extends AppCompatActivity {
    android.widget.ListView lvConsulta;
    ArrayList<String> listaInformacion;
    ArrayList<usuario> listaUsuario;
    ConexionSQLiteHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_list_view);
        conn= new ConexionSQLiteHelper(this,"bd_usuario",null,1);
        lvConsulta=(android.widget.ListView)findViewById(R.id.lvListado);

        Lista();
        ArrayAdapter<String> adapter= new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listaInformacion);
        lvConsulta.setAdapter(adapter);


    }
    private void Lista()
    {
        SQLiteDatabase db= conn.getReadableDatabase();
        usuario usuario=null;
        listaUsuario= new ArrayList<>();

        Cursor cursor=db.rawQuery("SELECT * FROM "+utilidades.TABLA_USUARIO,null,null);

        while (cursor.moveToNext())
        {
            usuario= new usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setDomicilio(cursor.getString(2));
            usuario.setEmail(cursor.getString(3));
            usuario.setPassword(cursor.getString(4));
            listaUsuario.add(usuario);
        }
        obtenerLista();
        db.close();
    }
    private void obtenerLista() {
        listaInformacion= new ArrayList<>();

        for (int i=0;i<listaUsuario.size();i++)
        {
            listaInformacion.add(listaUsuario.get(i).getId()+" - "
                    +listaUsuario.get(i).getNombre());
        }
    }
}