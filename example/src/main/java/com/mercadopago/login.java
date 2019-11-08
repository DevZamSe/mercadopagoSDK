package com.mercadopago;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.mercadopago.example.R;

public class login extends AppCompatActivity {

    Button crearcuenta;
    Button ingresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        crearcuenta = findViewById(R.id.crearcuenta);
        crearcuenta.setOnClickListener(v -> {
            Intent login = new Intent(login.this, creacuenta.class);
            startActivity(login);
        });

        ingresar = (Button) findViewById(R.id.ingresar);
        ingresar.setOnClickListener(v -> {
            Intent login = new Intent(login.this, MainActivity1.class);
            startActivity(login);
            finish();
        });

    }
}
