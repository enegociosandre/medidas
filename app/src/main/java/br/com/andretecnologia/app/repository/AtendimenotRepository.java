package br.com.andretecnologia.app.repository;

import br.com.andretecnologia.app.model.Atendimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AtendimenotRepository extends JpaRepository<Atendimento, Integer> {
}
