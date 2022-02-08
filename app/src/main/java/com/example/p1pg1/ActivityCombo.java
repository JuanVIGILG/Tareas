package com.example.p1pg1;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.p1pg1.configuraciones.SQLiteConexion;
import com.example.p1pg1.configuraciones.Transacciones;
import com.example.p1pg1.tablas.Empleados;

import java.util.ArrayList;

public class ActivityCombo extends AppCompatActivity {

    SQLiteConexion conexion;
    Spinner empleado;
    EditText nomb,ape,eda,corre;

    ArrayList<String> lista_empleados;
    ArrayList<Empleados> lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo);

        conexion= new SQLiteConexion(this, Transacciones.NameDatabase,null,1);
        empleado=(Spinner) findViewById(R.id.empleado);
        nomb=(EditText) findViewById(R.id.nomb);
        ape=(EditText) findViewById(R.id.ape);
        eda=(EditText) findViewById(R.id.eda);
        corre=(EditText) findViewById(R.id.corre);

        ObtenerListaEmpleados();

        ArrayAdapter<CharSequence> adp = new ArrayAdapter(this, android.R.layout.simple_spinner_item,lista_empleados);
        empleado.setAdapter(adp);

        empleado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long id) {
                nomb.setText(lista.get(i).getNombres());
                ape.setText(lista.get(i).getApellidos());
                eda.setText(lista.get(i).getEdad().toString());
                corre.setText(lista.get(i).getCorreo());

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void ObtenerListaEmpleados() {
        Empleados emple=null;
        lista = new ArrayList<Empleados>();

        SQLiteDatabase db = conexion.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ Transacciones.tablaempleados,null);

        while(cursor.moveToNext())
       {

           emple=new Empleados();
           emple.setId(cursor.getInt(0));
           emple.setNombres(cursor.getString(1));
           emple.setApellidos(cursor.getString(2));
           emple.setEdad(cursor.getInt(3));
           emple.setCorreo(cursor.getString(4));
           lista.add(emple);

        }
        cursor.close();

        fillCombo();
    }

    private void fillCombo() {
        lista_empleados = new ArrayList<String>();

        for (int i =0; i < lista.size(); i++)
        {

            lista_empleados.add(lista.get(i).getId() +" | "+
                    lista.get(i).getNombres() +" | "+
                    lista.get(i).getApellidos() +" | "+
                    lista.get(i).getEdad()+" | "+
                    lista.get(i).getCorreo());
        }
    }
}