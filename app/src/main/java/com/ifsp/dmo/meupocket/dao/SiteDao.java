package com.ifsp.dmo.meupocket.dao;

import com.ifsp.dmo.meupocket.model.Site;

import java.util.ArrayList;
import java.util.List;

/**
 * @author vinicius.montouro
 */
public class SiteDao {

    public static final List<Site> recuperateAll(){
        ArrayList<Site> arrayList = new ArrayList<>(10);
        arrayList.add(new Site("Google", "www.google.com.br"));
        arrayList.add(new Site("Terra", "www.terra.com.br"));
        arrayList.add(new Site("IFSP", "www.ifsp.edu.br"));
        arrayList.add(new Site("IFSP Câmpus Araraquara", "arq.ifsp.edu.br"));
        arrayList.add(new Site("Developers", "https://developer.android.com"));
        arrayList.add(new Site("Vida de Programador", "https://vidadeprogramador.com.br/"));
        arrayList.add(new Site("Stackoverflow", "https://stackoverflow.com/"));
        arrayList.add(new Site("Youtube", "www.youtube.com.br"));
        arrayList.add(new Site("Gmail", "www.gmail.com"));
        arrayList.add(new Site("Facebook", "www.facebook.com"));
        arrayList.add(new Site("Whatsapp", "www.whatsapp.com"));
        arrayList.add(new Site("Get Pocket", "www.getpocket.com"));
        return arrayList;
    }
}
