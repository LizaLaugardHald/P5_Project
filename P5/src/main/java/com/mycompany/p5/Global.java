package com.mycompany.p5;
//kilde: https://stackoverflow.com/questions/50538175/javafx-use-a-single-variable-in-multiple-fxml-scenes
//dette dokument er til at holde styr på globale variabler, såsom Cpr på den patient som er logget ind

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public final class Global{ //final class er en klasse man ikke kan extend fra. Final class kan ikke blive overskrevet og den kan ikke have subklasser. 
    private static long cpr; //static bruges, da cpr blivr kun allokeret i hukommelsen en gang, og når man laver ny instans er det stadig samme cpr man bruger 
    private static String navn;
    public static Patient patient;
    static Connection myConn = null;
    //private static int scanningId;
    //private static int personaleNummer;

    private Global(){ //denne er tom bevidst for at man ikke kan instansiere Global andre steder fra 
    }

    public static Patient hentPatient(){
        return patient;
    }

    public static void saetPatient(Patient patient){
        Global.patient = patient;
    }

    //Denne metode henter en patient baseret på et CPR nummer.
    public static List<Patient> hentPatientDB(long CPR) throws SQLException{ //Koden her henter cpr. static = kun bliver allokeret i hukommelsen en gang
        List<Patient> patientDB = new ArrayList<Patient>();
        Connection myConnect = null;
        try {
            Class.forName("com.mysql.cj.jdbc.driver");
            try{
                myConnect = DriverManager.getConnection("jdbc:mysql://db.course.hst.aau.dk:3306/hst_hst21st501?zeroDateTimeBehavior=CONVERT_TO_NULL [hst_hst21st501 on Default schema]", "hst_hst21st501", "japheequaikuuzuacuak");

                ResultSet result = myConnect.createStatement().executeQuery("Select * from patient where cpr = 1409891233");
            
        //result = myStatement.executeQuery("DECLARE @cpr bigint SET @cpr = CPR select * from patient where cpr = @CPR");
        //result = myStatement.executeQuery("Select * from patient where cpr = 1409891233");
        
        
                while(result.next()){
                    Patient patienten = new Patient();
                    patienten.CPR = result.getLong("cpr");
                    patienten.navn = result.getString("navn");
                    patienten.kodeord = result.getString("kodeord");
                    patientDB.add(patienten);
                }
            }
            catch(SQLException sqlex){
                System.out.println(sqlex.getMessage());
            }
        }
        catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }    
        return patientDB;
    }

    //Denne metode returnere en liste af scanninger. Skal f.eks. bruges til at generere scanningerne til patientens liste.
    public static List<Scanning> hentScanning(long patientCPR) throws SQLException{ //koden her henter scanningID (når man engang vil gøre borgerens liste klikbar, skal man have et ID til selve scanningsbilledet, fordi så ved vi hvilken scanningsbillede vi skal åbne op og det er også det som binde lægens note sammen.
        List<Scanning> scanningDB = new ArrayList<Scanning>();
        Statement myStatement = myConn.createStatement();
        ResultSet result = myStatement.executeQuery(""
+ "DECLARE @patientcpr bigint"
+ "SET @patientcpr = patientCPR"
+ "Select * from ScanningInformation where patientcpr = @patientcpr");
        while(result.next()){
            Date input = result.getDate("dato");
            LocalDate date = input.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            Scanning scanningen = new Scanning(date, result.getString("hospital"), result.getString("scanningstype"));
            scanningDB.add(scanningen);
        }
        return scanningDB;
    }
/*
    public static int hentPersonaleNummer(){ //henter personalenummer (lægeID)
        return personaleNummer;
    }

    public static void saetPersonaleNummer(int personaleNummer){ //sætter personale nummer (lægeID)
        Global.personaleNummer = personaleNummer;
    }*/

    public void initialize(URL url, ResourceBundle rb) {

    try {
        myConn = DriverManager.getConnection("jdbc:mysql://db.course.hst.aau.dk:3306/hst_hst21st501?serverTimezone=UTC", "hst_hst21st501", "japheequaikuuzuacuak");
    } catch (Exception exc) {
        exc.printStackTrace();
    }
}
}
