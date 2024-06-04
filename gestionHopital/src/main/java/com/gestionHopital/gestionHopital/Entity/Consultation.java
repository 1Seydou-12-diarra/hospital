package com.gestionHopital.gestionHopital.Entity;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Date;

@Entity
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateConsultation;
    private String rapportConsultation;
    private double prixConsulation;
    @OneToOne(mappedBy = "consultation")
    private  RendezVous  rendezVous;

    public Consultation() {
    }

    public Consultation(Long id, Date dateConsultation, String rapportConsultation, double prixConsulation, RendezVous rendezVous) {
        this.id = id;
        this.dateConsultation = dateConsultation;
        this.rapportConsultation = rapportConsultation;
        this.prixConsulation = prixConsulation;
        this.rendezVous = rendezVous;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateConsultation() {
        return dateConsultation;
    }

    public void setDateConsultation(Date dateConsultation) {
        this.dateConsultation = dateConsultation;
    }

    public String getRapportConsultation() {
        return rapportConsultation;
    }

    public void setRapportConsultation(String rapportConsultation) {
        this.rapportConsultation = rapportConsultation;
    }

    public double getPrixConsulation() {
        return prixConsulation;
    }

    public void setPrixConsulation(double prixConsulation) {
        this.prixConsulation = prixConsulation;
    }

    public RendezVous getRendezVous() {
        return rendezVous;
    }

    public void setRendezVous(RendezVous rendezVous) {
        this.rendezVous = rendezVous;
    }
}