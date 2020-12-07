package br.com.farmcontrol.model.dao;

import java.sql.Date;

import br.com.farmcontrol.model.vo.LoteAves;
import br.com.farmcontrol.model.vo.Mamifero;
import br.com.farmcontrol.model.vo.Racao;
import br.com.farmcontrol.model.vo.Reproducao;

public class ReproducaoDAODemo {
	public static void main(String[] args) {
		Mamifero m = new Mamifero();
		m.setId_animal(10);
		
		LoteAves la = new LoteAves();
		la.setId_animal(13);

		
		Date d = new Date(2000-1900, 2-1, 12);
		
		
		Reproducao re = new Reproducao();
		
		re.setData_reproducao(d);
		re.setDescricao_reprod("reprod 7 filhotes");
		re.setQntd_reproducao(3);
		re.setAnimal(m);
	
		
		
		System.out.println(re.getId_repoducao());
	    System.out.println(re.getDescricao_reprod());
	    System.out.println(re.getQntd_reproducao());
	    System.out.println(re.getData_reproducao());
	    System.out.println(re.getAnimal());
	    
	    ReproducaoDAO.create(re);
	    
	    
	   
	    
	 //   System.out.println(ReproducaoDAO.read(3));
	   // Date s= new Date(2015-1999,5-1,12);
	    //System.out.println(ReproducaoDAO.read(d));
	//    System.out.println(ReproducaoDAO.read("reprod 7 filhotes"));
	    
	    
	    Reproducao rep = new Reproducao();
        
        rep.setAnimal(m);
        rep.setDescricao_reprod("filhotes de macaco");      
        rep.setQntd_reproducao(2);
        rep.setId_repoducao(4);

        
        ReproducaoDAO.create(rep);
   
        
        rep.setQntd_reproducao(4);
        
        ReproducaoDAO.update(rep);
        
        for(Reproducao r: ReproducaoDAO.read()) {
	    	System.out.println(r);
	    }
	    
	    
	}
}
