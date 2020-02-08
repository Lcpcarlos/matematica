package com.example.testematematia.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Treinando {
    @PrimaryKey
    private int id;
    private String nome;
    private int ttlMultiplicacao;
    private int ttldivisao = 0;
    private int ttlErro = 0;
    private int ttlAcerto = 0;


    @Ignore
    public Treinando(int id, String nome, int ttlMultiplicacao, int ttldivisao) {
        this.id = id;
        this.nome = nome;
        this.ttlMultiplicacao = ttlMultiplicacao;
        this.ttldivisao = ttldivisao;
    }

    public Treinando() {
    }

    public int getTtlErro() {
        return ttlErro;
    }

    public void setTtlErro(int ttlErro) {
        this.ttlErro = ttlErro;
    }

    public int getTtlAcerto() {
        return ttlAcerto;
    }

    public void setTtlAcerto(int ttlAcerto) {
        this.ttlAcerto = ttlAcerto;
    }

    public int getTtlMultiplicacao() {
        return ttlMultiplicacao;
    }

    public void setTtlMultiplicacao(int ttlMultiplicacao) {
        this.ttlMultiplicacao = ttlMultiplicacao;
    }

    public int getTtldivisao() {
        return ttldivisao;
    }

    public void setTtldivisao(int ttldivisao) {
        this.ttldivisao = ttldivisao;
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

}
