package app.lugcor.co.com.pruebabrm.controlador.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import app.lugcor.co.com.pruebabrm.modelo.Marca;

/**
 * Created by luisgabrielcorredorcombita on 27/07/16.
 */
public class MarcaCrud extends BaseDeDatos implements ICrud{

    SQLiteDatabase db;

    public MarcaCrud(Context context) {
        super(context);
    }

    @Override
    public boolean insertar(Object o) {
        db = getWritableDatabase();
        ContentValues valores = new ContentValues();
        Marca marca = (Marca)o;
        valores.put("nombre", marca.getNombre());

        final long insertado = db.insert("marca", null, valores);
        db.close();
        return insertado>-1?true:false;
    }

    @Override
    public Object ObtenerItem(String id) {
        SQLiteDatabase db = getReadableDatabase();
        Marca marca = new Marca();

        String clave= "id = ?";
        String[] valor={id};

        Cursor c = db.query("marca", null,
                clave, valor, null, null, null, null);

        c.moveToFirst();
        marca.setId(c.getInt(0));
        marca.setNombre(c.getString(1));

        db.close();
        c.close();
        return marca;
    }

    @Override
    public List<Object> obtenerTodosLosItems() {
        List<Object> marcas = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.query("marca", null,
                null, null, null, null, null, null);


        if(c.getCount()>0) {
            c.moveToFirst();
            do {
                Marca marca = new Marca();

                marca.setId(c.getInt(0));
                marca.setNombre(c.getString(1));

                marcas.add(marca);
            } while (c.moveToNext());
        }
        db.close();
        c.close();

        return marcas;
    }

    @Override
    public List<Object> obtenerTodosLosItemsPorID(String column, String id) {
        List<Object> marcas = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String clave= column+" = ?";
        String[] valor={id};

        Cursor c = db.query("marca", null,
                clave, valor, null, null, null, null);


        if(c.getCount()>0) {
            c.moveToFirst();
            do {
                Marca marca = new Marca();

                marca.setId(c.getInt(0));
                marca.setNombre(c.getString(1));

                marcas.add(marca);
            } while (c.moveToNext());
        }
        db.close();
        c.close();

        return marcas;
    }

    @Override
    public boolean actualizarItem(Object nuevo, Object anterior) {

        db = getWritableDatabase();

        Marca marca = (Marca)nuevo;

        ContentValues valores = new ContentValues();
        valores.put("nombre", marca.getNombre());

        int result = db.update("marca", valores,"id="+((Marca)anterior).getId(), null);
        db.close();
        return result >= 1;
    }

    @Override
    public boolean borrarItem(Object elemento) {
        db = getWritableDatabase();

        String clave= "id= ?";
        String[] valor={((Marca)elemento).getId()+""};

        Marca marca = (Marca)elemento;
        int result = db.delete("marca", clave, valor);
        return result >= 1;
    }

    @Override
    public boolean borrarTodosLosItems() {

        db = getWritableDatabase();
        db.delete("marca", null, null);

        db.close();

        return true;
    }
}
