package app.lugcor.co.com.pruebabrm.vista;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import app.lugcor.co.com.pruebabrm.R;
import app.lugcor.co.com.pruebabrm.controlador.persistencia.MarcaCrud;
import app.lugcor.co.com.pruebabrm.modelo.Marca;

public class nuevaMarca extends AppCompatActivity {

    Button guardar, cancelar;
    EditText marcatxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_marca);


        guardar = (Button)findViewById(R.id.guardar);
        cancelar = (Button)findViewById(R.id.cancelar);
        marcatxt = (EditText) findViewById(R.id.nuevamarcatxt);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MarcaCrud marcaCrud = new MarcaCrud(getApplicationContext());

                Marca marca = new Marca();
                marca.setNombre(marcatxt.getText().toString().trim());

                marcaCrud.insertar(marca);
                finish();
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
