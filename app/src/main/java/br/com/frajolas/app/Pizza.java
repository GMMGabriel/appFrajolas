package br.com.frajolas.app;

/**
 * Created by 16254849 on 28/11/2017.
 */

public class Pizza {

    private int id;
    private String nome;
    private String descricao;
    private String preco;
    private String foto;
    private Double media;

    public static Pizza create(int id, String nome, String descricao, String preco, String foto, Double media){
        Pizza c = new Pizza();
        c.setId(id);
        c.setNome(nome);
        c.setDescricao(descricao);
        c.setPreco(preco);
        c.setFoto(foto);
        c.setMedia(media);
        return c;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Double getMedia() {
        return media;
    }

    public void setMedia(Double media) {
        this.media = media;
    }
}
