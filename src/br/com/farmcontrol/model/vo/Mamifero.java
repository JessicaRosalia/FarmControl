/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.farmcontrol.model.vo;

/**
 *
 * @author viniciuslopes
 */
public class Mamifero extends Animal {
    
    private String sexo_mamifero;
    private float valor_arroba;
    private float peso;
    private String tipo_mamifero;

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
    
}
