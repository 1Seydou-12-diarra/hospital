package com.gestionHopital.gestionHopital.Entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class RendezVous {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date dateRendezVous;

    @ManyToOne
    private Medecin medecin;

    @ManyToOne
    private Patient patient;

    @OneToOne
    private Consultation consultation;

    public RendezVous() {
        // Constructeur par défaut
    }

    public RendezVous(Date dateRendezVous, Medecin medecin, Patient patient, Consultation consultation) {
        this.dateRendezVous = dateRendezVous;
        this.medecin = medecin;
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
