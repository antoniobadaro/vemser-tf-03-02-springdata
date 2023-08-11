package br.com.dbc.vemser.tf03spring.repository;

import br.com.dbc.vemser.tf03spring.database.ConexaoBancoDeDados;
import br.com.dbc.vemser.tf03spring.dto.AlunoDTO;
import br.com.dbc.vemser.tf03spring.exception.BancoDeDadosException;
import br.com.dbc.vemser.tf03spring.exception.RegraDeNegocioException;
import br.com.dbc.vemser.tf03spring.model.Aluno;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AlunoRepository {

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

    public Aluno create(AlunoDTO alunoDTO) throws BancoDeDadosException {
        Aluno alunoCriado = null;

        try {
            Connection connection = ConexaoBancoDeDados.getConnection();

            int proximoId = obterProximoIdSequence("SEQ_ALUNO");
            String sql = "INSERT INTO ALUNO (ID_ALUNO, NOME, IDADE, CPF, NUMERO) VALUES (?, ?, ?, ?, ?)";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, proximoId);
            preparedStatement.setString(2, alunoDTO.getNome());
            preparedStatement.setString(3, alunoDTO.getIdade());
            preparedStatement.setString(4, alunoDTO.getCpf());
            preparedStatement.setString(5, alunoDTO.getNumeroDeMatricula());
            preparedStatement.executeUpdate();
            preparedStatement.close();

            String selectSql = "SELECT * FROM ALUNO WHERE ID_ALUNO = ?";

            PreparedStatement selectStatement = connection.prepareStatement(selectSql);
            selectStatement.setInt(1, proximoId);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                alunoCriado = new Aluno();
                alunoCriado.setIdAluno(resultSet.getInt("ID_ALUNO"));
                alunoCriado.setNome(resultSet.getString("NOME"));
                alunoCriado.setIdade(resultSet.getString("IDADE"));
                alunoCriado.setCpf(resultSet.getString("CPF"));
                alunoCriado.setNumeroDeMatricula(resultSet.getString("NUMERO"));
            }

            resultSet.close();
            selectStatement.close();
            connection.close();

            System.out.println("Aluno inserido com sucesso!");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Erro ao inserir aluno.");
            throw new BancoDeDadosException(exception.getCause());
        }

        return alunoCriado;
    }

    public List<Aluno> findAll() throws BancoDeDadosException {
        List<Aluno> todosOsAlunos = new ArrayList<>();;

        try {
            Connection connection = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM ALUNO";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Aluno aluno = new Aluno();
                aluno.setIdAluno(resultSet.getInt("ID_ALUNO"));
                aluno.setNome(resultSet.getString("NOME"));
                aluno.setIdade(resultSet.getString("IDADE"));
                aluno.setCpf(resultSet.getString("CPF"));
                aluno.setNumeroDeMatricula(resultSet.getString("NUMERO"));
                todosOsAlunos.add(aluno);
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());;
            System.out.println("Erro ao listar alunos.");
            throw new BancoDeDadosException(exception.getCause());
        }

        return todosOsAlunos;
    }

    public Aluno findById(Integer idAluno) throws BancoDeDadosException {
        Aluno alunoEncontrado = null;

        try {
            Connection connection = ConexaoBancoDeDados.getConnection();

            String sql = "SELECT * FROM ALUNO WHERE ID_ALUNO = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idAluno);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                alunoEncontrado = new Aluno();
                alunoEncontrado.setIdAluno(resultSet.getInt("ID_ALUNO"));
                alunoEncontrado.setNome(resultSet.getString("NOME"));
                alunoEncontrado.setIdade(resultSet.getString("IDADE"));
                alunoEncontrado.setCpf(resultSet.getString("CPF"));
                alunoEncontrado.setNumeroDeMatricula(resultSet.getString("NUMERO"));
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Erro ao procurar aluno por ID.");
            throw new BancoDeDadosException(exception.getCause());
        }

        return alunoEncontrado;
    }

    public Aluno update(Integer idAluno, AlunoDTO alunoDTO) throws BancoDeDadosException {
        Aluno alunoAtualizado = null;

        try {
            Connection connection = ConexaoBancoDeDados.getConnection();

            String sql = "UPDATE ALUNO SET NOME = ?, IDADE = ?, CPF = ?, NUMERO = ? WHERE ID_ALUNO = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, alunoDTO.getNome());
            preparedStatement.setString(2, alunoDTO.getIdade());
            preparedStatement.setString(3, alunoDTO.getCpf());
            preparedStatement.setString(4, alunoDTO.getNumeroDeMatricula());
            preparedStatement.setInt(5, idAluno);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            String selectSql = "SELECT * FROM ALUNO WHERE ID_ALUNO = ?";
            PreparedStatement selectStatement = connection.prepareStatement(selectSql);
            selectStatement.setInt(1, idAluno);
            ResultSet resultSet = selectStatement.executeQuery();

            if (resultSet.next()) {
                alunoAtualizado = new Aluno();
                alunoAtualizado.setIdAluno(resultSet.getInt("ID_ALUNO"));
                alunoAtualizado.setNome(resultSet.getString("NOME"));
                alunoAtualizado.setIdade(resultSet.getString("IDADE"));
                alunoAtualizado.setCpf(resultSet.getString("CPF"));
                alunoAtualizado.setNumeroDeMatricula(resultSet.getString("NUMERO"));
            }

            resultSet.close();
            selectStatement.close();
            connection.close();

            System.out.println("Aluno atualizado com sucesso!");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());
            System.out.println("Erro ao atualizar aluno.");
            throw new BancoDeDadosException(exception.getCause());
        }

        return alunoAtualizado;
    }

    public void delete(Integer idAluno) throws BancoDeDadosException {
        try {
            Connection connection = ConexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM ALUNO WHERE ID_ALUNO = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idAluno);
            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();

            System.out.println("Aluno deletado com sucesso!");
        } catch (SQLException exception) {
            System.out.println(exception.getMessage());;
            System.out.println("Erro ao deletar aluno.");
            throw new BancoDeDadosException(exception.getCause());
        }
    }

}
