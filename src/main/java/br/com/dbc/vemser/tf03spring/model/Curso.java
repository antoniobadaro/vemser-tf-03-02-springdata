package br.com.dbc.vemser.tf03spring.model;

import br.com.dbc.vemser.tf03spring.model.enums.TipoPeriodo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Curso {

    private Integer idCurso;
    private String nome;
    private Integer cargaHoraria;
    private TipoPeriodo periodo;
}

