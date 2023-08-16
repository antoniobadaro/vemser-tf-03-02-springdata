package br.com.dbc.vemser.tf03spring.model;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "PROFESSOR")
public class ProfessorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFESSOR_SEQ")
    @SequenceGenerator(name = "PROFESSOR_SEQ", sequenceName = "SEQ_PROFESSOR", allocationSize = 1)
    @Column(name = "ID_PROFESSOR")
    private Integer idProfessor;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "CPF")
    private String cpf;

    @Column(name = "ESPECIALIDADE")
    private String especialidade;


    @Column(name = "SALARIO")
    private Double salario;

}
