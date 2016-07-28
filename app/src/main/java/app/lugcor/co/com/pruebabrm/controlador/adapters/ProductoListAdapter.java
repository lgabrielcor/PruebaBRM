package app.lugcor.co.com.pruebabrm.controlador.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.lugcor.co.com.pruebabrm.R;
import app.lugcor.co.com.pruebabrm.controlador.persistencia.MarcaCrud;
import app.lugcor.co.com.pruebabrm.modelo.Marca;
import app.lugcor.co.com.pruebabrm.modelo.Producto;

/**
 * Created by luisgabrielcorredorcombita on 27/07/16.
 */
public class ProductoListAdapter extends BaseAdapter implements Filterable {

    public ArrayList<Producto> productoArrayList;
    public ArrayList<Producto> orig;
    public Context context;

    public ProductoListAdapter(Context context, List<?> objects){
        super();
        this.context=context;
        this.productoArrayList = (ArrayList<Producto>) objects;
    }

    public class ProductoHolder
    {

        TextView nombre, marca;

    }

    @Override
    public int getCount() {
        return this.productoArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.productoArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.productoArrayList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProductoHolder productoHolder;

        if (null == convertView) { //Si no existe, entonces inflarlo
            //view = inflater.inflate(R.layout.itemmarca, parent, false);
            convertView= LayoutInflater.from(context).inflate(R.layout.itemproducto, parent, false);
            productoHolder = new ProductoHolder();

            productoHolder.nombre =(TextView)convertView.findViewById(R.id.nombreProducto);
            productoHolder.marca =(TextView)convertView.findViewById(R.id.productoId);


            convertView.setTag(productoHolder);

        }else
        {
            productoHolder=(ProductoHolder) convertView.getTag();
        }

        Producto producto = (Producto)getItem(position);

        productoHolder.nombre.setText(producto.getNombre());
        //productoHolder.id.setText(producto.getId()+"");


        MarcaCrud marcaCrud = new MarcaCrud(context);
        Marca marca = (Marca)marcaCrud.ObtenerItem(producto.getMarca()+"");
        productoHolder.marca.setText("Id: "+producto.getId()+" Marca:"+marca.getNombre());

        return convertView;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {return new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            final FilterResults oReturn = new FilterResults();
            final ArrayList<Producto> results = new ArrayList<Producto>();

            if (orig == null)
                orig = productoArrayList;

            if (constraint != null) {
                if (orig != null && orig.size() > 0) {
                    for (final Producto g : orig) {
                        if (g.getNombre().toLowerCase()
                                .contains(constraint.toString()))
                            results.add(g);
                    }
                }
                oReturn.values = results;
            }
            return oReturn;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            productoArrayList = (ArrayList<Producto>) results.values;
            notifyDataSetChanged();
        }
    };
    }
}
