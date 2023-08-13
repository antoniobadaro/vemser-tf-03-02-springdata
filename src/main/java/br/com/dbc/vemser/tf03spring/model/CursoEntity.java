package br.com.dbc.vemser.tf03spring.model;

import br.com.dbc.vemser.tf03spring.model.enums.TipoPeriodo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "CURSO")
public class CursoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CURSO_SEQ")
    @SequenceGenerator(name = "CURSO_SEQ", sequenceName = "SEQ_CURSO", allocationSize = 1)
    @Column(name = "id_curso")
    private Integer idCurso;

    @Column(name = "nome")
    private String nome;

    @Column(name = "carga_horaria")
    private Integer cargaHoraria;

    @Column(name = "periodo")
    private TipoPeriodo periodo;
}

