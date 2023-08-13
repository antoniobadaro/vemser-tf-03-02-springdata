package br.com.dbc.vemser.tf03spring.repository;

import br.com.dbc.vemser.tf03spring.model.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<CursoEntity, Integer> {
}
