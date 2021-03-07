package application;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class convertisseur_DunitesController implements Initializable{
//pour declarer les elements
    @FXML
    private TextField txt$2;

    @FXML
    private TextField txt$1;

    @FXML
    private TextField txtL2;

    @FXML
    private TextField txtM1;

    @FXML
    private TextField txtL1;

    @FXML
    private TextField txtM2;

    @FXML
    private ComboBox<String> cboL1;

    @FXML
    private ComboBox<String> cboM2;

    @FXML
    private ComboBox<String> cboM1;

    @FXML
    private ComboBox<String> cboL2;

    @FXML
    private ComboBox<String> cbo$1;

    @FXML
    private ComboBox<String> cbo$2;
    
    //Declarer le liste pour les différentes longueurs qu'on va mettre dans le combo box
    private ObservableList<String> listeL = (ObservableList<String>)FXCollections.observableArrayList("Mile", "Kilomètre", "Mètre", "Centimètre", "Yard", "Pied", "Pouce");
    
    //ceci sont les valeurs des différentes longueurs
    double []longueur = {1.0, 1.60934, 1609.34, 160934.0, 1760.0, 5280.0, 63360.0}; 
    
    
    //Declarer le liste pour les différentes masses qu'on va mettre dans le combo box
    private ObservableList<String> listeM = (ObservableList<String>)FXCollections.observableArrayList("Kilogramme", "Gramme", "Milligramme", "Tonne", "Poid");
    
    //ceci sont les valeurs des différentes masses
    double []masses = {1.0, 1000.0, 1000000.0, 0.001, 2.205};
    
    //Declarer le liste pour les différentes monnaie qu'on va mettre dans le combo box
    private ObservableList<String> liste$ = (ObservableList<String>)FXCollections.observableArrayList("CAD", "USD", "EUR");
    
    //ceci snt les valeurs des différentes devises
    double []monnaie = {1.0, 0.79, 0.66};
    
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
    	cboL1.setItems(listeL);
    	cboL2.setItems(listeL);
    	cboL1.getSelectionModel().selectFirst();
    	cboL2.getSelectionModel().selectFirst();
    	cboM1.setItems(listeM);
    	cboM2.setItems(listeM);
    	cboM1.getSelectionModel().selectFirst();
    	cboM2.getSelectionModel().selectFirst();
    	cbo$1.setItems(liste$);
    	cbo$2.setItems(liste$);
    	cbo$1.getSelectionModel().selectFirst();
    	cbo$2.getSelectionModel().selectFirst();
    }
    //Cette methode va être utilisé pour convertir les nombres
    public void convertir(TextField txtA, TextField txtB, ComboBox boxA, ComboBox boxB, double []tab)
	{
		int item1 = boxA.getSelectionModel().getSelectedIndex();
		int item2 = boxB.getSelectionModel().getSelectedIndex();
		
		try
		{
		double taux = tab[item2]/tab[item1];
		double res = taux * (Double.parseDouble(txtA.getText()));
		txtB.setText(Double.toString(res));
		}
		catch (NumberFormatException e)
		{
			txtA.setText("0");
			Alert alert = new Alert(AlertType.ERROR);
    		alert.setHeaderText("Attention - Erreur");
    		alert.setTitle("Erreur");
    		alert.setContentText("Tu dois écrire un nombre");
    		alert.show();
    		txtA.requestFocus();
		}
	}
    
    @FXML // ceci est pour calculer le input dans le combo box 1 pour longueur
    void calculerL1()
    {
    	convertir(txtL1, txtL2, cboL1, cboL2, longueur);
    }
    
    @FXML // ceci est pour calculer le input dans le combo box 2 pour longueur
    void calculerL2()
    {
    	convertir(txtL2, txtL1, cboL2, cboL1, longueur);
    }
    
    @FXML // ceci est pour calculer le input dans le combo box 1 pour masse
    void calculerM1()
    {
    	convertir(txtM1, txtM2, cboM1, cboM2, masses);
    }
    
    @FXML // ceci est pour calculer le input dans le combo box 2 pour masse
    void calculerM2()
    {
    	convertir(txtM2, txtM1, cboM2, cboM1, masses);
    }
    
    @FXML // ceci est pour calculer le input dans le combo box 1 pour monnaie
    void calculer$1()
    {
    	convertir(txt$1, txt$2, cbo$1, cbo$2, monnaie);
    }
    
    @FXML // ceci est pour calculer le input dans le combo box 2 pour monnaie
    void calculer$2()
    {
    	convertir(txt$2, txt$1, cbo$2, cbo$1, monnaie);
    }
    
    @FXML
    void quitter() //Ceci est le code pour le button quitter sur chaque tab. Quand l'usager l'appuie, l'application ferme. Mais avant que l'application ferme. l'usager doit confirmer
    {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setHeaderText("Attention");
    	alert.setTitle("Confirmation");
    	alert.setContentText("Voulez-vous quitter l'application ?");
    	Optional<ButtonType> result = alert.showAndWait();
    	
    	if(result.get() == ButtonType.OK)
    	{
    		System.exit(0);
    	}
    }
}
