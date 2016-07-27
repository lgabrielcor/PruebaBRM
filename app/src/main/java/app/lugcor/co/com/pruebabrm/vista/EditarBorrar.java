package app.lugcor.co.com.pruebabrm.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import app.lugcor.co.com.pruebabrm.R;
import app.lugcor.co.com.pruebabrm.controlador.persistencia.MarcaCrud;
import app.lugcor.co.com.pruebabrm.modelo.Marca;

public class EditarBorrar extends AppCompatActivity {

    EditText nombre;

    Button guardar, eliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_borrar);

        Bundle bundle = getIntent().getExtras();
        String Value = bundle.getString("id");

        nombre=(EditText)findViewById(R.id.nombretxt);
        guardar=(Button)findViewById(R.id.guardarbtn);
        eliminar=(Button)findViewById(R.id.eliminarbtn);

        final MarcaCrud marcaCrud= new MarcaCrud(this.getApplicationContext());
        final Marca marca = (Marca)marcaCrud.ObtenerItem(Value);

        nombre.setText(marca.getNombre());

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


    }
}
