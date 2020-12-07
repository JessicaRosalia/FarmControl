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
abstract public class Animal {
    
    private int id_animal;
    private String raca;
    private Date data_nasc_aquisicao;
    private Date data_venda;
    private float valor_venda;

    public int getId_animal() {
        return id_animal;
    }

    public void setId_animal(int id_animal) {
        this.id_animal = id_animal;
    }

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

    public Date getData_nasc_aquisicao() {
        return data_nasc_aquisicao;
    }

    public void setData_nasc_aquisicao(Date data_nasc_aquisicao) {
        this.data_nasc_aquisicao = data_nasc_aquisicao;
    }

    public Date getData_venda() {
        return data_venda;
    }

    public void setData_venda(Date data_venda) {
        this.data_venda = data_venda;
    }

    public float getValor_venda() {
        return valor_venda;
    }

    public void setValor_venda(float valor_venda) {
        this.valor_venda = valor_venda;
    }

    @Override
    public String toString() {
        //return "Animal{" + "id_animal=" + id_animal + ", raca=" + raca + ", data_nasc_aquisicao=" + data_nasc_aquisicao + ", data_venda=" + data_venda + ", valor_venda=" + valor_venda + '}';
        return "idAnimal"+id_animal+" - "+raca;
    }
        
}
