/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.farmcontrol.controller.racao;

import br.com.farmcontrol.controller.reproducao.*;
import br.com.farmcontrol.model.dao.AnimalDAO;
import br.com.farmcontrol.model.dao.RacaoDAO;
import br.com.farmcontrol.model.dao.ReproducaoDAO;
import br.com.farmcontrol.model.vo.Animal;
import br.com.farmcontrol.model.vo.Racao;
import br.com.farmcontrol.model.vo.Reproducao;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author viniciuslopes
 */

public class CRUDRacaoController implements Initializable {

    
    @FXML
    private Pane paneAnimais;

    @FXML
    private ListView<Animal> listAnimais;
    
    private List<Animal> animais = new ArrayList<>();
    
    private ObservableList<Animal> obsAnimal;

    @FXML
    private ListView<Racao> listRacao;
    
    private List<Racao> racoes = new ArrayList<>();
            
    private ObservableList<Racao> obsRacao;
    
   @FXML
    private TextField idracao;

    @FXML
    private TextField idanimal;

    @FXML
    private TextField quantidade;

    @FXML
    private TextField descricao;

    @FXML
    private TextField data;

    @FXML
    private Button atualizar;

    @FXML
    private Button deletar;

    @FXML
    private Button adicionar;

    @FXML
    private TextField custo;

    @FXML
    private TitledPane titledPane;

    @FXML
    private TextField cadastrar_id;

    @FXML
    private TextField cadastrar_quant;

    @FXML
    private TextField cadastrar_desc;

    @FXML
    private TextField cadastrar_data;

    @FXML
    private Button cadastrar_button;

    @FXML
    private Button cancelar_button;

    @FXML
    private TextField cadastrar_custo;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        carregarAnimais();
    }
    
    public void cancelarCadastro(){
        titledPane.setVisible(false);
        listAnimais.setDisable(false);
        listRacao.setDisable(false);
        limparCampos();
    }
    
    public void desativarComponentes(Boolean b){
        listAnimais.setDisable(b);
        listRacao.setDisable(b);
        adicionar.setDisable(b);
        atualizar.setDisable(b);
        deletar.setDisable(b);
    }
    
    public void novaReprod(){
        //JOptionPane
        Animal m = listAnimais.getSelectionModel().getSelectedItem();
        
        desativarComponentes(true);
        
        titledPane.setVisible(true);
        cadastrar_id.setText(String.valueOf(m.getId_animal()));
        cadastrar_custo.setText("0");
    }
    
    public void cadastrarReprod(){
        
        Animal a = listAnimais.getSelectionModel().getSelectedItem();
        
        if(cadastrar_data.getText()==null||cadastrar_data.getText()==""||cadastrar_quant.getText()==null||cadastrar_quant.getText()==null){
            JOptionPane.showMessageDialog(null, "Os campos de data e quantidade são obrigatórios");
        }else{
            Racao r = new Racao();
            r.setAnimal(a);
            r.setData(dataPadrao(cadastrar_data.getText()));
            r.setDescricao(cadastrar_desc.getText());
            r.setQtd_racao(Integer.parseInt(cadastrar_quant.getText()));
            r.setCusto(Float.parseFloat(cadastrar_custo.getText()));
            RacaoDAO.create(r);
            
            titledPane.setVisible(false);
            
            carregarReproducoes();
            
            cadastrar_data.setText(null);
            cadastrar_desc.setText(null);
            cadastrar_quant.setText(null);
            
            desativarComponentes(false);
        }
        
    }
    
    public void deletarReprod(){
        Racao r = listRacao.getSelectionModel().getSelectedItem();
        RacaoDAO.delete(r);
        carregarReproducoes();
    }
    
    public void atualizarReprod(){
        Racao r = new Racao();
        Animal a =  listAnimais.getSelectionModel().getSelectedItem();
        r.setAnimal(a);
        r.setData(dataPadrao(data.getText()));
        r.setDescricao(descricao.getText());
        r.setId_racao(Integer.parseInt(idracao.getText()));
        r.setQtd_racao(Integer.parseInt(quantidade.getText()));
        r.setCusto(Float.parseFloat(custo.getText()));
        RacaoDAO.update(r);
        carregarReproducoes();
    }
    
    public void pegarDadosReproducoes(){
        Racao r = listRacao.getSelectionModel().getSelectedItem();
        idracao.setText(String.valueOf(r.getId_racao()));
        quantidade.setText(String.valueOf(r.getQtd_racao()));
        descricao.setText(r.getDescricao());
        data.setText(String.valueOf(r.getData()));
        custo.setText(String.valueOf(r.getCusto()));
        atualizar.setDisable(false);
        deletar.setDisable(false);
    }
    
    public void carregarReproducoes(){
        Animal m = listAnimais.getSelectionModel().getSelectedItem();
        racoes = RacaoDAO.read(m);
        obsRacao = FXCollections.observableArrayList(racoes);
        listRacao.setItems(obsRacao);
        idanimal.setText(String.valueOf(m.getId_animal()));
        limparCampos();
        atualizar.setDisable(true);
        deletar.setDisable(true);
        adicionar.setDisable(false);
    }
    
    public void carregarAnimais(){
        animais = AnimalDAO.read();      
        obsAnimal = FXCollections.observableArrayList(animais);
        listAnimais.setItems(obsAnimal);     
    }
    
    public void limparCampos(){
        idracao.setText(null);
        quantidade.setText(null);
        descricao.setText(null);
        data.setText(null);
        custo.setText(null);
    }
    
    public Date dataPadrao(String s){
        if(s==null || s.equals("null") || s.equals("")){
            return null;
        }
        int ano = Integer.parseInt(s.substring(0,4));
        int mes = Integer.parseInt(s.substring(5,7));
        int dia = Integer.parseInt(s.substring(8,10));   
        return new Date(ano-1900, mes-1, dia);
    } 
    
}
