package com.baccarin.reg;

/**
 *
 * @author baccarin
 */
public class Prato {

    private String nome;
    private Classificacao classificacao;

    public Prato(String nome){
        this.nome = nome;
    }
    
    public Prato(String nome, Classificacao classificacao) {
        this.nome = nome;
        this.classificacao = classificacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Classificacao classificacao) {
        this.classificacao = classificacao;
    }

}