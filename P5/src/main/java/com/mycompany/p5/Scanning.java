package com.mycompany.p5;

import java.time.LocalDate;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.ObjectProperty; //en property er en variabel man kan hente og sætte
import javafx.beans.property.SimpleObjectProperty;


public class Scanning { // klassen her sørger for objekter der skal ligges i listen
    ObjectProperty<LocalDate> dato = new SimpleObjectProperty(); //for hver kolonne skal man have en værdi og her angiver man at man skal have en dato værdi
    StringProperty scanningsType = new SimpleStringProperty(); //for hver kolonne skal man have en værdi og her angiver man at man skal have en scanningsType værdi
    StringProperty hospital = new SimpleStringProperty(); //for hver kolonne skal man have en værdi og her angiver man at man skal have en hospital værdi

    public Scanning(){ //constructor for hvis man ikke har nogen parameter. Hvis fx man ikke kender sine parametre, kan man sætte dem senere. Hvis ikke man har denne kode med, kan listen ikke sortere overhovedet.
    }

    public Scanning(LocalDate dato, String scanningsType, String hospital){ //constructor med de parametre vi kender allerede
        this.dato = new SimpleObjectProperty<LocalDate>(dato); //datovariablen der ligger i klassen bliver sat til den datovariabel der ligger i parametret
        this.scanningsType = new SimpleStringProperty(scanningsType);
        this.hospital = new SimpleStringProperty(hospital);
    }

    public LocalDate hentDato(){ //LocalDate er typen og hentDato er en metode til at hente dato
        return dato.get();  //returnerer en specifik dato
    }

    public ObjectProperty<LocalDate> datoProperty(){ 
        return dato;
    }

    public void setDato(LocalDate dato){ //den sætter datoen 
        this.dato.set(dato); 
    }

    public String hentScanningsType(){ //henter scanningstypen
        return scanningsType.get();
    }

    public StringProperty scanningsTypeProperty(){ 
        return scanningsType;
    }

    public void setScanningsType(String scanningsType){ //sætter scanningstype)
        this.scanningsType.set(scanningsType);
    }

    public String hentHospital(){ 
        return hospital.get();
    }

    public StringProperty hospitalProperty(){
        return hospital;
    }

    public void setHospital(String hospital){
        this.hospital.set(hospital);
    }
}