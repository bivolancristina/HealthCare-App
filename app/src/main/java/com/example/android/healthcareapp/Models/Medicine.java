package com.example.android.healthcareapp.Models;

public class Medicine {

    String medName;
    String medDescription;

    public Medicine() {
    }

    public Medicine(String medName, String medDescription) {
        this.medName = medName;
        this.medDescription = medDescription;
    }

    public String getMedName() {
        return medName;
    }

    public void setMedName(String medName) {
        this.medName = medName;
    }

    public String getMedDescription() {
        return medDescription;
    }

    public void setMedDescription(String medDescription) {
        this.medDescription = medDescription;
    }
}
