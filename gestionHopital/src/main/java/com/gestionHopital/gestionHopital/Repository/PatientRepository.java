package com.gestionHopital.gestionHopital.Repository;
import com.gestionHopital.gestionHopital.Entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    // Vous pouvez ajouter des méthodes de requête personnalisées ici si nécessaire

}

