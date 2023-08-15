package br.com.dbc.vemser.tf03spring.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity(name = "ENDERECO")
public class EnderecoEntity {
    private Integer idEndereco;
    private String cep;
    private String cidade;
    private String bairro;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String estado;
}
