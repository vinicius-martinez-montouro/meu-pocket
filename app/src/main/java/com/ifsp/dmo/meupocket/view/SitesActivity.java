package com.ifsp.dmo.meupocket.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.ifsp.dmo.meupocket.R;
import com.ifsp.dmo.meupocket.dao.SiteDao;
import com.ifsp.dmo.meupocket.model.Site;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vinicius.montouro
 */
public class SitesActivity extends AppCompatActivity{

    //Referência para o elemento de RecyclerView
    private RecyclerView sitesRecyclerView;

    //Fonte de dados, essa lista possue os dados que são apresentados
    //na tela dos dispositivo.
    private List<Site> siteList;

    private ItemSiteAdapter siteAdapter;
    private Site mSite;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sites);

        //Recupera a referência do elemento no layout
        sitesRecyclerView = findViewById(R.id.recycler_lista_sites);

        //Ao contrário do ListView um RecyclerView necessita de um LayoutManager (gerenciador de
        // layout) para gerenciar o posicionamento de seus itens. Utilizarei um LinearLayoutManager
        // que fará com que nosso RecyclerView se pareça com um ListView.
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        sitesRecyclerView.setLayoutManager(layoutManager);

        //Carrega a fonte de dados
        //siteList = SiteDao.recuperateAll();
        mSharedPreferences = this.getPreferences(MODE_PRIVATE);

        recuperaSites();
        adicionaSitesView();
    }

    private void adicionaSitesView(){
        siteAdapter = new ItemSiteAdapter(siteList);

        sitesRecyclerView.setAdapter(siteAdapter);

        siteAdapter.setClickListener(new RecyclerItemClickListener() {
            @Override
            public void onItemClick(int position) {
                String url = corrigeEndereco(siteList.get(position).getEndereco());
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                startActivity(intent);
            }
        });
    }

    private String corrigeEndereco(String endereco) {
        String url = endereco.trim().replace(" ","");
        if (!url.startsWith("http://")) {
            return "http://" + url;
        }
        return url;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_mypocket, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_adicionar:
                Intent in = new Intent(this, NovoSite.class);
                startActivity(in);
                break;
            //TODO abrir tela para adicionar novo site.
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        //Carrega a fonte de dados
        //siteList = SiteDao.recuperateAll();
        recuperaSites();
        adicionaSitesView();
        super.onResume();
    }

    private void recuperaSites(){
        String sites = mSharedPreferences
                .getString(getString(R.string.key_sites_db), "");
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray;
        siteList = new ArrayList<>();
        try{
            jsonArray = new JSONArray(sites);
            for(int i = 0; i<jsonArray.length(); i++){
                jsonObject = jsonArray.getJSONObject(i);
                mSite = new Site(jsonObject
                        .getString("titulo"), jsonObject.getString("endereco"));
                siteList.add(mSite);
            }
        } catch (JSONException ex){
            siteList = null;
        }
        if (siteList != null) {
            for (Site s : siteList) {
                Log.i(getString(R.string.tag), s.toString());
            }
        }
    }

}