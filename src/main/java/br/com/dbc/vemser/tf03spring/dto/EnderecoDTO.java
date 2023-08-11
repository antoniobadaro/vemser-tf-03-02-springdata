package br.com.dbc.vemser.tf03spring.dto;

import br.com.dbc.vemser.tf03spring.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EnderecoDTO extends EnderecoCreateDTO{
    private Integer idEndereco;
    private String cep;
    private String cidade;
    private String bairro;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String estado;

    public EnderecoDTO (Endereco endereco){
        this.idEndereco = endereco.getIdEndereco();
        this.cep = endereco.getCep();
        this.cidade = endereco.getCidade();
        this.bairro = endereco.getBairro();
        this.logradouro = endereco.getLogradouro();
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
        this.estado = endereco.getEstado();
    }

    public EnderecoDTO (EnderecoCreateDTO enderecoCreateDTO){
        this.cep = enderecoCreateDTO.getCep();
        this.cidade = enderecoCreateDTO.getCidade();
        this.bairro = enderecoCreateDTO.getBairro();
        this.logradouro = enderecoCreateDTO.getLogradouro();
        this.numero = enderecoCreateDTO.getNumero();
        this.complemento = enderecoCreateDTO.getComplemento();
        this.estado = enderecoCreateDTO.getEstado();
    }
}
