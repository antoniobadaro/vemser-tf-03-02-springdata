package br.com.dbc.vemser.tf03spring.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import java.util.Set;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "PROFESSOR")
public class ProfessorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFESSOR_SEQ")
    @SequenceGenerator(name = "PROFESSOR_SEQ", sequenceName = "SEQ_PROFESSOR", allocationSize = 1)
    @Column(name = "id_professor")
    private Integer idProfessor;

    @Column(name = "nome")
    private String nome;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "especialidade")
    private String especialidade;

    @Column(name = "salario")
    private double salario;

    @JsonIgnore
    @OneToOne(mappedBy = "professor", cascade = CascadeType.ALL)
    private EnderecoEntity endereco;

    @JsonIgnore
    @OneToMany(mappedBy = "professor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CursoEntity> cursos;


}