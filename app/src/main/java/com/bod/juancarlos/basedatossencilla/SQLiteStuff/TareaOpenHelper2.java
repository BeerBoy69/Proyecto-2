package com.bod.juancarlos.basedatossencilla.SQLiteStuff;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

public class TareaOpenHelper2 extends SQLiteOpenHelper {
    /*Permite manipular la base de datos sql*/
    public TareaOpenHelper2(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    /*Permite crear la base de datos con los elementos que al componen*/
    /*tales como el nombre de la tarea, la fecha, la hora, etc.*/
    /*Ademas el tipo de dato de las mismas*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tareas2 (id INTEGER PRIMARY KEY, nombre TEXT, fecha TEXT, hora TEXT, categoria TEXT," +
                " foto INTEGER, dificultad INTEGER,completado INTEGER)");
    }
    /*Se utiliza para actulizar la base de datos en funcion de las tareas que se realizaron*/

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
