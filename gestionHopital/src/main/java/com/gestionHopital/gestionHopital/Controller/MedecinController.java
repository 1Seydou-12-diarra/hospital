package com.gestionHopital.gestionHopital.Controller;
import com.gestionHopital.gestionHopital.Dto.MedecinDto;
import com.gestionHopital.gestionHopital.Service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/medecins")
public class MedecinController {

    @Autowired
    private MedecinService medecinService;

    @GetMapping
    public ResponseEntity<List<MedecinDto>> getAllMedecins() {
        List<MedecinDto> medecins = medecinService.getAllMedecins();
        return new ResponseEntity<>(medecins, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedecinDto> getMedecinById(@PathVariable("id") Long id) {
        Optional<MedecinDto> medecin = medecinService.getMedecinById(id);
        return medecin.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<MedecinDto> createMedecin(@RequestBody MedecinDto medecinDto) {
        MedecinDto createdMedecin = medecinService.createMedecin(medecinDto);
        return new ResponseEntity<>(createdMedecin, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedecinDto> updateMedecin(@PathVariable("id") Long id, @RequestBody MedecinDto medecinDto) {
        Optional<MedecinDto> updatedMedecin = medecinService.updateMedecin(id, medecinDto);
        return updatedMedecin.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedecin(@PathVariable("id") Long id) {
        medecinService.deleteMedecin(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

