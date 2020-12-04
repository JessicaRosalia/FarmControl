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
public class Abate {
    
    private Date data_abate;
    private Mamifero animal;

    public Date getData_abate() {
        return data_abate;
    }

    public void setData_abate(Date data_abate) {
        this.data_abate = data_abate;
    }

    public Mamifero getAnimal() {
        return animal;
    }

    public void setAnimal(Mamifero animal) {
        this.animal = animal;
    }
            
}
