package br.com.farmcontrol.model.dao;

import java.sql.Date;

import br.com.farmcontrol.model.vo.LoteAves;

public class LoteAvesDAODemo {
	public static void main(String[] args) {
	
		Date d = new Date(2000-1999,9-1,19);
		
		/*
		LoteAves la = new LoteAves();
		la.setId_animal(50);
		la.setData_nasc_aquisicao(d);
		la.setQuantidade(14);
		
		la.setRaca("testando 3 ");
		
		la.setTipo_ave("de raça");
		
		LoteAvesDAO.create(la); */
		
		
		
		//READ
        for(LoteAves m: LoteAvesDAO.read("galinha")){
            System.out.println(m);
        }
        
        System.out.println(LoteAvesDAO.read(21));
        /*READ por ID
        */
        

        
        //UPDATE
     LoteAves m = new LoteAves();
        m.setId_animal(52);
        m.setRaca("na");
        m.setTipo_ave("testando alterar 10");
        m.setQuantidade(8);
     //   m.setData_nasc_aquisicao(new Date(2020-1900, 7-1, 10));
    //    m.setValor_venda((float) 90);
      //  m.setData_venda(new Date(2020-1900, 12-1, 10));
        
        LoteAvesDAO.update(m);  
        
      
//        DELETE
      
        
        
        
        for(LoteAves mi: LoteAvesDAO.read()){
            System.out.println(mi);
        }
        
	}
}
