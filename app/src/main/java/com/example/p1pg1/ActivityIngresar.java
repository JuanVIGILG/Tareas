package com.example.p1pg1;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.p1pg1.configuraciones.SQLiteConexion;
import com.example.p1pg1.configuraciones.Transacciones;

public class ActivityIngresar extends AppCompatActivity {

    EditText nombres, apellidos, edad, correo;
    Button btnadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);

        nombres = (EditText) findViewById(R.id.txtnombres);
        apellidos = (EditText) findViewById(R.id.txtapellido);
        edad = (EditText) findViewById(R.id.txtedad);
        correo = (EditText) findViewById(R.id.txtcorreo);
        btnadd = (Button) findViewById(R.id.btnadd);

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                AgregarPersonas();

            }
        });

    }

    private void AgregarPersonas()
    {
        SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        SQLiteDatabase db = conexion.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombres, nombres.getText().toString());
        valores.put(Transacciones.apellidos,apellidos.getText().toString());
        valores.put(Transacciones.edad,edad.getText().toString());
        valores.put(Transacciones.correo,correo.getText().toString());

        long resultado = db.insert(Transacciones.tablaempleados, Transacciones.id, valores);

        Toast.makeText(getApplicationContext(), String.format("Registro ingreso con exito!! Codigo " + resultado, toString()),
                Toast.LENGTH_LONG).show();

        db.close();

        LimpiarPantalla();
    }

    private void LimpiarPantalla()
    {
        nombres.setText("");
        apellidos.setText("");
        edad.setText("");
        correo.setText("");
    }
}