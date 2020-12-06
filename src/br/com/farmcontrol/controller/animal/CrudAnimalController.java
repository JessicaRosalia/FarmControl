package br.com.farmcontrol.controller.animal;

import java.io.IOException;
import java.text.Format;

import br.com.farmcontrol.controller.LayoutController;
import br.com.farmcontrol.model.dao.MamiferoDAO;
import br.com.farmcontrol.model.vo.Mamifero;
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

public class CrudAnimalController {

    @FXML
    private AnchorPane crudAnimal;
   

    @FXML
	public static Button cadastrar_animal;

    @FXML
    private Button editar_animal;

    @FXML
    private Button buscar_animal;

    @FXML
    private Button excluir_animal;

    @FXML
    private Button listar_animal;
    
    
    
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
    private Button idCadastra;

    @FXML
    private Button idDescarta;
    
    
    @FXML
    private AnchorPane PaneCadastroAnimal;



	@FXML
    void buscarAnimal(ActionEvent event) {

    }

    @FXML
    void cadastrarAnimal(ActionEvent event) throws IOException {
			
    	crudAnimal.getChildren().clear();
		AnchorPane a = null;

		try {
			a = FXMLLoader.load(getClass().getResource("/br/com/farmcontrol/view/resources/FXML/animal/CadastroAnimal.fxml"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		crudAnimal.getChildren().add(a);
    }

    @FXML
    void editarAnimal(ActionEvent event) {

    }

    @FXML
    void excluirAnimal(ActionEvent event) {

    }
    
    
    @FXML
    void descartarCadastro(ActionEvent event) {

    }

    @FXML
    void salvarCadastro(ActionEvent event) {
    	Mamifero m = new Mamifero();
    	//MamiferoDAO dao = new MamiferoDAO();
    	
    	m.setRaca(IDtextR.getText());
    	//m.setData_nasc_aquisicao();
    	
    	MamiferoDAO.create(m);
    	
    	IDtextR.setText("");
    	
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   

        
}




