package app.lugcor.co.com.pruebabrm.vista;

import android.app.Activity;
import android.database.DataSetObserver;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.SearchView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import app.lugcor.co.com.pruebabrm.R;
import app.lugcor.co.com.pruebabrm.controlador.adapters.MarcaListAdapter;
import app.lugcor.co.com.pruebabrm.controlador.persistencia.MarcaCrud;

public class Marcas extends AppCompatActivity implements SearchView.OnQueryTextListener {

    ListView marcas;
    SearchView search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marcas);

        final MarcaCrud cr = new MarcaCrud(getApplicationContext());
        List<Object> marcasList = cr.obtenerTodosLosItems();
        final MarcaListAdapter marcaListAdapter= new MarcaListAdapter(this.getApplicationContext(), marcasList);
        marcas = (ListView)findViewById(R.id.listaDeMarcas);
        marcas.setAdapter(marcaListAdapter);

        marcas.setTextFilterEnabled(true);

        search = (SearchView)findViewById(R.id.search);
        setupSearchView();

        marcas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("item seleccionado", position+" ");
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
