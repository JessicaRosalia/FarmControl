/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.farmcontrol.model.vo;

import java.util.Date;

/** Classe para objetos do tipo Animal, onde serão contidos atributos e métodos para permitir
 * a manipulação de instâncias de Sub-Classes que herdam seus atribituos e métodos.
 * @author equipe
 * @version 1.1
 * @since Realease 1.2 da aplicação
 */
abstract public class Animal {
	
	 /**
     * Utilizado para definir o id do animal
     */
	private int id_animal;
	
	/**
	 * Utilizado para definir a raça do animal
	 */
    private String raca;
    
    /** 
     * Utilizado para definir a data de nascimento ou aquisição de um determinado animal
     */
    private Date data_nasc_aquisicao;
    
    /**
     * Utilizado para definir a data de venda
     */
    private Date data_venda;
    
    /**
     * Utilizado para definir o valor de venda
     */
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
        return "ID: "+id_animal+" - "+raca;
    }
        
}
