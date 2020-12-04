package br.com.farmcontrol.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CrudAnimalController implements Initializable {

	 @FXML
	    private AnchorPane tdo;

	    @FXML
	    private AnchorPane menu;

	    @FXML
	    private Button pagina_inicial;

	    @FXML
	    private ImageView img_paginainicial;

	    @FXML
	    private Button animal;

	    @FXML
	    private ImageView img_animal;

	    @FXML
	    private Button vacina;

	    @FXML
	    private ImageView img_vacina;

	    @FXML
	    private Button leite;

	    @FXML
	    private ImageView img_leite;

	    @FXML
	    private Button ovos;

	    @FXML
	    private ImageView img_ovo;

	    @FXML
	    private Button racao;

	    @FXML
	    private ImageView img_racao;

	    @FXML
	    private Button reproducao;

	    @FXML
	    private ImageView img_reproducao;

	    @FXML
	    private Button relatorio;

	    @FXML
	    private ImageView img_relatorio;

	    @FXML
	    private ImageView logo;

	    @FXML
	    private AnchorPane miolo;

	    @FXML
	    private Button cadastrar_animal;

	    @FXML
	    private Button editar_animal;

	    @FXML
	    private Button buscar_animal;

	    @FXML
	    private Button excluir_animal;
            
            @FXML
             private Text Titulo1;

            @FXML
            private TextField idText1;

             @FXML
             private Text Titulo2;

             @FXML
             private TextField idText12;
             
             
            @FXML
            private HBox idHbox;
            @FXML
            private AnchorPane IdPane;
            @FXML
            private Text idTituloCadastro;
            @FXML
            private TabPane idTabPane;
            @FXML
            private AnchorPane idTabDadosAnimal;
            @FXML
            private Text idRacaAnimal;
             @FXML
             private Text IdAnimal;
             @FXML
             private Text idDNA;
             @FXML
             private Text idDIE;
             @FXML
             private TextField IDtextR;
             @FXML
             private TextField idTextID;
             @FXML
             private DatePicker idCalDNA;
             @FXML
    private DatePicker idCalDIE;
    @FXML
    private AnchorPane idHP;
    @FXML
    private AnchorPane idRa;
    @FXML
    private AnchorPane idVa;
    @FXML
    private Button idCadastra;
    @FXML
    private Button idDescarta;
         
             
         
             @FXML
	    void mudarCor(MouseEvent event) {
                  
                 
	    }
            
             @FXML
	    private void cadastrarAnimal(ActionEvent event) throws IOException{
                
                Parent cadastro = FXMLLoader.load(getClass().getResource("/br/com/farmcontrol/view/resources/FXML/FXMLcadastro.fxml"));
                Scene cadastroCena = new Scene(cadastro); 
                Stage cadastroTela = (Stage) ((Node) event.getSource()).getScene().getWindow();
                
                cadastroTela.setScene(cadastroCena);
                cadastroTela.show();
                
	    }

	    @FXML
	    void buscarAnimal(ActionEvent event) {

	    }

            
	    @FXML
	    void editarAnimal(ActionEvent event) {

	    }

	    @FXML
	    void excluirAnimal(ActionEvent event) {

	    }

	    @FXML
	    void exibirPaginaAnimais(ActionEvent event) {

	    }

	    @FXML
	    void exibirPaginaInicial(ActionEvent event) {

	    }

	    @FXML
	    void exibirPaginaLeite(ActionEvent event) {

	    }

	    @FXML
	    void exibirPaginaOvos(ActionEvent event) {

	    }

	    @FXML
	    void exibirPaginaRacao(ActionEvent event) {

	    }

	    @FXML
	    void exibirPaginaRelatorios(ActionEvent event) {

	    }

	    @FXML
	    void exibirPaginaReproducao(ActionEvent event) {

	    }

	    @FXML
	    void exibirPaginaVacina(ActionEvent event) {

	    }
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}

}
