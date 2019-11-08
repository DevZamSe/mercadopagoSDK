package com.mercadopago;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mercadopago.example.R;

public class EnviarActivity extends AppCompatActivity {

    EditText txtnombre;
    Button button;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enviar);

        txtnombre = (EditText) findViewById(R.id.txtnombre);
        button = (Button) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.text_view_main);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {/*
                String nombre = getString(findViewById(R.id.text_view_main);
                Intent intent = new Intent(EnviarActivity.this,MainActivity.class);
                intent.putExtra("clave",nombre);
                startActivity(intent);*/
            }
        });
    }
}
