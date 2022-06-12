package com.mycompany.p5; //alle pakker bliver samlet, så er det nemmere at referere til andre filer i pakken, fx login.fxml

import javafx.application.Application; //for at få en fx applikation til at virke (SceneBuilder)
import javafx.fxml.FXMLLoader; //bruges til at loade scenerne med 
import javafx.scene.Parent; //denne klasse håndterer alle scener, så den bestemmer hvordan scenerne hænger samme hirakisk (fx hvad er hovedscene, hvad er underscene osv.)
import javafx.scene.Scene; //for at kunne lave scener
import javafx.stage.Stage; //for at kunne lave stages, altså bestemmer hvilken scene man ser

import java.io.IOException; //hvis man skal fremvise en exception (IO=In Out). Denne kode er til debugging, så man kan printe ud hvis der sker en fejl

/**
 * JavaFX App
 */
public class App extends Application { //en klasse App, der nedarver fra Application 
    @Override  //Application har en standard funktionalitet som man overskriver her, se l.16-47
    public void start(Stage primaryStage) throws Exception { //det er en metode, der har parameteren primaryStage. "throws Exception" skriver noget i konsollen hvis der er fejl
        // Henter loaderen og panelen/scenen (pane) for den første scene
        // Herefter vil loaderen give mulighed for at hente den relaterede kontroller 
        FXMLLoader firstPageLoader = new FXMLLoader(getClass().getResource("Login.fxml")); //laver en ny loader og sætter den til at have ressourcen Login.fxml
        Parent firstPane = firstPageLoader.load(); //den tager den forrige firstPane loader og loader den her faktisk (på l.19 sættes den blot til ressourcen, men her loader den nu). Parent er key, Pane er variabelnavn.
        Scene firstScene = new Scene(firstPane, 800, 1200); //Laver en ny scene baseret på firstPane og den størrelse (højde x bredde)
        
        //Viser scene og sætter scene:
        primaryStage.setScene(firstScene);
        primaryStage.setWidth(1200);
        primaryStage.setHeight(800);
        primaryStage.show();
    }
}


//Kilder:  
//til FXMLLoader: https://coderedirect.com/questions/421555/javafx-how-to-inject-new-fxml-content-to-current-scene 
//til primaryStage.show();: https://stackoverflow.com/questions/14723664/change-scene-in-full-screen-javafx 
//til at gøre vinduerne store nok: https://stackoverflow.com/questions/8790853/set-scene-width-and-height 