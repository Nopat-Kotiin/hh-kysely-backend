package fi.hh.swd22.HHkysely.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.hh.swd22.HHkysely.domain.Kysely;
import fi.hh.swd22.HHkysely.domain.KyselyRepository;
import fi.hh.swd22.HHkysely.domain.Kysymys;
import fi.hh.swd22.HHkysely.domain.KysymysRepository;


@Controller
@CrossOrigin("*")
public class KyselyController {

    @Autowired
    private KyselyRepository kyselyRepository;

    @Autowired
    private KysymysRepository kysymysRepository;

    @GetMapping("/kyselyt")
    public @ResponseBody List<Kysely> getKyselyt() {
        return (List<Kysely>) kyselyRepository.findAll();
    }

    @RequestMapping(value="/lisaakysely")
    public String lisaaKysely(Model model) {
    	
    	
    	model.addAttribute("kysely", new Kysely());
    	model.addAttribute("kyselyt", kyselyRepository.findAll());
    	
    	return "lisaakysely";
    	
    }
    
    @RequestMapping(value = "/surveys", method = RequestMethod.GET)
	public String getKyselyt (Model model) {
		List<Kysely> kyselyt = (List<Kysely>) kyselyRepository.findAll();
		model.addAttribute("kyselyt", kyselyt);
		return "surveys";
    }
    
    @RequestMapping(value="/save", method = RequestMethod.POST)
    public String tallennaKysely(@ModelAttribute Kysely kysely) {    	
    	kyselyRepository.save(kysely);

        for (Kysymys k : kysely.getKysymykset()) {
            k.setKysely(kysely);
            kysymysRepository.save(k);
        }
    	
    	return "redirect:/surveys";
    }
    
    @GetMapping("addsurvey")
    public String addSurvey(Model model) {
        List<Kysymys> kysymykset = new ArrayList<>();
        Kysely kysely = new Kysely("");
        Kysymys k1 = new Kysymys("Kysymys 1", kysely);
        kysymykset.add(k1);
        model.addAttribute("kysely", kysely);
        model.addAttribute("kysymykset", kysymykset);
        return "addsurvey";
    }

    @GetMapping("edit/{id}")
    public String editSurvey(@PathVariable("id") Long id, Model model) {
        Kysely kysely = kyselyRepository.findById(id).get();
        List<Kysymys> kysymykset = kysely.getKysymykset();
        model.addAttribute("kysely", kysely);
        model.addAttribute("kysymykset", kysymykset);
        return "addsurvey";
    }

    // Palauttaa No Content statuksen, jos kyselyä ei löydy
    @GetMapping("survey/{id}")
    public @ResponseBody ResponseEntity<Kysely> getSurveyById(@PathVariable("id") Long id) {
        HttpStatus status = HttpStatus.NO_CONTENT;
        Optional<Kysely> k = kyselyRepository.findById(id);
        Kysely kysely = new Kysely();
        if (k.isPresent()) {
            kysely = k.get();
            status = HttpStatus.OK;
        }
        return new ResponseEntity<>(kysely, status);
    }
    
}
