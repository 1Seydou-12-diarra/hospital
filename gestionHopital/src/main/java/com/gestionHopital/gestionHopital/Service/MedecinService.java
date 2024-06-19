package com.gestionHopital.gestionHopital.Service;
import com.gestionHopital.gestionHopital.Dto.MedecinDto;
import com.gestionHopital.gestionHopital.Entity.Medecin;
import com.gestionHopital.gestionHopital.Repository.MedecinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MedecinService {

    @Autowired
    private MedecinRepository medecinRepository;

    // Récupère tous les médecins
    public List<MedecinDto> getAllMedecins() {
        List<Medecin> medecins = medecinRepository.findAll();
        return medecins.stream().map(this::toDto).collect(Collectors.toList());
    }

    // Récupère un médecin par son identifiant
    public Optional<MedecinDto> getMedecinById(Long id) {
        Optional<Medecin> medecin = medecinRepository.findById(id);
        return medecin.map(this::toDto);
    }

    // Crée un nouveau médecin
    public MedecinDto createMedecin(MedecinDto medecinDto) {
        Medecin medecin = toEntity(medecinDto);
        Medecin savedMedecin = medecinRepository.save(medecin);
        return toDto(savedMedecin);
    }

    // Met à jour un médecin existant
    public Optional<MedecinDto> updateMedecin(Long id, MedecinDto medecinDto) {
        Optional<Medecin> existingMedecin = medecinRepository.findById(id);
        if (existingMedecin.isPresent()) {
            Medecin medecin = existingMedecin.get();
            medecin.setNom(medecinDto.getNom());
            medecin.setSpecialite(medecinDto.getSpecialite());
            medecin.setEmail(medecinDto.getEmail());
            Medecin updatedMedecin = medecinRepository.save(medecin);
            return Optional.of(toDto(updatedMedecin));
        } else {
            return Optional.empty();
        }
    }

    // Supprime un médecin par son identifiant
    public void deleteMedecin(Long id) {
        medecinRepository.deleteById(id);
    }

    // Convertit un Medecin en MedecinDto
    private MedecinDto toDto(Medecin medecin) {
        return new MedecinDto(
                medecin.getId(),
                medecin.getNom(),
                medecin.getSpecialite(),
                medecin.getEmail()

        );
    }

    // Convertit un MedecinDto en Medecin
    private Medecin toEntity(MedecinDto medecinDto) {
        Medecin medecin = new Medecin();
        medecin.setId(medecinDto.getId());
        medecin.setNom(medecinDto.getNom());
        medecin.setSpecialite(medecinDto.getSpecialite());
        medecin.setEmail(medecinDto.getEmail());
        return medecin;
    }
}

