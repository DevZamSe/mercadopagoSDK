package com.mercadopago;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mercadopago.android.px.internal.view.MPButton;
import com.mercadopago.android.px.utils.ExamplesUtils;
import com.mercadopago.example.R;
import com.squareup.picasso.Picasso;

public class DatosActivity extends AppCompatActivity {

    TextView tvnombre, tvvendedor, tvprecio, tvventas,tvdescripcion;
    ImageView imageView;
    private MPButton button;
    Bundle bundle;
    Modelo mod = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos);

        tvnombre = (TextView) findViewById(R.id.textView5);
        tvvendedor = (TextView) findViewById(R.id.textView4);
        tvdescripcion = (TextView) findViewById(R.id.textView7);
        tvprecio = (TextView) findViewById(R.id.tvprecio);

        tvventas = (TextView) findViewById(R.id.textView9);

        imageView = (ImageView) findViewById(R.id.imageView);

        button = findViewById(R.id.button4);

        bundle = getIntent().getExtras();
        mod = (Modelo) bundle.getSerializable("objeto");
        tvnombre.setText(mod.getNombre());
        tvvendedor.setText(mod.getVendedor());
        tvdescripcion.setText(mod.getDescripcion());
        tvprecio.setText(mod.getPrecio());
        //tvubicacion.setText(mod.getUbicacion());
        tvventas.setText(mod.getVentas()+"");
        Picasso.with(this).load(mod.getUrl()).into(imageView);

        WebView view = findViewById(R.id.webView);

        view.getSettings().setAllowFileAccessFromFileURLs(true);
        view.getSettings().setBuiltInZoomControls(true);
        view.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        view.getSettings().setDomStorageEnabled(true);
        view.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        view.getSettings().setJavaScriptEnabled(true);
        view.getSettings().setDisplayZoomControls(false);
        view.getSettings().setBuiltInZoomControls(true);
        view.loadUrl("https://sharp-mccarthy-f50d52.netlify.com/");

        view.setWebViewClient(new WebViewClient(){
            public boolean shouldOverriceUrlLoading (WebView view, String url){
                return false;
            }
        });

        WebView view1 = findViewById(R.id.webView2);

        view1.getSettings().setAllowFileAccessFromFileURLs(true);
        view1.getSettings().setBuiltInZoomControls(true);
        view1.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        view1.getSettings().setDomStorageEnabled(true);
        view1.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        view1.getSettings().setJavaScriptEnabled(true);
        view1.getSettings().setDisplayZoomControls(false);
        view1.getSettings().setBuiltInZoomControls(true);
        view1.loadUrl("https://assistant-chat-us-south.watsonplatform.net/web/public/5b861af8-c741-4390-af6f-39bd76449300");

        view.setWebViewClient(new WebViewClient(){
            public boolean shouldOverriceUrlLoading (WebView view, String url){
                return false;
            }
        });

        button.setOnClickListener(
                v -> ExamplesUtils.createBase().build().startPayment(DatosActivity.this, 1));
    }
}
