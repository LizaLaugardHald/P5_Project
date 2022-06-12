package com.mycompany.p5;

//Imports skal være her!


public class Patient{
    
    long CPR;
    String navn;
    String kodeord; 

    public Patient(){
        
    }
    public Patient(long cpr, String navn, String kodeord){
        this.CPR = cpr;
        this.navn = navn;
        this.kodeord = kodeord;
    }
}