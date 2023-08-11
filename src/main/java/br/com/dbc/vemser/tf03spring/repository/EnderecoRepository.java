package br.com.dbc.vemser.tf03spring.repository;

import br.com.dbc.vemser.tf03spring.database.ConexaoBancoDeDados;
import br.com.dbc.vemser.tf03spring.dto.AlunoDTO;
import br.com.dbc.vemser.tf03spring.dto.EnderecoDTO;
import br.com.dbc.vemser.tf03spring.exception.BancoDeDadosException;
import br.com.dbc.vemser.tf03spring.model.Aluno;
import br.com.dbc.vemser.tf03spring.model.Endereco;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class EnderecoRepository {
    private Integer obterProximoIdSequence(String nomeDaSequence) throws SQLException {
        int proximoId = 0;
        Connection connection = null;
        PreparedStatement sequenceStatement = null;
        ResultSet sequenceResultSet = null;

        try {
            connection = ConexaoBancoDeDados.getConnection();
            String sequenceSql = "SELECT " + nomeDaSequence + ".NEXTVAL FROM DUAL";
            sequenceStatement = connection.prepareStatement(sequenceSql);
            sequenceResultSet = sequenceStatement.executeQuery();

            if (sequenceResultSet.next()) {
                proximoId = sequenceResultSet.getInt(1);
            }

        } finally {
            if (sequenceResultSet != null) {
                sequenceResultSet.close();
            }
            if (sequenceStatement != null) {
                sequenceStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return proximoId;
    }

    public Endereco create(EnderecoDTO enderecoDTO) throws BancoDeDadosException {
        Endereco enderecoCriado = new Endereco();

        try {
            Connection connection = ConexaoBancoDeDados.getConnection();

            int proximoId = obterProximoIdSequence("SEQ_ENDERECO");
            String sql = "INSERT INTO ENDERECO (ID_ENDERECO, CEP, CIDADE, BAIRRO, LOGRADOURO, NUMERO, COMPLEMENTO, ESTADO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, proximoId);
            preparedStatement.setString(2, enderecoDTO.getCep());
            preparedStatement.setString(3, enderecoDTO.getCidade());
            preparedStatement.setString(4, enderecoDTO.getBairro());
            preparedStatement.setString(5, enderecoDTO.getLogradouro());
            preparedStatement.setInt(6, enderecoDTO.getNumero());
            preparedStatement.setString(7, enderecoDTO.getComplemento());
            preparedStatement.setString(8, enderecoDTO.getEstado());
            preparedStatement.executeUpdate();
            preparedStatement.close();

            String selectSql = "SELECT * FROM ENDERECO WHERE ID_ENDERECO = ?";

            PreparedStatement selectStatement = connection.prepareStatement(selectSql);
            selectStatement.setInt(1, proximoId);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                enderecoCriado.setIdEndereco(resultSet.getInt("ID_ENDERECO"));
                enderecoCriado.setCep(resultSet.getString("CEP"));
                enderecoCriado.setCidade(resultSet.getString("CIDADE"));
                enderecoCriado.setBairro(resultSet.getString("BAIRRO"));
                enderecoCriado.setLogradouro(resultSet.getString("LOGRADOURO"));
                enderecoCriado.setNumero(resultSet.getInt("NUMERO"));
                enderecoCriado.setComplemento(resultSet.getString("COMPLEMENTO"));
                enderecoCriado.setEstado(resultSet.getString("ESTADO"));
            }

            resultSet.close();
            selectStatement.close();
            connection.close();

            System.out.println("Endereco inserido com sucesso!");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Erro ao inserir endereco.");
            throw new BancoDeDadosException(exception.getCause());
        }

        return enderecoCriado;
    }

    public List<Endereco> findAll() throws BancoDeDadosException {
        List<Endereco> todosOsEnderecos = new ArrayList<>();

        try {
            Connection connection = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM ENDERECO";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Endereco endereco = new Endereco();
                endereco.setIdEndereco(resultSet.getInt("ID_ENDERECO"));
                endereco.setCep(resultSet.getString("CEP"));
                endereco.setCidade(resultSet.getString("CIDADE"));
                endereco.setBairro(resultSet.getString("BAIRRO"));
                endereco.setLogradouro(resultSet.getString("LOGRADOURO"));
                endereco.setNumero(resultSet.getInt("NUMERO"));
                endereco.setComplemento(resultSet.getString("COMPLEMENTO"));
                endereco.setEstado(resultSet.getString("ESTADO"));
                todosOsEnderecos.add(endereco);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());;
            System.out.println("Erro ao listar enderecos.");
            throw new BancoDeDadosException(exception.getCause());
        }

        return todosOsEnderecos;
    }

    public Endereco findById(Integer idEndereco) throws BancoDeDadosException {
        Endereco enderecoEncontrado = new Endereco();

        try {
            Connection connection = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM ENDERECO WHERE ID_ENDERECO = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idEndereco);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                enderecoEncontrado.setIdEndereco(resultSet.getInt("ID_ENDERECO"));
                enderecoEncontrado.setCep(resultSet.getString("CEP"));
                enderecoEncontrado.setCidade(resultSet.getString("CIDADE"));
                enderecoEncontrado.setBairro(resultSet.getString("BAIRRO"));
                enderecoEncontrado.setLogradouro(resultSet.getString("LOGRADOURO"));
                enderecoEncontrado.setNumero(resultSet.getInt("NUMERO"));
                enderecoEncontrado.setComplemento(resultSet.getString("COMPLEMENTO"));
                enderecoEncontrado.setEstado(resultSet.getString("ESTADO"));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Erro ao procurar endereco por ID.");
            throw new BancoDeDadosException(exception.getCause());
        }

        return enderecoEncontrado;
    }

    public Endereco update(Integer idEndereco, EnderecoDTO enderecoDTO) throws BancoDeDadosException {
        Endereco enderecoAtualizado = new Endereco();

        try {
            Connection connection = ConexaoBancoDeDados.getConnection();

            String sql = "UPDATE ENDERECO SET CEP = ?, CIDADE = ?, BAIRRO = ?, LOGRADOURO = ?, NUMERO = ?, COMPLEMENTO = ?, ESTADO = ? WHERE ID_ENDERECO = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, enderecoDTO.getCep());
            preparedStatement.setString(2, enderecoDTO.getCidade());
            preparedStatement.setString(3, enderecoDTO.getBairro());
            preparedStatement.setString(4, enderecoDTO.getLogradouro());
            preparedStatement.setInt(5, enderecoDTO.getNumero());
            preparedStatement.setString(6, enderecoDTO.getComplemento());
            preparedStatement.setString(7, enderecoDTO.getEstado());
            preparedStatement.setInt(8, idEndereco);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            String selectSql = "SELECT * FROM ENDERECO WHERE ID_ENDERECO = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectSql);
            selectStatement.setInt(1, idEndereco);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                enderecoAtualizado.setIdEndereco(resultSet.getInt("ID_ENDERECO"));
                enderecoAtualizado.setCep(resultSet.getString("CEP"));
                enderecoAtualizado.setCidade(resultSet.getString("CIDADE"));
                enderecoAtualizado.setBairro(resultSet.getString("BAIRRO"));
                enderecoAtualizado.setLogradouro(resultSet.getString("LOGRADOURO"));
                enderecoAtualizado.setNumero(resultSet.getInt("NUMERO"));
                enderecoAtualizado.setComplemento(resultSet.getString("COMPLEMENTO"));
                enderecoAtualizado.setEstado(resultSet.getString("ESTADO"));
            }

            resultSet.close();
            selectStatement.close();
            connection.close();

            System.out.println("Endereco atualizado com sucesso!");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Erro ao atualizar endereco.");
            throw new BancoDeDadosException(exception.getCause());
        }

        return  enderecoAtualizado;
    }

    public void delete(Integer idEndereco) throws BancoDeDadosException {
        try {
            Connection connection = ConexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM ENDERECO WHERE ID_ENDERECO = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idEndereco);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            System.out.println("Endereco deletado com sucesso!");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());;
            System.out.println("Erro ao deletar endereco.");
            throw new BancoDeDadosException(exception.getCause());
        }
    }
}
