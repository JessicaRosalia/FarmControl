package br.com.farmcontrol.model.vo;

import java.util.Date;

public class Racao {
    
    private int id_racao;
    private int qtd_racao;
    private String descricao;
    private float custo;
    private Date data;
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
		return "Racao{" + "ID da racao=" + this.id_racao + ", Descricao=" + this.descricao + ", Quantidade=" + this.qtd_racao + ", Custo=" + this.custo + ", ID Animal=" + this.animal.getId_animal() + ", Data="+this.data+'}';
	}
    
    
    
}
