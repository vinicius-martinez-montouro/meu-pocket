package com.ifsp.dmo.meupocket.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.ifsp.dmo.meupocket.R;
import com.ifsp.dmo.meupocket.model.Site;

import java.util.List;

/** A classe ItemSiteAdapter é responsável por fazer a ligação da fonte de dados
 * com a ListView que irá apresentá-los. É nessa implementação que devemos
 *  especificar o comportamento dos elementos da Lista
 */
public class ItemSiteAdapter extends RecyclerView.Adapter<ItemSiteAdapter.SitesViewHolder> {

    //Objeto responsável por "inflar" (criar) um elemento
    // na tela
    //private LayoutInflater inflater;

    //A fonte de dados está em nossa implementação
    private List<Site> siteList;

    //Aqui implementamos um objeto que será responsável pelo tratamento do clique
    // no item de nosso RecyclerView
    private static RecyclerItemClickListener clickListener;

    //Construtor
    public ItemSiteAdapter(List<Site> siteList) {
        this.siteList = siteList;
    }

    //Método setClickListener receberá a implamentação do clique no elemento da lista.
    // Essa implementação é forncedida MainActivity, por exemplo.
    public void setClickListener(RecyclerItemClickListener clickListener) {
        ItemSiteAdapter.clickListener = clickListener;
    }

