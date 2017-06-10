package com.i022213.notificaciones.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class HelperUser extends SQLiteOpenHelper {

    private static final String LOGTAG = "LOGTAG";
    public static final String DATABASE_NAME = "users";
    private static final int DATABASE_VERSION = 1;

    // -----------------------------tabla usuarios------------------------------------------------

    public static final String TABLE_USERS = "users";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String COLUMN_STATUS = "status";

    public static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_USERS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_EMAIL + " TEXT," +
                    COLUMN_USERNAME + " TEXT," +
                    COLUMN_PASSWORD + " TEXT," +
                    COLUMN_STATUS + " TEXT" +
                    ")";
   // -----------------------------tabla empresas------------------------------------------------

    public static final String TABLE_EMPRESAS = "empresas";
    public static final String COLUMN_EMPRESA = "item_b";
    public static final String COLUMN_NIT = "nit";

    public static final String TABLE_CREATE_EMPRESAS=
            "CREATE TABLE " + TABLE_EMPRESAS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_EMPRESA+ " TEXT, " +
                    COLUMN_NIT + " TEXT" +
                    ")";
    // -----------------------------tabla markador-empresa-usuario------------------------------------------------

    public static final String TABLE_MARK_EMPRESAS_USERS="markEmpresasUsers";
    public static final String COLUMN_ID_USER = "idUser";
    public static final String COLUMN_ID_EMPRESA = "idEmpresa";

    public static final String TABLE_CREATE_MARK =
            "CREATE TABLE " + TABLE_MARK_EMPRESAS_USERS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_ID_USER + " INTEGER, " +
                    COLUMN_ID_EMPRESA + " INTEGER" +
                    ")";

    public HelperUser(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        db.execSQL(TABLE_CREATE_EMPRESAS);
        db.execSQL(TABLE_CREATE_MARK);
        Log.i(LOGTAG, "Tabla de usuarios creada correctamente.");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_EMPRESAS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_MARK_EMPRESAS_USERS);
        onCreate(db);
    }
}
