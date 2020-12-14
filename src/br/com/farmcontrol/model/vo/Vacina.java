/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.farmcontrol.model.vo;

import java.util.Date;

/** Classe para objetos do tipo Vacina, onde ser�o contidos atributos e m�todos para permitir 
 * a manipula��o de inst�ncias do tipo desta classe.
 * @author equipe
 * @version 1.1
 * @since Realease 1.2 da aplica��o
 */
public class Vacina {
	
	/**
     * � utilizado para definir o id da vacina
     */
    private int id_vacina;
    
    /**
     * � utilizado para definir a data de vacina do animal
     */
    private Date data_vacina;
    
    /**
     * Utilizado para descri��o da vacina
     */
    private String descricao;
    
    /**
     * Utilizado para definir o custo da vacina
     */
    private float custo;
    
    /**
     * Utilizado para definir para qual animal a vacina ser� aplicada
     */
    private Animal animal;

    

    public int getId_vacina() {
        return id_vacina;
    }

    public void setId_vacina(int id_vacina) {
        this.id_vacina = id_vacina;
    }

    public Date getData_vacina() {
        return data_vacina;
    }

    public void setData_vacina(Date data_vacina) {
        this.data_vacina = data_vacina;
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

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    @Override
    public String toString() {
        return "ID: "+this.id_vacina+" - "+this.data_vacina;
    }
    
    
    
}
