package com.example.datossqlite;

public class utilidades {
    public static final String TABLA_USUARIO="usuario";
    public static final String  CAMPO_ID="id";
    public static final String  CAMPO_NOMBRE="nombre";
    public static final String  CAMPO_DOMICILIO="domicilio";
    public static final String  CAMPO_EMAIL="email";
    public static final String  CAMPO_PASSWORD="password";

    public static final String CREAR_TABLA_USUARIO="CREATE TABLE usuario "+"(" +CAMPO_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
            +CAMPO_NOMBRE+" TEXT, " +CAMPO_DOMICILIO+" TEXT, " +CAMPO_EMAIL+" TEXT, " +CAMPO_PASSWORD+" TEXT)";

    }
