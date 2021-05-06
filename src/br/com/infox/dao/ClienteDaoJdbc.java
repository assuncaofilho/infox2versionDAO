
package br.com.infox.dao;

import br.com.infox.connection.ConexaoUtil;
import br.com.infox.entity.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

class ClienteDaoJdbc implements ClienteDao {

    private Connection conexao = ConexaoUtil.getConnection();

    private boolean isValido(Cliente c) {
        return (!c.getNome().isEmpty() && !c.getFone().isEmpty() && !c.getEmail().isEmpty());
    }

    @Override
    public int cadastrar(Cliente c) { // retorna o ID do cliente;
        PreparedStatement pst = null;
        ResultSet rs = null;

        String adicionar = "insert into tbclientes (idcli, nomecli, endcli, telefonecli, emailcli) "
                + "values (default, ?, ?, ?, ?)";

        try {
            if (isValido(c)) {
                pst = conexao.prepareStatement(adicionar);
                // substituindo os campos ? da query adicionar;
                pst.setString(1, c.getNome());
                pst.setString(2, c.getEnd());
                pst.setString(3, c.getFone());
                pst.setString(4, c.getEmail());

                pst.executeUpdate(); // retorna 1 para DML (Insert, Update or Delete);
                String buscaID = "select idcli from tbclientes where emailcli = ?"; // emailcli é UNIQUE;
                PreparedStatement buscadora = conexao.prepareStatement(buscaID);
                buscadora.setString(1, c.getEmail()); // pois email é UNIQUE;
                rs = buscadora.executeQuery();
                rs.next(); // é necessário inicializar o ResultSet para manipulá-lo.
                return rs.getInt("idcli");
            } else {
                throw new DadosInvalidosException("Não foi possível cadastrar o cliente. \n Verifique os campos obrigatórios.");
            }
        } catch (java.sql.SQLIntegrityConstraintViolationException e) {
            throw new ChaveVioladaException("Este e-mail já está sendo utilizado, favor informe outro e-mail.");
        } catch (java.sql.SQLException e1) {
            throw new AcessoAoBancoException("erro de acesso ao Banco de Dados. Favor verifique a comunicação com o servidor.");
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }
    
    @Override
    public Cliente pesquisarById(int id){
        
        
        PreparedStatement pst = null;
        ResultSet rs = null;
        Cliente buscado = null;
        
        
        
        String pesquisar = "select * from tbclientes where idcli= ?";
        
        try {
            pst = conexao.prepareStatement(pesquisar);
            pst.setString(1, Integer.toString(id));
            rs = pst.executeQuery();
            if(rs.next()){
                Cliente c = new Cliente(rs.getInt("idcli"),rs.getString("nomecli"),rs.getString("endcli"),rs.getString("telefonecli"), rs.getString("emailcli"));
                buscado = c;
            }

        } catch (Exception e) {
            throw new AcessoAoBancoException("não foi possível carregar os dados deste cliente! tente novamente!");
        }
        return buscado;
    }
    
    @Override
    public List<Cliente> pesquisar(String search) {
        
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        List<Cliente> list = new ArrayList<Cliente>();
        
        String pesquisar = "select * from tbclientes where nomecli like ?";
        
        try {
            pst = conexao.prepareStatement(pesquisar);
            pst.setString(1, search + "%");
            rs = pst.executeQuery();
            while(rs.next()){
                Cliente c = new Cliente(rs.getInt("idcli"),rs.getString("nomecli"),rs.getString("endcli"),rs.getString("telefonecli"), rs.getString("emailcli"));
                list.add(c);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return list;
    }


    @Override
    public int editar(Cliente c) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        int editado = 0;

        String editar = "update tbclientes set nomecli =?, endcli=?, telefonecli=?, emailcli=? where idcli=?";
        try {
            if (isValido(c)) {

                pst = conexao.prepareStatement(editar);
                pst.setString(1, c.getNome());
                pst.setString(2, c.getEnd());
                pst.setString(3, c.getFone());
                pst.setString(4, c.getEmail());
                pst.setString(5, Integer.toString(c.getId()));

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
    public int remover(Cliente c) {
        
        PreparedStatement pst = null;
        ResultSet rs = null;
        
  
        try {
            if(isValido(c)){
            String remover = "delete from tbclientes where idcli=?";
            pst = conexao.prepareStatement(remover);
            pst.setString(1, Integer.toString(c.getId()));
            int verificador = pst.executeUpdate(); // retorna 1 para DML (insert, update, delete, drop);
            return verificador;
                
            }
                throw new DadosInvalidosException("Não altere os dados do usuário a ser removido! Faça a busca novamente");
                
            
        } catch (Exception e) {
            throw new FalhaNaOperacaoException("Falha ao remover o usuário" + e.getMessage());
        
        }
    }

    @Override
    public List<Cliente> listar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}