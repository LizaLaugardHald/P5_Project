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

public class LoginDoctorController { 

    private String password;
    private String username;
//fxml til hver ting som man skal ændre eller interagere med på en eller anden måde, ellers virker de ikke:
    @FXML private Button Næste2;
    @FXML private Button tilbageFraLoginK;
    @FXML private Label fejlBesked;
    @FXML private TextField personaleNummer;
    @FXML private PasswordField adgangskode2;

    @FXML private void handleButtonActionNæste2 (ActionEvent e) { //knap for 'næste' når man har testet nemid og kode ind
        try{
            username = personaleNummer.getText(); //aflæser hvad der står i tekstfeltet
            password = adgangskode2.getText(); //aflæser hvad der står i kodefeltet

            //Kode til at kigge i datafolder.
            Path path = Paths.get("src/main/resources/Data/Patient/Patienter.txt"); //Ændrer denne til at være for lægerne.
            long count = Files.lines(path).count(); //tæller hvor mange linjer der er i txt filen. Det kunne være stor fil, så den er long.

            for (int i = 0; i < count; i++){ //for hver linje i txt filen kører den igennem og tjekker hvad der står
                String line = Files.readAllLines(path).get(i); //læser alle linjer en af gangen
                if(!line.trim().equals("")){ //hvis linjen ikke (!) er tom, går den videre
                    String[] patient = line.split(","); //splitter hver linje ud på komma. Så  den tager hvad der er efter og før komma og smider tingene i string array
                    
                    String aflaestPersonaleNummer = patient[0]; //aflæser brugernavn i den første del af arrayet
                    String aflaestAdgangskode = patient[1]; //aflæser brugernavn i den anden del af arrayet
//Tjekker om brugernavn og kode matcher, som det der er skrevet ind i txt-filen:
                    if(aflaestPersonaleNummer.trim().equals(username)){ //aflæst brugernavn = username som er tastet ind i feltet. Her sammenlignes to string
                        if(aflaestAdgangskode.trim().equals(password)){ //det samme som forrige linje, bare med password

                            //Global.saetCPR(0101001111);
                            //Global.saetNavn("Hans Børge");
                            
                            URL fxmlURL = getClass().getResource("SearchFieldDoctor.fxml"); //henter stien (path) til borgerens liste
                            Parent tableViewParent = FXMLLoader.load(fxmlURL); //loader ny scene
                            Scene tableViewScene = new Scene(tableViewParent); //åbner borgerens liste op som ny scene                           
                            Stage window = (Stage) Næste2.getScene().getWindow(); //laver nyt stage baseret på scenen og åbner det stage
                            window.setScene(tableViewScene); //sætter scenen
                            window.show();                   //fremviser stage       
                        }
                    }
                }
            }
            fejlBesked.setText("Den indtastede information er ikke korrekt!");  //hvis brugernavn eller kode indtastes forkert eller feltet er tomt og man har klikket næste, så ændres det tomme label over brugernavnet til at den orange tekst kommer frem           
        }catch(IOException io){ //fanger IOException fejl
            io.printStackTrace(); //hvis IO fejl er fanget, så printer programmet noget ud 
        }
    }
     @FXML private void handleButtonActionTilbage0 (ActionEvent e) { //login knap for personale
        try{  //try bruges til at fange exceptions, og exceptions er hvis der opstår noget fejl
            URL fxmlURL = getClass().getResource("Login.fxml"); //sætter URL til at være en sti til NemId.fxml (sti bruges til at betegne hvor man klikker hen)
            Parent tableViewParent = FXMLLoader.load(fxmlURL);  //loader URL (stien) og herved loader NemId.fxml. Parent indikerer at det er en ny scene. 
            Scene tableViewScene = new Scene(tableViewParent); //Laver ny scene med NemId her. tableViewScene er variabelnavnet til en ny scene

            //herfra sættes stagen nu hvor man har klikket  på login-knappen:
            Stage window = (Stage) tilbageFraLoginK.getScene().getWindow(); //laver en ny stage som hedder window
            window.setScene(tableViewScene); //sætter scenen for window med den scene fra l.23
            window.show(); //koden her fremviser det nye stage 
        }catch (IOException io){ //catch fanger en IOException (IO fejl) i dette tilfælde 
            io.printStackTrace(); //hvis der er en IOException så printer den "StackTrace" og hvad stacken indeholder. Debugging.
        }
    }
}
//Kilder: https://stackoverflow.com/questions/53020451/how-to-create-javafx-save-read-information-from-text-file-and-letting-user-to-e 
