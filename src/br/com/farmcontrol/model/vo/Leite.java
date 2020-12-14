/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.farmcontrol.model.vo;

import java.util.Date;

/** Classe para objetos do tipo Leite, onde serão contidos atributos e métodos para permitir 
 * a manipulação de instâncias do tipo desta classe.
 * @author equipe
 * @version 1.1
 * @since Realease 1.2 da aplicação
 */
public class Leite {
	
	/**
	 * Utilizada para definir o id do leite
	 */
    private int id_leite;
    
    /**
     * Utilizada para definri o valor do litro de leite
     */
    private float valor_litro;
    
    /**
     * Utilizada para definir a quantidade de leite
     */
    private int qtd_leite;
    
    /**
     * Utilizada para definir a data de produção
     */
    private Date data_producao;
    
    /**
     * Utilizada para definir a qual animal o leite pertence
     */
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
    
    @Override
    public String toString() {
        //return "Leite{" + "ID do Leite=" + this.id_leite + ", Valor do litro=" + this.valor_litro + ", Quantidade=" + this.qtd_leite + ", Data=" + this.data_producao +", ID Animal=" + this.animal.getId_animal()+'}';
        return "ID: "+this.id_leite+" - "+this.data_producao;
    }
      
}
