/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.farmcontrol.controller.vacina;

import br.com.farmcontrol.controller.reproducao.*;
import br.com.farmcontrol.model.dao.AnimalDAO;
import br.com.farmcontrol.model.dao.ReproducaoDAO;
import br.com.farmcontrol.model.dao.VacinaDAO;
import br.com.farmcontrol.model.vo.Animal;
import br.com.farmcontrol.model.vo.Reproducao;
import br.com.farmcontrol.model.vo.Vacina;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author viniciuslopes
 */
public class CRUDVacinaController implements Initializable {
    
    @FXML
    private Pane paneAnimais;
    
    @FXML
    private AnchorPane crudReprod;

    @FXML
    private ListView<Animal> listAnimais;
    
    private List<Animal> animais = new ArrayList<>();
    
    private ObservableList<Animal> obsAnimal;

    @FXML
    private ListView<Vacina> listVacina;
    
    private List<Vacina> vacinas = new ArrayList<>();
            
    private ObservableList<Vacina> obsVacina;
    
    @FXML
    private TextField idVacina;

    @FXML
    private TextField idanimal;

    @FXML
    private TextField descricao;

    @FXML
    private TextField custo;

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
    private TextField cadastrar_desc;

    @FXML
    private TextField cadastrar_custo;

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
        listVacina.setDisable(false);
        limparCampos();
    }
    
    public void desativarComponentes(Boolean b){
        listAnimais.setDisable(b);
        listVacina.setDisable(b);
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
                cadastrar_desc.getText()==null||cadastrar_desc.getText()==null){
            JOptionPane.showMessageDialog(null, "Os campos de data e descricao são obrigatórios");
        }else{
            Vacina r = new Vacina();
            r.setAnimal(a);
            r.setData_vacina(dataPadrao(cadastrar_data.getText()));
            r.setDescricao(cadastrar_desc.getText());
            r.setCusto(Float.parseFloat(cadastrar_custo.getText()));
            VacinaDAO.create(r);
            
            titledPane.setVisible(false);
            
            carregarReproducoes();
            
            cadastrar_data.setText(null);
            cadastrar_desc.setText(null);
            cadastrar_custo.setText(null);
            
            desativarComponentes(false);
        }
        
    }
    
    public void deletarReprod(){
        Vacina r = listVacina.getSelectionModel().getSelectedItem();
        VacinaDAO.delete(r);
        carregarReproducoes();
    }
    
    public void atualizarReprod(){
        Vacina r = new Vacina();
        Animal a =  listAnimais.getSelectionModel().getSelectedItem();
        r.setAnimal(a);
        r.setData_vacina(dataPadrao(data.getText()));
        r.setDescricao(descricao.getText());
        r.setId_vacina(Integer.parseInt(idVacina.getText()));
        r.setCusto(Float.parseFloat(custo.getText()));
        VacinaDAO.update(r);
        carregarReproducoes();
    }
    
    public void pegarDadosReproducoes(){
        Vacina r = listVacina.getSelectionModel().getSelectedItem();
        idVacina.setText(String.valueOf(r.getId_vacina()));
        custo.setText(String.valueOf(r.getCusto()));
        descricao.setText(r.getDescricao());
        data.setText(String.valueOf(r.getData_vacina()));
        atualizar.setDisable(false);
        deletar.setDisable(false);
    }
    
    public void carregarReproducoes(){
        Animal m = listAnimais.getSelectionModel().getSelectedItem();
        vacinas = VacinaDAO.read(m);
        obsVacina = FXCollections.observableArrayList(vacinas);
        listVacina.setItems(obsVacina);
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
        idVacina.setText(null);
        custo.setText(null);
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
