package com.gestionHopital.gestionHopital.Entity;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Medecin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String specialite;
    private String email;
    @OneToMany(mappedBy = "medecin")
    private Collection<RendezVous> rendezVous;

    public Medecin() {
    }

    public Medecin(Long id, String nom, String specialite, String email, Collection<RendezVous> rendezVous) {
        this.id = id;
        this.nom = nom;
        this.specialite = specialite;
        this.email = email;
        this.rendezVous = rendezVous;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<RendezVous> getRendezVous() {
        return rendezVous;
    }

    public void setRendezVous(Collection<RendezVous> rendezVous) {
        this.rendezVous = rendezVous;
    }
}



