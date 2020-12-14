/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.farmcontrol.controller.reproducao;

import br.com.farmcontrol.model.dao.AnimalDAO;
import br.com.farmcontrol.model.dao.ReproducaoDAO;
import br.com.farmcontrol.model.vo.Animal;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;


public class CRUDReproducaoController implements Initializable {
    
    @FXML
    private Pane paneAnimais;
    
    @FXML
    private AnchorPane crudReprod;

    @FXML
    private ListView<Animal> listAnimais;
    
    private List<Animal> animais = new ArrayList<>();
    
    private ObservableList<Animal> obsAnimal;

    @FXML
    private ListView<Reproducao> listReprod;
    
    private List<Reproducao> reproducoes = new ArrayList<>();
            
    private ObservableList<Reproducao> obsReprod;
    
    @FXML
    private TextField idreprod;

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
        listReprod.setDisable(false);
        limparCampos();
    }
    
    public void desativarComponentes(Boolean b){
        listAnimais.setDisable(b);
        listReprod.setDisable(b);
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
    }
    
    public void cadastrarReprod(){
        
        Animal a = listAnimais.getSelectionModel().getSelectedItem();
        
        if(cadastrar_data.getText()==null||cadastrar_data.getText()==""||
                cadastrar_quant.getText()==null||cadastrar_quant.getText()==null){
            JOptionPane.showMessageDialog(null, "Os campos de data e quantidade são obrigatórios");
        }else{
            Reproducao r = new Reproducao();
            r.setAnimal(a);
            r.setData_reproducao(dataPadrao(cadastrar_data.getText()));
            r.setDescricao_reprod(cadastrar_desc.getText());
            r.setQntd_reproducao(Integer.parseInt(cadastrar_quant.getText()));
            ReproducaoDAO.create(r);
            
            titledPane.setVisible(false);
            
            carregarReproducoes();
            
            cadastrar_data.setText(null);
            cadastrar_desc.setText(null);
            cadastrar_quant.setText(null);
            
            desativarComponentes(false);
        }
        
    }
    
    public void deletarReprod(){
        Reproducao r = listReprod.getSelectionModel().getSelectedItem();
        ReproducaoDAO.delete(r);
        carregarReproducoes();
    }
    
    public void atualizarReprod(){
        Reproducao r = new Reproducao();
        Animal a =  listAnimais.getSelectionModel().getSelectedItem();
        r.setAnimal(a);
        r.setData_reproducao(dataPadrao(data.getText()));
        r.setDescricao_reprod(descricao.getText());
        r.setId_repoducao(Integer.parseInt(idreprod.getText()));
        r.setQntd_reproducao(Integer.parseInt(quantidade.getText()));
        ReproducaoDAO.update(r);
        carregarReproducoes();
    }
    
    public void pegarDadosReproducoes(){
        Reproducao r = listReprod.getSelectionModel().getSelectedItem();
        idreprod.setText(String.valueOf(r.getId_repoducao()));
        quantidade.setText(String.valueOf(r.getQntd_reproducao()));
        descricao.setText(r.getDescricao_reprod());
        data.setText(String.valueOf(r.getData_reproducao()));
        atualizar.setDisable(false);
        deletar.setDisable(false);
    }
    
    public void carregarReproducoes(){
        Animal m = listAnimais.getSelectionModel().getSelectedItem();
        reproducoes = ReproducaoDAO.read(m);
        obsReprod = FXCollections.observableArrayList(reproducoes);
        listReprod.setItems(obsReprod);
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
        idreprod.setText(null);
        quantidade.setText(null);
        descricao.setText(null);
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
