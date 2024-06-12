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
@RequestMapping("/rendezvous")
public class RendezVousController {

    @Autowired
    private RendezVousService rendezVousService;

    @GetMapping
    public ResponseEntity<List<RendezVousDto>> getAllRendezVous() {
        List<RendezVousDto> rendezVousList = rendezVousService.getAllRendezVous();
        return new ResponseEntity<>(rendezVousList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RendezVousDto> getRendezVousById(@PathVariable("id") Long id) {
        Optional<RendezVousDto> rendezVous = rendezVousService.getRendezVousById(id);
        return rendezVous.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }



    @PutMapping("/{id}")
    public ResponseEntity<RendezVousDto> updateRendezVous(@PathVariable("id") Long id, @RequestBody RendezVousDto rendezVousDto) {
        Optional<RendezVousDto> updatedRendezVous = rendezVousService.updateRendezVous(id, rendezVousDto);
        return updatedRendezVous.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRendezVous(@PathVariable("id") Long id) {
        rendezVousService.deleteRendezVous(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

