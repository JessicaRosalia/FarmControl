/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.farmcontrol.model.vo;

import java.util.Date;

/** Classe para objetos do tipo Reproducao, onde serão contidos atributos e métodos 
 * para permitir a manipulação de instâncias do tipo desta classe.
 * @author equipe
 * @version 1.1
 * @since Realease 1.2 da aplicação
 */
public class Reproducao {
	/** 
	 * Utilizado para definir o id da reprodução
	 */
    private int id_repoducao;
    
    /**
     * Utilizada para definir a quantidade de reprodução
     */
    private int qntd_reproducao;
    
    /**
     * Utilizada para definir a descrição da reprodução
     */
    private String descricao_reprod;
    
    /**
     * Utilizada para definir a qual animal a reprodução pertence
     */
    private Animal animal;
    
    /**
     * Utilizada para definir a data de reprodução
     */
    private Date data_reproducao;


    public int getId_repoducao() {
        return id_repoducao;
    }

    public void setId_repoducao(int id_repoducao) {
        this.id_repoducao = id_repoducao;
    }

    public int getQntd_reproducao() {
        return qntd_reproducao;
    }

    public void setQntd_reproducao(int qntd_reproducao) {
        this.qntd_reproducao = qntd_reproducao;
    }

    public String getDescricao_reprod() {
        return descricao_reprod;
    }

    public void setDescricao_reprod(String descricao_reprod) {
        this.descricao_reprod = descricao_reprod;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public Date getData_reproducao() {
        return data_reproducao;
    }

    public void setData_reproducao(Date data_reproducao) {
        this.data_reproducao = data_reproducao;
    }

	@Override
	public String toString() {
        return "ID: "+this.id_repoducao+
                " - Data: "+this.data_reproducao;
    }
    
    
    
}
