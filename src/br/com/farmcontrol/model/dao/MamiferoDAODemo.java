/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.farmcontrol.model.dao;

import br.com.farmcontrol.model.vo.Animal;
import br.com.farmcontrol.model.vo.Mamifero;
import java.sql.Date;

/**
 *
 * @author viniciuslopes
 */
public class MamiferoDAODemo {
    
    public static void main(String[] args){
        /*INSERT
        Mamifero m = new Mamifero();
        m.setRaca("Boi do Sul");
        m.setData_nasc_aquisicao(new Date(2020-1900, 8-1, 10));
        m.setSexo_mamifero("masculino");
        m.setTipo_mamifero("porco");
        
        MamiferoDAO.create(m);*/
        
        /*READ
        for(Animal m: MamiferoDAO.read()){
            System.out.println(m);
        }*/
        
        /*READ por ID
        System.out.println(MamiferoDAO.read(7));
        */
        
        /*READ por tipo
        for(Animal m: MamiferoDAO.read("boi")){
            System.out.println(m);
        }*/
        
        /*UPDATE
        Mamifero m = new Mamifero();
        m.setId_animal(9);
        m.setRaca("Porco do Sul");
        m.setData_nasc_aquisicao(new Date(2020-1900, 7-1, 10));
        m.setSexo_mamifero("feminino");
        m.setTipo_mamifero("porco");
        m.setValor_arroba((float) 30.5);
        m.setPeso(500);
        m.setData_abate(new Date(2020-1900, 12-1, 10));
        m.setValor_venda((float) 5000);
        m.setData_venda(new Date(2020-1900, 12-1, 10));
        
        MamiferoDAO.update(m);*/
        
        /*DELETE
        Mamifero m = new Mamifero();
        m.setId_animal(9);
        MamiferoDAO.delete(m);*/
        
    }
    
}
