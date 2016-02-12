package com.example.cesarmendes.mediasapp.models;

import java.io.Serializable;

/**
 * Created by cesar.mendes on 10/02/2016.
 */
public class Cadeira implements Serializable {

    private Integer id;
    private String nome;
    private Double credito;
    private Integer ano;
    private Integer nota;

    public Cadeira(String nome, Double credito, Integer ano, Integer nota, Integer id){
        this.id = id;
        this.nome = nome;
        this.credito = credito;
        this.ano = ano;
        this.nota = nota;
    }

    public Cadeira() {
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public void setCredito(Double credito) {
        this.credito = credito;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public Integer getAno() {
        return ano;
    }

    public Double getCredito() {
        return credito;
    }

    public Integer getNota() {
        return nota;
    }

    public String getNome() {
        return nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(int id) { this.id = id;
    }
}
