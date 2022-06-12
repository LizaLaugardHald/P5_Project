package com.mycompany.p5;

import java.net.URL;
import java.nio.file.Files; //håndtering af filer
import java.nio.file.Path; //håndtering af filer
import java.nio.file.Paths; //håndtering af filer
import javafx.fxml.FXMLLoader; //loader fxml filer
import javafx.fxml.FXML; //håndterer fxml filer
import javafx.scene.Parent; 
import javafx.scene.Scene;
import javafx.scene.control.Button; //kontrollerer knapper
import javafx.scene.control.PasswordField; //kontrollerer kodefelt
import javafx.scene.control.TextField; //kontrollerer tekstfelter
import javafx.scene.control.Label; //kontrollerer labels til tekster
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import java.util.Set;

import java.io.IOException; 
import java.sql.SQLException;
import java.util.List;

public class NemIdPatientController { 

    private String password;
    private String username;
//fxml til hver ting som man skal ændre eller interagere med på en eller anden måde, ellers virker de ikke:
    @FXML private Button GlemtKode;
    @FXML private Button tilbage2;
    @FXML private Button Næste3;
    @FXML private TextField brugernavn;
    @FXML private PasswordField adgangskode;
    @FXML private Label fejlBesked; 
    @FXML private MenuSaq7ScanPatientController controller1;

    @FXML private void handleButtonActionGlemtKode (ActionEvent e) { //knap for glemt kode
    
    }
//tilbage knap som fører tilbage til den side hvor man kan vælge at logge ind som patient el. kardiolog
    @FXML private void handleButtonActionTilbage2 (ActionEvent e){
        try{
            URL fxmlURL = getClass().getResource("Login.fxml"); //URL er stien til at finde ressourcen for login FXML filen. URL er typen, mens fxmlURL er variabelnavnet. getClass() skrives for at hente ressourcen. 
            Parent tableViewParent = FXMLLoader.load(fxmlURL); //loader FXML filen og sætter en rute til hvordan scenerne skal hænge sammen hierarkisk
            Scene tableViewScene = new Scene(tableViewParent); //tableViewScene er variabelnavnet for den nye scene man laver her til login

            Stage window = (Stage) tilbage2.getScene().getWindow(); //her laves Stagen som kaldes window
            window.setScene(tableViewScene); //sætter stage her 
            window.show(); //viser window
        }catch (IOException io){ //finder ud af om der er fejl
            io.printStackTrace(); //printer fejlen ud, hvis der er noget fejl
        }
    }

    @FXML private void handleButtonActionNæste3 (ActionEvent e) throws SQLException { //knap for 'næste' når man har testet nemid og kode ind
        try{
            username = brugernavn.getText(); //aflæser hvad der står i tekstfeltet
            password = adgangskode.getText(); //aflæser hvad der står i kodefeltet
            
            List<Patient> brugere = Global.hentPatientDB(Long.parseLong(username));
            
            Patient bruger = brugere.get(0);
            
            if(bruger != null && bruger.kodeord == password){
                Global.saetPatient(bruger);  
           
                URL fxmlURL = getClass().getResource("MenuSaq7ScanPatient.fxml"); //henter stien (path) til borgerens liste
                Parent tableViewParent = FXMLLoader.load(fxmlURL); //loader ny scene
                Scene tableViewScene = new Scene(tableViewParent); //åbner borgerens liste op som ny scene                           
                Stage window = (Stage) Næste3.getScene().getWindow(); //laver nyt stage baseret på scenen og åbner det stage
                window.setScene(tableViewScene); //sætter scenen
                window.show();                   //fremviser stage       
            }
            else if(bruger == null || bruger.kodeord != password){
                fejlBesked.setText("Den indtastede information er ikke korrekt!");  //hvis brugernavn eller kode indtastes forkert eller feltet er tomt og man har klikket næste, så ændres det tomme label over brugernavnet til at den orange tekst kommer frem           
            }                          
        }catch(IOException io){ //fanger IOException fejl
            io.printStackTrace(); //hvis IO fejl er fanget, så printer programmet noget ud 
        }
    }
}
//Kilder: https://stackoverflow.com/questions/53020451/how-to-create-javafx-save-read-information-from-text-file-and-letting-user-to-e 
