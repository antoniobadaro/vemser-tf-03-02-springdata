package br.com.dbc.vemser.tf03spring.repository;

import br.com.dbc.vemser.tf03spring.dto.RelatorioDTO;
import br.com.dbc.vemser.tf03spring.model.EnderecoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EnderecoRepository extends JpaRepository<EnderecoEntity, Integer> {


    @Query("SELECT NEW br.com.dbc.vemser.tf03spring.dto.RelatorioDTO("+
            "a.nome, "+
            "a.idade, "+
            "a.cpf, "+
            "a.numeroDeMatricula, "+
            "a.email, "+
            "e.cep, "+
            "e.cidade, "+
            "e.bairro, "+
            "e.logradouro, "+
            "e.numero, "+
            "e.complemento, "+
            "e.estado) "+
            "FROM ALUNO a "+
            "INNER JOIN ENDERECO e "+
            "ON (a.idAluno = e.idAluno)"
    )
    List<RelatorioDTO> createRelatorioDTO();
}
