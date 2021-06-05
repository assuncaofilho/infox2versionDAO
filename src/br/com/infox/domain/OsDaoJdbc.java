  // As implementações das interfaces DAO (neste caso as classes DAOJdbc) correspondem ao Control do modelo MVC?
package br.com.infox.domain;

import br.com.infox.connection.ConexaoUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;


class OsDaoJdbc implements OsDao {
    
    private Connection conexao = ConexaoUtil.getConnection();
    
    private boolean isValido(Os o){
        return(!(Integer.toString(o.getIdcli()).isEmpty()) &&!o.getEquipamento().isEmpty() && !o.getDefeito().isEmpty() && !o.getServico().isEmpty() && !(Double.toString(o.getValor()).isEmpty()));
    }

    private boolean isInteiro(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    
    @Override
    public Os cadastrar(Os o) {
        PreparedStatement pst = null;
        ResultSet rs = null;

        String cadastrar = "insert into tbos (os, data_os, tipo, situacao, equipamento, defeito, servico, tecnico, valor, idcli) "
                + "values (default, default, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            if (isValido(o)) {
                pst = conexao.prepareStatement(cadastrar);

                pst.setString(1, o.getTipo());
                pst.setString(2, o.getSituacao());
                pst.setString(3, o.getEquipamento());
                pst.setString(4, o.getDefeito());
                pst.setString(5, o.getServico());
                pst.setString(6, o.getTecnico());
                pst.setString(7, Double.toString(o.getValor()));
                pst.setString(8, Integer.toString(o.getIdcli()));

                pst.executeUpdate(); 
                
                return o;
            } else {
                throw new DadosInvalidosException("Não foi possível cadastrar esta OS ou orçamento. \n Verifique os campos obrigatórios.");
            }

        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
            throw new AcessoAoBancoException("erro de acesso ao Banco de Dados. Favor verifique a comunicação com o servidor.");
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override
    public Os pesquisar(int id) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        Os encontrada = null;
        
        String pesquisar = "select * from tbos where os=?";
        
        try {
            if(isInteiro(Integer.toString(id))){
            pst = conexao.prepareStatement(pesquisar);
            pst.setString(1, Integer.toString(id));
            rs = pst.executeQuery();
                if(rs.next()){
                    Os o = new Os(rs.getInt("os"), rs.getString("data_os"), rs.getString("tipo"),rs.getString("situacao"), rs.getString("equipamento"), rs.getString("defeito"),rs.getString("servico"), rs.getString("tecnico"), rs.getDouble("valor"), rs.getInt("idcli"));
                    encontrada = o;
                
                             }
            return encontrada;
        } else{
                throw new FormatoInvalidoDeDadosException("Informe um número válido de OS.");
              }
            
        } catch(java.sql.SQLException e){
            e.printStackTrace();
            throw new FalhaNaOperacaoException("Erro ao consultar esta OS. Verifique a comunicação com o servidor.");
        } catch(Exception e1){
            e1.printStackTrace();
            throw new RuntimeException(e1);
        }
        
    }

    @Override
    public int editar(Os o) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        int editado = 0;

        String editar = "update tbos set tipo=?, situacao=?, equipamento=?, defeito=?, servico=?, tecnico=?, valor=? where os=?";
        try {
            if (isValido(o)) {

                pst = conexao.prepareStatement(editar);
                pst.setString(1, o.getTipo());
                pst.setString(2, o.getSituacao());
                pst.setString(3, o.getEquipamento());
                pst.setString(4, o.getDefeito());
                pst.setString(5, o.getServico());
                pst.setString(6, o.getTecnico());
                pst.setString(7, Double.toString(o.getValor()));
                pst.setString(8, Integer.toString(o.getId()));

                int verificador = pst.executeUpdate();
                if (verificador > 0) { // se a execução for exitosa de uma DML (insert, upedate, delete or drop)
                    editado = verificador;

                }
                return editado;

            } else {
                throw new DadosInvalidosException("Preencha todos os campos obrigatórios para editar este cliente!");
            }
        } catch (SQLException e) {
            throw new AcessoAoBancoException("Falha ao editar o cliente. Verifique a comunicação com o Banco de Dados.");
        }

    }

    @Override
    public int remover(Os o) {
            
        PreparedStatement pst = null;
        ResultSet rs = null;
        
  
        try {
            if(isValido(o)){
            String remover = "delete from tbos where os=?";
            pst = conexao.prepareStatement(remover);
            pst.setString(1, Integer.toString(o.getId()));
            int verificador = pst.executeUpdate(); // retorna 1 para DML (insert, update, delete, drop);
            return verificador;
                
            }
                throw new DadosInvalidosException("Não altere os dados da OS a ser removida! Faça a busca novamente");
                
            
        } catch (Exception e) {
            throw new FalhaNaOperacaoException("Falha ao remover a OS. erro:" + e.getMessage());
        
        }
    }


    }



  

