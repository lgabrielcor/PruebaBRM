package app.lugcor.co.com.pruebabrm.controlador.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.lugcor.co.com.pruebabrm.R;
import app.lugcor.co.com.pruebabrm.modelo.Marca;

/**
 * Created by luisgabrielcorredorcombita on 27/07/16.
 */
public class MarcaListAdapter extends BaseAdapter implements Filterable {


    public ArrayList<Marca> marcaArrayList;
    public ArrayList<Marca> orig;
    public Context context;

    public MarcaListAdapter(Context context, List<?> objects) {
        super();
        this.context=context;
        this.marcaArrayList = (ArrayList<Marca>) objects;
    }


    @Override
    public int getCount() {
        return this.marcaArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.marcaArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return this.marcaArrayList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = convertView;

        if (null == convertView) { //Si no existe, entonces inflarlo
            view = inflater.inflate(R.layout.itemmarca, parent, false);
        }

        TextView nombre = (TextView)view.findViewById(R.id.nombreMarca);
        TextView id = (TextView)view.findViewById(R.id.marcaId);

        Marca marca = (Marca)getItem(position);

        nombre.setText(marca.getNombre());
        id.setText(marca.getId()+"");

        return view;
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    public Filter getFilter(){return new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            final FilterResults oReturn = new FilterResults();
            final ArrayList<Marca> results = new ArrayList<Marca>();

            if (orig == null)
                orig = marcaArrayList;

            if (constraint != null) {
                if (orig != null && orig.size() > 0) {
                    for (final Marca g : orig) {
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
            marcaArrayList = (ArrayList<Marca>) results.values;
            notifyDataSetChanged();
        }
    };
    }


}
