package br.com.dbc.vemser.tf03spring.model;

import br.com.dbc.vemser.tf03spring.dto.AlunoDTO;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity(name = "ALUNO")
public class AlunoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ALUNO_SEQ")
    @SequenceGenerator(name = "ALUNO_SEQ", sequenceName = "SEQ_ALUNO", allocationSize = 1)
    @Column(name = "id_aluno")
    private Integer idAluno;

    @Column(name = "nome")
    private String nome;

    @Column(name = "idade")
    private String idade;

    @Column(name = "cpf")
    private String cpf;

    @Column(name = "numero")
    private String numeroDeMatricula;
}
