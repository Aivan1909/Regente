package com.full.aivan.regente;

/**
 * Created by usuario on 19/10/2017.
 */

public class dbManagerReg {
    public static final String TABLE_NAME = "estudiante";

    public static final String CN_ID = "_id";
    public static final String CN_NAME = "nombre";
    public static final String CN_PHONE = "telefono";

    public static final String CREATE_TABLE = "create table " +TABLE_NAME+ " ("
            + CN_ID + " text primary key autoincrement,"
            + CN_NAME + " text not null,"
            + CN_PHONE + " text);";
}
