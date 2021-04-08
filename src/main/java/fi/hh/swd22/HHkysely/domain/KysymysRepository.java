package fi.hh.swd22.HHkysely.domain;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

public interface KysymysRepository extends CrudRepository<Kysymys, Long> {
    List<Kysymys> findByKyselyId(Long id);
}