package fr.epsi.Persistance.Controller;

import fr.epsi.Persistance.Repository.LivreRepository;
import fr.epsi.Persistance.common.Livre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/livre")
public class LivreController {

    @Autowired
    private LivreRepository livreRepository;

    @PostMapping
    public Livre createLivre(@RequestBody Livre livre) {
        return livreRepository.save(livre);
    }

    @GetMapping
    public List<Livre> getAllLivres() {
        return livreRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livre> getLivreById(@PathVariable Long id) {
        Optional<Livre> optionalLivre = livreRepository.findById(id);
        if (optionalLivre.isPresent()) {
            Livre livre = optionalLivre.get();
            return ResponseEntity.ok(livre);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livre> updateLivre(@PathVariable Long id, @RequestBody Livre livreData) {
        Optional<Livre> optionalLivre = livreRepository.findById(id);
        if (optionalLivre.isPresent()) {
            Livre livre = optionalLivre.get();
            livre.setTitre(livreData.getTitre());
            livre.setDate_de_Parution(livreData.getDate_de_Parution());
            livre.setNombre_de_pages(livreData.getNombre_de_pages());
            livre.setAuteur(livreData.getAuteur());
            livre.setCategorie(livreData.getCategorie());
            livreRepository.save(livre);
            return ResponseEntity.ok(livre);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLivre(@PathVariable Long id) {
        Optional<Livre> optionalLivre = livreRepository.findById(id);
        if (optionalLivre.isPresent()) {
            Livre livre = optionalLivre.get();
            livreRepository.delete(livre);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}