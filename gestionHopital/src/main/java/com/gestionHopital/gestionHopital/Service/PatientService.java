package com.gestionHopital.gestionHopital.Service;


import com.gestionHopital.gestionHopital.Dto.PatientDto;
import com.gestionHopital.gestionHopital.Entity.Patient;
import com.gestionHopital.gestionHopital.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    /**
     * Récupère tous les patients.
     *
     * @return une liste de PatientDTO
     */
    public List<PatientDto> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(this::toDTO).collect(Collectors.toList());
    }

    /**
     * Récupère un patient par son identifiant.
     *
     * @param id l'identifiant du patient
     * @return un Optional contenant PatientDTO si trouvé, sinon vide
     */
    public Optional<PatientDto> getPatientById(Long id) {
        Optional<Patient> patient = patientRepository.findById(id);
        return patient.map(this::toDTO);
    }

    /**
     * Crée un nouveau patient.
     *
     * @param patientDTO les données du patient à créer
     * @return le PatientDTO créé
     */
    public PatientDto createPatient(PatientDto patientDTO) {
        Patient patient = toEntity(patientDTO);
        Patient savedPatient = patientRepository.save(patient);
        return toDTO(savedPatient);
    }

    /**
     * Met à jour un patient existant.
     *
     * @param id         l'identifiant du patient à mettre à jour
     * @param patientDTO les nouvelles données du patient
     * @return un Optional contenant le PatientDTO mis à jour si trouvé, sinon vide
     */
    public Optional<PatientDto> updatePatient(Long id, PatientDto patientDTO) {
        Optional<Patient> existingPatient = patientRepository.findById(id);
        if (existingPatient.isPresent()) {
            Patient patient = existingPatient.get();
            patient.setNom(patientDTO.getNom());
            patient.setPrenom(patientDTO.getPrenom());
            patient.setAdresse(patientDTO.getAdresse());
            patient.setTelephone(patientDTO.getTelephone());
            patient.setEmail(patientDTO.getEmail());
            Patient updatedPatient = patientRepository.save(patient);
            return Optional.of(toDTO(updatedPatient));
        } else {
            return Optional.empty();
        }
    }

    /**
     * Supprime un patient par son identifiant.
     *
     * @param id l'identifiant du patient à supprimer
     */
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }

    /**
     * Convertit un Patient en PatientDTO.
     *
     * @param patient l'entité Patient à convertir
     * @return le PatientDTO converti
     */
    private PatientDto toDTO(Patient patient) {
        return new PatientDto(
                patient.getId(),
                patient.getNom(),
                patient.getPrenom(),
                patient.getAdresse(),
                patient.getTelephone(),
                patient.getEmail()
        );
    }

    /**
     * Convertit un PatientDTO en Patient.
     *
     * @param patientDTO le DTO Patient à convertir
     * @return l'entité Patient convertie
     */
    private Patient toEntity(PatientDto patientDTO) {
        Patient patient = new Patient();
        patient.setId(patientDTO.getId());
        patient.setNom(patientDTO.getNom());
        patient.setPrenom(patientDTO.getPrenom());
        patient.setAdresse(patientDTO.getAdresse());
        patient.setTelephone(patientDTO.getTelephone());
        patient.setEmail(patientDTO.getEmail());
        return patient;
    }
}
