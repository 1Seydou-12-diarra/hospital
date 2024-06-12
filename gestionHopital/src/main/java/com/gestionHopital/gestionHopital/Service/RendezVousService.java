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

    public List<RendezVousDto> getAllRendezVous() {
        List<RendezVous> rendezVousList = rendezVousRepository.findAll();
        return rendezVousList.stream().map(this::toDto).collect(Collectors.toList());
    }

    public Optional<RendezVousDto> getRendezVousById(Long id) {
        Optional<RendezVous> rendezVous = rendezVousRepository.findById(id);
        return rendezVous.map(this::toDto);
    }

}

