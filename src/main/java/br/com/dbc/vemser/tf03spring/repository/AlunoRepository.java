package br.com.dbc.vemser.tf03spring.repository;

import br.com.dbc.vemser.tf03spring.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Integer> {
}
