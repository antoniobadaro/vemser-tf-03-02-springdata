package br.com.dbc.vemser.tf03spring.repository;

import br.com.dbc.vemser.tf03spring.dto.AlunoDTO;
import br.com.dbc.vemser.tf03spring.model.AlunoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<AlunoEntity, Integer> {

    Page<AlunoEntity> findAll(Pageable pageable);

}
