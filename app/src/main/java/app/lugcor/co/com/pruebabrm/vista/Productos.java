package app.lugcor.co.com.pruebabrm.vista;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import app.lugcor.co.com.pruebabrm.R;
import app.lugcor.co.com.pruebabrm.controlador.persistencia.MarcaCrud;
import app.lugcor.co.com.pruebabrm.controlador.persistencia.ProductoCrud;
import app.lugcor.co.com.pruebabrm.modelo.Marca;
import app.lugcor.co.com.pruebabrm.modelo.Producto;

public class Productos extends AppCompatActivity {

    EditText marcaProducto, nombreProducto, TallaProducto, observaciones, inventario;
    TextView fecha;
    Button guardarProductobtn, borrarBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        Bundle bundle = getIntent().getExtras();
        String Value = bundle.getString("idProducto");

        MarcaCrud marcaCrud = new MarcaCrud(getApplicationContext());
        final Marca marca = (Marca)marcaCrud.ObtenerItem(Value);

        marcaProducto = (EditText)findViewById(R.id.marcaProducto);
        marcaProducto.setHint("Marca");
        marcaProducto.setText("Marca: "+marca.getNombre());

        nombreProducto = (EditText)findViewById(R.id.nombreProducto);
        TallaProducto = (EditText)findViewById(R.id.tallaProducto);
        observaciones = (EditText)findViewById(R.id.observacionesProducto);
        inventario = (EditText)findViewById(R.id.inventarioProducto);
        fecha = (TextView)findViewById(R.id.fechaEmbarqueProducto);
        fecha.setText(java.text.DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime()));
        guardarProductobtn = (Button)findViewById(R.id.guardarProductobtn);
        guardarProductobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Producto producto = new Producto();
                producto.setNombre(nombreProducto.getText().toString().trim());
                producto.setInventario(Integer.parseInt(inventario.getText().toString().trim()));
                producto.setTalla(TallaProducto.getText().toString().trim());
                producto.setObservaciones(observaciones.getText().toString().trim());
                producto.setFechaEmbarque(new Date());
                producto.setMarca(marca.getId());
                ProductoCrud productoCrud = new ProductoCrud(getApplicationContext());
                productoCrud.insertar(producto);
                finish();
            }
        });

        borrarBtn = (Button)findViewById(R.id.borrarBtn);
        borrarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
