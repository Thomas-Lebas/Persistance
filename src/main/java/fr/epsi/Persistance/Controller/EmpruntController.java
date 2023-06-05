package fr.epsi.Persistance.Controller;

import fr.epsi.Persistance.Repository.EmpruntRepository;
import fr.epsi.Persistance.common.Emprunt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/emprunt")
public class EmpruntController {

    @Autowired
    private EmpruntRepository empruntRepository;

    @PostMapping
    public Emprunt createEmprunt(@RequestBody Emprunt emprunt) {
        return empruntRepository.save(emprunt);
    }

    @GetMapping
    public List<Emprunt> getAllEmprunts() {
        return (List<Emprunt>) empruntRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Emprunt> getEmpruntById(@PathVariable Long id) {
        Optional<Emprunt> optionalEmprunt = empruntRepository.findById(id);
        if (optionalEmprunt.isPresent()) {
            Emprunt emprunt = optionalEmprunt.get();
            return ResponseEntity.ok(emprunt);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Emprunt> updateEmprunt(@PathVariable Long id, @RequestBody Emprunt empruntData) {
        Optional<Emprunt> optionalEmprunt = empruntRepository.findById(id);
        if (optionalEmprunt.isPresent()) {
            Emprunt emprunt = optionalEmprunt.get();
            emprunt.setDate_emprunt(empruntData.getDate_emprunt());
            emprunt.setDate_fin_prevue(empruntData.getDate_fin_prevue());
            emprunt.setDate_retour(empruntData.getDate_retour());
            emprunt.setLivre(empruntData.getLivre());
            empruntRepository.save(emprunt);
            return ResponseEntity.ok(emprunt);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmprunt(@PathVariable Long id) {
        Optional<Emprunt> optionalEmprunt = empruntRepository.findById(id);
        if (optionalEmprunt.isPresent()) {
            Emprunt emprunt = optionalEmprunt.get();
            empruntRepository.delete(emprunt);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

