package app.lugcor.co.com.pruebabrm.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.List;

import app.lugcor.co.com.pruebabrm.R;
import app.lugcor.co.com.pruebabrm.controlador.adapters.ProductoListAdapter;
import app.lugcor.co.com.pruebabrm.controlador.persistencia.MarcaCrud;
import app.lugcor.co.com.pruebabrm.controlador.persistencia.ProductoCrud;
import app.lugcor.co.com.pruebabrm.modelo.Marca;
import app.lugcor.co.com.pruebabrm.modelo.Producto;

public class EditarBorrarMarcasYListarProductos extends AppCompatActivity  implements SearchView.OnQueryTextListener {

    EditText nombre;
    ListView listaProductos;
    Button guardar, eliminar, nuevoProducto;
    SearchView searchViewProductos;
    String Value ="";

    @Override
    public void onResume() {
        super.onResume();

        /*final ProductoCrud cr = new ProductoCrud(getApplicationContext());
        final List<Object> productoList = cr.obtenerTodosLosItems();
        final ProductoListAdapter marcaListAdapter= new ProductoListAdapter(this.getApplicationContext(), productoList);
        listaProductos = (ListView)findViewById(R.id.listaDeMarcas);
        listaProductos.setAdapter(marcaListAdapter);*/

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_borrar_marca);

        Bundle bundle = getIntent().getExtras();
        Value = bundle.getString("id");

        nombre=(EditText)findViewById(R.id.nombretxt);
        guardar=(Button)findViewById(R.id.guardarbtn);
        eliminar=(Button)findViewById(R.id.eliminarbtn);
        nuevoProducto=(Button)findViewById(R.id.nuevoProducto);

        final MarcaCrud marcaCrud= new MarcaCrud(this.getApplicationContext());

        final Marca marca = (Marca)marcaCrud.ObtenerItem(Value);

        nombre.setText(marca.getNombre());

        //obtener los productos del id de la marca
        ProductoCrud prc = new ProductoCrud(this.getApplicationContext());
        List<Object> listProductos = prc.obtenerTodosLosItemsPorID("id", Value);
        final ProductoListAdapter productoListAdapter= new ProductoListAdapter(this.getApplicationContext(), listProductos);
        listaProductos = (ListView)findViewById(R.id.listViewProductos);
        listaProductos.setAdapter(productoListAdapter);
        listaProductos.setTextFilterEnabled(true);
        listaProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent = new Intent(getApplicationContext(), Productos.class);
                Bundle b = new Bundle();
                b.putString("idProducto", ((Producto)productoListAdapter.getItem(position)).getId()+"");
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        //inicializar el searchview
        searchViewProductos = (SearchView)findViewById(R.id.searchViewProductos);
        setupSearchView();



        eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                marcaCrud.borrarItem(marca);
                finish();
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Marca nuevo = new Marca();
                nuevo.setId(marca.getId());
                nuevo.setNombre(nombre.getText().toString().trim());
                marcaCrud.actualizarItem(nuevo, marca);
                finish();
            }
        });

        nuevoProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Productos.class);
                Bundle b = new Bundle();
                b.putString("idMarca", Value);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }

    private void setupSearchView()
    {
        searchViewProductos.setIconifiedByDefault(false);
        searchViewProductos.setOnQueryTextListener(this);
        searchViewProductos.setSubmitButtonEnabled(true);
        searchViewProductos.setQueryHint("Buscar Productos de la marca");
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
