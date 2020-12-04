/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.farmcontrol.model.dao;

import br.com.farmcontrol.model.vo.LoteAves;
import br.com.farmcontrol.model.vo.Ovo;
//import java.util.Date;
import java.sql.Date;

/**
 *
 * @author viniciuslopes
 */
public class OvoDAODemo {
    public static void main(String[] args){
        /* TESTE DA OPERAÇÃO CREATE DA CLASSE OvoDao 
        
        LoteAves lote = new LoteAves();
        lote.setId_animal(1);
        
        Ovo o = new Ovo();
                
        o.setLote(lote);
        o.setQtd_ovos(30);
        o.setValor_unidade((float) 0.80);
        o.setData_producao(new Date(2020-1900, 12-1, 5));
        
        
        System.out.println(o.getLote().getId_animal());
        System.out.println(o.getQtd_ovos());
        System.out.println(o.getValor_unidade());
        
        OvoDao.create(o);
        */
        
        /*TESTANDO OPERAÇÃO read()
        //OvoDao dao = new OvoDao();
        
        for(Ovo o: OvoDao.read()){
            
            System.out.println(o);
            
        }*/
        
        /*TESTANDO OPERACÇÃO update()     
        Ovo o = new Ovo();
        OvoDao dao = new OvoDao();
               
        o.setQtd_ovos(4);
        o.setValor_unidade((float) 0.5);        
//PACOTE (java.sql.Date)
//O -1900 tem que ter pra normalizar o ano
//0 -1 tem que ter pra normalizar o mês
//Date d2 = new Date((2020-1900), 11-1, 27);
        o.setData_producao(new Date((2020-1900), (8-1), 13));
        o.setId_ovo(3);
        
        dao.update(o);*/
        
        /*//TESTANDO OPERACAO delete()
        Ovo o = new Ovo();
        //OvoDao dao = new OvoDao();
        o.setId_ovo(6);
        OvoDao.delete(o);
        */
        
        /*TESTANDO BUSCA POR DATA
        for(Ovo o: OvoDao.read(new Date((2020-1900), (12-1), 05))){
            
            System.out.println(o);
            
        }*/
        
        /*TESTANDO BUSCA POR LOTE
        
        LoteAves lote = new LoteAves();
        lote.setId_animal(1);
        
        for(Ovo o: OvoDao.read(lote)){
            
            System.out.println(o);
            
        }*/
        
        /*TESTANDO BUSCA PELO idovos
            
        System.out.println(OvoDao.read(7));
        */
    }
    
}
