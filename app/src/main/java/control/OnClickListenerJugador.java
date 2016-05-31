package control;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.jaimea.miappcurso.R;

import model.Jugador;

/**
 * Created by jaimea on 5/18/16.
 */
public class OnClickListenerJugador implements View.OnClickListener {
    public void  onClick(View view){

        final Context context =view.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View elementosformulario = inflater.inflate(R.layout.formguardarbd,null, false);

        final EditText txt_nombre =(EditText)elementosformulario.findViewById(R.id.txt_nombre);
        final EditText txt_nick =(EditText)elementosformulario.findViewById(R.id.txt_nick);
        final EditText txt_clave =(EditText)elementosformulario.findViewById(R.id.txt_clave);
        final EditText txt_clave_confirm =(EditText)elementosformulario.findViewById(R.id.txt_clave2);

        new AlertDialog.Builder(context)
                .setView(elementosformulario)
                .setTitle("Registrar Nuevo Jugador")
                .setPositiveButton("Registrar",
                        new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id){
                                //dialog.cancel();
                                Jugador jugador = new Jugador();
                                jugador.nombre=txt_nombre.getText().toString();
                                jugador.nick=txt_nick.getText().toString();
                                jugador.clave=txt_clave.getText().toString();
                                boolean fueregistrado = new TablaControlJugador(context).registrarJugador(jugador);
                                if (fueregistrado){
                                    Toast.makeText(context, "Jugador Registrado correctamente.",Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(context, "Ya Existe Un Jugador con Ese Nick.",Toast.LENGTH_SHORT ).show();
                                }


                            }

                        })
                .setNegativeButton("Cancelar",null)
                .show();
    }
}
