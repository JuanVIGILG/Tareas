package com.example.p1pg1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.p1pg1.configuraciones.SQLiteConexion;
import com.example.p1pg1.tablas.Empleados;

import java.util.ArrayList;

public class ActivityEditar extends AppCompatActivity {

    EditText nombres;
    EditText apellidos;
    EditText edad;
    EditText correo;
    Button btnActualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        nombres = (EditText) findViewById(R.id.nombres);
        apellidos = (EditText) findViewById(R.id.apellidos);
        edad = (EditText) findViewById(R.id.edad);
        correo = (EditText) findViewById((R.id.correo));

        String codigo = getIntent().getStringExtra("codigo");
        String nombre = getIntent().getStringExtra("nombres");
        String apellido = getIntent().getStringExtra("apellidos");
        String edads = getIntent().getStringExtra("edad");
        String corre = getIntent().getStringExtra("correo");

        nombres.setText(nombre);
        apellidos.setText(apellido);
        edad.setText(edads);
        correo.setText(corre);

        System.out.println(codigo);
    }
}

