package com.gestionHopital.gestionHopital.Dto;

import com.gestionHopital.gestionHopital.Entity.Consultation;
import com.gestionHopital.gestionHopital.Entity.Medecin;
import com.gestionHopital.gestionHopital.Entity.Patient;

import java.util.Date;

public class RendezVousDto {
    private Long id;
    private Date dateRendezVous;
    private Medecin medecin;
    private Patient patient;
    private Consultation consultation;

    public RendezVousDto() {
    }

    public RendezVousDto(Long id, Date dateRendezVous, Medecin medecin, Patient patient, Consultation consultation) {
        this.id = id;
        this.dateRendezVous = dateRendezVous;
        this.medecin = this.medecin;
        this.patient = patient;
        this.consultation = consultation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateRendezVous() {
        return dateRendezVous;
    }

    public void setDateRendezVous(Date dateRendezVous) {
        this.dateRendezVous = dateRendezVous;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Consultation getConsultation() {
        return consultation;
    }

    public void setConsultation(Consultation consultation) {
        this.consultation = consultation;
    }
}
