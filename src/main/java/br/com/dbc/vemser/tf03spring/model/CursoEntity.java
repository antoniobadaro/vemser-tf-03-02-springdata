package br.com.dbc.vemser.tf03spring.model;

import br.com.dbc.vemser.tf03spring.model.enums.TipoPeriodo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "CURSO")
public class CursoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CURSO_SEQ")
    @SequenceGenerator(name = "CURSO_SEQ", sequenceName = "SEQ_CURSO", allocationSize = 1)
    @Column(name = "IDCURSO")
    private Integer idCurso;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "CARGAHORARIA")
    private Integer cargaHoraria;

    @Enumerated(EnumType.STRING)
    @Column(name = "PERIODO")
    private TipoPeriodo periodo;

    @JsonIgnore
    @ManyToMany(mappedBy = "cursos")
    private Set<AlunoEntity> alunos;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_professor", referencedColumnName = "id_professor")
    private ProfessorEntity professor;

}

