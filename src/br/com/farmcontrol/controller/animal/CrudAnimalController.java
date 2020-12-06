package br.com.farmcontrol.controller.animal;

import java.io.IOException;
import java.time.LocalDate;

import br.com.farmcontrol.model.dao.MamiferoDAO;
import br.com.farmcontrol.model.dao.RacaoDAO;
import br.com.farmcontrol.model.dao.ReproducaoDAO;
import br.com.farmcontrol.model.dao.VacinaDAO;
import br.com.farmcontrol.model.vo.Mamifero;
import br.com.farmcontrol.model.vo.Racao;
import br.com.farmcontrol.model.vo.Reproducao;
import br.com.farmcontrol.model.vo.Vacina;
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
    private AnchorPane PaneCadastroAnimal;

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
    private TextField IDtextRaca;

    @FXML
    private DatePicker idCalDNA;

    @FXML
    private DatePicker idCalDIE;

    @FXML
    private AnchorPane idHP;

    @FXML
    private Text idQuantRep;

    @FXML
    private DatePicker idCalDNAReprod;

    @FXML
    private Text idDescRep;

    @FXML
    private Text idDatNascRep;

    @FXML
    private TextField textQuantReprod;

    @FXML
    private TextArea textDescReprod;

    @FXML
    private AnchorPane idRa;

    @FXML
    private Text idQuantRa;

    @FXML
    private Text idCustRa;

    @FXML
    private Text idDescRa;

    @FXML
    private TextArea textDescRacao;

    @FXML
    private TextField textQuantRacao;

    @FXML
    private TextField textCustRacao;

    @FXML
    private DatePicker calDatComRacao;

    @FXML
    private Text idDatCompRa;

    @FXML
    private AnchorPane idVa;

    @FXML
    private Text idDescVa;

    @FXML
    private Text idDatVac;

    @FXML
    private TextArea textDescVacina;

    @FXML
    private TextField textCustVacina;

    @FXML
    private DatePicker calDatVacina;

    @FXML
    private Button idCadastra;

    @FXML
    private Button idDescarta;
    
    
    
    



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
    	Mamifero mamifero = new Mamifero();
    	Reproducao reprod = new Reproducao();
    	Racao racao = new Racao();
    	Vacina vacina = new Vacina();
    	
    	mamifero.setRaca(IDtextRaca.getText());
    	
    	LocalDate ld = idCalDNA.getValue();
    	java.sql.Date sqlDateDNA = java.sql.Date.valueOf(ld);
    	mamifero.setData_nasc_aquisicao(sqlDateDNA);
    	
    	LocalDate ld2 = idCalDNAReprod.getValue();
    	java.sql.Date sqlDateReprod = java.sql.Date.valueOf(ld2);
    	
    	
    	reprod.setData_reproducao(sqlDateReprod);
    	reprod.setQntd_reproducao(Integer.parseInt(textQuantReprod.getText()));
    	reprod.setDescricao_reprod(textDescReprod.getText());
    	reprod.setAnimal(mamifero);
    	
    	
    	LocalDate ld3 = calDatComRacao.getValue();
    	java.sql.Date sqlDateCompra = java.sql.Date.valueOf(ld3);
    	racao.setData(sqlDateCompra);
    	
    	racao.setQtd_racao(Integer.parseInt(textQuantRacao.getText()));
    	racao.setCusto(Float.parseFloat(textCustRacao.getText()));
    	racao.setDescricao(textDescRacao.getText());
    	racao.setAnimal(mamifero);
    	
    	
    	LocalDate ld4 = calDatVacina.getValue();
    	java.sql.Date sqlDateVacina = java.sql.Date.valueOf(ld4);
    	vacina.setData_vacina(sqlDateVacina);
    	
    	vacina.setCusto(Float.parseFloat(textCustVacina.getText()));
    	vacina.setDescricao(textDescVacina.getText());
    	vacina.setAnimal(mamifero);
    
    	
    	
    	
    	
    	
    	MamiferoDAO.create(mamifero);
    	//ReproducaoDAO.create(reprod);
    	RacaoDAO.create(racao);
    	//VacinaDAO.create(vacina);
    	
    	IDtextRaca.setText("");
    	idCalDNA.setValue(null);
    	
    	idCalDNAReprod.setValue(null);
    	textQuantReprod.setText("");
    	textDescReprod.setText("");
    	
    	calDatComRacao.setValue(null);
    	textQuantRacao.setText("");
    	textCustRacao.setText("");
    	textDescRacao.setText("");
    	
    	calDatVacina.setValue(null);
    	textCustVacina.setText("");
    	textDescVacina.setText("");
    	
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   

        
}




