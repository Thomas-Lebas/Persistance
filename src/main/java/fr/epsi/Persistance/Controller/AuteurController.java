package fr.epsi.Persistance.Controller;

import fr.epsi.Persistance.Repository.AuteurRepository;
import fr.epsi.Persistance.common.Auteur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/auteur")
public class AuteurController {

    @Autowired
    private AuteurRepository auteurRepository;

    @GetMapping
    public List<Auteur> getAllAuteurs() {
        return (List<Auteur>) auteurRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Auteur> getAuteurById(@PathVariable Long id) {
        Optional<Auteur> optionalAuteur = auteurRepository.findById(id);
        if (optionalAuteur.isPresent()) {
            return ResponseEntity.ok(optionalAuteur.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Auteur createAuteur(@RequestBody Auteur auteur) {
        return auteurRepository.save(auteur);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Auteur> updateAuteur(@PathVariable Long id, @RequestBody Auteur auteur) {
        Optional<Auteur> optionalAuteur = auteurRepository.findById(id);
        if (optionalAuteur.isPresent()) {
            auteur.setId(id);
            Auteur updatedAuteur = auteurRepository.save(auteur);
            return ResponseEntity.ok(updatedAuteur);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuteur(@PathVariable Long id) {
        Optional<Auteur> optionalAuteur = auteurRepository.findById(id);
        if (optionalAuteur.isPresent()) {
            auteurRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

