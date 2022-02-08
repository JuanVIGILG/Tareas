package com.example.p1pg1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.ContentValues;


import com.example.p1pg1.configuraciones.SQLiteConexion;
import com.example.p1pg1.configuraciones.Transacciones;

public class ActivityConsulta extends AppCompatActivity {

    SQLiteConexion conexion;
    EditText id, nombres, apellidos, edad, correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        Button btnconsulta = (Button) findViewById(R.id.btnconsulta);

        //Boton para actualizar
        Button btnactual = (Button) findViewById(R.id.btnactual);


        id = (EditText) findViewById(R.id.codigofind);
        nombres = (EditText) findViewById(R.id.txtnom);
        apellidos = (EditText) findViewById(R.id.txtape);
        edad = (EditText) findViewById(R.id.txtage);
        correo = (EditText) findViewById(R.id.txtemail);

        btnconsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Buscar();
            }
        });

        btnactual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Actualizar();
            }
        });

    }

    private void Buscar() {
        SQLiteDatabase db = conexion.getWritableDatabase();

        //parametros de consuta para realizar la sentencia select

        String [] params = {id.getText().toString()};
        String [] fields = {Transacciones.nombres,
                            Transacciones.apellidos,
                            Transacciones.edad, Transacciones.correo};

        String whereCondition = Transacciones.id + "=?";

        try
        {
            Cursor cdata = db.query(Transacciones.tablaempleados,fields,whereCondition,params,null,null,null);
            cdata.moveToFirst();

            nombres.setText(cdata.getString(0));
            apellidos.setText(cdata.getString(1));
            edad.setText(cdata.getString(2));
            correo.setText(cdata.getString(3));

            Toast.makeText(getApplicationContext(), "Consulta con exito", Toast.LENGTH_LONG).show();
        }
        catch (Exception ex)
        {
            Toast.makeText(getApplicationContext(), "Elemento no encontrado", Toast.LENGTH_LONG).show();
        }

    }
    // Funcion para actualizar los datos de una persona
    private void Actualizar() {
        SQLiteDatabase db = conexion.getWritableDatabase();
        String [] params = {id.getText().toString()};

        ContentValues valores = new ContentValues();
        valores.put(Transacciones.nombres, nombres.getText().toString());
        valores.put(Transacciones.apellidos, apellidos.getText().toString());
        valores.put(Transacciones.edad, edad.getText().toString());
        valores.put(Transacciones.correo, correo.getText().toString());

        db.update(Transacciones.tablaempleados, valores, Transacciones.id + "=?", params);
        Toast.makeText(getApplicationContext(), "Dato Actualizado", Toast.LENGTH_LONG).show();
        ClearScreen();

    }
    //Funcion para limpiar las cajas de texto
    private void ClearScreen() {
        id.setText("");
        nombres.setText("");
        apellidos.setText("");
        edad.setText("");
        correo.setText("");
    }


}