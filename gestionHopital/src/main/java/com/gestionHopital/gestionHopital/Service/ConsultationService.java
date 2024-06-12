package com.gestionHopital.gestionHopital.Service;

import com.gestionHopital.gestionHopital.Dto.ConsultationDto;
import com.gestionHopital.gestionHopital.Entity.Consultation;
import com.gestionHopital.gestionHopital.Repository.ConsultationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsultationService {

    @Autowired
    private ConsultationRepository consultationRepository;

    // Récupère toutes les consultations
    public List<ConsultationDto> getAllConsultations() {
        List<Consultation> consultations = consultationRepository.findAll();
        return consultations.stream().map(this::toDto).collect(Collectors.toList());
    }

    // Récupère une consultation par son identifiant
    public Optional<ConsultationDto> getConsultationById(Long id) {
        Optional<Consultation> consultation = consultationRepository.findById(id);
        return consultation.map(this::toDto);
    }

    // Crée une nouvelle consultation
    public ConsultationDto createConsultation(ConsultationDto consultationDto) {
        Consultation consultation = toEntity(consultationDto);
        Consultation savedConsultation = consultationRepository.save(consultation);
        return toDto(savedConsultation);
    }

    // Met à jour une consultation existante
    public Optional<ConsultationDto> updateConsultation(Long id, ConsultationDto consultationDto) {
        Optional<Consultation> existingConsultation = consultationRepository.findById(id);
        if (existingConsultation.isPresent()) {
            Consultation consultation = existingConsultation.get();
            consultation.setDateConsultation(consultationDto.getDateConsultation());
            consultation.setRapportConsultation(consultationDto.getRapportConsultation());
            consultation.setPrixConsultation(consultationDto.getPrixConsultation());
            Consultation updatedConsultation = consultationRepository.save(consultation);
            return Optional.of(toDto(updatedConsultation));
        } else {
            return Optional.empty();
        }
    }

    // Supprime une consultation par son identifiant
    public void deleteConsultation(Long id) {
        consultationRepository.deleteById(id);
    }

    // Convertit une Consultation en ConsultationDto
    private ConsultationDto toDto(Consultation consultation) {
        return new ConsultationDto(
                consultation.getId(),
                consultation.getDateConsultation(),
                consultation.getRapportConsultation(),
                consultation.getPrixConsultation()
        );
    }

    // Convertit une ConsultationDto en Consultation
    private Consultation toEntity(ConsultationDto consultationDto) {
        Consultation consultation = new Consultation();
        consultation.setId(consultationDto.getId());
        consultation.setDateConsultation(consultationDto.getDateConsultation());
        consultation.setRapportConsultation(consultationDto.getRapportConsultation());
        consultation.setPrixConsultation(consultationDto.getPrixConsultation());

        return consultation;
    }
}
