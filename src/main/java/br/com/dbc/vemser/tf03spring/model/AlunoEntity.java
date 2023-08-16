package br.com.dbc.vemser.tf03spring.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "ALUNO")
public class AlunoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ALUNO_SEQ")
    @SequenceGenerator(name = "ALUNO_SEQ", sequenceName = "SEQ_ALUNO", allocationSize = 1)
    @Column(name = "ID_ALUNO")
    private Integer idAluno;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "IDADE")
    private String idade;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "NUMERO")
    private String numeroDeMatricula;

    @Column(name = "EMAIL")
    private String email;

}
