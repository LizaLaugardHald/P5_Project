package com.mycompany.p5;

import java.net.URL; //for at kunne URL skal man importere det
import javafx.fxml.FXMLLoader; //for at kunne lave @fxml (se fx l. 16)
import javafx.fxml.FXML;  //fxml objekter forbindes til fxml filen
import javafx.scene.Parent; 
import javafx.scene.Scene;
import javafx.scene.control.Button; //man har en knap, så man skal også have en control.Button
import javafx.stage.Stage;
import javafx.event.ActionEvent; //kaldes når man klikker på knappen

import java.io.IOException; //hvis fx nemid.fxml fra l.20 er væk, så giver den fejl og hjælper med fejlen

public class MenuSaq7ScanPatientController { 

    @FXML private Button scanning; //refererer til den knap som hedder loginBorger (@FXML=et objekt)
    @FXML private Button SAQ7;
    @FXML private Button logAf5;


    private int cpr;

    public void saetCPR(int cpr){
        this.cpr = cpr;
    }

    public int faaCPR(){
        return this.cpr;
    }

    //handleButtonActionLoginBorger er en metode, som gør at når man klikker på en knap, så tager den videre til NemId scene
    @FXML private void handleButtonActionScanning (ActionEvent e) {
        try{  //try bruges til at fange exceptions, og exceptions er hvis der opstår noget fejl
            URL fxmlURL = getClass().getResource("PatientListe.fxml"); //sætter URL til at være en sti til NemId.fxml (sti bruges til at betegne hvor man klikker hen)
            Parent tableViewParent = FXMLLoader.load(fxmlURL);  //loader URL (stien) og herved loader NemId.fxml. Parent indikerer at det er en ny scene. 
            Scene tableViewScene = new Scene(tableViewParent); //Laver ny scene med NemId her. tableViewScene er variabelnavnet til en ny scene

            //herfra sættes stagen nu hvor man har klikket  på login-knappen:
            Stage window = (Stage) scanning.getScene().getWindow(); //laver en ny stage som hedder window
            window.setScene(tableViewScene); //sætter scenen for window med den scene fra l.23
            window.show(); //koden her fremviser det nye stage 
        }catch (IOException io){ //catch fanger en IOException (IO fejl) i dette tilfælde 
            io.printStackTrace(); //hvis der er en IOException så printer den "StackTrace" og hvad stacken indeholder. Debugging.
        }
    }
     
    
    
     
    @FXML private void handleButtonActionSAQ7 (ActionEvent e) { //login knap for personale
        /*try{
            
        }catch (IOException io){
            io.printStackTrace();
        }*/
        try{  //try bruges til at fange exceptions, og exceptions er hvis der opstår noget fejl
            URL fxmlURL = getClass().getResource("PatientSAQ7_1.fxml"); //sætter URL til at være en sti til NemId.fxml (sti bruges til at betegne hvor man klikker hen)
            Parent tableViewParent = FXMLLoader.load(fxmlURL);  //loader URL (stien) og herved loader NemId.fxml. Parent indikerer at det er en ny scene. 
            Scene tableViewScene = new Scene(tableViewParent); //Laver ny scene med NemId her. tableViewScene er variabelnavnet til en ny scene

            //herfra sættes stagen nu hvor man har klikket  på login-knappen:
            Stage window = (Stage) SAQ7.getScene().getWindow(); //laver en ny stage som hedder window
            window.setScene(tableViewScene); //sætter scenen for window med den scene fra l.23
            window.show(); //koden her fremviser det nye stage 
        }catch (IOException io){ //catch fanger en IOException (IO fejl) i dette tilfælde 
            io.printStackTrace(); //hvis der er en IOException så printer den "StackTrace" og hvad stacken indeholder. Debugging.
        }
    }

    @FXML private void handleButtonActionLogAf5 (ActionEvent e){
        try{
            URL fxmlURL = getClass().getResource("Login.fxml"); //URL er stien til at finde ressourcen for login FXML filen. URL er typen, mens fxmlURL er variabelnavnet. getClass() skrives for at hente ressourcen. 
            Parent tableViewParent = FXMLLoader.load(fxmlURL); //loader FXML filen og sætter en rute til hvordan scenerne skal hænge sammen hierarkisk
            Scene tableViewScene = new Scene(tableViewParent); //tableViewScene er variabelnavnet for den nye scene man laver her til login

            Stage window = (Stage) logAf5.getScene().getWindow(); //her laves Stagen som kaldes window
            window.setScene(tableViewScene); //sætter stage her 
            window.show(); //viser window
        }catch (IOException io){ //finder ud af om der er fejl
            io.printStackTrace(); //printer fejlen ud, hvis der er noget fejl
        }
    }
}


//Kilder
//til l.22: https://www.programcreek.com/java-api-examples/?api=javafx.fxml.FXMLLoader 
//https://stackoverflow.com/questions/49354362/action-button-does-not-work-scenebuilder-javafx 