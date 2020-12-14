package br.com.farmcontrol.model.vo;

import java.util.Date;

/** Classe para objetos do tipo Racao, onde serão contidos atributos e métodos para permitir 
 * a manipulação de instâncias do tipo desta classe.
 * @author equipe
 * @version 1.1
 * @since Realease 1.2 da aplicação
 */

public class Racao {
	
	/**
     * Utilizada para definir o id da ração
     */
	private int id_racao;
	
	/**
	 * Utilizada para definir a quantidade de ração
	 */
    private int qtd_racao;
    
    /**
     * Utilizada para definir a descrição da ração
     */
    private String descricao;
    
    /**
     * Utilizada para definir o custo da ração
     */
    private float custo;
    
    /**
     * Utilizada para definir a data da ração
     */
    private Date data;
    
    /**
     * Utilizada para definir a qual animal a ração pertence
     */
    private Animal animal;
    
    public int getId_racao() {
        return id_racao;
    }

    public void setId_racao(int id_racao) {
        this.id_racao = id_racao;
    }

    public int getQtd_racao() {
        return qtd_racao;
    }

    public void setQtd_racao(int qtd_racao) {
        this.qtd_racao = qtd_racao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getCusto() {
        return custo;
    }

    public void setCusto(float custo) {
        this.custo = custo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

	@Override
	public String toString() {
            return "ID: "+this.id_racao+" - "+this.data;
        }
    
    
    
}
