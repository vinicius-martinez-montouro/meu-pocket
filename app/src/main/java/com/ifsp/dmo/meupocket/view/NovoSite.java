package com.ifsp.dmo.meupocket.view;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.ifsp.dmo.meupocket.R;
import com.ifsp.dmo.meupocket.model.Site;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class NovoSite extends AppCompatActivity implements View.OnClickListener{

    private EditText nomeSiteEditText;
    private EditText enderecoSiteEditText;
    private Button salvarButton;
    private Site mSite;
    private List<Site> siteList = null;
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_site);
        nomeSiteEditText = findViewById(R.id.edittext_novo_site);
        enderecoSiteEditText = findViewById(R.id.edittext_endereco);
        salvarButton = findViewById(R.id.button_salvar);
        salvarButton.setOnClickListener(this);
       // mSharedPreferences =
       //         this.getSharedPreferences(getString(R.string.sites_cadastrodos), MODE_PRIVATE);
        mEditor = mSharedPreferences.edit();

    }

    @Override
    public void onClick(View view) {
        if(view == salvarButton){
            mSite = new Site(nomeSiteEditText.getText().toString(), enderecoSiteEditText.getText().toString());
            adicionarBD();
            finish();
        }
    }

    private void adicionarBD(){
        JSONObject jsonObject;
        JSONArray jsonArray = new JSONArray();
        if(siteList == null){
            siteList = new ArrayList<>(1);
        }
        siteList.add(mSite);
        for(Site s : siteList){
            jsonObject = new JSONObject();
            try {
                jsonObject.put("titulo", s.getTitulo());
                jsonObject.put("endereco", s.getEndereco());
                jsonArray.put(jsonObject);
            }catch (JSONException e){
                Log.e(getString(R.string.tag), getString(R.string.erro_recupera_lista));
            }
        }
        String sites = jsonArray.toString();
        mEditor.putString(getString(R.string.key_sites_db), sites);
        mEditor.commit();
    }


}
