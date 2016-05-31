package control;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import datos.DatabaseHandler;
import model.Jugador;

/**
 * Created by jjhon on 5/18/16.
 */
public class TablaControlJugador extends DatabaseHandler {
    public TablaControlJugador(Context context){
        super(context);

    }
    public boolean registrarJugador (Jugador objectoJugador){
        ContentValues values = new ContentValues();

        values.put("nombre", objectoJugador.nombre);
        values.put("nick", objectoJugador.nick);
        values.put("clave", objectoJugador.clave);

        SQLiteDatabase db = this.getWritableDatabase();

        boolean fueregistrado = db.insert("jugador", null, values)>0;
        db.close();
        return fueregistrado;

    }
    public  int contar(){
        SQLiteDatabase db =this.getReadableDatabase();
        String sql ="SELECT * FROM jugador;";
        int cantidad = db.rawQuery(sql, null).getCount();
        db.close();
        return  cantidad;
    }
    public List<Jugador> acceder (Jugador jugador){
        List<Jugador> Lista = new ArrayList<Jugador>();
        String sql ="SELECT * FROM jugador WHERE nick='"+jugador.nick+"' "+"and clave='"+jugador.clave+"' ;";


        SQLiteDatabase db =this.getReadableDatabase();
        Cursor cursor =db.rawQuery(sql, null);

        if (cursor.moveToFirst()){
            //    do {
            int id = Integer.parseInt(cursor.getString(cursor.getColumnIndex("_id")));
            String nombre =cursor.getString(cursor.getColumnIndex("nombre"));
            String nick =cursor.getString(cursor.getColumnIndex("nick"));

            jugador = new Jugador();
            jugador.id=id;
            jugador.nombre=nombre;
            jugador.nick=nick;
            Lista.add(jugador);
            // }while (cursor.moveToNext());


        }
        cursor.close();
        db.close();
        return Lista;
    }
}
