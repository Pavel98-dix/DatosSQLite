package com.example.datossqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class registrarUsuario extends AppCompatActivity {
    TextInputLayout tilNombre,tilDomicilio,tilEmail,tilPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        tilNombre=(TextInputLayout)findViewById(R.id.tilNombre);
        tilDomicilio=(TextInputLayout)findViewById(R.id.tilDomicilio);
        tilEmail=(TextInputLayout)findViewById(R.id.tilEmail);
        tilPassword=(TextInputLayout)findViewById(R.id.tilPassword);
    }
    public void agregar(View v)
    {
        //Se agrega el metodo al evento del boton
        registrarUsuario();
    }
    private boolean esNombreValido(String otroNombre)
    {
        if (otroNombre.equals("") || otroNombre.length()>=20)
        {
            tilNombre.setError("Nombre Invalido");
            return false;
        }
        else
        {
            tilNombre.setError(null);
        }
        return true;
    }

    private boolean esCorreoValido(String correo)
    {

        if (correo.equals("")||!Patterns.EMAIL_ADDRESS.matcher(correo).matches())
        {
            tilEmail.setError("Correo electronico invalido");
            return false;
        }
        else
        {
            tilEmail.setError(null);
        }
        return true;
    }
    private boolean esDireccionValido(String direccion)
    {
        Pattern pattern= Pattern.compile("[0-09-zA-Z]");
        if (direccion.equals("")||direccion.length()>30 )
        {
            tilDomicilio.setError("Direccion invalida");
            return false;
        }
        else
        {
            tilDomicilio.setError(null);
        }
        return true;
    }
    //Metodo "Content values" para registrar
    private void registrarUsuario()
    {
        //Se crean variables, donde en ellas se obtiene los datos en los TextInputLayout
        final String nombre1=tilNombre.getEditText().getText().toString();
        final String direccion1=tilDomicilio.getEditText().getText().toString();
        final String email1=tilEmail.getEditText().getText().toString();
        //Se almacenan los valores de la variables
        boolean a=esNombreValido(nombre1);
        boolean b=esDireccionValido(direccion1);
        boolean c=esCorreoValido(email1);
        //La condicional donde si las variables cumplen con la validación correspondiente
        if (a && b && c)
        {
            //Se llama a la base de datos
            ConexionSQLiteHelper conn= new ConexionSQLiteHelper(this,"bd_usuario",null,1);

            SQLiteDatabase db= conn.getWritableDatabase();
            //Se egrgan los datos de los TextInputLayouten los respectivos campos de la BD
            ContentValues valores= new ContentValues();


            valores.put(utilidades.CAMPO_NOMBRE,tilNombre.getEditText().getText().toString());
            valores.put(utilidades.CAMPO_DOMICILIO,tilDomicilio.getEditText().getText().toString());
            valores.put(utilidades.CAMPO_EMAIL,tilEmail.getEditText().getText().toString());
            valores.put(utilidades.CAMPO_PASSWORD,tilPassword.getEditText().getText().toString());

            Long idRestante= db.insert(utilidades.TABLA_USUARIO,utilidades.CAMPO_NOMBRE,valores);
            //Al ser insertado en la BD de muestra el ID con el que fue guardado
            Toast.makeText(getApplicationContext(),"Numero de registro: "+ idRestante,Toast.LENGTH_SHORT).show();
            db.close();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Datos invalidos",Toast.LENGTH_SHORT).show();
        }




    }
}