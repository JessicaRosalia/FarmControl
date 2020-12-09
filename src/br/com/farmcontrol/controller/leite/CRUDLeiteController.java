/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.farmcontrol.controller.leite;

import br.com.farmcontrol.controller.reproducao.*;
import br.com.farmcontrol.model.dao.AnimalDAO;
import br.com.farmcontrol.model.dao.LeiteDAO;
import br.com.farmcontrol.model.dao.MamiferoDAO;
import br.com.farmcontrol.model.dao.ReproducaoDAO;
import br.com.farmcontrol.model.vo.Animal;
import br.com.farmcontrol.model.vo.Leite;
import br.com.farmcontrol.model.vo.Mamifero;
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
public class CRUDLeiteController implements Initializable {
    
    @FXML
    private Pane paneAnimais;

    @FXML
    private ListView<Mamifero> listAnimais;
    
    private List<Mamifero> animais = new ArrayList<>();
    
    private ObservableList<Mamifero> obsAnimal;

    @FXML
    private ListView<Leite> listLeite;
    
    private List<Leite> leites = new ArrayList<>();
            
    private ObservableList<Leite> obsLeite;
    
    @FXML
    private TextField idleite;

    @FXML
    private TextField idanimal;

    @FXML
    private TextField quantidade;

    @FXML
    private TextField valor_litro;

    @FXML
    private TextField data;

    @FXML
    private Button atualizar;

    @FXML
    private Button deletar;

    @FXML
    private Button adicionar;

    @FXML
    private TitledPane titledPane;

    @FXML
    private TextField cadastrar_id;

    @FXML
    private TextField cadastrar_quant;

    @FXML
    private TextField cadastrar_valor;

    @FXML
    private TextField cadastrar_data;

    @FXML
    private Button cadastrar_button;

    @FXML
    private Button cancelar_button;
    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        carregarAnimais();
    }
    
    public void cancelarCadastro(){
        titledPane.setVisible(false);
        listAnimais.setDisable(false);
        listLeite.setDisable(false);
        limparCampos();
    }
    
    public void desativarComponentes(Boolean b){
        listAnimais.setDisable(b);
        listLeite.setDisable(b);
        adicionar.setDisable(b);
        atualizar.setDisable(b);
        deletar.setDisable(b);
    }
    
    public void novaReprod(){
        //JOptionPane
        Mamifero m = listAnimais.getSelectionModel().getSelectedItem();
        
        desativarComponentes(true);
        
        titledPane.setVisible(true);
        cadastrar_id.setText(String.valueOf(m.getId_animal()));
    }
    
    public void cadastrarReprod(){
        
        Mamifero a = listAnimais.getSelectionModel().getSelectedItem();
        
        if(cadastrar_data.getText()==null||cadastrar_data.getText()==""||
                cadastrar_quant.getText()==null||cadastrar_quant.getText()==null){
            JOptionPane.showMessageDialog(null, "Os campos de data e quantidade são obrigatórios");
        }else{
            Leite r = new Leite();
            r.setAnimal(a);
            r.setData_producao(dataPadrao(cadastrar_data.getText()));
            r.setValor_litro(Float.parseFloat(cadastrar_valor.getText()));
            r.setQtd_leite(Integer.parseInt(cadastrar_quant.getText()));
            LeiteDAO.create(r);
            
            titledPane.setVisible(false);
            
            carregarReproducoes();
            
            cadastrar_data.setText(null);
            cadastrar_valor.setText(null);
            cadastrar_quant.setText(null);
            
            desativarComponentes(false);
        }
        
    }
    
    public void deletarReprod(){
        Leite r = listLeite.getSelectionModel().getSelectedItem();
        LeiteDAO.delete(r);
        carregarReproducoes();
    }
    
    public void atualizarReprod(){
        Leite r = new Leite();
        Mamifero a =  listAnimais.getSelectionModel().getSelectedItem();
        r.setAnimal(a);
        r.setData_producao(dataPadrao(data.getText()));
        r.setValor_litro(Float.parseFloat(valor_litro.getText()));
        r.setId_leite(Integer.parseInt(idleite.getText()));
        r.setQtd_leite(Integer.parseInt(quantidade.getText()));
        LeiteDAO.update(r);
        carregarReproducoes();
    }
    
    public void pegarDadosReproducoes(){
        Leite r = listLeite.getSelectionModel().getSelectedItem();
        idleite.setText(String.valueOf(r.getId_leite()));
        quantidade.setText(String.valueOf(r.getQtd_leite()));
        valor_litro.setText(String.valueOf(r.getValor_litro()));
        data.setText(String.valueOf(r.getData_producao()));
        atualizar.setDisable(false);
        deletar.setDisable(false);
    }
    
    public void carregarReproducoes(){
        Mamifero m = listAnimais.getSelectionModel().getSelectedItem();
        leites = LeiteDAO.read(m);
        obsLeite = FXCollections.observableArrayList(leites);
        listLeite.setItems(obsLeite);
        idanimal.setText(String.valueOf(m.getId_animal()));
        limparCampos();
        atualizar.setDisable(true);
        deletar.setDisable(true);
        adicionar.setDisable(false);
    }
    
    public void carregarAnimais(){
        animais = MamiferoDAO.read();      
        obsAnimal = FXCollections.observableArrayList(animais);
        listAnimais.setItems(obsAnimal);     
    }
    
    public void limparCampos(){
        idleite.setText(null);
        quantidade.setText(null);
        valor_litro.setText(null);
        data.setText(null);
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
