package com.gestionHopital.gestionHopital.Controller;

import com.gestionHopital.gestionHopital.Dto.ConsultationDto;
import com.gestionHopital.gestionHopital.Service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/consultations")
public class ConsultationController {

    @Autowired
    private ConsultationService consultationService;

    @GetMapping
    public ResponseEntity<List<ConsultationDto>> getAllConsultations() {
        List<ConsultationDto> consultations = consultationService.getAllConsultations();
        return new ResponseEntity<>(consultations, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsultationDto> getConsultationById(@PathVariable("id") Long id) {
        Optional<ConsultationDto> consultation = consultationService.getConsultationById(id);
        return consultation.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ConsultationDto> createConsultation(@RequestBody ConsultationDto consultationDto) {
        ConsultationDto createdConsultation = consultationService.createConsultation(consultationDto);
        return new ResponseEntity<>(createdConsultation, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsultationDto> updateConsultation(@PathVariable("id") Long id, @RequestBody ConsultationDto consultationDto) {
        Optional<ConsultationDto> updatedConsultation = consultationService.updateConsultation(id, consultationDto);
        return updatedConsultation.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteConsultation(@PathVariable("id") Long id) {
        consultationService.deleteConsultation(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
