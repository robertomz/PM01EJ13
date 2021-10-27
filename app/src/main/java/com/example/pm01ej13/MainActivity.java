package com.example.pm01ej13;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pm01ej13.config.SQLiteConn;
import com.example.pm01ej13.config.transacciones;

public class MainActivity extends AppCompatActivity {

    EditText fname, lname, age, mail, locate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* OBTENER Y GUARDAR DATOS */
        fname = (EditText) findViewById(R.id.txtNombre);
        lname = (EditText) findViewById(R.id.txtApellido);
        age = (EditText) findViewById(R.id.txtEdad);
        mail = (EditText) findViewById(R.id.txtCorreo);
        locate = (EditText) findViewById(R.id.txtDireccion);

        Button btnGuardar = (Button) findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(view -> Guardar());

        /* VER REGISTROS */
        Button btnRegistro = (Button) findViewById(R.id.btnRegistro);

        btnRegistro.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ActivityRegistros.class);
            startActivity(intent);
        });
    }

    private void Guardar() {
        SQLiteConn conn = new SQLiteConn(this, transacciones.NameDataBase, null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues valores = new ContentValues();

        valores.put(transacciones.fname, fname.getText().toString());
        valores.put(transacciones.lname, lname.getText().toString());
        valores.put(transacciones.age, age.getText().toString());
        valores.put(transacciones.mail, mail.getText().toString());
        valores.put(transacciones.locate, locate.getText().toString());

        Long result = db.insert(transacciones.tablePersonas, transacciones.id, valores);

        Toast.makeText(getApplicationContext(), "Registro Ingresado: " + result.toString(), Toast.LENGTH_LONG).show();

        db.close();

        Clean();
    }

    private void Clean() {
        fname.setText("");
        lname.setText("");
        age.setText("");
        mail.setText("");
        locate.setText("");
    }
}