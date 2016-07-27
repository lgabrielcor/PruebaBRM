package app.lugcor.co.com.pruebabrm.controlador.persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import app.lugcor.co.com.pruebabrm.modelo.Producto;


/**
 * Created by luisgabrielcorredorcombita on 27/07/16.
 */
public class ProductoCrud extends BaseDeDatos implements ICrud{
    SQLiteDatabase db;


    public ProductoCrud(Context context) {
        super(context);
    }

    @Override
    public boolean insertar(Object o) {
        db = getWritableDatabase();

        Producto producto = (Producto)o;

        ContentValues valores = getContentValues(producto);

        final long insertado = db.insert("producto", null, valores);

        db.close();

        return insertado>-1?true:false;
    }

    @NonNull
    private ContentValues getContentValues(Producto producto) {
        ContentValues valores = new ContentValues();
        valores.put("nombre", producto.getNombre());
        valores.put("talla", producto.getTalla());
        valores.put("observaciones", producto.getObservaciones());
        valores.put("cantidad", producto.getInventario());
        valores.put("fecha", DateFormat.getDateTimeInstance().format(producto.getFechaEmbarque()));
        valores.put("marca", producto.getMarca());
        return valores;
    }

    @Override
    public Object ObtenerItem(String id) {
        SQLiteDatabase db = getReadableDatabase();

        Producto producto = new Producto();
        String clave= "id = ?";
        String[] valor={id};

        Cursor c = db.query("producto", null,
                clave, valor, null, null, null, null);
        c.moveToFirst();

        extraerProducto(producto, c);


        return producto;
    }

    private void extraerProducto(Producto producto, Cursor c) {
        producto.setId(c.getInt(0));
        producto.setNombre(c.getString(1));
        producto.setTalla(c.getString(2));
        producto.setObservaciones(c.getString(3));
        producto.setMarca(c.getInt(4));
        producto.setInventario(c.getInt(5));
        try {
            producto.setFechaEmbarque(new SimpleDateFormat("dd/mm/yyyy hh:mm:ss").parse(c.getString(6)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Object> obtenerTodosLosItems() {

        List<Object> productos= new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.query("producto", null,
                null, null, null, null, null, null);

        if(c.getCount()>0) {
            c.moveToFirst();
            do {
                Producto producto = new Producto();

                extraerProducto(producto, c);

                productos.add(producto);
            } while (c.moveToNext());
        }
        db.close();
        c.close();


        return productos;
    }

    @Override
    public List<Object> obtenerTodosLosItemsPorID(String column, String id) {
        List<Object> productos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        String clave= column+" = ?";
        String[] valor={id};

        Cursor c = db.query("producto", null,
                clave, valor, null, null, null, null);


        if(c.getCount()>0) {
            c.moveToFirst();
            do {
                Producto producto = new Producto();

                extraerProducto(producto, c);

                productos.add(producto);
            } while (c.moveToNext());
        }
        db.close();
        c.close();

        return productos;
    }

    @Override
    public boolean actualizarItem(Object nuevo, Object anterior) {
        db = getWritableDatabase();

        int result = db.update("producto", getContentValues((Producto)nuevo),"id="+((Producto)anterior).getId(), null);
        db.close();
        return result >= 1;
    }

    @Override
    public boolean borrarItem(Object elemento) {
        db = getWritableDatabase();

        String clave= "id= ?";
        String[] valor={((Producto)elemento).getId()+""};

        int result = db.delete("producto", clave, valor);
        return result >= 1;
    }

    @Override
    public boolean borrarTodosLosItems() {
        db = getWritableDatabase();
        db.delete("producto", null, null);

        db.close();

        return true;
    }
}
