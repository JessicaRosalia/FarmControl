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
import br.com.farmcontrol.model.dao.AnimalDAO;
import br.com.farmcontrol.model.dao.LeiteDAO;
import br.com.farmcontrol.model.dao.LoteAvesDAO;
import br.com.farmcontrol.model.dao.MamiferoDAO;
import br.com.farmcontrol.model.dao.OvoDao;
import br.com.farmcontrol.model.dao.ReproducaoDAO;
import br.com.farmcontrol.model.dao.VacinaDAO;
import br.com.farmcontrol.model.vo.Animal;
import br.com.farmcontrol.model.vo.Leite;
import br.com.farmcontrol.model.vo.LoteAves;
import br.com.farmcontrol.model.vo.Mamifero;
import br.com.farmcontrol.model.vo.Ovo;
import br.com.farmcontrol.model.vo.Reproducao;
import br.com.farmcontrol.model.vo.Vacina;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MenuRelatorioController {

    @FXML
    private Button rel_mamifero;

    @FXML
    private Button rel_aves;

    @FXML
    private Button rel_ovo;

    @FXML
    private Button rel_reprod;

    @FXML
    private Button rel_vacina;
    
    @FXML
    private Button rel_leite;
    

    @FXML
    void gerarRelatorioMamiferos() {
	    Document doc = new Document();
	    List<Mamifero> listM = MamiferoDAO.reportQuery();
	    String arquivoPdf = "C:/Users/Public/Mamiferos.pdf";
	    	
	    try {
			PdfWriter.getInstance(doc, new FileOutputStream(arquivoPdf));
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
			
		
			
			Paragraph p = new Paragraph("Relatório Mamíferos \t\t\t\t\t\t\t\t\t" + new SimpleDateFormat("dd/MM/yyyy").format(new Date())+"\n", new Font(FontFamily.HELVETICA, 18, Font.BOLD,null));
			p.setAlignment(1);
			
			p.setSpacingBefore(30);
			p.setSpacingAfter(20);
			doc.add(p);
			
			
			
			PdfPTable table = new PdfPTable( new float[] {0.1f, 0.3f, 0.3f, 0.1f,0.2f });
			
			PdfPCell cel1 = new PdfPCell(new Paragraph("ID Animal",new Font(FontFamily.HELVETICA,10, Font.BOLD, null)));
			cel1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cel1.setHorizontalAlignment(1);
			
			
			PdfPCell cel2 = new PdfPCell(new Paragraph("Raça",new Font(FontFamily.HELVETICA,10, Font.BOLD, null)));
			cel2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cel2.setHorizontalAlignment(3);
			
			PdfPCell cel3 = new PdfPCell(new Paragraph("Tipo",new Font(FontFamily.HELVETICA,10, Font.BOLD, null)));
			cel3.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cel3.setHorizontalAlignment(3);
			
			PdfPCell cel4 = new PdfPCell(new Paragraph("Sexo",new Font(FontFamily.HELVETICA,10, Font.BOLD, null)));
			cel4.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cel4.setHorizontalAlignment(3);
			
			
			PdfPCell cel5 = new PdfPCell(new Paragraph("Data Nasc./Compra",new Font(FontFamily.HELVETICA,10, Font.BOLD, null)));
			cel5.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cel5.setHorizontalAlignment(1);
			
		
			table.addCell(cel1);
			table.addCell(cel2);
			table.addCell(cel3);
			table.addCell(cel4);
			table.addCell(cel5);
			
			for(Mamifero animal : listM) {
				cel1 = new PdfPCell(new Paragraph(animal.getId_animal()+""));
				cel1.setHorizontalAlignment(3);
				
				
				cel2 = new PdfPCell(new Paragraph(animal.getRaca()+""));
				cel2.setHorizontalAlignment(3);
				
				cel3 = new PdfPCell(new Paragraph(animal.getTipo_mamifero()+""));
				cel3.setHorizontalAlignment(3);
				
				cel4 = new PdfPCell(new Paragraph(animal.getSexo_mamifero()+""));
				cel4.setHorizontalAlignment(3);
				
				cel5 = new PdfPCell(new Paragraph(animal.getData_nasc_aquisicao()+""));
				cel5.setHorizontalAlignment(3);
				
				table.addCell(cel1);
				table.addCell(cel2);
				table.addCell(cel3);
				table.addCell(cel4);
				table.addCell(cel5);
			}
			
			doc.add(table);
			
			doc.close();
			
			try {
				Desktop.getDesktop().open(new File(arquivoPdf));
			} catch (IOException e) {
				e.printStackTrace();
			}
				
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
	
			e.printStackTrace();
		}
    }

    
    @FXML
    void gerarRelatorioLotesAves() {
    	Document doc = new Document();
	    List<LoteAves> listA = LoteAvesDAO.reportQuery();
	    String arquivoPdf = "C:/Users/Public/LotesAves.pdf";
	    	
	    try {
			PdfWriter.getInstance(doc, new FileOutputStream(arquivoPdf));
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
			
		
			
			Paragraph p = new Paragraph("Relatório Lotes de Aves \t\t\t\t\t\t\t\t\t" + new SimpleDateFormat("dd/MM/yyyy").format(new Date())+"\n", new Font(FontFamily.HELVETICA, 18, Font.BOLD,null));
			p.setAlignment(1);

			p.setSpacingBefore(30);
			p.setSpacingAfter(20);
			doc.add(p);
			
			
			PdfPTable table = new PdfPTable( new float[] {0.2f, 0.4f, 0.2f, 0.2f});
			
			PdfPCell cel1 = new PdfPCell(new Paragraph("ID Animal",new Font(FontFamily.HELVETICA,10, Font.BOLD, null)));
			cel1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cel1.setHorizontalAlignment(3);
			
			PdfPCell cel2 = new PdfPCell(new Paragraph("Tipo",new Font(FontFamily.HELVETICA,10, Font.BOLD, null)));
			cel2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cel2.setHorizontalAlignment(3);
			
			PdfPCell cel3 = new PdfPCell(new Paragraph("Data Nascimento/Compra",new Font(FontFamily.HELVETICA,10, Font.BOLD, null)));
			cel3.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cel3.setHorizontalAlignment(1);
			
			PdfPCell cel4 = new PdfPCell(new Paragraph("Quantidade",new Font(FontFamily.HELVETICA,10, Font.BOLD, null)));
			cel4.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cel4.setHorizontalAlignment(3);
			
		
			table.addCell(cel1);
			table.addCell(cel2);
			table.addCell(cel3);
			table.addCell(cel4);
			
			for(LoteAves animal : listA) {
				cel1 = new PdfPCell(new Paragraph(animal.getId_animal()+""));
				cel1.setHorizontalAlignment(3);
				
				
				cel2 = new PdfPCell(new Paragraph(animal.getTipo_ave()));
				cel2.setHorizontalAlignment(3);
				
				cel3 = new PdfPCell(new Paragraph(animal.getData_nasc_aquisicao()+""));
				cel3.setHorizontalAlignment(3);
				
				cel4 = new PdfPCell(new Paragraph(animal.getQuantidade()+""));
				cel4.setHorizontalAlignment(3);
				
				table.addCell(cel1);
				table.addCell(cel2);
				table.addCell(cel3);
				table.addCell(cel4);
			}
			
			doc.add(table);
			
		
			
			doc.close();
			
			try {
				Desktop.getDesktop().open(new File(arquivoPdf));
			} catch (IOException e) {
				e.printStackTrace();
			}
				
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
	
			e.printStackTrace();
		}
    }

   
    @FXML
    void gerarRelatorioOvos() {
    	Document doc = new Document();
	    List<Ovo> listO = OvoDao.reportQuery();
	    String arquivoPdf = "C:/Users/Public/ProducaoOvos.pdf";
	    	
	    try {
			PdfWriter.getInstance(doc, new FileOutputStream(arquivoPdf));
			doc.open();
			
			
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
		
			
			Paragraph p = new Paragraph("Relatório de Produção de Ovos \t\t\t\t\t\t\t\t\t" + new SimpleDateFormat("dd/MM/yyyy").format(new Date())+"\n", new Font(FontFamily.HELVETICA, 18, Font.BOLD,null));
			p.setAlignment(1);
			
			p.setSpacingBefore(30);
			p.setSpacingAfter(20);
			doc.add(p);
			
			PdfPTable table = new PdfPTable( new float[] {0.2f, 0.3f, 0.2f, 0.1f, 0.2f});
			
			PdfPCell cel1 = new PdfPCell(new Paragraph("ID Animal",new Font(FontFamily.HELVETICA,10, Font.BOLD, null)));
			cel1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cel1.setHorizontalAlignment(3);
			
			
			PdfPCell cel2 = new PdfPCell(new Paragraph("Tipo",new Font(FontFamily.HELVETICA,10, Font.BOLD, null)));
			cel2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cel2.setHorizontalAlignment(3);
			
			PdfPCell cel3 = new PdfPCell(new Paragraph("Quantidade",new Font(FontFamily.HELVETICA,10, Font.BOLD, null)));
			cel3.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cel3.setHorizontalAlignment(3);
			
			PdfPCell cel4 = new PdfPCell(new Paragraph("Valor Unid.",new Font(FontFamily.HELVETICA,10, Font.BOLD, null)));
			cel4.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cel4.setHorizontalAlignment(3);
			
			PdfPCell cel5 = new PdfPCell(new Paragraph("Data Produção",new Font(FontFamily.HELVETICA,10, Font.BOLD, null)));
			cel5.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cel5.setHorizontalAlignment(3);
			
		
			table.addCell(cel1);
			table.addCell(cel2);
			table.addCell(cel3);
			table.addCell(cel4);
			table.addCell(cel5);
			
			for(Ovo ovo : listO) {
				
				cel1 = new PdfPCell(new Paragraph(ovo.getLote().getId_animal()+""));
				cel1.setHorizontalAlignment(3);
				
				
				if(ovo.getLote().getTipo_ave() == null ) {
					cel2 = new PdfPCell(new Paragraph(""));
				}else {
					cel2 = new PdfPCell(new Paragraph(ovo.getLote().getTipo_ave()));
				}
				cel2.setHorizontalAlignment(3);
				
				cel3 = new PdfPCell(new Paragraph(ovo.getQtd_ovos()+""));
				cel3.setHorizontalAlignment(3);
				
				cel4 = new PdfPCell(new Paragraph(ovo.getValor_unidade()+""));
				cel4.setHorizontalAlignment(3);
				
				cel5 = new PdfPCell(new Paragraph(ovo.getData_producao()+""));
				cel5.setHorizontalAlignment(3);
				
				table.addCell(cel1);
				table.addCell(cel2);
				table.addCell(cel3);
				table.addCell(cel4);
				table.addCell(cel5);
			}
			
			doc.add(table);
			
		
			
			doc.close();
			
			try {
				Desktop.getDesktop().open(new File(arquivoPdf));
			} catch (IOException e) {
				e.printStackTrace();
			}
				
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
	
			e.printStackTrace();
		} 
    }

   
    @FXML
    void gerarRelatorioReproducao() {
    	Document doc = new Document();
	    List<Reproducao> listRep = ReproducaoDAO.reportQuery();
	    String arquivoPdf = "C:/Users/Public/Reproducoes.pdf";
	    	
	    try {
			PdfWriter.getInstance(doc, new FileOutputStream(arquivoPdf));
			doc.open();
			
			
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
			
		
			
			Paragraph p = new Paragraph("Relatório de Reprodução \t\t\t\t\t\t\t\t\t" + new SimpleDateFormat("dd/MM/yyyy").format(new Date())+"\n", new Font(FontFamily.HELVETICA, 18, Font.BOLD,null));
			p.setAlignment(1);
			
			p.setSpacingBefore(30);
			p.setSpacingAfter(20);
			doc.add(p);
			
			
			
			PdfPTable table = new PdfPTable( new float[] {0.1f, 0.2f, 0.1f, 0.1f, 0.3f, 0.2f});
			
			PdfPCell cel1 = new PdfPCell(new Paragraph("ID Animal",new Font(FontFamily.HELVETICA,10, Font.BOLD, null)));
			cel1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cel1.setHorizontalAlignment(1);
			
			
			PdfPCell cel2 = new PdfPCell(new Paragraph("Raça",new Font(FontFamily.HELVETICA,10, Font.BOLD, null)));
			cel2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cel2.setHorizontalAlignment(3);
			
			PdfPCell cel3 = new PdfPCell(new Paragraph("ID Reprod.",new Font(FontFamily.HELVETICA,10, Font.BOLD, null)));
			cel3.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cel3.setHorizontalAlignment(1);
			
			PdfPCell cel4 = new PdfPCell(new Paragraph("Quantidade",new Font(FontFamily.HELVETICA,10, Font.BOLD, null)));
			cel4.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cel4.setHorizontalAlignment(1);
			
			PdfPCell cel5 = new PdfPCell(new Paragraph("Descrição",new Font(FontFamily.HELVETICA,10, Font.BOLD, null)));
			cel5.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cel5.setHorizontalAlignment(3);
			
			PdfPCell cel6 = new PdfPCell(new Paragraph("Data Reprod.",new Font(FontFamily.HELVETICA,10, Font.BOLD, null)));
			cel6.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cel6.setHorizontalAlignment(3);
			
		
			table.addCell(cel1);
			table.addCell(cel2);
			table.addCell(cel3);
			table.addCell(cel4);
			table.addCell(cel5);
			table.addCell(cel6);
			
			for(Reproducao rep : listRep) {
				cel1 = new PdfPCell(new Paragraph(rep.getAnimal().getId_animal()+""));
				cel1.setHorizontalAlignment(3);
				
				
				cel2 = new PdfPCell(new Paragraph(rep.getAnimal().getRaca()));
				cel2.setHorizontalAlignment(3);
				
				cel3 = new PdfPCell(new Paragraph(rep.getId_repoducao()+""));
				cel3.setHorizontalAlignment(3);
				
				cel4 = new PdfPCell(new Paragraph(rep.getQntd_reproducao()+""));
				cel4.setHorizontalAlignment(3);
				
				cel5 = new PdfPCell(new Paragraph(rep.getDescricao_reprod()+""));
				cel5.setHorizontalAlignment(3);
				
				cel6 = new PdfPCell(new Paragraph(rep.getData_reproducao()+""));
				cel6.setHorizontalAlignment(3);
				
				table.addCell(cel1);
				table.addCell(cel2);
				table.addCell(cel3);
				table.addCell(cel4);
				table.addCell(cel5);
				table.addCell(cel6);
			}
			
			doc.add(table);
			
			doc.close();
			
			try {
				Desktop.getDesktop().open(new File(arquivoPdf));
			} catch (IOException e) {
				e.printStackTrace();
			}
				
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
	
			e.printStackTrace();
		} 
    }
    
    

   @FXML
    void gerarRelatorioVacina() {
    	Document doc = new Document();
	    List<Vacina> listVac = VacinaDAO.reportQuery();
	    String arquivoPdf = "C:/Users/Public/Vacinas.pdf";
	    	
	    try {
			PdfWriter.getInstance(doc, new FileOutputStream(arquivoPdf));
			doc.open();
			
			
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
			
		
			
			Paragraph p = new Paragraph("Relatório de Vacinas \t\t\t\t\t\t\t\t\t" + new SimpleDateFormat("dd/MM/yyyy").format(new Date())+"\n", new Font(FontFamily.HELVETICA, 18, Font.BOLD,null));
			p.setAlignment(1);
			
			p.setSpacingBefore(30);
			p.setSpacingAfter(20);
			doc.add(p);
			
			
			
			PdfPTable table = new PdfPTable( new float[] {0.1f, 0.2f, 0.15f, 0.3f, 0.15f, 0.1f});
			
			PdfPCell cel1 = new PdfPCell(new Paragraph("ID Animal",new Font(FontFamily.HELVETICA,9, Font.BOLD, null)));
			cel1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cel1.setHorizontalAlignment(1);
			
			
			PdfPCell cel2 = new PdfPCell(new Paragraph("Raça",new Font(FontFamily.HELVETICA,9, Font.BOLD, null)));
			cel2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cel2.setHorizontalAlignment(1);
			
			PdfPCell cel3 = new PdfPCell(new Paragraph("Data Nasc/Compra",new Font(FontFamily.HELVETICA,9, Font.BOLD, null)));
			cel3.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cel3.setHorizontalAlignment(1);
			
			PdfPCell cel4 = new PdfPCell(new Paragraph("Descrição",new Font(FontFamily.HELVETICA,9, Font.BOLD, null)));
			cel4.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cel4.setHorizontalAlignment(1);
			
			PdfPCell cel5 = new PdfPCell(new Paragraph("Data Vacina",new Font(FontFamily.HELVETICA,9, Font.BOLD, null)));
			cel5.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cel5.setHorizontalAlignment(1);
			
			PdfPCell cel6 = new PdfPCell(new Paragraph("Custo",new Font(FontFamily.HELVETICA,9, Font.BOLD, null)));
			cel6.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cel6.setHorizontalAlignment(1);
			
		
			table.addCell(cel1);
			table.addCell(cel2);
			table.addCell(cel3);
			table.addCell(cel4);
			table.addCell(cel5);
			table.addCell(cel6);
			
			for(Vacina v : listVac) {
				cel1 = new PdfPCell(new Paragraph(v.getAnimal().getId_animal()+""));
				cel1.setHorizontalAlignment(3);
				
				
				cel2 = new PdfPCell(new Paragraph(v.getAnimal().getRaca()));
				cel2.setHorizontalAlignment(3);
				
				if(v.getAnimal().getData_nasc_aquisicao() == null) {
					cel3 = new PdfPCell(new Paragraph(""));
				}else {
					cel3 = new PdfPCell(new Paragraph(v.getAnimal().getData_nasc_aquisicao()+""));
				}
				cel3.setHorizontalAlignment(3);
				
				cel4 = new PdfPCell(new Paragraph(v.getDescricao()));
				cel4.setHorizontalAlignment(3);
				
				cel5 = new PdfPCell(new Paragraph(v.getData_vacina()+""));
				cel5.setHorizontalAlignment(3);
				
				cel6 = new PdfPCell(new Paragraph(v.getCusto()+""));
				cel6.setHorizontalAlignment(3);
				
				table.addCell(cel1);
				table.addCell(cel2);
				table.addCell(cel3);
				table.addCell(cel4);
				table.addCell(cel5);
				table.addCell(cel6);
			}
			
			doc.add(table);
			
			doc.close();
			
			try {
				Desktop.getDesktop().open(new File(arquivoPdf));
			} catch (IOException e) {
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
   	Document doc = new Document();
	    List<Leite> listLeite = LeiteDAO.reportQuery();
	    String arquivoPdf = "C:/Users/Public/ProducaoLeite.pdf";
	    	
	    try {
			PdfWriter.getInstance(doc, new FileOutputStream(arquivoPdf));
			doc.open();
			
			
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
			
		
			
			Paragraph p = new Paragraph("Relatório de Produção de Leite \t\t\t\t\t\t\t\t\t" + new SimpleDateFormat("dd/MM/yyyy").format(new Date())+"\n", new Font(FontFamily.HELVETICA, 18, Font.BOLD,null));
			p.setAlignment(1);
			
			p.setSpacingBefore(30);
			p.setSpacingAfter(20);
			doc.add(p);
			
			
			
			PdfPTable table = new PdfPTable( new float[] {0.2f, 0.2f, 0.2f, 0.2f, 0.2f});
			
			PdfPCell cel1 = new PdfPCell(new Paragraph("ID Animal",new Font(FontFamily.HELVETICA,9, Font.BOLD, null)));
			cel1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cel1.setHorizontalAlignment(1);
			
			
			PdfPCell cel2 = new PdfPCell(new Paragraph("Tipo",new Font(FontFamily.HELVETICA,9, Font.BOLD, null)));
			cel2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cel2.setHorizontalAlignment(1);
			
			PdfPCell cel3 = new PdfPCell(new Paragraph("Quantidade",new Font(FontFamily.HELVETICA,9, Font.BOLD, null)));
			cel3.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cel3.setHorizontalAlignment(1);
			
			PdfPCell cel4 = new PdfPCell(new Paragraph("Data Prod.",new Font(FontFamily.HELVETICA,9, Font.BOLD, null)));
			cel4.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cel4.setHorizontalAlignment(1);
			
			PdfPCell cel5 = new PdfPCell(new Paragraph("Valor Litro",new Font(FontFamily.HELVETICA,9, Font.BOLD, null)));
			cel5.setBackgroundColor(BaseColor.LIGHT_GRAY);
			cel5.setHorizontalAlignment(1);
			
		
			table.addCell(cel1);
			table.addCell(cel2);
			table.addCell(cel3);
			table.addCell(cel4);
			table.addCell(cel5);
			
			for(Leite l : listLeite) {
				cel1 = new PdfPCell(new Paragraph(l.getAnimal().getId_animal()+""));
				cel1.setHorizontalAlignment(3);
				
				if(l.getAnimal().getTipo_mamifero() == null) {
					cel2 = new PdfPCell(new Paragraph(""));
				}else {
					cel2 = new PdfPCell(new Paragraph(l.getAnimal().getTipo_mamifero()+""));
				}
				cel2.setHorizontalAlignment(3);
				
				cel3 = new PdfPCell(new Paragraph(l.getQtd_leite()+""));
				cel3.setHorizontalAlignment(3);
				
				cel4 = new PdfPCell(new Paragraph(l.getData_producao()+""));
				cel4.setHorizontalAlignment(3);

				cel5 = new PdfPCell(new Paragraph(l.getValor_litro()+""));
				cel5.setHorizontalAlignment(3);
				
				table.addCell(cel1);
				table.addCell(cel2);
				table.addCell(cel3);
				table.addCell(cel4);
				table.addCell(cel5);
			}
			
			doc.add(table);
			
			doc.close();
			
			try {
				Desktop.getDesktop().open(new File(arquivoPdf));
			} catch (IOException e) {
				e.printStackTrace();
			}
				
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (DocumentException e) {
	
			e.printStackTrace();
		} 
   }

}
