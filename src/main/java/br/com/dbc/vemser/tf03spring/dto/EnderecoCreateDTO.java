package br.com.dbc.vemser.tf03spring.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EnderecoCreateDTO {
    @NotNull(message = "O cep nome não pode ser nulo")
    @Schema(description = "CEP do endereço", required = true, example = "12340000")
    private String cep;

    @NotNull(message = "O campo cidade não pode ser nulo")
    @Schema(description = "Cidade do endereço", required = true, example = "Porto Alegre")
    private String cidade;

    @NotNull(message = "O campo bairro não pode ser nulo")
    @Schema(description = "Bairro do endereço", required = true, example = "Centro")
    private String bairro;

    @NotNull(message = "O campo logradouro não pode ser nulo")
    @Schema(description = "Logradouro do endereço", required = true, example = "Avenida das flores")
    private String logradouro;

    @NotNull(message = "O campo numero não pode ser nulo")
    @Schema(description = "Número do endereço", required = true, example = "54")
    private Integer numero;

    @NotNull(message = "O campo complemento não pode ser nulo")
    @Schema(description = "Complemento do endereço", required = true, example = "Ao lado dos Correios")
    private String complemento;

    @NotNull(message = "O campo estado não pode ser nulo")
    @Schema(description = "Estado do endereço", required = true, example = "Rio Grande do Sul")
    private String estado;
}
