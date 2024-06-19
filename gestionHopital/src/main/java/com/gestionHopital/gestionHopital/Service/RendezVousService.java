package com.gestionHopital.gestionHopital.Service;
import com.gestionHopital.gestionHopital.Dto.RendezVousDto;
import com.gestionHopital.gestionHopital.Entity.Consultation;
import com.gestionHopital.gestionHopital.Entity.Medecin;
import com.gestionHopital.gestionHopital.Entity.Patient;
import com.gestionHopital.gestionHopital.Entity.RendezVous;
import com.gestionHopital.gestionHopital.Repository.ConsultationRepository;
import com.gestionHopital.gestionHopital.Repository.MedecinRepository;
import com.gestionHopital.gestionHopital.Repository.PatientRepository;
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
    @Autowired
    private ConsultationRepository consultationRepository;
    @Autowired
    private MedecinRepository medecinRepository;
    @Autowired
    private PatientRepository patientRepository;





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

    public RendezVousDto createRendezVous(RendezVousDto rendezVousDto) {
        // Récupérer le médecin à partir de l'ID
        Medecin medecin = this.medecinRepository.findById(rendezVousDto.getMedecin().getId())
                .orElseThrow(() -> new IllegalArgumentException("Médecin avec ID " + rendezVousDto.getMedecin() + " non trouvé"));

        // Récupérer le patient à partir de l'ID
        Patient patient = this.patientRepository.findById(rendezVousDto.getPatient().getId())
                .orElseThrow(() -> new IllegalArgumentException("Patient avec ID " + rendezVousDto.getPatient() + " non trouvé"));

        // Récupérer la consultation à partir de l'ID
        Consultation consultation = this.consultationRepository.findById(rendezVousDto.getConsultation().getId())
                .orElseThrow(() -> new IllegalArgumentException("Consultation avec ID " + rendezVousDto.getConsultation() + " non trouvée"));

        // Créer un nouveau rendez-vous avec les objets récupérés
        RendezVous rendezVous = new RendezVous();
        rendezVous.setDateRendezVous(rendezVousDto.getDateRendezVous());
        rendezVous.setMedecin(medecin);
        rendezVous.setPatient(patient);
        rendezVous.setConsultation(consultation);

        // Enregistrer le rendez-vous
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
            // Assurez-vous de mapper correctement les champs de relation
            // Par exemple, pour Medecin, Patient et Consultation :
            rendezVous.setMedecin(rendezVousDto.getMedecin());
            rendezVous.setPatient(rendezVousDto.getPatient());
            rendezVous.setConsultation(rendezVousDto.getConsultation());

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

    private RendezVousDto toDto(RendezVous rendezVous) {
        return new RendezVousDto(
                rendezVous.getId(),
                rendezVous.getDateRendezVous(),
                // Récupérer les autres attributs des objets Medecin, Patient et Consultation
                medecinRepository.findById(rendezVous.getMedecin().getId()).orElse(null),
                patientRepository.findById(rendezVous.getPatient().getId()).orElse(null),
                consultationRepository.findById(rendezVous.getConsultation().getId()).orElse(null)
        );
    }

    // Convertir un DTO en entité RendezVous
    private RendezVous toEntity(RendezVousDto rendezVousDto) {
        RendezVous rendezVous = new RendezVous();
        rendezVous.setId(rendezVousDto.getId());
        rendezVous.setDateRendezVous(rendezVousDto.getDateRendezVous());
        rendezVous.setMedecin(rendezVousDto.getMedecin());
        rendezVous.setPatient(rendezVousDto.getPatient());
        rendezVous.setConsultation(rendezVousDto.getConsultation());
        return rendezVous;
    }
    // Autres méthodes de service (getAllRendezVous, getRendezVousById, updateRendezVous, deleteRendezVous)


}

