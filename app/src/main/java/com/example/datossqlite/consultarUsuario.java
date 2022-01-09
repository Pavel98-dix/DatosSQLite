package com.example.datossqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class consultarUsuario extends AppCompatActivity {
    TextInputLayout tilId,tilNombre,tilDireccion,tilEmail,tilPassword;;
    TextInputEditText tietNombre,tietDomicilio,tietEmail,tietPassword;
    ConexionSQLiteHelper conn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_usuario);
        conn= new ConexionSQLiteHelper(this,"bd_usuario",null,1);
        tilId=(TextInputLayout)findViewById(R.id.tilID);
        tilNombre=(TextInputLayout)findViewById(R.id.tilNombre);
        tilDireccion=(TextInputLayout)findViewById(R.id.tilDomicilio);
        tilEmail=(TextInputLayout)findViewById(R.id.tilEmail);
        tilPassword=(TextInputLayout)findViewById(R.id.tilPassword);

        tietNombre= findViewById(R.id.tietNombre);
        tietDomicilio= findViewById(R.id.tietDomicilio);
        tietEmail= findViewById(R.id.tietEmail);
        tietPassword= findViewById(R.id.tietPassword);
    }
    public void consultar(View v)
    {
        SQLiteDatabase db=conn.getReadableDatabase();
        String [] parametros={tilId.getEditText().getText().toString()};
        String [] campos={utilidades.CAMPO_NOMBRE,utilidades.CAMPO_DOMICILIO,utilidades.CAMPO_EMAIL,utilidades.CAMPO_PASSWORD};

        try {
            Cursor cursor= db.query(utilidades.TABLA_USUARIO,campos,"id=?",
                    parametros,null,null,null);
            cursor.moveToFirst();
            tilNombre.setHint(cursor.getString(0));
            tilDireccion.setHint(cursor.getString(1));
            tilEmail.setHint(cursor.getString(2));
            tilPassword.setHint(cursor.getString(3));

            tietNombre.setText(cursor.getString(0));
            tietDomicilio.setText(cursor.getString(1));
            tietEmail.setText(cursor.getString(2));
            tietPassword.setText(cursor.getString(3));

            cursor.close();

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El ID no existe",Toast.LENGTH_LONG).show();

        }
    }
    public void actualizarUsuario(View v)
    {
        SQLiteDatabase db= conn.getWritableDatabase();
        String [] parametros={tilId.getEditText().getText().toString()};

        ContentValues valor= new ContentValues();

        valor.put(utilidades.CAMPO_NOMBRE,tilNombre.getEditText().getText().toString());
        valor.put(utilidades.CAMPO_DOMICILIO,tilDireccion.getEditText().getText().toString());
        valor.put(utilidades.CAMPO_EMAIL,tilEmail.getEditText().getText().toString());
        valor.put(utilidades.CAMPO_PASSWORD,tilPassword.getEditText().getText().toString());

        db.update(utilidades.TABLA_USUARIO,valor,"id=?",parametros);

        Toast.makeText(getApplicationContext(),"Datos Actualizados",Toast.LENGTH_LONG).show();
        db.close();
    }
    public void eliminarUsuario(View v)
    {
        SQLiteDatabase db= conn.getWritableDatabase();

        String[] parametros={tilId.getEditText().getText().toString()};

        db.delete(utilidades.TABLA_USUARIO,"id=?",parametros);
        Toast.makeText(getApplicationContext(),"Usuario Eliminado",Toast.LENGTH_LONG).show();
        tilId.setHint("");

        db.close();
    }
}