package br.com.farmcontrol.model.vo;

import java.util.Date;

/** Classe para objetos do tipo Racao, onde ser�o contidos atributos e m�todos para permitir 
 * a manipula��o de inst�ncias do tipo desta classe.
 * @author equipe
 * @version 1.1
 * @since Realease 1.2 da aplica��o
 */

public class Racao {
	
	/**
     * Utilizada para definir o id da ra��o
     */
	private int id_racao;
	
	/**
	 * Utilizada para definir a quantidade de ra��o
	 */
    private int qtd_racao;
    
    /**
     * Utilizada para definir a descri��o da ra��o
     */
    private String descricao;
    
    /**
     * Utilizada para definir o custo da ra��o
     */
    private float custo;
    
    /**
     * Utilizada para definir a data da ra��o
     */
    private Date data;
    
    /**
     * Utilizada para definir a qual animal a ra��o pertence
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
