package cl.chisa.myapplication.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.Vector;

import cl.chisa.myapplication.R;
import cl.chisa.myapplication.bd.clases.ActividadConDetalle;

public class ActividadAdapter extends RecyclerView.Adapter<ActividadAdapter.ViewHolder> implements Filterable {
    private Vector<ActividadConDetalle> listdata;
    private Vector<ActividadConDetalle> listdataFilter;
    private CustomFilter mFilter;

    public ActividadAdapter(Vector<ActividadConDetalle> listdata) {
        this.listdata = listdata;
        this.listdataFilter = new Vector<>();
        this.listdataFilter.addAll(listdata);
        this.mFilter = new CustomFilter(ActividadAdapter.this);
    }

    @Override
    public int getItemCount() {
        return listdataFilter.size();
    }

    @Override
    public Filter getFilter() {
        return mFilter;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_nombre_detalle, tv_fecha_detalle, tv_asignatura_detalle, tv_sede_detalle;
        public ViewHolder(View itemView) {
            super(itemView);
            this.tv_nombre_detalle = (TextView) itemView.findViewById(R.id.tv_nombre_detalle);
            this.tv_fecha_detalle = (TextView) itemView.findViewById(R.id.tv_fecha_detalle);
            this.tv_asignatura_detalle = (TextView) itemView.findViewById(R.id.tv_asignatura_detalle);
            this.tv_sede_detalle = (TextView) itemView.findViewById(R.id.tv_sede_detalle);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.rv_actividad_detalle, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ActividadConDetalle actividad = listdataFilter.get(position);
        holder.tv_asignatura_detalle.setText(String.valueOf(actividad.getDesc_asignatura()));
        holder.tv_fecha_detalle.setText(String.valueOf(actividad.getFecha_actividad()));
        String nombre = new String();
        nombre  = String.valueOf(actividad.getPersona_nombre()) +" "+ String.valueOf(actividad.getPersona_paterno())+" "+String.valueOf(actividad.getPersona_materno());
        holder.tv_nombre_detalle.setText(String.valueOf(nombre));
        holder.tv_sede_detalle.setText(String.valueOf(actividad.getDesc_sede()));

    }

    public class CustomFilter extends Filter {
        private ActividadAdapter listAdapter;

        public CustomFilter(ActividadAdapter listAdapter) {
            super();
            this.listAdapter = listAdapter;
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            listdataFilter.clear();
            final FilterResults results = new FilterResults();
            if (constraint.length() == 0) {
                listdataFilter.addAll(listdata);
            } else {
                final String filterPattern = constraint.toString().toLowerCase().trim();
                for (final ActividadConDetalle person : listdata) {
                    if (person.getPersona_rut_persona().toLowerCase().contains(filterPattern)
                        || person.getFecha_actividad().toLowerCase().contains(filterPattern)
                        || person.getFecha_actividad().toLowerCase().contains(filterPattern)
                        || person.getPersona_nombre().toLowerCase().contains(filterPattern)
                        || person.getPersona_paterno().toLowerCase().contains(filterPattern)
                        || person.getPersona_materno().toLowerCase().contains(filterPattern)
                        || person.getDesc_asignatura().toLowerCase().contains(filterPattern)
                        || person.getDesc_sede().toLowerCase().contains(filterPattern)
                    ){
                        listdataFilter.add(person);
                    }
                }
            }
            results.values = listdataFilter;
            results.count = listdataFilter.size();
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            this.listAdapter.notifyDataSetChanged();
        }
    }

}
