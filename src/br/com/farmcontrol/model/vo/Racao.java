/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.farmcontrol.model.vo;

import java.util.Date;

/**
 *
 * @author viniciuslopes
 */
public class Racao {
    
    private int id_racao;
    private int qtd_racao;
    private String descricao;
    private float custo;
    private Date data;
    private Animal animal;
    
    public int getId_racao() {
        return id_racao;
    }

    public void setId_racao(int id_racao) {
        this.id_racao = id_racao;
    }

    public int getQtd_racao() {
        return qtd_racao;
    }

    public void setQtd_racao(int qtd_racao) {
        this.qtd_racao = qtd_racao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getCusto() {
        return custo;
    }

    public void setCusto(float custo) {
        this.custo = custo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
    
}
