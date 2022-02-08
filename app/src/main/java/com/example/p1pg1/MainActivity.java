package com.example.p1pg1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Button btnagregar, btnpasar;
    EditText txtnombres;
    EditText txtapellidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnagregar = (Button) findViewById(R.id.btnagregar);
        btnpasar = (Button) findViewById(R.id.btnpasar);
        txtnombres = (EditText) findViewById(R.id.txtnombre);
        txtapellidos = (EditText) findViewById(R.id.txtapellidos);


        btnagregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Bienvenidos "+txtnombres.getText().toString(), Toast.LENGTH_LONG).show();
            }
        });

        btnpasar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), MainActivity2.class);
                startActivity(intent);
            }
        });
    }
}