package br.com.dbc.vemser.tf03spring.service;

import br.com.dbc.vemser.tf03spring.dto.AlunoDTO;
import br.com.dbc.vemser.tf03spring.dto.EnderecoDTO;
import br.com.dbc.vemser.tf03spring.exception.BancoDeDadosException;
import br.com.dbc.vemser.tf03spring.model.Aluno;
import br.com.dbc.vemser.tf03spring.model.Endereco;
import br.com.dbc.vemser.tf03spring.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository){
        this.enderecoRepository = enderecoRepository;
    }

    public EnderecoDTO create(EnderecoDTO enderecoDTO) throws BancoDeDadosException {
        Endereco enderecoCriado = enderecoRepository.create(enderecoDTO);

        return new EnderecoDTO(enderecoCriado);
    }

    public List<EnderecoDTO> findAll() throws BancoDeDadosException {
        List<Endereco> todosOsEnderecos = enderecoRepository.findAll();
        List<EnderecoDTO> enderecoDTOS = new ArrayList<>();

        for (Endereco endereco : todosOsEnderecos) {
            enderecoDTOS.add(new EnderecoDTO(endereco));
        }

        return enderecoDTOS;
    }

    public EnderecoDTO findById(Integer idEndereco) throws BancoDeDadosException {
        Endereco enderecoEncontrado = enderecoRepository.findById(idEndereco);

        return new EnderecoDTO(enderecoEncontrado);
    }

    public EnderecoDTO update(Integer idEndereco, EnderecoDTO enderecoDTO) throws BancoDeDadosException {
        Endereco enderecoAtualizado = enderecoRepository.update(idEndereco, enderecoDTO);

        return new EnderecoDTO(enderecoAtualizado);
    }

    public void delete(Integer idEndereco) throws BancoDeDadosException {
        enderecoRepository.delete(idEndereco);
    }

}
