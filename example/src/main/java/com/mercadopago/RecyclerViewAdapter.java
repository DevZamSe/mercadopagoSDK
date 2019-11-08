package com.mercadopago;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mercadopago.example.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private OnItemClickListener mListener;
    Context ctx;

    public interface OnItemClickListener{
        void OnItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvprecio, tvnombre, tvventas;
        ImageView imagenView;

        public ViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            tvprecio = (TextView) itemView.findViewById(R.id.tvprecio);
            tvnombre = (TextView) itemView.findViewById(R.id.textView);
            tvventas = (TextView) itemView.findViewById(R.id.tview);
            imagenView = (ImageView) itemView.findViewById(R.id.imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.OnItemClick(position);
                        }
                    }
                }
            });
        }
    }


    public List<Modelo> Lista;

    public RecyclerViewAdapter(){}

    public RecyclerViewAdapter (List<Modelo> Lista){
        this.Lista = Lista;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card,parent,false);

        ctx = parent.getContext();

        ViewHolder viewHolder = new ViewHolder(view, mListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {

        holder.tvnombre.setText(Lista.get(i).getNombre());
        holder.tvprecio.setText(Lista.get(i).getPrecio());
        holder.tvventas.setText(Lista.get(i).getVentas()+"");
        Picasso.with(ctx).load(Lista.get(i).getUrl()).into(holder.imagenView);

    }

    @Override
    public int getItemCount() {
        return Lista.size();
    }


}
