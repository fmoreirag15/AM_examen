package com.example.segundodeber;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.segundodeber.modelo.adaptadorLista;
import com.example.segundodeber.modelo.adaptadorLista3;
import com.example.segundodeber.modelo.modelo_journal;
import com.example.segundodeber.modelo.modelos_pubs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity3 extends AppCompatActivity {
    List<modelos_pubs> datos= new ArrayList<>();
    String id="78";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        id=getIntent().getExtras().getString("Issue_id");
        obtenerDatosConVolley(id);
    }
    private void obtenerDatosConVolley(String id)
    {

        RequestQueue queue = Volley.newRequestQueue(this);
        String url="https://revistas.uteq.edu.ec/ws/pubs.php?i_id="+id+"";
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        if(response.length()>0) {
                            datos.clear();
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    JSONObject Obj = response.getJSONObject(i);
                                    datos.add(new modelos_pubs(Obj.get("section").toString(), Obj.get("title").toString(),
                                            Obj.get("doi").toString()));

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
       adaptadorLista3 lista= new adaptadorLista3(datos, this);
       RecyclerView recyclerView=findViewById(R.id.recyclerpubs);
       recyclerView.setHasFixedSize(true);
       recyclerView.setLayoutManager(new LinearLayoutManager(this));
        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=datos.get(recyclerView.getChildAdapterPosition(v)).getDoi();
                Uri uri =Uri.parse(url);
                Intent intent=new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
            }
        });
       recyclerView.setAdapter(lista);

    }
}