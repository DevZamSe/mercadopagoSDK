package com.mercadopago;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.mercadopago.example.R;

import java.util.ArrayList;
import java.util.List;

public class  MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerViewAdapter adaptador;
    public static List<Modelo> lista = new ArrayList<>();

    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    ImageButton button3;
    TextView gaa;

    public String dato = "datos";
    Contador cont = new Contador();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /*
        for(int i=0;i<7;i++){
            Modelo modelo1 = new Modelo();
            String id = databaseReference.push().getKey();
            modelo1.setId(id);
            modelo1.setNombre("Polo");
            modelo1.setVendedor("vendedor");
            modelo1.setUrl("");
            modelo1.setUbicacion("");
            if(i==1 || i==3 || i== 5){
                modelo1.setDescripcion("Polo talla L");
            } else{
                modelo1.setDescripcion("Polo talla M");
            }
            modelo1.setVentas(i+1);
            databaseReference.child("Lista").child("Polos").child(id).setValue(modelo1);
        }
        */


        gaa = findViewById(R.id.gaa);

        adaptador = new RecyclerViewAdapter(lista);
        recyclerView.setAdapter(adaptador);

        Bundle parametros = this.getIntent().getExtras();
        if(parametros != null) {
            dato = parametros.getString("clave");
            gaa.setText(dato);}

        listarFb();

        button3 = findViewById(R.id.button3);
        button3.setOnClickListener(v -> {
            Intent login = new Intent(MainActivity.this, MainActivity1.class);
            startActivity(login);
        });

        contador();
        cantVistas();

        adaptador.setOnItemClickListener(position -> {
            Modelo mod;
            mod = lista.get(position);
            Intent intent = new Intent(MainActivity.this, DatosActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("objeto",mod);
            intent.putExtras(bundle);
            startActivity(intent);
        });
    }

    public void contador(){

        if(dato != "datos"){
            String idcel = Settings.Secure.getString(getBaseContext().getContentResolver(), Settings.Secure.ANDROID_ID);
            cont.setId(idcel);
            databaseReference.child("Contador").child(dato).child("usuarios").push().setValue(cont);
        }
    }

    public void cantVistas(){
        if(dato != "datos") {

            final List<Contador> tmpcont = new ArrayList<>();
            databaseReference.child("Contador").child(dato).child("usuarios").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    tmpcont.clear();
                    for (DataSnapshot objsnapshot : dataSnapshot.getChildren()) {
                         Contador cont = objsnapshot.getValue(Contador.class);
                         tmpcont.add(cont);
                    }
                    databaseReference.child("Contador").child(dato).child("cantidad").setValue(tmpcont.size());
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    public void listarFb(){
        if(dato!="datos"){
            databaseReference.child("Lista").child(dato).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    lista.clear();
                    for (DataSnapshot objSnapshot : dataSnapshot.getChildren()){
                        Modelo c = objSnapshot.getValue(Modelo.class);
                        lista.add(c);
                    }
                    recyclerView.setAdapter(adaptador);
                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                }
            });
        }
    }
}
