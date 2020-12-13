package br.com.farmcontrol.controller.relatorio;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.farmcontrol.connection.ConnectionFactory;
import br.com.farmcontrol.model.dao.AnimalDAO;
import br.com.farmcontrol.model.vo.Animal;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuRelatorioController {

	private static ConnectionFactory conexao = null;
    @FXML
    private Button rel_animal;

    @FXML
    private Button rel_leite;

    @FXML
    private Button rel_ovo;

    @FXML
    private Button rel_reprod;

    @FXML
    private Button rel_vacina;

    @FXML
    void gerarRelatorioAnimais() {
    	conexao =new ConnectionFactory();
    	
   	 
    Document doc = new Document();
    List<Animal> listA = AnimalDAO.read();
    String arquivoPdf = "C:/Users/Public/relatorio.pdf";
    	
    try {
		PdfWriter w = PdfWriter.getInstance(doc, new FileOutputStream(arquivoPdf));
		doc.open();
		
		doc.setPageCount(10);
		
		Image imgHeader=null;
		try {
			imgHeader = Image.getInstance("C:\\Users\\jessi\\Documents\\projeto-poo\\src\\br\\com\\farmcontrol\\view\\resources\\images\\logo.png");
		} catch (MalformedURLException e1) {
			
			e1.printStackTrace();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
		imgHeader.setAlignment(Element.ALIGN_CENTER);
		doc.add(imgHeader);
		
	
		
		Paragraph p = new Paragraph("Relatório Animais \t\t\t\t\t\t\t\t\t" + new SimpleDateFormat("dd/MM/yyyy").format(new Date())+"\n", new Font(FontFamily.HELVETICA, 18, Font.BOLD,null));
		p.setAlignment(1);
		
		//doc.add(p);
		//p = new Paragraph();
		p.setSpacingBefore(30);
		p.setSpacingAfter(20);
		doc.add(p);
		
		
		
		//Paragraph p = new Paragraph("This is a paragraph", new Font(FontFamily.HELVETICA, 18, Font.BOLDITALIC, new BaseColor(255, 255, 255)) );
		
		
		PdfPTable table = new PdfPTable( new float[] {0.2f, 0.4f, 0.4f});
		
		PdfPCell cel1 = new PdfPCell(new Paragraph("ID Animal",new Font(FontFamily.HELVETICA,10, Font.BOLD, null)));
		cel1.setBackgroundColor(BaseColor.LIGHT_GRAY);
		//cel1.setBorder(Rectangle.BOTTOM);
		cel1.setHorizontalAlignment(3);
		
		
		PdfPCell cel2 = new PdfPCell(new Paragraph("Raça",new Font(FontFamily.HELVETICA,10, Font.BOLD, null)));
		cel2.setBackgroundColor(BaseColor.LIGHT_GRAY);
		//cel2.setBorder(Rectangle.BOTTOM);
		cel2.setHorizontalAlignment(3);
		
		PdfPCell cel3 = new PdfPCell(new Paragraph("Data Nascimento/Compra",new Font(FontFamily.HELVETICA,10, Font.BOLD, null)));
		cel3.setBackgroundColor(BaseColor.LIGHT_GRAY);
		//cel3.setBorder(Rectangle.BOTTOM);
		cel3.setHorizontalAlignment(3);
		
	
		table.addCell(cel1);
		table.addCell(cel2);
		table.addCell(cel3);
		
		for(Animal animal : listA) {
			cel1 = new PdfPCell(new Paragraph(animal.getId_animal()+""));
		//	cel1.setBorder(Rectangle.NO_BORDER);
			cel1.setHorizontalAlignment(3);
			
			
			cel2 = new PdfPCell(new Paragraph(animal.getRaca()));
		//	cel2.setBorder(Rectangle.NO_BORDER);
			cel2.setHorizontalAlignment(3);
			
			cel3 = new PdfPCell(new Paragraph(animal.getData_nasc_aquisicao()+""));
		//	cel3.setBorder(Rectangle.NO_BORDER);
			cel3.setHorizontalAlignment(3);
			
			table.addCell(cel1);
			table.addCell(cel2);
			table.addCell(cel3);
		}
		
		doc.add(table);
		
		w.setPageCount(1);
		
		doc.close();
		
		try {
			Desktop.getDesktop().open(new File(arquivoPdf));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (DocumentException e) {

		e.printStackTrace();
	}
	
   
    }

    @FXML
    void gerarRelatorioLeite() {

    }

    @FXML
    void gerarRelatorioOvos() {

    }

    @FXML
    void gerarRelatorioReproducao() {

    }

    @FXML
    void gerarRelatorioVacina() {

    }

}
