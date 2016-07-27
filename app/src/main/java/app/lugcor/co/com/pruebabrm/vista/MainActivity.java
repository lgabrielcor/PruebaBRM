package app.lugcor.co.com.pruebabrm.vista;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.lugcor.co.com.pruebabrm.R;
import app.lugcor.co.com.pruebabrm.controlador.persistencia.MarcaCrud;
import app.lugcor.co.com.pruebabrm.controlador.persistencia.ProductoCrud;
import app.lugcor.co.com.pruebabrm.modelo.Marca;
import app.lugcor.co.com.pruebabrm.modelo.Producto;

public class MainActivity extends Activity {

    Button marcas, productos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        marcas = (Button)findViewById(R.id.marcabtn);
        productos=(Button)findViewById(R.id.productobtn);

        marcas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MarcaCrud cr = new MarcaCrud(getApplicationContext());
                Marca marca = new Marca();
                marca.setNombre("1");

                //insertar un item
                cr.insertar(marca);

/*                //obtener todos los items
                List<Object> marcas =  cr.obtenerTodosLosItems();

                //obtener 1 item
                Object obj = cr.ObtenerItem(((Marca)marcas.get(0)).getId()+"");

                //actualizar todos los items por ID
                List<Object> marcas2 =  cr.obtenerTodosLosItemsPorID("id",((Marca)marcas.get(0)).getId()+"");

                //borrar un item
                cr.borrarItem((Marca)marcas.get(0));*/



                Intent intent = new Intent(getApplicationContext(), Marcas.class);
                startActivity(intent);
            }
        });

        productos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ProductoCrud cr1 = new ProductoCrud(getApplicationContext());

                Producto producto = new Producto();
                producto.setNombre("producto1");
                producto.setInventario(10);
                producto.setFechaEmbarque(new Date());
                producto.setMarca(5);
                producto.setObservaciones("observaciones marca 1");
                producto.setTalla("S");

                //insertar un item
                boolean resultado = cr1.insertar(producto);

                //obtener todos los items
                List<Object> productos =  cr1.obtenerTodosLosItems();

                //obtener un item
                List<Object> productos2 =  cr1.obtenerTodosLosItemsPorID("marca", "4");

                Intent intent = new Intent(getApplicationContext(), Productos.class);
                startActivity(intent);
            }
        });
    }
}
