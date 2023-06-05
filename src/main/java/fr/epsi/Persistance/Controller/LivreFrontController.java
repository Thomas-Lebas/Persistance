package fr.epsi.Persistance.Controller;

import org.springframework.ui.Model;
import fr.epsi.Persistance.Repository.LivreRepository;
import fr.epsi.Persistance.common.Livre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class LivreFrontController {

    @Autowired
    private LivreRepository livreRepository;

    @GetMapping("/index")
    public String afficherPageTest(Model model) {
        List<Livre> livres = livreRepository.findAll();
        model.addAttribute("livres", livres);
        return "index";
    }

    @GetMapping("/creer-livre")
    public String afficherFormulaireCreationLivre() {
        return "creer-livre";
    }

    @PostMapping("/creer-livre")
    public String creerLivre(@RequestParam("titre") String titre,
                             @RequestParam("dateParution") LocalDate dateParution,
                             @RequestParam("nombrePages") int nombrePages) {

        Livre livre = new Livre();
        livre.setTitre(titre);
        livre.setDate_de_Parution(dateParution);
        livre.setNombre_de_pages(nombrePages);

        livreRepository.save(livre);

        return "redirect:/index";
    }


}
