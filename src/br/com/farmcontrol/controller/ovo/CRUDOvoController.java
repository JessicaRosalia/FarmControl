/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.farmcontrol.controller.ovo;


import br.com.farmcontrol.model.dao.LoteAvesDAO;
import br.com.farmcontrol.model.dao.OvoDao;
import br.com.farmcontrol.model.vo.Animal;
import br.com.farmcontrol.model.vo.LoteAves;
import br.com.farmcontrol.model.vo.Ovo;
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

public class CRUDOvoController implements Initializable {
    
    @FXML
    private Pane paneAnimais;
    
    @FXML
    private AnchorPane crudReprod;

    @FXML
    private ListView<LoteAves> listAnimais;
    
    private List<LoteAves> animais = new ArrayList<>();
    
    private ObservableList<LoteAves> obsAnimal;

    @FXML
    private ListView<Ovo> listOvos;
    
    private List<Ovo> ovos = new ArrayList<>();
            
    private ObservableList<Ovo> obsOvos;
    
     @FXML
    private TextField idovo;

    @FXML
    private TextField idanimal;

    @FXML
    private TextField quantidade;

    @FXML
    private TextField valor_und;

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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        carregarAnimais();
    }
    
    public void cancelarCadastro(){
        titledPane.setVisible(false);
        listAnimais.setDisable(false);
        listOvos.setDisable(false);
        limparCampos();
    }
    
    public void desativarComponentes(Boolean b){
        listAnimais.setDisable(b);
        listOvos.setDisable(b);
        adicionar.setDisable(b);
        atualizar.setDisable(b);
        deletar.setDisable(b);
    }
    
    public void novaReprod(){
        Animal m = listAnimais.getSelectionModel().getSelectedItem();
        
        desativarComponentes(true);
        
        titledPane.setVisible(true);
        cadastrar_id.setText(String.valueOf(m.getId_animal()));
    }
    
    public void cadastrarReprod(){
        
        LoteAves a = listAnimais.getSelectionModel().getSelectedItem();
        
        if(cadastrar_data.getText()==null||cadastrar_data.getText()==""||
                cadastrar_quant.getText()==null||cadastrar_quant.getText()==null){
            JOptionPane.showMessageDialog(null, "Os campos de data e quantidade são obrigatórios");
        }else{
            Ovo r = new Ovo();
            r.setLote(a);
            r.setData_producao(dataPadrao(cadastrar_data.getText()));
            r.setValor_unidade(Float.parseFloat(cadastrar_valor.getText()));
            r.setQtd_ovos(Integer.parseInt(cadastrar_quant.getText()));
            OvoDao.create(r);
            
            titledPane.setVisible(false);
            
            carregarReproducoes();
            
            cadastrar_data.setText(null);
            cadastrar_valor.setText(null);
            cadastrar_quant.setText(null);
            
            desativarComponentes(false);
        }
        
    }
    
    public void deletarReprod(){
        Ovo r = listOvos.getSelectionModel().getSelectedItem();
        OvoDao.delete(r);
        carregarReproducoes();
    }
    
    public void atualizarReprod(){
        Ovo r = new Ovo();
        LoteAves a =  listAnimais.getSelectionModel().getSelectedItem();
        r.setLote(a);
        r.setData_producao(dataPadrao(data.getText()));
        r.setValor_unidade(Float.valueOf(valor_und.getText()));
        r.setId_ovo(Integer.parseInt(idovo.getText()));
        r.setQtd_ovos(Integer.parseInt(quantidade.getText()));
        OvoDao.update(r);
        carregarReproducoes();
    }
    
    public void pegarDadosReproducoes(){
        Ovo r = listOvos.getSelectionModel().getSelectedItem();
        idovo.setText(String.valueOf(r.getId_ovo()));
        quantidade.setText(String.valueOf(r.getQtd_ovos()));
        valor_und.setText(String.valueOf(r.getValor_unidade()));
        data.setText(String.valueOf(r.getData_producao()));
        atualizar.setDisable(false);
        deletar.setDisable(false);
    }
    
    public void carregarReproducoes(){
        LoteAves m = listAnimais.getSelectionModel().getSelectedItem();
        ovos = OvoDao.read(m);
        obsOvos = FXCollections.observableArrayList(ovos);
        listOvos.setItems(obsOvos);
        idanimal.setText(String.valueOf(m.getId_animal()));
        limparCampos();
        atualizar.setDisable(true);
        deletar.setDisable(true);
        adicionar.setDisable(false);
    }
    
    public void carregarAnimais(){
        animais = LoteAvesDAO.read();      
        obsAnimal = FXCollections.observableArrayList(animais);
        listAnimais.setItems(obsAnimal);     
    }
    
    public void limparCampos(){
        idovo.setText(null);
        quantidade.setText(null);
        valor_und.setText(null);
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
