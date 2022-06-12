/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class PatientSAQ7_2Controller {
   @FXML private Button naeste5;
   @FXML private Button tilbage6;
   @FXML private Button logAf6;
   
   
   @FXML private void handleButtonActionNaeste5 (ActionEvent e) {
        try{  //try bruges til at fange exceptions, og exceptions er hvis der opstår noget fejl
            URL fxmlURL = getClass().getResource("MenuSaq7ScanPatient.fxml"); //sætter URL til at være en sti til MenuSaq7ScanPatient.fxml (sti bruges til at betegne hvor man klikker hen)
            Parent tableViewParent = FXMLLoader.load(fxmlURL);  //loader URL (stien) og herved loader MenuSaq7ScanPatient.fxml. Parent indikerer at det er en ny scene. 
            Scene tableViewScene = new Scene(tableViewParent); //Laver ny scene med MenuSaq7ScanPatient her. tableViewScene er variabelnavnet til en ny scene

            //herfra sættes stagen nu hvor man har klikket på næste-knappen:
            Stage window = (Stage) naeste5.getScene().getWindow(); //laver en ny stage som hedder window
            window.setScene(tableViewScene); //sætter scenen for window med den scene fra l.23
            window.show(); //koden her fremviser det nye stage 
        }catch (IOException io){ //catch fanger en IOException (IO fejl) i dette tilfælde 
            io.printStackTrace(); //hvis der er en IOException så printer den "StackTrace" og hvad stacken indeholder. Debugging.
        }
    }
   
    @FXML private void handleButtonActionTilbage6 (ActionEvent e) {
        try{  //try bruges til at fange exceptions, og exceptions er hvis der opstår noget fejl
            URL fxmlURL = getClass().getResource("PatientSAQ7_1.fxml"); //sætter URL til at være en sti til PatientSAQ7_1.fxml (sti bruges til at betegne hvor man klikker hen)
            Parent tableViewParent = FXMLLoader.load(fxmlURL);  //loader URL (stien) og herved loader PatientSAQ7_1.fxml. Parent indikerer at det er en ny scene. 
            Scene tableViewScene = new Scene(tableViewParent); //Laver ny scene med PatientSAQ7_1 her. tableViewScene er variabelnavnet til en ny scene

            //herfra sættes stagen nu hvor man har klikket på næste-knappen:
            Stage window = (Stage) tilbage6.getScene().getWindow(); //laver en ny stage som hedder window
            window.setScene(tableViewScene); //sætter scenen for window med den scene fra l.23
            window.show(); //koden her fremviser det nye stage 
        }catch (IOException io){ //catch fanger en IOException (IO fejl) i dette tilfælde 
            io.printStackTrace(); //hvis der er en IOException så printer den "StackTrace" og hvad stacken indeholder. Debugging.
        }
    }
    
    @FXML private void handleButtonActionLogAf6 (ActionEvent e) {
        try{  //try bruges til at fange exceptions, og exceptions er hvis der opstår noget fejl
            URL fxmlURL = getClass().getResource("Login.fxml"); //sætter URL til at være en sti til Login.fxml (sti bruges til at betegne hvor man klikker hen)
            Parent tableViewParent = FXMLLoader.load(fxmlURL);  //loader URL (stien) og herved loader Login.fxml. Parent indikerer at det er en ny scene. 
            Scene tableViewScene = new Scene(tableViewParent); //Laver ny scene med Login her. tableViewScene er variabelnavnet til en ny scene

            //herfra sættes stagen nu hvor man har klikket på næste-knappen:
            Stage window = (Stage) logAf6.getScene().getWindow(); //laver en ny stage som hedder window
            window.setScene(tableViewScene); //sætter scenen for window med den scene fra l.23
            window.show(); //koden her fremviser det nye stage 
        }catch (IOException io){ //catch fanger en IOException (IO fejl) i dette tilfælde 
            io.printStackTrace(); //hvis der er en IOException så printer den "StackTrace" og hvad stacken indeholder. Debugging.
        }
    }
}   
