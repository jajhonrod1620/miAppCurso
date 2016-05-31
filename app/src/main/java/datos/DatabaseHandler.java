package datos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by jjhon on 29/04/2016.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private  static final int DATABASE_VERSION = 1;
    protected static final String DATABASE_NAME="miAppCursoBD";

    public DatabaseHandler(Context context){
        super (context, DATABASE_NAME, null, DATABASE_VERSION);

    }
    public void onCreate(SQLiteDatabase bd){
        String sql = "CREATE TABLE jugador" +"(_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                +"nombre TEXT, "
                +"nick TEXT, "
                + "clave TEXT);";
        bd.execSQL(sql);

    }
    public  void onUpgrade (SQLiteDatabase bd, int oldVersion, int newVersion){
        String sql = "DROP TABLE IF EXISTS jugador;";
        bd.execSQL(sql);
        onCreate(bd);

    }
}