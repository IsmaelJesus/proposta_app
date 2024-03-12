package net.atos.propostaapp.repository;

import net.atos.propostaapp.entity.Proposta;
import org.springframework.data.repository.CrudRepository;

public interface PropostaRepository extends CrudRepository<Proposta,Long> {
}
