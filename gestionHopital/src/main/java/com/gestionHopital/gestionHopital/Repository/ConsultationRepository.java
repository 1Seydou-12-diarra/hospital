package com.gestionHopital.gestionHopital.Repository;
import com.gestionHopital.gestionHopital.Entity.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {
    // Vous pouvez ajouter des méthodes personnalisées de requête si nécessaire
}

