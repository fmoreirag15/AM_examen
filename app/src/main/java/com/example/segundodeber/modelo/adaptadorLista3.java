package com.example.segundodeber.modelo;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.segundodeber.MainActivity;
import com.example.segundodeber.MainActivity2;
import com.example.segundodeber.MainActivity3;
import com.example.segundodeber.R;
import com.squareup.picasso.Picasso;

import java.util.List;
public class adaptadorLista3 extends RecyclerView.Adapter<adaptadorLista3.ViewHolder> implements  View.OnClickListener   {

    private List<modelos_pubs> Data;
    private LayoutInflater myinflater;
    private Context context;
    private View.OnClickListener listener;

    public adaptadorLista3(List<modelos_pubs> itemList, Context context)
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
    public adaptadorLista3.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view=myinflater.inflate(R.layout.lista_pubs, null);
        view.setOnClickListener(this);
        return new adaptadorLista3.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(final  adaptadorLista3.ViewHolder holder, final int position)
    {
        holder.bindData(Data.get(position));

    }
    public  void  setItems(List<modelos_pubs> items){Data=items;}
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
        TextView name,autore,doi,html;
        ViewHolder(View itemView)
        {
            super(itemView);
            name=itemView.findViewById(R.id.namePubs);
            autore=itemView.findViewById(R.id.txtautores);
            doi=itemView.findViewById(R.id.txtDoin);
        }
        void bindData(final  modelos_pubs item)
        {
            name.setText(item.getSection());
            autore.setText(item.getTitle());
            doi.setText(item.getDoi());

        }
    }


}
