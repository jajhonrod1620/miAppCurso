package com.example.jaimea.miappcurso;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import control.OnClickListenerJugador;
import control.TablaControlJugador;
import model.Jugador;

public class MainActivity extends AppCompatActivity {
    Button btnAcceder, btnReg;
    EditText txtNick, txtClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //btnAcceder = (Button)findViewById(R.id.btn_login);
        inicializarControles();
        btnReg = (Button)findViewById(R.id.btn_reg);
        btnReg.setOnClickListener(new OnClickListenerJugador());
        /*btnAcceder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, PantallaPrinc.class);
                startActivity(i);
            }
        });*/
    }

    private void inicializarControles() {
        txtNick = (EditText) findViewById(R.id.txt_nick);
        txtClave = (EditText) findViewById(R.id.txt_clave);
        btnAcceder = (Button) findViewById(R.id.btn_login);
    }

    public void iniciarSesion(View view) {
        if (txtNick.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(MainActivity.this, "Ingrese NickName", Toast.LENGTH_SHORT).show();
            txtNick.requestFocus();
        } else if (txtClave.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(MainActivity.this, "Ingrese Clave", Toast.LENGTH_SHORT).show();
            txtClave.requestFocus();
        } else if (this.acceder()) {
            Toast.makeText(getApplicationContext(), "Bienvenido mi App", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this, PantJuego.class);
            i.putExtra("nick", txtNick.getText().toString());
            startActivity(i);
            this.finish();

        } else {
            new AlertDialog.Builder(this)
                    .setMessage("Datos de acceso incorrectos")
                    .setCancelable(false)
                    .setPositiveButton("Intentar de nuevo", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            txtClave.setText("");
                            txtNick.requestFocus();
                        }
                    })
                    .setNegativeButton("Cerrar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            cerrarAplicacion(null);
                        }
                    })
                    .show();
        }
    }

    public void cerrarAplicacion(View view) {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setMessage("Desea Cerrar ?")
                .setCancelable(false)
                .setPositiveButton("si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private boolean acceder(){
        Jugador j=new Jugador();
        j.nick = txtNick.getText().toString();
        j.clave = txtClave.getText().toString();
        List<Jugador> jugador= new TablaControlJugador(this).acceder(j);

        if (jugador.size()>0){
            for (Jugador obj : jugador){

                int id=obj.id;
                String nombre=obj.nombre;
                String nick=obj.nick;

            }
            return  true;

        }else{
            return  false;

        }

    }
}