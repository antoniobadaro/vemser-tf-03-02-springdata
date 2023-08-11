package br.com.dbc.vemser.tf03spring.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CursoDTO extends CursoCreateDTO{
    @Schema(description = "Id do curso")
    private Integer idCurso;

}
