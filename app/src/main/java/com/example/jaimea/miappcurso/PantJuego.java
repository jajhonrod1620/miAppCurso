package com.example.jaimea.miappcurso;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Time;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class PantJuego extends AppCompatActivity {
    String vector_colores[]={"Azul","Verde","Rojo","Amarillo"};
    TextView tiempo,texto_colores,tiempo_mili, txtUsuario;

    CountDownTimer cuenta_atras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pant_juego);
        tiempo = (TextView) findViewById(R.id.txt_tiempo);
        texto_colores = (TextView) findViewById(R.id.color_texto);
        inicializarCampos();
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            txtUsuario.setText(bundle.getString("nick"));
        } else {
            txtUsuario.setText("");
        }
        iniciar();
        //verTiempo = (TextView) findViewById(R.id.txt_tiempo);
        //final ContadorClass crono = new ContadorClass(5000,1000);
        //crono.start();
    }

    private void inicializarCampos() {
        txtUsuario = (TextView) findViewById(R.id.txt_usuario);
    }

    int intentos=5;
    private void iniciar() {
        if (intentos > 0) {
            tiempo.setTextColor(getResources().getColor(R.color.azul));
            cambiarTexto(texto_colores);
            cuenta_atras = new CountDownTimer(5000, 1000) {
                public void onTick(long mili) {
                    long v = mili / 1000;
                    tiempo.setText("" + v);
                    if (v == 0) {
                        texto_colores.setText("");
                        tiempo.setTextColor(getResources().getColor(R.color.gris1));
                        cambiarColorTexto(texto_colores);
                    }
                }

                @Override
                public void onFinish() {
                    iniciar();
                }

            }.start();
        }else{
            intentos=5;
        }
    }

    private void cambiarColorTexto(TextView colortexto){
        int max=3;
        switch (new Random().nextInt(max)){
            case 0: colortexto.setTextColor(getResources().getColor(R.color.azul));
                break;
            case 1: colortexto.setTextColor(getResources().getColor(R.color.verde));
                break;
            case 2: colortexto.setTextColor(getResources().getColor(R.color.rojo));
                break;
            case 3: colortexto.setTextColor(getResources().getColor(R.color.amarillo));
                break;
        }

    }

    private void cambiarTexto(TextView texto){
        texto.setText(vector_colores[new Random().nextInt(4)]);
    }
}