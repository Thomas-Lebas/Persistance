package fr.epsi.Persistance.Controller;

import fr.epsi.Persistance.Repository.AdherentRepository;
import fr.epsi.Persistance.common.Adherent;
import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/adherent")
public class AdherentController {

    @Autowired
    private AdherentRepository adherentRepository;


    @GetMapping("/{id}")
    public ResponseEntity<Adherent> getAdherentById(@PathVariable("id") Long id) {
        Optional<Adherent> adherentOptional = adherentRepository.findById(id);
        if (adherentOptional.isPresent()) {
            return ResponseEntity.ok(adherentOptional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Adherent createAdherent(@RequestBody Adherent adherent) {
        return adherentRepository.save(adherent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Adherent> updateAdherent(@PathVariable("id") Long id, @RequestBody Adherent adherent) {
        Optional<Adherent> adherentOptional = adherentRepository.findById(id);
        if (adherentOptional.isPresent()) {
            Adherent existingAdherent = adherentOptional.get();
            existingAdherent.setNom(adherent.getNom());
            existingAdherent.setPrenom(adherent.getPrenom());
            existingAdherent.setEmail(adherent.getEmail());
            existingAdherent.setDate_inscription(adherent.getDate_inscription());
            return ResponseEntity.ok(adherentRepository.save(existingAdherent));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdherent(@PathVariable("id") Long id) {
        Optional<Adherent> adherentOptional = adherentRepository.findById(id);
        if (adherentOptional.isPresent()) {
            adherentRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
