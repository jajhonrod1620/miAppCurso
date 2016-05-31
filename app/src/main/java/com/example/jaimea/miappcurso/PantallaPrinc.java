package com.example.jaimea.miappcurso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PantallaPrinc extends AppCompatActivity {
    Button jugar;
    TextView txt_nick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_princ);
        jugar = (Button)findViewById(R.id.btn_jugar);
        inicializarCampos();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            txt_nick.setText(bundle.getString("nick"));
        } else {
            txt_nick.setText("");
        }
        jugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PantallaPrinc.this, PantJuego.class);
                startActivity(i);
            }
        });
    }

    private void inicializarCampos() {
        txt_nick = (TextView) findViewById(R.id.txt_nick);
    }


}
