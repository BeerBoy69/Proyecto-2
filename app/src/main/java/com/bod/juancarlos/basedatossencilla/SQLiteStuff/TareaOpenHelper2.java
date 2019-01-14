package com.bod.juancarlos.basedatossencilla.SQLiteStuff;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;

public class TareaOpenHelper2 extends SQLiteOpenHelper {

    public TareaOpenHelper2(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE tareas2 (id INTEGER PRIMARY KEY, nombre TEXT, fecha TEXT, hora TEXT, categoria TEXT," +
                " foto INTEGER, dificultad INTEGER,completado INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
