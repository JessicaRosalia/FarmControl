package br.com.farmcontrol.controller.ovo;

import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import br.com.farmcontrol.model.dao.LoteAvesDAO;
import br.com.farmcontrol.model.dao.OvoDao;
import br.com.farmcontrol.model.dao.ReproducaoDAO;
import br.com.farmcontrol.model.vo.Animal;
import br.com.farmcontrol.model.vo.LoteAves;
import br.com.farmcontrol.model.vo.Ovo;
import br.com.farmcontrol.model.vo.Reproducao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class CRUDOvoController {

    @FXML
    private AnchorPane crudReprod;

    @FXML
    private Pane paneAnimais;
    
    @FXML
    private TextField idovos;

    @FXML
    private ListView<LoteAves> listLotes;
    
    private List<LoteAves> lotes = new ArrayList<>();
    
    private ObservableList<LoteAves> obsLotes;
    

    @FXML
    private ListView<Ovo> listOvo;
 
    private List<Ovo> ovos = new ArrayList<>();
    
    private ObservableList<Ovo> obsOvos;

    @FXML
    private TextField idanimal;

    @FXML
    private TextField quantidade;

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
    private TextField valor_unid;
    
    @FXML
    private TextField cadastrar_valor_unid;

    @FXML
    private TextField cadastrar_data;

    @FXML
    private Button cadastrar_button;

    @FXML
    private Button cancelar_button;

    @FXML
    void atualizarOvos() {
    	Ovo o = new Ovo();
    	LoteAves la = listLotes.getSelectionModel().getSelectedItem();
    	o.setLote(la);
    	o.setData_producao(dataPadrao(data.getText()));
        o.setQtd_ovos(Integer.parseInt(quantidade.getText()));
        o.setValor_unidade(Float.parseFloat(valor_unid.getText()));
        OvoDao.update(o);
        carregarReproducoes();
    	
    }
    

    @FXML
    void cadastrarOvo(ActionEvent event) {
    	LoteAves la = listLotes.getSelectionModel().getSelectedItem();
    	
    	if(cadastrar_data.getText()==null || cadastrar_data.getText()=="" || cadastrar_quant.getText()==null || cadastrar_quant.getText()==null) {
    		JOptionPane.showMessageDialog(null, "Os campos de data e quantidade são obrigatórios");
    	}else {
    		Ovo o = new Ovo();
            o.setLote(la);
            o.setData_producao(dataPadrao(cadastrar_data.getText()));
            o.setQtd_ovos(Integer.parseInt(cadastrar_quant.getText()));
            o.setValor_unidade(Float.parseFloat(valor_unid.getText()));
            OvoDao.create(o);
            
            titledPane.setVisible(false);
            
            carregarReproducoes();
            
            cadastrar_data.setText(null);
            valor_unid.setText(null);
            cadastrar_quant.setText(null);
            
            desativarComponentes(false);
    	}
    }

    @FXML
    void cancelarCadastro() {
    	titledPane.setVisible(false);
    	listLotes.setDisable(false);
    	listOvo.setDisable(false);
    }
    

    @FXML
    void carregarReproducoes() {
    	LoteAves la = listLotes.getSelectionModel().getSelectedItem();
    	ovos = OvoDao.read(la);
    	obsOvos =FXCollections.observableArrayList(ovos);
    	listOvo.setItems(obsOvos);
    	
    	idanimal.setText(String.valueOf(la.getId_animal()));
    	limparCampos();
    	atualizar.setDisable(true);
    	deletar.setDisable(true);
    	adicionar.setDisable(false);
    	
    }
    

    @FXML
    void deletarOvos() {
    	Ovo o = listOvo.getSelectionModel().getSelectedItem();
    	OvoDao.delete(o);
    	carregarReproducoes();
    }
    
    @FXML
    void novaProd() {
    	LoteAves la = listLotes.getSelectionModel().getSelectedItem();
    	desativarComponentes(true);
    	
        titledPane.setVisible(true);
        cadastrar_id.setText(String.valueOf(la.getId_animal()));
    	
    }
    

    @FXML
    void pegarDadosReproducoes() {
    	Ovo o = listOvo.getSelectionModel().getSelectedItem();
    	idovos.setText(String.valueOf(o.getId_ovo()));
    	quantidade.setText(String.valueOf(o.getQtd_ovos()));
    	valor_unid.setText(String.valueOf(o.getValor_unidade()));
    	data.setText(String.valueOf(o.getData_producao()));
        atualizar.setDisable(false);
        deletar.setDisable(false);
    }

    
    public Date dataPadrao(String s){
        int ano = Integer.parseInt(s.substring(0,4));
        int mes = Integer.parseInt(s.substring(5,7));
        int dia = Integer.parseInt(s.substring(8,10));   
        return new Date(ano-1900, mes-1, dia);
    }
    
    
    public void limparCampos(){
    	idovos.setText(null);
    	idanimal.setText(null);
      quantidade.setText(null);
    	valor_unid.setText(null);
        data.setText(null);
    }
    
    

    @FXML
    void carregarLotes() {
    	lotes = LoteAvesDAO.read();     
    	obsLotes = FXCollections.observableArrayList(lotes);
        listLotes.setItems(obsLotes);
    }
    
    public void desativarComponentes(Boolean b){
        listLotes.setDisable(b);
        listOvo.setDisable(b);
        adicionar.setDisable(b);
        atualizar.setDisable(b);
        deletar.setDisable(b);
    }
    
    
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        carregarLotes();
    }

}

