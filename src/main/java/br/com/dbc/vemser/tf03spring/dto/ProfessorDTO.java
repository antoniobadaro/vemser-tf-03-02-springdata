package br.com.dbc.vemser.tf03spring.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@EqualsAndHashCode(callSuper = true)
public class ProfessorDTO extends ProfessorCreateDTO {

    @Schema(description = "ID do professor")
    private Integer idProfessor;

}