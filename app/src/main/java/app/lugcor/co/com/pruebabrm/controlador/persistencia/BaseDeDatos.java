package app.lugcor.co.com.pruebabrm.controlador.persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by luisgabrielcorredorcombita on 27/07/16.
 */
public class BaseDeDatos extends SQLiteOpenHelper {

    String creadorMarca= " CREATE TABLE `marca` (\n" +
            "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "\t`nombre`\tTEXT NOT NULL\n" +
            ");";

    String creadorProducto= " CREATE TABLE `producto` (\n" +
            "\t`id`\tINTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,\n" +
            "\t`nombre`\tTEXT NOT NULL,\n" +
            "\t`talla`\tTEXT NOT NULL,\n" +
            "\t`observaciones`\tTEXT NOT NULL,\n" +
            "\t`marca`\tNUMBER NOT NULL,\n" +
            "\t`cantidad`\tNUMBER NOT NULL,\n" +
            "\t`fecha`\tDATE NOT NULL\n" +
            ");";

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "cacheCatalogo.sqlite";
    SQLiteDatabase db;

    public BaseDeDatos(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(creadorMarca);
        db.execSQL(creadorProducto);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
