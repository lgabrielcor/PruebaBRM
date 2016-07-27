package app.lugcor.co.com.pruebabrm.vista;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import app.lugcor.co.com.pruebabrm.R;

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
                Intent intent = new Intent(getApplicationContext(), Marcas.class);
                startActivity(intent);
            }
        });

        productos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Productos.class);
                startActivity(intent);
            }
        });
    }
}
