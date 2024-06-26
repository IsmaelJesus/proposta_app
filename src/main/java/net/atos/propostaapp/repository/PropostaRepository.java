package net.atos.propostaapp.repository;

import net.atos.propostaapp.entity.Proposta;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PropostaRepository extends CrudRepository<Proposta,Long> {
    List<Proposta> findAllByIntegradaIsFalse();
}
