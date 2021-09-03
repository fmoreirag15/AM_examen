package com.example.segundodeber.modelo;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.segundodeber.MainActivity2;
import com.example.segundodeber.R;
import com.squareup.picasso.Picasso;

import java.util.List;
public class adaptadorLista extends RecyclerView.Adapter<adaptadorLista.ViewHolder> implements  View.OnClickListener  {
    private List<modelo_journal> Data;
    private LayoutInflater myinflater;
    private Context context;
    private View.OnClickListener listener;

    public adaptadorLista(List<modelo_journal> itemList, Context context)
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
    public adaptadorLista.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view=myinflater.inflate(R.layout.lista_de_elementos, null);
        view.setOnClickListener(this);
        return new adaptadorLista.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final  adaptadorLista.ViewHolder holder, final int position)
    {
        holder.bindData(Data.get(position));

    }
    public  void  setItems(List<modelo_journal> items){Data=items;}
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
        ImageView portada;
        TextView name, description;
        ViewHolder(View itemView)
        {
            super(itemView);
            portada=itemView.findViewById(R.id.portada);
            description=itemView.findViewById(R.id.textDescription);
            name=itemView.findViewById(R.id.name);
        }
        void bindData(final  modelo_journal item)
        {
            Picasso.get().load(item.getPortada()).resize(100,100).centerCrop().into(portada);
            name.setText(item.getName());
            description.setText(Html.fromHtml(item.getDescription()));
        }
    }


}
