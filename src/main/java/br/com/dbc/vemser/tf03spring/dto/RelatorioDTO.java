package br.com.dbc.vemser.tf03spring.dto;

import lombok.Data;

import javax.persistence.Column;

@Data
public class RelatorioDTO {

    private String nome;
    private String idade;
    private String cpf;
    private String numeroDeMatricula;
    private String email;
    private String cep;
    private String cidade;
    private String bairro;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String estado;


    public RelatorioDTO(String nome, String idade, String cpf, String numeroDeMatricula, String email,  String cep, String cidade, String bairro, String logradouro, Integer numero, String complemento, String estado) {
        this.nome = nome;
        this.idade = idade;
        this.cpf = cpf;
        this.numeroDeMatricula = numeroDeMatricula;
        this.email = email;
        this.cep = cep;
        this.cidade = cidade;
        this.bairro = bairro;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.estado = estado;
    }
}
