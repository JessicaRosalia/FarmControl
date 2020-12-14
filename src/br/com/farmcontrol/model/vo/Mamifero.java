/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.farmcontrol.model.vo;

import java.util.Date;

/** Classe para objetos do tipo Mamifero, onde serão contidos atributos e métodos,
 *  sendo alguns destes heranças de Animal. Permite a manipulação de instâncias do tipo desta classe.
 * @author equipe
 * @version 1.1
 * @since Realease 1.2 da aplicação
 */

public class Mamifero extends Animal {
	
	/**
     * É utilizado para definir o sexo do mamifero
     */
	private String sexo_mamifero;
	
	/**
	 * Utilizado para definit o valor da arroba
	 */
    private float valor_arroba;
    
    /**
     * Utilizado para definir o peso do mamifero
     */
    private float peso;
    
    /**
     * Utilizado para definir o tipo do mamifero
     */
    private String tipo_mamifero;
    
    /**
     * Utilizado para definir a data do abate do Mamifero
     */
    private Date data_abate;

    public String getSexo_mamifero() {
        return sexo_mamifero;
    }

    public void setSexo_mamifero(String sexo_mamifero) {
        this.sexo_mamifero = sexo_mamifero;
    }

    public float getValor_arroba() {
        return valor_arroba;
    }

    public void setValor_arroba(float valor_arroba) {
        this.valor_arroba = valor_arroba;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public String getTipo_mamifero() {
        return tipo_mamifero;
    }

    public void setTipo_mamifero(String tipo_mamifero) {
        this.tipo_mamifero = tipo_mamifero;
    }

    public Date getData_abate() {
        return data_abate;
    }

    public void setData_abate(Date data_abate) {
        this.data_abate = data_abate;
    }

    @Override
    public String toString() {
        return super.toString();
    }
    
}
