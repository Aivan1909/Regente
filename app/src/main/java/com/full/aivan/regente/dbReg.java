package com.full.aivan.regente;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by usuario on 18/10/2017.
 */

public class dbReg extends SQLiteOpenHelper{

    //la base de datos
    private static final String DB_NAME = "dbRegente.sqlite";
    //version del esquema de la base de datos
    private static final int DB_SCHEME_VERSION = 1;

    public dbReg(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, nombre, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("");
    }
}