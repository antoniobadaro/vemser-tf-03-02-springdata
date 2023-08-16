package br.com.dbc.vemser.tf03spring.repository;

import br.com.dbc.vemser.tf03spring.dto.RelatorioCursoDTO;
import br.com.dbc.vemser.tf03spring.dto.RelatorioDTO;
import br.com.dbc.vemser.tf03spring.model.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CursoRepository extends JpaRepository<CursoEntity, Integer> {

    @Query("SELECT NEW br.com.dbc.vemser.tf03spring.dto.RelatorioCursoDTO("+
            "c.cargaHoraria, " +
            "p.nome, " +
            "p.cpf, " +
            "p.especialidade ) " +
            "FROM CURSO c " +
            "INNER JOIN PROFESSOR p "+
            "ON (c.idProfessor = p.idProfessor)")
    List<RelatorioCursoDTO> createRelatorioCursoDTO();
}
