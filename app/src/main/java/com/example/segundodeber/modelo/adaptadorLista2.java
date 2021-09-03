package com.example.segundodeber.modelo;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.segundodeber.R;
import com.squareup.picasso.Picasso;

import java.util.List;
public class adaptadorLista2 extends RecyclerView.Adapter<adaptadorLista2.ViewHolder> implements  View.OnClickListener   {
    private List<modelos_revista> Data;
    private LayoutInflater myinflater;
    private Context context;
    private View.OnClickListener listener;

    public adaptadorLista2(List<modelos_revista> itemList, Context context)
    {
        this.myinflater=LayoutInflater.from(context);
        this.context=context;
        this.Data=itemList;
    }
    @Override
    public  int getItemCount()
    { return Data.size();
    }
    @Override
    public adaptadorLista2.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view=myinflater.inflate(R.layout.lista_revista, null);
        view.setOnClickListener(this);
        return new adaptadorLista2.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final  adaptadorLista2.ViewHolder holder, final int position)
    {
        holder.bindData(Data.get(position));

    }
    public  void  setItems(List<modelos_revista> items){Data=items;}
    public  void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }
    @Override
    public void onClick(View v) {
        if(listener!=null)
        {
            listener.onClick(v);
        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageRevista;
        TextView titulo, volumen,doi, anio;
        ViewHolder(View itemView)
        {
            super(itemView);
            imageRevista=itemView.findViewById(R.id.imagenViewlista);
            titulo=itemView.findViewById(R.id.txtTitulo);
            volumen=itemView.findViewById(R.id.Txtvolumen);
            doi=itemView.findViewById(R.id.txtDoi);
            anio=itemView.findViewById(R.id.Txtanio);

        }
        void bindData(final  modelos_revista item)
        {
            Picasso.get().load(item.getCover()).resize(100,100).centerCrop().into(imageRevista);
            titulo.setText(item.getTitle());
            volumen.setText(item.getVolume());
            doi.setText(item.getDoi());
            anio.setText(item.getYear());
        }
    }


}
