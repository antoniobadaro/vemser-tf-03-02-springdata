package br.com.dbc.vemser.tf03spring.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class AlunoDTO extends AlunoCreateDTO {

    @Schema(description = "ID do aluno")
    private Integer idAluno;

}
