package fi.hh.swd22.HHkysely.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import fi.hh.swd22.HHkysely.domain.Kysely;
import fi.hh.swd22.HHkysely.domain.KyselyRepository;
import fi.hh.swd22.HHkysely.domain.KysymysRepository;

@RestController
@CrossOrigin("*")
public class KyselyController {

    @Autowired
    private KyselyRepository kyselyRepository;

    @Autowired
    private KysymysRepository kysymysRepository;

    @GetMapping("/kyselyt")
    public List<Kysely> getKyselyt() {
        return (List<Kysely>) kyselyRepository.findAll();
    }

}
