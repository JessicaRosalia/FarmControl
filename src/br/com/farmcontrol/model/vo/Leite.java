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
public class Leite {
 
    private int id_leite;
    private float valor_litro;
    private int qtd_leite;
    private Date data_producao;
    private Mamifero animal;

    public int getId_leite() {
        return id_leite;
    }

    public void setId_leite(int id_leite) {
        this.id_leite = id_leite;
    }

    public float getValor_litro() {
        return valor_litro;
    }

    public void setValor_litro(float valor_litro) {
        this.valor_litro = valor_litro;
    }

    public int getQtd_leite() {
        return qtd_leite;
    }

    public void setQtd_leite(int qtd_leite) {
        this.qtd_leite = qtd_leite;
    }

    public Date getData_producao() {
        return data_producao;
    }

    public void setData_producao(Date data_producao) {
        this.data_producao = data_producao;
    }

    public Mamifero getAnimal() {
        return animal;
    }

    public void setAnimal(Mamifero animal) {
        this.animal = animal;
    }
      
}
