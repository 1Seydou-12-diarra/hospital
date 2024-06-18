package com.gestionHopital.gestionHopital.Service;
import com.gestionHopital.gestionHopital.Dto.RendezVousDto;
import com.gestionHopital.gestionHopital.Entity.RendezVous;
import com.gestionHopital.gestionHopital.Repository.RendezVousRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RendezVousService {

    @Autowired
    private RendezVousRepository rendezVousRepository;

    // Récupère tous les rendez-vous
    public List<RendezVousDto> getAllRendezVous() {
        List<RendezVous> rendezVousList = rendezVousRepository.findAll();
        return rendezVousList.stream().map(this::toDto).collect(Collectors.toList());
    }

    // Récupère un rendez-vous par son identifiant
    public Optional<RendezVousDto> getRendezVousById(Long id) {
        Optional<RendezVous> rendezVous = rendezVousRepository.findById(id);
        return rendezVous.map(this::toDto);
    }

    // Crée un nouveau rendez-vous
    public RendezVousDto createRendezVous(RendezVousDto rendezVousDto) {
        RendezVous rendezVous = toEntity(rendezVousDto);
        RendezVous savedRendezVous = rendezVousRepository.save(rendezVous);
        return toDto(savedRendezVous);
    }

    // Met à jour un rendez-vous existant
    public Optional<RendezVousDto> updateRendezVous(Long id, RendezVousDto rendezVousDto) {
        Optional<RendezVous> existingRendezVous = rendezVousRepository.findById(id);
        if (existingRendezVous.isPresent()) {
            RendezVous rendezVous = existingRendezVous.get();
            rendezVous.setDateRendezVous(rendezVousDto.getDateRendezVous());

            // Mettez à jour d'autres champs selon votre modèle
            RendezVous updatedRendezVous = rendezVousRepository.save(rendezVous);
            return Optional.of(toDto(updatedRendezVous));
        } else {
            return Optional.empty();
        }
    }

    // Supprime un rendez-vous par son identifiant
    public void deleteRendezVous(Long id) {
        rendezVousRepository.deleteById(id);
    }

    // Convertit un RendezVous en RendezVousDto
    private RendezVousDto toDto(RendezVous rendezVous) {
        return new RendezVousDto(
                rendezVous.getId(),
                rendezVous.getDateRendezVous(),

                // Ajoutez d'autres champs si nécessaire
                rendezVous.getMedecin(),
                rendezVous.getPatient(),
                rendezVous.getConsultation()
        );
    }

    // Convertit un RendezVousDto en RendezVous
    private RendezVous toEntity(RendezVousDto rendezVousDto) {
        RendezVous rendezVous = new RendezVous();
        rendezVous.setId(rendezVousDto.getId());
        rendezVous.setDateRendezVous(rendezVousDto.getDateRendezVous());
        // Mappez d'autres champs selon votre modèle
        rendezVous.setMedecin(rendezVousDto.getMedecin());
        rendezVous.setPatient(rendezVousDto.getPatient());
        rendezVous.setConsultation(rendezVousDto.getConsultation());
        return rendezVous;
    }
}

