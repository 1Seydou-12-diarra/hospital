package com.gestionHopital.gestionHopital.Controller;
import com.gestionHopital.gestionHopital.Dto.PatientDto;
import com.gestionHopital.gestionHopital.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;
    /**
     * liste des patients
     * */
    @GetMapping
    public ResponseEntity<List<PatientDto>> getAllPatients() {
        List<PatientDto> patients = patientService.getAllPatients();
        return new ResponseEntity<>(patients, HttpStatus.OK);
    }
    /**
     * rechercher un patient par son id
     * **/

    @GetMapping("/{id}")
    public ResponseEntity<PatientDto> getPatientById(@PathVariable("id") Long id) {
        Optional<PatientDto> patient = patientService.getPatientById(id);
        return patient.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * creer un patient
     * */
    @PostMapping
    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDTO) {
        PatientDto createdPatient = patientService.createPatient(patientDTO);
        return new ResponseEntity<>(createdPatient, HttpStatus.CREATED);
    }
    /**
     * modifier un patient
     * */
    @PutMapping("/{id}")
    public ResponseEntity<PatientDto> updatePatient(@PathVariable("id") Long id, @RequestBody PatientDto patientDTO) {
        Optional<PatientDto> updatedPatient = patientService.updatePatient(id, patientDTO);
        return updatedPatient.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    /**
     * supprimer un patient
     * */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable("id") Long id) {
        patientService.deletePatient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
