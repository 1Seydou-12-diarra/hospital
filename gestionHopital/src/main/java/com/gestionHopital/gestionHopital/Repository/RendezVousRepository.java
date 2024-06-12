package com.gestionHopital.gestionHopital.Repository;

import com.gestionHopital.gestionHopital.Entity.RendezVous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
    // Vous pouvez ajouter des méthodes personnalisées de requête si nécessaire
}

