/**
 * Sample Skeleton for 'PowerOutages.fxml' Controller Class
 */

package it.polito.tdp.poweroutages;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.poweroutages.db.PowerOutageDAO;
import it.polito.tdp.poweroutages.model.Blackout;
import it.polito.tdp.poweroutages.model.Model;
import it.polito.tdp.poweroutages.model.Nerc;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class PowerOutagesController {

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML // fx:id="choiceNerc"
    private ChoiceBox<Nerc> choiceNerc; // Value injected by FXMLLoader

    @FXML // fx:id="txtAnni"
    private TextField txtAnni; // Value injected by FXMLLoader

    @FXML // fx:id="txtOre"
    private TextField txtOre; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader
    
    private Model model;
    
    
    public void setModel(Model model) {
		this.model = model;
		this.choiceNerc.getItems().addAll(model.getNercList());
	}

    @FXML
    void doAnalisi(ActionEvent event) {
    		int anni = Integer.parseInt(this.txtAnni.getText());
    		int ore = Integer.parseInt(this.txtOre.getText());
    		
    		this.txtRisultato.appendText("Persone coinvolte: "+this.model.calcolaVittime(this.model.getSequenza(this.choiceNerc.getValue(),anni,ore))+"\n");
    		this.txtRisultato.appendText("Ore totali: "+this.model.calcolaOreTotali(this.model.getSequenza(this.choiceNerc.getValue(),anni,ore))+"\n");
    		
    		for(Blackout b : this.model.getSequenza(this.choiceNerc.getValue(),anni,ore)) 
    			this.txtRisultato.appendText(b.toString()+"\n");
    		
    		
    		
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert choiceNerc != null : "fx:id=\"choiceNerc\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtAnni != null : "fx:id=\"txtAnni\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert txtOre != null : "fx:id=\"txtOre\" was not injected: check your FXML file 'PowerOutages.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'PowerOutages.fxml'.";

    }
}
