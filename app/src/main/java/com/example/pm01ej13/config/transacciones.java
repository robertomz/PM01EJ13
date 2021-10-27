package com.example.pm01ej13.config;

public class transacciones {

    //Nombre de la base de datos
    public static final String NameDataBase = "PM01EJ13";

    //Tablas de la base de datos
    public static final String tablePersonas = "Personas";

    //Campos de la tabla
    public static final String id = "id";
    public static final String fname = "fname";
    public static final String lname = "lname";
    public static final String age = "age";
    public static final String mail = "mail";
    public static final String locate = "locate";

    //Transacciones DDL (Data Definition Language) tabla personas
    public static final String CreateTablePersonas = "CREATE TABLE Personas (id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "fname TEXT, lname TEXT, age TEXT, mail TEXT, locate TEXT)";

    public static final String DROPTablePersonas = "DROP TABLE IF EXISTS Personas";
}
