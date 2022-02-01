package com.example.chu;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.ResourceBundle;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.concurrent.Worker;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import org.apache.commons.io.FileUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
public class pay_facture  implements Initializable{
	@FXML
	 TextField invoice;
	@FXML
	WebView webView ;
	@FXML
	Button print ;
	@FXML 
	Button pay ;
	@Override
	public void initialize(URL location , ResourceBundle resources) {
		reset();
	}
	public void reset() {
		print.setVisible(false);
		pay.setVisible(false);
		invoice.setAccessibleText("Please Type Invoice's ID");
		webView.setVisible(false);
	}
public void CreateFile(ActionEvent event) throws SQLException, DocumentException, MalformedURLException, IOException, URISyntaxException  {
	print.setVisible(false);
	pay.setVisible(false);
	webView.setVisible(false);
	String invoice_id_form = invoice.getText();
	String style = "-fx-border-color : red ;-fx-border-width:2px;";
	String invoice_string = invoice.getText() ;
	Connect connectnow = new Connect();
	Connection connectDB = connectnow.getConnection();
	if(invoice_string.isEmpty()== false) {
		String Query = "Select * from patient,invoice where invoice.patient = patient.fullname and invoice.invoice_id='"+invoice_id_form+"'";
			Statement  statement = connectDB.createStatement();
			ResultSet resultSet = statement.executeQuery(Query);
              if(resultSet.next()) {
           webView.setVisible(true);
         		 String styleSucces = "-fx-border-color : green ;-fx-border-width:2px;";
         		 String invoice_status = resultSet.getString("invoice.invoice_status") ;
        		 invoice.setStyle(styleSucces);
        		 if(invoice_status.equals("in progress")) {
        			 pay.setVisible(true);
        		 }
        		 if(invoice_status.equals("paied")) {
        			 print.setVisible(true);
        		 }
				String Invoice_id = resultSet.getString("invoice.invoice_id");
					Document doc = new Document();
					String string_file = "C:\\Users\\Sharef Eddine\\Desktop\\report\\Ainvoice-"+invoice_id_form+".pdf";
					FileOutputStream fos = new FileOutputStream(string_file);
					PdfWriter.getInstance(doc, fos);
						doc.open();
						Image img = Image.getInstance("C:\\Users\\Sharef Eddine\\Eclipse new Version\\CHU_intlj\\src\\main\\resources\\pic\\CHU_logo_invoice.jpg");
						Paragraph p1 = new Paragraph("People's Democratic Republic of Algeria\r\n"
								+ "Ministry of Health, Population and Hospital Reform\r\n"
								+ "Directorate of Health of the state of Batna\r\n"
								+ "University Hospital Center Batna\r\n"
								+ "Benflis Tohami") ;	
						img.setAbsolutePosition(10, 730);
						p1.setAlignment(Element.ALIGN_CENTER);
						p1.setSpacingAfter(50);
						doc.add(img);
						doc.add(p1);
						String date =  java.time.LocalDate.now().toString();
						String invoice = "Invoice's type : Personnal type                   Invoice's ID :"+Invoice_id + "                      Batna in : " + date; 
						Paragraph invoicetitle = new Paragraph(invoice);
						doc.add(invoicetitle);
						Paragraph p_patient_info = new Paragraph("Patient's personal information");
						p_patient_info.setSpacingAfter(20);
						p_patient_info.setSpacingBefore(10);
						p_patient_info.setAlignment(Element.ALIGN_CENTER);
						doc.add(p_patient_info);
				        String firstname_string="Firstname : "+ resultSet.getString("patient.firstname");
				        Paragraph firstname_p = new Paragraph(firstname_string);
				        firstname_p.setSpacingAfter(10);
				        doc.add(firstname_p);
				        String lastname_string="Lastname :   "+ resultSet.getString("patient.lastname");
				        Paragraph lastname_p = new Paragraph(lastname_string);
				        lastname_p.setSpacingAfter(10);
				        doc.add(lastname_p);
				        String age_string = "Age : " + resultSet.getString("patient.age") + " years";
				        Paragraph age_p  = new  Paragraph(age_string);
				        age_p.setSpacingAfter(10);
				        doc.add(age_p);
				        String gendre_string = "Gendre : "+ resultSet.getString("patient.gendre");
				        Paragraph gendre_p = new Paragraph(gendre_string);
				        gendre_p.setSpacingAfter(10);
				        doc.add(gendre_p);
				        String birthday_string = "Birthday : " + resultSet.getString("patient.birthdate");
				        Paragraph  birthday_p = new Paragraph(birthday_string);
				        birthday_p.setSpacingAfter(10);
				        doc.add(birthday_p);
				        String adress_string = "Adress : "+ resultSet.getString("patient.adress");
				        Paragraph adress_p = new Paragraph(adress_string);
				        adress_p.setSpacingAfter(10);
				        doc.add(adress_p);
				        String mobile_string = "Mobile : "+ resultSet.getString("patient.mobile");
				        Paragraph mobile_p = new Paragraph(mobile_string);
				        mobile_p.setSpacingAfter(10);
				        doc.add(mobile_p);
				        String sn_string = "SN : "+ resultSet.getString("patient.SN");
				        Paragraph sn_p = new Paragraph(sn_string);
				        sn_p.setSpacingAfter(10);
				        doc.add(sn_p);
				        // Table 
				        Paragraph invoice_info = new Paragraph("Invoice information");
				        invoice_info.setAlignment(Element.ALIGN_CENTER);
				        doc.add(invoice_info);
				        PdfPTable table = new PdfPTable(2);
				        PdfPCell servicetype_cell = new PdfPCell(new Phrase("patient.Service type"));
				        PdfPCell price_cell = new PdfPCell(new Phrase("Price"));
				        price_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				        table.addCell(servicetype_cell);
				        table.addCell(price_cell);
				        table.setHeaderRows(1);
				        String medical_phrase = resultSet.getString("invoice.Medical_Supplies_Medicines") + "DA";
				        if(medical_phrase != "0") {
				        PdfPCell Medicalsupplies_cell = new PdfPCell(new Phrase("Medical Supplies "));
				        table.addCell(Medicalsupplies_cell);
				        PdfPCell medical_price_cell = new PdfPCell(new Phrase(medical_phrase));
				        medical_price_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(medical_price_cell);
				        }
				        String doctor_phrase = resultSet.getString("invoice.Doctors_burden")+ "DA";
				        if(doctor_phrase!= "0") {
				        PdfPCell doctor_cell = new PdfPCell(new Phrase("Doctors' burden"));
				        table.addCell(doctor_cell);	
				        PdfPCell doctor_price_cell = new PdfPCell(new Phrase(doctor_phrase));
				        doctor_price_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
						table.addCell(doctor_price_cell);
				        }
				        String lab_phrase = resultSet.getString("invoice.lab_test")+ "DA";
				        if(lab_phrase != "0") {
				       	PdfPCell lab_test_cell = new PdfPCell(new Phrase("Lab tests"));
					        table.addCell(lab_test_cell);
					        PdfPCell lab_test_price_cell = new PdfPCell(new Phrase(lab_phrase));
					        lab_test_price_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					        table.addCell(lab_test_price_cell);	
				        }
				        String xray_phrase = resultSet.getString("invoice.xray")+ "DA";
				        if(xray_phrase != "0") {
					    PdfPCell xray_cell = new PdfPCell(new Phrase("X-ray examinations"));
				        table.addCell(xray_cell);
				        PdfPCell xray_price_cell = new PdfPCell(new Phrase(xray_phrase));
				        xray_price_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				        table.addCell(xray_price_cell);
				        }
				        String hostablied_phrase = resultSet.getString("invoice.hostablised")+ "DA";
				        if(hostablied_phrase != "0") {
				        	PdfPCell hostablied_cell = new PdfPCell(new Phrase("Hostablied"));
					        table.addCell(hostablied_cell);
					        PdfPCell hostablied_price_cell = new PdfPCell(new Phrase(hostablied_phrase));
					        hostablied_price_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					        table.addCell(hostablied_price_cell); 
				        }
				        String op_phrase = resultSet.getString("invoice.operations_cost")+ "DA";
				        if(resultSet.getString("operations_cost") != "0") {
				        	PdfPCell op_cell = new PdfPCell(new Phrase("Operations"));
					        table.addCell(op_cell);
					        PdfPCell op_price_cell = new PdfPCell(new Phrase(op_phrase));
					        op_price_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
					        table.addCell(op_price_cell);
				        }
				        String total_phrase = resultSet.getString("invoice.somme")+ "DA";
				        PdfPCell total_cell = new PdfPCell(new Phrase("Total"));
				        table.addCell(total_cell);
				        PdfPCell total_price_cell = new PdfPCell(new Phrase(total_phrase));
				        total_price_cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				        table.addCell(total_price_cell);
				        table.setSpacingBefore(20);
				        doc.add(table);
				        Paragraph info_s = new Paragraph("Signature of the service'head             the financial director's signature          Patient's signature");
				        info_s.setSpacingAfter(120);
				        info_s.setSpacingBefore(10);
				        doc.add(info_s);
				        String footer_string = "University Hospital Center Batna - Tazoult Road 5000 Phone number: 033308401";
				        Paragraph footer_p = new Paragraph(footer_string);
				        footer_p.setAlignment(Element.ALIGN_CENTER);
				        doc.add(footer_p);
						doc.close();
						    WebEngine engine = webView.getEngine();
						    String url = getClass().getResource("/web/viewer.html").toURI().toString();
						    engine.setUserStyleSheetLocation(getClass().getResource("/web/viewer.css").toURI().toString());
						    engine.setJavaScriptEnabled(true);
						    engine.load(url);
						    engine.getLoadWorker().stateProperty().addListener((obs, oldV, newV) -> {
						    	 if (Worker.State.SUCCEEDED == newV) {
						    	        InputStream stream = null;
						    	        try {
						    	          byte[] bytes = FileUtils.readFileToByteArray(new File(string_file));
						    	          String base64 = Base64.getEncoder().encodeToString(bytes);
						    	          engine.executeScript("openFileFromBase64('" + base64 + "')");
						    	        } catch (Exception ex) {
						    	          ex.printStackTrace();
						    	        } finally {
						    	          if (stream != null) {  
						    	              try {
												stream.close();
											} catch (IOException e) {
												e.printStackTrace();
											}
						    	          }
						    	        }
						    	      }
						    	    });		
			}else {
				invoice.setStyle(style);	
				Alert ale = new Alert(AlertType.ERROR) ;
				ale.setHeaderText("Not found");
				ale.setContentText("Invoice  "+ invoice_string + " : Not found");
				ale.show() ;
				reset();
				}
	} else {
		invoice.setStyle(style);
		Alert ale = new Alert(AlertType.ERROR) ;
		ale.setHeaderText("TextField is Empty");
		ale.setContentText("TextField is empty please fill it ");
		ale.show() ;
		reset();
	}	
}
public void Print(ActionEvent event) {
	
}
public void Pay(ActionEvent event) throws SQLException {
	String invoice_id_form = invoice.getText();
	Connect connectnow = new Connect();
	Connection connectDB = connectnow.getConnection();
	String query = "Update invoice set invoice_status=? where invoice_id=?";
PreparedStatement ps = connectDB.prepareStatement(query);
ps.setString(1, "paied");
ps.setString(2, invoice_id_form);
int pse = ps.executeUpdate();
if(pse==1) {
	pay.setVisible(false);
	Alert ale = new Alert(AlertType.CONFIRMATION);
	ale.setHeaderText("Invoice");
	ale.setContentText("Invoice "+invoice_id_form+" : paied Succfuly");
	ale.show();
	print.setVisible(true);
} else {
	Alert ale = new Alert(AlertType.ERROR);
	ale.setHeaderText("Invoice ");
	ale.setContentText("Invoice "+invoice_id_form+" :  not paied ");
	ale.show();
	reset();
}
}
}
