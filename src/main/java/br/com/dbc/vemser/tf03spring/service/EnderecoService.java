package br.com.dbc.vemser.tf03spring.service;

import br.com.dbc.vemser.tf03spring.dto.AlunoDTO;
import br.com.dbc.vemser.tf03spring.dto.EnderecoCreateDTO;
import br.com.dbc.vemser.tf03spring.dto.EnderecoDTO;
import br.com.dbc.vemser.tf03spring.dto.RelatorioDTO;
import br.com.dbc.vemser.tf03spring.exception.BancoDeDadosException;
import br.com.dbc.vemser.tf03spring.exception.RegraDeNegocioException;
import br.com.dbc.vemser.tf03spring.model.AlunoEntity;
import br.com.dbc.vemser.tf03spring.model.EnderecoEntity;
import br.com.dbc.vemser.tf03spring.model.ProfessorEntity;
import br.com.dbc.vemser.tf03spring.repository.EnderecoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final AlunoService alunoService;
    private final ObjectMapper objectMapper;

    public EnderecoService(EnderecoRepository enderecoRepository, AlunoService alunoService, ObjectMapper objectMapper){
        this.alunoService = alunoService;
        this.objectMapper=objectMapper;
        this.enderecoRepository = enderecoRepository;
    }

    public EnderecoDTO create(EnderecoCreateDTO enderecoCreateDTO) throws BancoDeDadosException, RegraDeNegocioException {
        AlunoDTO alunoId = alunoService.findById(enderecoCreateDTO.getIdAluno());
        AlunoEntity alunoEntity = objectMapper.convertValue(alunoId, AlunoEntity.class);

        if(alunoEntity != null) {
            EnderecoEntity enderecoCriado = retornarEntidade(enderecoCreateDTO);
            EnderecoEntity enderecoEnviar = enderecoRepository.save(enderecoCriado);
            return retornarDTO(enderecoEnviar);
        }
        return null;
    }
    public List<RelatorioDTO> createRelatorioDTO(){
        return enderecoRepository.createRelatorioDTO();
    }

    public List<EnderecoDTO> findAll() throws BancoDeDadosException {
        return enderecoRepository.findAll().stream()
                .map(this::retornarDTO)
                .collect(Collectors.toList());
    }

    public EnderecoDTO findById(Integer idEndereco) throws BancoDeDadosException {
        EnderecoEntity enderecoEncontrado = enderecoRepository.findById(idEndereco).get();
        return retornarDTO(enderecoEncontrado);
    }


    public EnderecoDTO update(Integer idEndereco, EnderecoDTO enderecoDTO) throws RegraDeNegocioException {
        EnderecoEntity enderecoAtualizado = enderecoRepository.findById(idEndereco)
                .orElseThrow(() -> new RegraDeNegocioException("Endereco não encontrado"));


        enderecoAtualizado.setLogradouro(enderecoDTO.getLogradouro());
        enderecoAtualizado.setEstado(enderecoDTO.getEstado());
        enderecoAtualizado.setComplemento(enderecoDTO.getComplemento());
        enderecoAtualizado.setCidade(enderecoDTO.getCidade());
        enderecoAtualizado.setCep(enderecoDTO.getCep());
        enderecoAtualizado.setBairro(enderecoDTO.getBairro());
        enderecoAtualizado.setNumero(enderecoDTO.getNumero());

        EnderecoEntity enderecoEnviar = enderecoRepository.save(enderecoAtualizado);
        return retornarDTO(enderecoEnviar);
    }

    public void delete(Integer idEndereco) throws BancoDeDadosException {
        enderecoRepository.deleteById(idEndereco);
    }

    public EnderecoEntity retornarEntidade(EnderecoCreateDTO enderecoCreateDTO){
        return objectMapper.convertValue(enderecoCreateDTO, EnderecoEntity.class);
    }

    public EnderecoDTO retornarDTO(EnderecoEntity enderecoEntity){
        return objectMapper.convertValue(enderecoEntity, EnderecoDTO.class);
    }

}
