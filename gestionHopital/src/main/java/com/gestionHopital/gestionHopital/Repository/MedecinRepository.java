package com.gestionHopital.gestionHopital.Repository;

import com.gestionHopital.gestionHopital.Entity.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedecinRepository extends JpaRepository<Medecin, Long> {
    // Vous pouvez ajouter des méthodes personnalisées de requête si nécessaire
}
