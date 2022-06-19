package com.example.finalseizureyarab.Models;

public class Signals {
int id;

    public Signals(int id, String classification, String probabilityNon, String probabilitySeizure, String type) {
        this.id = id;
        this.classification = classification;
        this.probabilityNon = probabilityNon;
        this.probabilitySeizure = probabilitySeizure;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String classificationEEG, probabilityNonEEG, probabilitySeizureEEG, typeEEG, classificationEMG, probabilityNonEMG, probabilitySeizureEMG, typeEMG, token;
    String classification,probabilityNon,probabilitySeizure,type;
    public String getToken() {
        return token;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getProbabilityNon() {
        return probabilityNon;
    }

    public void setProbabilityNon(String probabilityNon) {
        this.probabilityNon = probabilityNon;
    }

    public String getProbabilitySeizure() {
        return probabilitySeizure;
    }

    public void setProbabilitySeizure(String probabilitySeizure) {
        this.probabilitySeizure = probabilitySeizure;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Signals(String classification, String probabilityNon, String probabilitySeizure, String type) {
        this.classification = classification;
        this.probabilityNon = probabilityNon;
        this.probabilitySeizure = probabilitySeizure;
        this.type = type;
    }

    public String getClassificationEEG() {
        return classificationEEG;
    }

    public void setClassificationEEG(String classificationEEG) {
        this.classificationEEG = classificationEEG;
    }

    public String getProbabilityNonEEG() {
        return probabilityNonEEG;
    }

    public void setProbabilityNonEEG(String probabilityNonEEG) {
        this.probabilityNonEEG = probabilityNonEEG;
    }

    public String getProbabilitySeizureEEG() {
        return probabilitySeizureEEG;
    }

    public void setProbabilitySeizureEEG(String probabilitySeizureEEG) {
        this.probabilitySeizureEEG = probabilitySeizureEEG;
    }

    public String getTypeEEG() {
        return typeEEG;
    }

    public void setTypeEEG(String typeEEG) {
        this.typeEEG = typeEEG;
    }

    public Signals(String classificationEEG, String probabilityNonEEG, String probabilitySeizureEEG, String typeEEG, String token) {
        this.classificationEEG = classificationEEG;
        this.probabilityNonEEG = probabilityNonEEG;
        this.probabilitySeizureEEG = probabilitySeizureEEG;
        this.typeEEG = typeEEG;
        this.token = token;
    }

    public String getClassificationEMG() {
        return classificationEMG;
    }

    public void setClassificationEMG(String classificationEMG) {
        this.classificationEMG = classificationEMG;
    }

    public String getProbabilityNonEMG() {
        return probabilityNonEMG;
    }

    public void setProbabilityNonEMG(String probabilityNonEMG) {
        this.probabilityNonEMG = probabilityNonEMG;
    }

    public String getProbabilitySeizureEMG() {
        return probabilitySeizureEMG;
    }

    public void setProbabilitySeizureEMG(String probabilitySeizureEMG) {
        this.probabilitySeizureEMG = probabilitySeizureEMG;
    }

    public String getTypeEMG() {
        return typeEMG;
    }

    public void setTypeEMG(String typeEMG) {
        this.typeEMG = typeEMG;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Signals(String token) {
        this.token = token;
    }

    public Signals() {
    }

}