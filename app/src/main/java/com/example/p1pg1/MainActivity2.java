package com.example.p1pg1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity2 extends AppCompatActivity {

    Button btnregistro,btnactualizar,btnlista,btncombo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        btnregistro = (Button) findViewById(R.id.btnregistro);
        btnactualizar = (Button) findViewById(R.id.btnactualizar);
        btnlista = (Button) findViewById(R.id.btnlista);
        btncombo = (Button) findViewById(R.id.btncombo);

        btnregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), ActivityIngresar.class);
                startActivity(intent);
            }
        });
        btnactualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), ActivityConsulta.class);
                startActivity(intent);
            }
        });
        btnlista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(getApplicationContext(), ActivityListView.class);
                startActivity(intent);
            }
        });

        btncombo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ActivityCombo.class);
                startActivity(intent);
            }
        });
    }
}