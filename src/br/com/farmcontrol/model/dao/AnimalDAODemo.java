/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.farmcontrol.model.dao;

import br.com.farmcontrol.model.vo.Animal;
import br.com.farmcontrol.model.vo.LoteAves;
import br.com.farmcontrol.model.vo.Mamifero;
import java.sql.Date;

/**
 *
 * @author viniciuslopes
 */
public class AnimalDAODemo {
    
    public static void main(String[] args){
        /*INSERT
        Animal ani = new Mamifero();
        ani.setRaca("Gado do Sul");
        ani.setData_nasc_aquisicao(new Date(2020-1900, 10-1, 10));
        AnimalDAO.create(ani);*/
        
        /*READ
        for(Animal a:AnimalDAO.read()){
            System.out.println(a);
        }*/
        
        /*READ POR ID*/
        
        System.out.println(AnimalDAO.read(2));
        
        
        /*UPDATE
        Animal ani = new Mamifero();
        ani.setId_animal(2);
        ani.setRaca("Gado Tradicional");
        ani.setData_nasc_aquisicao(new Date(2020-1900, 9-1, 20));
        ani.setData_venda(new Date(2020-1900, 12-1, 20));
        ani.setValor_venda(5500);
        AnimalDAO.update(ani);*/
        
        /*DELETE
        Animal a = new Mamifero();
        a.setId_animal(3);
        AnimalDAO.delete(a);*/
          
    }
    
}
