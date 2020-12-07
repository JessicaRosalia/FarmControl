package br.com.farmcontrol.controller.racao;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class CrudRacaoController {

    @FXML
    private AnchorPane crudRacao;
    

    @FXML
    private AnchorPane PaneCadastroRacao;


    @FXML
    private Button cadastrar_racao;

    @FXML
    private Button editar_racao;

    @FXML
    private Button buscar_racao;

    @FXML
    private Button excluir_racao;

    @FXML
    private Button listar_racao;
    
    @FXML
    private Text idTituloCadastro;

    @FXML
    private TabPane idTabPane;

    @FXML
    private AnchorPane idTabDadosAnimal;

    @FXML
    private Text idRacaAnimal;

    @FXML
    private Text idDNA;

    @FXML
    private Text idDIE;

    @FXML
    private TextField IDtextR;

    @FXML
    private DatePicker idCalDNA;

    @FXML
    private DatePicker idCalDIE;

    @FXML
    private AnchorPane idHP;

    @FXML
    private Text idQuantRep;

    @FXML
    private DatePicker idCalDNA1;

    @FXML
    private Text idDescRep;

    @FXML
    private Text idDatNascRep;

    @FXML
    private TextField textQuantRep;

    @FXML
    private TextArea textDescRep;

    @FXML
    private AnchorPane idRa;

    @FXML
    private Text idQuantRa;

    @FXML
    private Text idCustRa;

    @FXML
    private Text idDescRa;

    @FXML
    private TextArea textDescRa;

    @FXML
    private TextField textQuantRa;

    @FXML
    private TextField textCustRa;

    @FXML
    private DatePicker calDatComR;

    @FXML
    private Text idDatCompRa;

    @FXML
    private AnchorPane idVa;

    @FXML
    private TextField textCustVa;

    @FXML
    private Text idDescVa;

    @FXML
    private Text idDatVac;

    @FXML
    private TextArea textDescVa;

    @FXML
    private DatePicker calDatVa;

    @FXML
    private Button idDescarta;
    
    @FXML
    private Button btn_salvarRacao;

    
    
    

    @FXML
    void buscarRacao(ActionEvent event) {

    }

    @FXML
    void cadastrarRacao(ActionEvent event) {
    	crudRacao.getChildren().clear();
		AnchorPane ap = null;

		try {
			ap = FXMLLoader.load(getClass().getResource("/br/com/farmcontrol/view/resources/FXML/racao/ExclusaoRacao.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		crudRacao.getChildren().add(ap);
    }

    @FXML
    void editarRacao(ActionEvent event) {
    	crudRacao.getChildren().clear();
		AnchorPane ap = null;

		try {
			ap = FXMLLoader.load(getClass().getResource("/br/com/farmcontrol/view/resources/FXML/racao/EdicaoRacao.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		crudRacao.getChildren().add(ap);
    }

    @FXML
    void excluirRacao(ActionEvent event) {
    	crudRacao.getChildren().clear();
		AnchorPane ap = null;

		try {
			ap = FXMLLoader.load(getClass().getResource("/br/com/farmcontrol/view/resources/FXML/racao/ExclusaoRacao.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		crudRacao.getChildren().add(ap);
    }

    @FXML
    void listarRacao(ActionEvent event) {
    	crudRacao.getChildren().clear();
		AnchorPane ap = null;

		try {
			ap = FXMLLoader.load(getClass().getResource("/br/com/farmcontrol/view/resources/FXML/racao/ListagemRacao.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		crudRacao.getChildren().add(ap);
    }
    
    
    @FXML
    void salvar(ActionEvent event) {

    }

}
