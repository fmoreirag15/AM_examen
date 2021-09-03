package com.example.segundodeber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.segundodeber.modelo.adaptadorLista;
import com.example.segundodeber.modelo.modelo_journal;
import com.example.segundodeber.modelo.modelos_revista;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import  android.widget.Toast;
public class MainActivity extends AppCompatActivity {
     List<modelo_journal> datos= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        obtenerDatosConVolley();
    }
    private void obtenerDatosConVolley()
    {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url="https://revistas.uteq.edu.ec/ws/journals.php";
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length()>0) {
                            datos.clear();
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject Obj = response.getJSONObject(i);
                                    datos.add(new modelo_journal(Obj.get("portada").toString(), Obj.get("description").toString(),
                                                                 Obj.get("name").toString(),Obj.get("journal_id").toString()));
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }else
                        {
                            mensajes("No se encontraron resultados");
                        }
                        ejecutar();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        mensajes("Ocurrio un error");
                        error.printStackTrace();

                    }
                });

        queue.add(jsonObjectRequest);

    }
    public void  mensajes(String mensaje)
    {
        Toast.makeText(this,mensaje,Toast.LENGTH_LONG ).show();
    }
    public void ejecutar()
    {
     adaptadorLista lista= new adaptadorLista(datos, this);
     RecyclerView recyclerView=findViewById(R.id.listaRevista);
     recyclerView.setHasFixedSize(true);
     recyclerView.setLayoutManager(new LinearLayoutManager(this));

     lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,MainActivity2.class);
                i.putExtra("id",datos.get(recyclerView.getChildAdapterPosition(v)).getJournal_id());
                i.putExtra("name",datos.get(recyclerView.getChildAdapterPosition(v)).getName());
                startActivity(i);
            }
        });

     recyclerView.setAdapter(lista);

    }
}