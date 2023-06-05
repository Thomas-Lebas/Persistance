package fr.epsi.Persistance.Controller;

import fr.epsi.Persistance.Repository.CategorieRepository;
import fr.epsi.Persistance.common.Categorie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class CategorieFrontController {

    @Autowired
    private CategorieRepository categorieRepository;


    @GetMapping("/creer-categorie")
    public String afficherFormulaireCreationCategorie(Model model) {
        model.addAttribute("categorie", new Categorie());
        return "creer-categorie";
    }

    @PostMapping("/creer-categorie")
    public String creerCategorie(@RequestParam("nom") String nom) {
        Categorie categorie = new Categorie();
        categorie.setNom(nom);
        categorieRepository.save(categorie);

        return "redirect:/index"; // Redirection vers la page de liste des catégories après la création
    }


    // CRUD
    @PostMapping("/categories")
    public String createCategorie(@RequestParam("nom") String nom) {
        Categorie categorie = new Categorie();
        categorie.setNom(nom);
        categorieRepository.save(categorie);
        return "redirect:/categories";
    }

    @GetMapping("/categories/{id}")
    public String getCategorie(@PathVariable("id") Long id, Model model) {
        Optional<Categorie> categorieOptional = categorieRepository.findById(id);
        if (categorieOptional.isPresent()) {
            Categorie categorie = categorieOptional.get();
            model.addAttribute("categorie", categorie);
        }
        return "categorie";
    }

    @PostMapping("/categories/{id}")
    public String updateCategorie(@PathVariable("id") Long id, @RequestParam("nom") String nom) {
        Optional<Categorie> categorieOptional = categorieRepository.findById(id);
        if (categorieOptional.isPresent()) {
            Categorie categorie = categorieOptional.get();
            categorie.setNom(nom);
            categorieRepository.save(categorie);
        }
        return "redirect:/accueil";
    }

    @GetMapping("/categories/{id}/delete")
    public String deleteCategorie(@PathVariable("id") Long id) {
        categorieRepository.deleteById(id);
        return "redirect:/accueil";
    }
}