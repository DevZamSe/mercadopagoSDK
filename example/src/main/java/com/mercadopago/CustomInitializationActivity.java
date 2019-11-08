package com.mercadopago;

import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import com.mercadopago.android.px.core.MercadoPagoCheckout;
import com.mercadopago.example.R;

public class CustomInitializationActivity extends AppCompatActivity {

    protected static final int REQUEST_CODE = 0x01;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_initialization);

        final TextInputEditText publicKeyInput = findViewById(R.id.publicKeyInput);
        final TextInputEditText preferenceIdInput = findViewById(R.id.preferenceIdInput);
        final TextInputEditText accessTokenInput = findViewById(R.id.accessTokenInput);

        findViewById(R.id.clearButton).setOnClickListener(v -> {
            publicKeyInput.setText("");
            preferenceIdInput.setText("");
            accessTokenInput.setText("");
        });

        findViewById(R.id.startButton)
            .setOnClickListener(v -> new MercadoPagoCheckout.Builder(publicKeyInput.getText().toString(),
                preferenceIdInput.getText().toString())
                .setPrivateKey(accessTokenInput.getText().toString()).build()
                .startPayment(this, REQUEST_CODE));
    }
}