    /*
    Esse método precisa ser sobrescrito para que o tratamento adequado dos itens da
    RecyclerViewseja realizado. Aqui configuraremos qual o layout que será utilizado e
    inflaremos esse layout.Tudo aqui é definido no ViewHolder.
    */
    @NonNull
    @Override
    public SitesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_pocket,
                parent,false);
        SitesViewHolder viewHolder = new SitesViewHolder(view);
        return viewHolder;
    }
    /*
    Esse método é chamado sempre que um item do RecyclerView é apresentado, assim ele é
    responsávelpor reciclar os elementos de layout e configurar o que será apresentado
    na tela do aplicativo.
    */
    @Override
    public void onBindViewHolder(@NonNull SitesViewHolder holder, final int position) {
        holder.tituloTextView.setText(siteList.get(position).getTitulo());
        holder.enderecoTextView.setText(siteList.get(position).getTitulo());
        if(siteList.get(position).isFavorito())
            holder.favoritoImageView.setImageResource(R.drawable.ic_favorito);
        else
            holder.favoritoImageView.setImageResource(R.drawable.ic_nao_favorito);
        /*
        Aqui tratamos o clique na imnagem, observe que o ImageView é um elemento do item do
        RecyclerView,assim, tratamos o onClickListener normalmente.
        */
        holder.favoritoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onEstrelaClique(position);
            }
        });
    }
    /*
    Como a fonte de dados não está na classe pai nosso adapter é quem deve devolver a
    quantidadede elementos. Esse método é que define o tamanho de nossa RecyclerView e é
    consultado sempreque a lista é rolada. Definir um valor inválido aqui pode causar falhas
    graves em nosso app.
    */
    @Override
    public int getItemCount() {
        return siteList.size();
    }
    /*
    A inner class SitesViewHolder continua seguindo o mesmo padrão de projeto, contudo agora
    ela deve estender a classe ViewHolder do pacote RecyclerView.
    */
    public static class SitesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        //Os atributos disponíveis no layout
        public TextView tituloTextView;
        public TextView enderecoTextView;
        public ImageView favoritoImageView;
        /*
        O Construtor recupera os elementos de layout
        */
        public SitesViewHolder(@NonNull View itemView) {
            super(itemView);
            tituloTextView = itemView.findViewById(R.id.text_titulo);
            enderecoTextView = itemView.findViewById(R.id.text_endereco);
            favoritoImageView = itemView.findViewById(R.id.image_favorito);
            itemView.setOnClickListener(this);
        }
        /*Aqui tratamos o clique no item e não em elementos do item.*/
        @Override
        public void onClick(View view) {
            if (clickListener != null)
                clickListener.onItemClick(getAdapterPosition());
        }
    }
    /*
    Configuramos se o objeto é ou não favorito
    */
    private void onEstrelaClique(int position) {
        if (siteList.get(position).isFavorito())
            siteList.get(position).undoFavorite();
        else
            siteList.get(position).doFavotite();
        notifyDataSetChanged();
    }

    /**
     * Método construtor responsável por instânciar o adapter. Vale observar que
     * a classe é uma extensão de ArrayAdapter, por isso é necessário utilizar
     * um dos construtores da classe pai.
     * @param context
     * @param siteList
     */
    /**
    public ItemSiteAdapter(Context context, List<Site> siteList) {
        super(context, R.layout.item_lista_pocket, siteList);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
     */

    /**
     * O método getView() é invocado quando os ListView é apresentado. É importante observar que
     * o ListView possui os objetos que estão na tela, os demais objetos "escondidos" não fazem
     * parte o ListView, por isso, o método getView é invocado sempre que a lista é rolada,
     * trocando os elementos que estão visíveis.
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    /**
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
        */
            /*
            O objeto convertView será nulo quando ainda não foi instânciado, ou seja,
            em nosso exemploé invocado quando o site ainda não está na ListView, dessa forma,
            é preciso instância oobjeto (inflar um novo item_lista_pocket) e obter as
            referências de cada elemento de layout.
            */
           /**
            convertView = inflater.inflate(R.layout.item_lista_pocket, null);
            holder = new ViewHolder();
            holder.tituloTextView = convertView.findViewById(R.id.text_titulo);
            holder.enderecoTextView = convertView.findViewById(R.id.text_endereco);
            holder.favoritoImageView = convertView.findViewById(R.id.image_favorito);
            */
            /*
            Uma View possui um atributo do tipo Object denominado tag no qual podemos
            guardar qualquer objeto instânciado. Em nosso exemplo, como as referências dos
            elementosvisuais da lista estão em um holder, vamos armazenar esse objeto na tag
            para queposteriormente ele seja recuparado.
            */
            /**
            convertView.setTag(holder);
        } else {
             */
            /*
            Esse else indicada que o contentView já existe, assim precisamos ajustar nosso objeto
            holder para o holder que foi armazenado na tag da View, sendo necessário
            apenas recuperara tag.
            */
            /**
            holder = (ViewHolder) convertView.getTag();
        }

        /*
        Nossa figura de favorito deve ser clicável, por isso, iremos definir que existe um
        listener para esse objeto. Aqui vamos implementar um onClick() que executa a trocada
        imagem do favorito.
        */
            /**
        holder.favoritoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view == holder.favoritoImageView) {
                    onEstrelaClique(position);
                }
            }
        });
        */

        /*
        É importante lembrar que estamos implementando uma classe que estende um ArrayAdapter,
        ou seja, possui todas as características da classe pai.O método getView() recebe como
        argumento a posição do elemento da fonte de dados queprecisa ser apresentado, lembrando
        que a fonte de dados foi inserida no objeto pai pelométodo construtor.Aqui vamos recuperar
        o objeto (Site) que está na fonte de dados utilizando o métodogetItem() do ArrayAdapter.
        Com o objeto recuperado vamos inserir os dados dele noselementos de layout que possuímos
        e estão com a referência dentro do holder. Atenção para a imagem, que recuperamos uma
        imagem de acordo com a preferência do usuárioindicada no objeto Site.
        */
        /**
        Site obj = (Site) getItem(position);
        holder.tituloTextView.setText(obj.getTitulo());
        holder.enderecoTextView.setText(obj.getEndereco());
        if (obj.isFavorito())
            holder.favoritoImageView.setImageResource(R.drawable.ic_favorito);
        else
            holder.favoritoImageView.setImageResource(R.drawable.ic_nao_favorito);
        */
        /*
        Depois de tudo pronto basta devolver o contentView para que seja apresentado peloListView
        */
        /**
        return convertView;
    }
    */
    /**
     *  O método onEstrelaClique() é chamado quando um ImageView é clicado, assim, recuperamos
     *  o objeto da fonte de dados e atualizamos a preferência do usuário no objeto.
     *  @param position
     */
    /**
    private void onEstrelaClique(int position) {
        Site site = (Site) getItem(position);
        if (site.isFavorito())
            site.undoFavorite();
        else
            site.doFavotite();
     */
        /*
        Aqui uma chamada muito importante e muito bacana, ao atualizar uma propriedade de nosso
         objeto Site, alteramos a fonte de dados, um atributo que seja, contudo o ListView não
         sabe disso.Precisamos notificar que houve uma atualização nos dados a partir no método
         notifyDataSetChanged()que o Adapter se encarregará de atualizar a lista com os
         novos dadas.
         */
        /**
        notifyDataSetChanged();
    }
    */
    /*
    A classe ViewHolder é uma inner class, ou seja, uma classe interna.
    Ela consegue "enchergar" tudoda classe ItemSiteAdapter.
    Vamos utilizar essa classe para armazenar as referências dos elementosde
    layout que estão no layout item_lista_pocket.
    */
    /**
    private static class ViewHolder {
        public TextView tituloTextView;
        public TextView enderecoTextView;
        public ImageView favoritoImageView;
    }
     */
}
