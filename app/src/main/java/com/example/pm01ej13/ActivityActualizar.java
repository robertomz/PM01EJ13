package com.example.pm01ej13;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pm01ej13.config.SQLiteConn;
import com.example.pm01ej13.config.transacciones;
import com.example.pm01ej13.tables.Personas;

public class ActivityActualizar extends AppCompatActivity {

    SQLiteConn conn;
    private Personas item;
    EditText fname, lname, age, mail, locate;
    Integer id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);

        conn = new SQLiteConn(this, transacciones.NameDataBase, null, 1);

        item = (Personas) getIntent().getSerializableExtra("objeto");

        fname = (EditText) findViewById(R.id.txtNombreA);
        lname = (EditText) findViewById(R.id.txtApellidoA);
        age = (EditText) findViewById(R.id.txtEdadA);
        mail = (EditText) findViewById(R.id.txtCorreoA);
        locate = (EditText) findViewById(R.id.txtDireccionA);

        id = item.getId();
        fname.setText(item.getFname());
        lname.setText(item.getLname());
        age.setText(item.getAge());
        mail.setText(item.getMail());
        locate.setText(item.getLocate());

        Button btnAtras2 = (Button) findViewById(R.id.btnAtras2);
        Button btnActualizar = (Button) findViewById(R.id.btnActualizar);
        Button btnEliminar = (Button) findViewById(R.id.btnEliminar);

        btnAtras2.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ActivityRegistros.class);
            startActivity(intent);
        });

        btnActualizar.setOnClickListener(view -> Actualizar());

        btnEliminar.setOnClickListener(view -> Eliminar());
    }

    private void Actualizar() {
        SQLiteDatabase db = conn.getWritableDatabase();

        String [] params = {
                id.toString()
        };

        ContentValues valores = new ContentValues();
        valores.put(transacciones.fname, fname.getText().toString());
        valores.put(transacciones.lname, lname.getText().toString());
        valores.put(transacciones.age, age.getText().toString());
        valores.put(transacciones.mail, mail.getText().toString());
        valores.put(transacciones.locate, locate.getText().toString());

        db.update(transacciones.tablePersonas, valores, transacciones.id + "=?", params);

        Toast.makeText(getApplicationContext(), "Registro Actualizado", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getApplicationContext(), ActivityActualizar.class);
        startActivity(intent);
    }

    private void Eliminar() {
        SQLiteDatabase db = conn.getWritableDatabase();

        String [] params = {
                id.toString()
        };

        String wherecond = transacciones.id + "=?";

        db.delete(transacciones.tablePersonas, wherecond, params);

        Toast.makeText(getApplicationContext(), "Registro Eliminado", Toast.LENGTH_LONG).show();

        Intent intent = new Intent(getApplicationContext(), ActivityRegistros.class);
        startActivity(intent);
    }
}