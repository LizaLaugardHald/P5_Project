
package com.mycompany.p5;

import java.net.URL; //til at definere stien til ressourcen, når man laver den næste scene (fx se l.45, hvor stien sættes til en variabel, som er typen URL
import javafx.fxml.FXMLLoader; //l.46, loade FXML filer (bruges til scenes)
import javafx.fxml.FXML; 
import javafx.scene.Parent; //denne klasse håndterer alle scener, så den bestemmer hvordan scenerne hænger samme hirakisk (fx hvad er hovedscene, hvad er underscene osv.)
import javafx.scene.Scene;  
import javafx.scene.control.Button; 
import javafx.scene.control.TableColumn; //Det er selve de 3 kolonner man laver på SceneBuilder til at sortere scanninger
import javafx.scene.control.TableView; //rammen hvor TableColumn ligger i
import javafx.scene.control.cell.PropertyValueFactory; //Når man laver en ny række i kolonnen skal den vide hvad for en værdi der skal matche hvad for en kolonne
import javafx.scene.control.Label; //styrer labels
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable; //for at kunne styre hvad der sker når man starter scenen
import java.util.ResourceBundle; //gør at programmet kan indlæse ting fra ressourcepakker 
import java.time.LocalDate;  //for at kunne sætte dato og tid
import javafx.collections.FXCollections; //for at kunne lave en arrayliste
import javafx.collections.ObservableList; 
import java.io.IOException; //hvis programmet ikke kan finde ud af at skrive eller læse et bestemt sted, er det en fejl (IO=Input/Output)

public class PatientListeController implements Initializable {  //implements gør, at man kan bruge Java elementer i klassen. Initializable er et bibliotek man henter ting fra.
//fra l.34-37 bruges denne: https://www.youtube.com/watch?v=uh5R7D_vFto 
    //Her skal vi definere vores knapper så vi kan bruge FXML elementer
    @FXML private Button LogAf; 
    @FXML private Button tilbage12;
    @FXML private Label navn;
    @FXML private TableView<Scanning> scanningView; //Scanning er en hjælpeklasse som hjælper med at ligge objekter ind i TableView (se Scanning.java)
    @FXML private TableColumn<Scanning, LocalDate> dato; //kolonne, der skal indeholde datoen
    @FXML private TableColumn<Scanning, String> scanningsType; //kolonne, der skal indeholde scanningstype
    @FXML private TableColumn<Scanning, String> hospital; //kolonne, der skal indeholde hospital
    
    
    private Patient patient = Global.patient; //henter cpr til den person som er logget ind, og den er global da den bruges på mange scener

//kode til at få log af knappen til at fungere: https://stackoverflow.com/questions/49354362/action-button-does-not-work-scenebuilder-javafx
    @FXML private void handleButtonActionLogAf (ActionEvent e) { //denne knap skal logge af og returnere tilbage til Login.fxml
        try{
            URL fxmlURL = getClass().getResource("Login.fxml"); //URL er stien til at finde ressourcen for login FXML filen. URL er typen, mens fxmlURL er variabelnavnet. getClass() skrives for at hente ressourcen. 
            Parent tableViewParent = FXMLLoader.load(fxmlURL); //loader FXML filen og sætter en rute til hvordan scenerne skal hænge sammen hierarkisk
            Scene tableViewScene = new Scene(tableViewParent); //tableViewScene er variabelnavnet for den nye scene man laver her til login

            Stage window = (Stage) LogAf.getScene().getWindow(); //her laves Stagen som kaldes window
            window.setScene(tableViewScene); //sætter stage her 
            window.show(); //viser window
        }catch (IOException io){ //finder ud af om der er fejl
            io.printStackTrace(); //printer fejlen ud, hvis der er noget fejl
        }
    }
    
    @FXML private void handleButtonActionTilbage12 (ActionEvent e) { //denne knap skal logge af og returnere tilbage til Login.fxml
        try{
            URL fxmlURL = getClass().getResource("MenuSaq7ScanPatient.fxml"); //URL er stien til at finde ressourcen for login FXML filen. URL er typen, mens fxmlURL er variabelnavnet. getClass() skrives for at hente ressourcen. 
            Parent tableViewParent = FXMLLoader.load(fxmlURL); //loader FXML filen og sætter en rute til hvordan scenerne skal hænge sammen hierarkisk
            Scene tableViewScene = new Scene(tableViewParent); //tableViewScene er variabelnavnet for den nye scene man laver her til login

            Stage window = (Stage) tilbage12.getScene().getWindow(); //her laves Stagen som kaldes window
            window.setScene(tableViewScene); //sætter stage her 
            window.show(); //viser window
        }catch (IOException io){ //finder ud af om der er fejl
            io.printStackTrace(); //printer fejlen ud, hvis der er noget fejl
        }
    }
//fra l. 57 og ned bruges denne her side: https://www.youtube.com/watch?v=uh5R7D_vFto 
    //i videoen fra 8:00 kan man se den her kode:
    @Override
    public void initialize(URL url, ResourceBundle rb){ //ResourceBoundle er typen og variabelnavn er rb. Denne kode er til at mappe hvad for en række der skal være i hvad for en kolonne.
        dato.setCellValueFactory(new PropertyValueFactory<Scanning, LocalDate>("dato")); //dato skal sættes i kolonnen for dato
        scanningsType.setCellValueFactory(new PropertyValueFactory<Scanning, String>("scanningsType"));
        hospital.setCellValueFactory(new PropertyValueFactory<Scanning, String>("hospital"));

        scanningView.setItems(hentScanning()); //kalder funktionen hentScanning fra l.63, se 10:46 i videoen

        navn.setText(patient.navn); //henter og sætter patientens navn i label, det gør man vha. Global.java klassen
    }

    public ObservableList<Scanning> hentScanning(){  //fra videoen 12:00
        ObservableList<Scanning> scanning = FXCollections.observableArrayList(); //observableArrayList() er en funktion som har indbygget metode .add, som kan tilføje ting
        scanning.add(new Scanning(LocalDate.parse("2018-02-22"), "CT", "Hjørring hospital")); //parser datoen , altså laver den til en String og derefter laver det til en LocalDate.
        scanning.add(new Scanning(LocalDate.parse("2020-10-24"), "PET", "Sygehus syd, Aalborg"));
        scanning.add(new Scanning(LocalDate.parse("2020-01-13"), "PET", "Sygehus syd, Aalborg"));

        return scanning;
    }
}

