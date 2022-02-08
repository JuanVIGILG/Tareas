package com.example.p1pg1;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.p1pg1.configuraciones.SQLiteConexion;
import com.example.p1pg1.configuraciones.Transacciones;
import com.example.p1pg1.tablas.Empleados;

import java.io.Serializable;
import java.util.ArrayList;

public class ActivityListView extends AppCompatActivity {

    //Variables globales  -Tarea de custom adapter con un extra data a la pantalla de administracion

    SQLiteConexion conexion;
    ListView lista;
    ArrayList<Empleados> listaempleados;
    ArrayList<String> ArregloEmpleados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase,null,1);

        lista = (ListView) findViewById(R.id.lista);

        ObtenerListaEmpleados();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, ArregloEmpleados);
        lista.setAdapter(adp);

        //seleccionar un registro de la lista y mandarlo a actualizar

       lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l){

                obtenerobjeto(i);
            }
        });

    }

    private void obtenerobjeto(int id) {
        Empleados emp = listaempleados.get(id);

        Intent intent = new Intent(getApplicationContext(), ActivityEditar.class);

        intent.putExtra("codigo",emp.getId());
        intent.putExtra("nombres",emp.getNombres());
        intent.putExtra("apellidos",emp.getApellidos());
        intent.putExtra("edad",emp.getEdad()+"");
        intent.putExtra("correo",emp.getCorreo());

        startActivity(intent);

    }

    private void ObtenerListaEmpleados()
    {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Empleados list_emple = null;
        listaempleados = new ArrayList<Empleados>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+Transacciones.tablaempleados, null);

        while (cursor.moveToNext()){
            list_emple = new Empleados();
            list_emple.setId(cursor.getInt(0));
            list_emple.setNombres(cursor.getString(1));
            list_emple.setApellidos(cursor.getString(2));
            list_emple.setEdad(cursor.getInt(3));
            list_emple.setCorreo(cursor.getString(4));

            listaempleados.add(list_emple);
        }

        cursor.close();

        llenarlista();
    }

    private void llenarlista()
    {
        ArregloEmpleados = new ArrayList<String>();

        for (int i=0; i<listaempleados.size();i++)
        {
            ArregloEmpleados.add(listaempleados.get(i).getId()+" | "+
                    listaempleados.get(i).getNombres() + " | "+
                    listaempleados.get(i).getApellidos() +" | "+
                    listaempleados.get(i).getEdad() +" | "+
                    listaempleados.get(i).getCorreo());
        }
    }

}