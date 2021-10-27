package com.example.pm01ej13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.pm01ej13.config.SQLiteConn;
import com.example.pm01ej13.config.transacciones;
import com.example.pm01ej13.tables.Personas;

import java.util.ArrayList;

public class ActivityRegistros extends AppCompatActivity {

    SQLiteConn conn;
    ListView listaContactos;
    ArrayList<Personas> lista;
    ArrayList<String> ArregloPersonas;

    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros);

        /* LISTAR CONTACTOS */
        searchView = (SearchView) findViewById(R.id.SearchView);
        ArregloPersonas = new ArrayList<String>();

        conn = new SQLiteConn(this, transacciones.NameDataBase, null, 1);
        listaContactos = (ListView) findViewById(R.id.listaPesonas);

        ObtenerContactos();

        ArrayAdapter adp = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, ArregloPersonas);
        listaContactos.setAdapter(adp);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adp.getFilter().filter(newText);

                return false;
            }
        });

        listaContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), ActivityActualizar.class);
                intent.putExtra("objeto", lista.get(position));
                startActivity(intent);
            }
        });

        /* REGRESAR */
        Button btnAtras = (Button) findViewById(R.id.btnAtras);

        btnAtras.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
    }

    private void ObtenerContactos() {
        SQLiteDatabase db = conn.getReadableDatabase();
        Personas list_personas = null;
        lista = new ArrayList<Personas>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + transacciones.tablePersonas, null);

        while(cursor.moveToNext()) {
            list_personas = new Personas();

            list_personas.setId(cursor.getInt(0));
            list_personas.setFname(cursor.getString(1));
            list_personas.setLname(cursor.getString(2));
            list_personas.setAge(cursor.getString(3));
            list_personas.setMail(cursor.getString(4));
            list_personas.setLocate(cursor.getString(5));

            lista.add(list_personas);
        }

        cursor.close();

        filllist();
    }

    private void filllist() {
        ArregloPersonas = new ArrayList<String>();

        for (int i = 0; i < lista.size(); i++) {
            ArregloPersonas.add(lista.get(i).getFname() + " "
                    + lista.get(i).getLname() + " | "
                    + lista.get(i).getAge() + " años | "
                    + lista.get(i).getMail() + " | "
                    + lista.get(i).getLocate());
        }
    }
}