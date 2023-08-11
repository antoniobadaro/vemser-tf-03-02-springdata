package br.com.dbc.vemser.tf03spring.repository;

import br.com.dbc.vemser.tf03spring.database.ConexaoBancoDeDados;
import br.com.dbc.vemser.tf03spring.exception.BancoDeDadosException;
import br.com.dbc.vemser.tf03spring.model.Curso;
import br.com.dbc.vemser.tf03spring.model.enums.TipoPeriodo;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CursoRepository{


    public Integer getProximoId(Connection connection) throws SQLException {
        String sql = "SELECT seq_curso.nextval mysequence from DUAL";

        Statement stmt = connection.createStatement();
        ResultSet res = stmt.executeQuery(sql);

        if (res.next()) {
            return res.getInt("mysequence");
        }

        return null;
    }


    public List<Curso> listar() throws BancoDeDadosException {
        List<Curso> cursos = new ArrayList<>();

        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();
            Statement stmt = con.createStatement();

            String sql = "SELECT * FROM CURSO\n";

            ResultSet res = stmt.executeQuery(sql);

            while (res.next()) {
                Curso curso = new Curso();
                curso.setIdCurso(res.getInt("idCurso"));
                curso.setNome(res.getString("nome"));
                curso.setCargaHoraria(res.getInt("cargaHoraria"));
                curso.setPeriodo(TipoPeriodo.fromString(res.getString("periodo")));

                cursos.add(curso);
            }

            return cursos;

        }
        catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public Curso adicionar(Curso curso) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            Integer proximoIdCurso = this.getProximoId(con);
            curso.setIdCurso(proximoIdCurso);

            String sqlCurso = "INSERT INTO CURSO\n" +
                    "(idCurso, nome, cargaHoraria, periodo)\n" +
                    "VALUES(?, ?, ?, ?)\n";

            PreparedStatement stmtCurso = con.prepareStatement(sqlCurso);

            stmtCurso.setInt(1, curso.getIdCurso());
            stmtCurso.setString(2, curso.getNome().trim());
            stmtCurso.setInt(3, curso.getCargaHoraria());
            stmtCurso.setString(4, curso.getPeriodo().toString());


            stmtCurso.executeUpdate();
            return curso;
        }
        catch (SQLException e) {
            System.err.println("Ocorreu um erro");
        }
        catch (Exception e) {
            System.out.println("Ocorreu um erro");;
            return null;
        }
        finally {
            try {
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public Curso editar(Curso curso, Integer idCurso) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE CURSO SET ");
            sql.append(" nome = ?,");
            sql.append(" cargaHoraria = ?,");
            sql.append(" periodo = ?");
            sql.append(" WHERE idCurso = ? ");

            PreparedStatement stmt = con.prepareStatement(sql.toString());

            stmt.setString(1, curso.getNome());
            stmt.setInt(2, curso.getCargaHoraria());
            stmt.setString(3, curso.getPeriodo().toString());
            stmt.setInt(4, idCurso);

            int res = stmt.executeUpdate();

            if (res > 0) {
                // Consulta o curso atualizado usando o ID
                String consultaSql = "SELECT * FROM CURSO WHERE idCurso = ?";
                PreparedStatement consultaStmt = con.prepareStatement(consultaSql);
                consultaStmt.setInt(1, idCurso);
                ResultSet rs = consultaStmt.executeQuery();

                if (rs.next()) {
                    Curso cursoAtualizado = new Curso();
                    cursoAtualizado.setIdCurso(rs.getInt("idCurso"));
                    cursoAtualizado.setNome(rs.getString("nome"));
                    cursoAtualizado.setCargaHoraria(rs.getInt("cargaHoraria"));
                    cursoAtualizado.setPeriodo(TipoPeriodo.fromString(rs.getString("periodo")));

                    return cursoAtualizado;
                }
            }
        } catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Retornar null caso ocorra alguma exceção
        } finally {
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null; // Retornar null caso não encontre o curso atualizado ou a atualização não seja bem-sucedida
    }


    public void remover(Integer idCurso) throws BancoDeDadosException {
        Connection con = null;
        try {
            con = ConexaoBancoDeDados.getConnection();

            String sql = "DELETE FROM CURSO WHERE idCurso = ?";

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setInt(1, idCurso);


            stmt.executeUpdate();


        }
        catch (SQLException e) {
            throw new BancoDeDadosException(e.getCause());
        }
        catch (Exception e) {
            e.printStackTrace();
            //return false;
        }
        finally {
            try {
                if (con != null) {
                    con.close();
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
