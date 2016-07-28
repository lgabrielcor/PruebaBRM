package app.lugcor.co.com.pruebabrm.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.SearchView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import app.lugcor.co.com.pruebabrm.R;
import app.lugcor.co.com.pruebabrm.controlador.adapters.MarcaListAdapter;
import app.lugcor.co.com.pruebabrm.controlador.persistencia.MarcaCrud;
import app.lugcor.co.com.pruebabrm.modelo.Marca;

public class Marcas extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ListView marcas;
    SearchView search;
    Button nuevo;

    @Override
    public void onResume() {
        super.onResume();

        final MarcaCrud cr = new MarcaCrud(getApplicationContext());
        final List<Object> marcasList = cr.obtenerTodosLosItems();
        final MarcaListAdapter marcaListAdapter= new MarcaListAdapter(this.getApplicationContext(), marcasList);
        marcas = (ListView)findViewById(R.id.listaDeMarcas);
        marcas.setAdapter(marcaListAdapter);
        
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcas);

        final MarcaCrud cr = new MarcaCrud(getApplicationContext());
        final List<Object> marcasList = cr.obtenerTodosLosItems();
        final MarcaListAdapter marcaListAdapter= new MarcaListAdapter(this.getApplicationContext(), marcasList);
        marcas = (ListView)findViewById(R.id.listaDeMarcas);
        marcas.setAdapter(marcaListAdapter);

        marcas.setTextFilterEnabled(true);

        search = (SearchView)findViewById(R.id.search);
        setupSearchView();

        marcas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), EditarBorrarMarcasYListarProductos.class);
                Bundle b = new Bundle();
                b.putString("id", ((Marca)marcaListAdapter.getItem(position)).getId()+"");
                intent.putExtras(b);
                startActivity(intent);
            }
        });

        nuevo= (Button)findViewById(R.id.buttonNuevo);
        nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), nuevaMarca.class);
                startActivity(intent);
            }
        });

    }

    private void setupSearchView()
    {
        search.setIconifiedByDefault(false);
        search.setOnQueryTextListener(this);
        search.setSubmitButtonEnabled(true);
        search.setQueryHint("Buscar Marca");
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (TextUtils.isEmpty(newText)) {
            marcas.clearTextFilter();
        } else {
            marcas.setFilterText(newText);
        }
        return true;

    }
}
