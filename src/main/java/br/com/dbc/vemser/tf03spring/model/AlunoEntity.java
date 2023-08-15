package br.com.dbc.vemser.tf03spring.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

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

    @Column(name = "email")
    private String email;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "Aluno_X_Curso",
            joinColumns = @JoinColumn (name = "id_aluno"),
            inverseJoinColumns = @JoinColumn (name = "idcurso")
    )
    private Set<CursoEntity> cursos;

    @JsonIgnore
    @OneToOne(mappedBy = "aluno", cascade = CascadeType.ALL)
    private EnderecoEntity endereco;
}
