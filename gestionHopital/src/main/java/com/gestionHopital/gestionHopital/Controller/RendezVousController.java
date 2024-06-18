package com.gestionHopital.gestionHopital.Controller;

import com.gestionHopital.gestionHopital.Dto.RendezVousDto;
import com.gestionHopital.gestionHopital.Service.RendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rendezvous") // Chemin de base pour ce contrôleur
public class RendezVousController {

    @Autowired
    private RendezVousService rendezVousService;

    // Endpoint pour récupérer tous les rendez-vous
    @GetMapping("/all")
    public ResponseEntity<List<RendezVousDto>> getAllRendezVous() {
        List<RendezVousDto> rendezVousList = rendezVousService.getAllRendezVous();
        return ResponseEntity.ok(rendezVousList);
    }

    // Endpoint pour récupérer un rendez-vous par son identifiant
    @GetMapping("/{id}")
    public ResponseEntity<RendezVousDto> getRendezVousById(@PathVariable Long id) {
        Optional<RendezVousDto> rendezVousDto = rendezVousService.getRendezVousById(id);
        return rendezVousDto.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint pour créer un nouveau rendez-vous
    @PostMapping("/create")
    public ResponseEntity<RendezVousDto> createRendezVous(@RequestBody RendezVousDto rendezVousDto) {
        RendezVousDto createdRendezVous = rendezVousService.createRendezVous(rendezVousDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdRendezVous);
    }

    // Endpoint pour mettre à jour un rendez-vous existant
    @PutMapping("/{id}")
    public ResponseEntity<RendezVousDto> updateRendezVous(@PathVariable Long id, @RequestBody RendezVousDto rendezVousDto) {
        Optional<RendezVousDto> updatedRendezVous = rendezVousService.updateRendezVous(id, rendezVousDto);
        return updatedRendezVous.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Endpoint pour supprimer un rendez-vous par son identifiant
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRendezVous(@PathVariable Long id) {
        rendezVousService.deleteRendezVous(id);
        return ResponseEntity.noContent().build();
    }
}
