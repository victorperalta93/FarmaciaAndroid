package ugr.victorperalta.sufarmacia;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

// adapter para recyclerview, asigna datos a cada item del mismo
public class ListaProductosAdapter extends RecyclerView.Adapter<ListaProductosAdapter.ViewHolder> {
    private ArrayList<Producto> productos;
    private final ClickListener listener;

    public ListaProductosAdapter(ArrayList<Producto> productos,ClickListener listener){
        this.productos = productos;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        public TextView nombre;
        public TextView laboratorio;
        public TextView patologia;
        public TextView precio;
        public ImageView imagen;
        public Button cesta;
        private WeakReference<ClickListener> listenerRef; // utilizado para clicklistener del boton cesta

        public ViewHolder(View v, ClickListener listener) {
            super(v);

            listenerRef = new WeakReference<>(listener);

            this.nombre = v.findViewById(R.id.nombre);
            //this.laboratorio = v.findViewById(R.id.laboratorio);
            //this.patologia = v.findViewById(R.id.patologia);
            this.imagen = v.findViewById(R.id.imagen);
            this.precio = v.findViewById(R.id.precio);

            // EventListener para el boton Añadir a Cesta
            cesta = v.findViewById(R.id.addCesta);
            cesta.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listenerRef.get().onPositionClicked(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }


    // crea una nueva vista
    @NonNull
    @Override
    public ListaProductosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // crear nueva vista
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lista_productos, parent, false);

        return new ViewHolder(itemView,listener);
    }

    // reemplaza el contenido de una vista
    @Override
    public void onBindViewHolder(@NonNull ListaProductosAdapter.ViewHolder holder, int position) {
        Producto producto = productos.get(position);

        holder.nombre.setText(producto.getNombre());
        holder.imagen.setImageResource(producto.getResource());
        holder.precio.setText(producto.getPrecio());
        //holder.laboratorio.setText(producto.getLaboratorio());
        //holder.patologia.setText(String.valueOf(producto.getID()));
    }

    // devuelve la cantidad de productos en la base de datos
    @Override
    public int getItemCount() {
        return productos.size();
    }
}
