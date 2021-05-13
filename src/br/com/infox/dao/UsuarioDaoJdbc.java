package br.com.infox.dao;

import br.com.infox.connection.ConexaoUtil;
import br.com.infox.entity.Cliente;
import br.com.infox.entity.Os;
import br.com.infox.entity.Usuario;
import br.com.infox.telas.TelaLogin;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

class UsuarioDaoJdbc implements UsuarioDao {

    private Connection conexao = ConexaoUtil.getConnection();
    

    


    public Usuario logar(String usuario, String senha) throws AcessoAoBancoException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from tbusuarios where login =?  and senha = ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, usuario);
            pst.setString(2, senha);
            rs = pst.executeQuery();

            if (rs.next()) {

                return new Usuario(rs.getString("usuario"), rs.getString("telefone"), rs.getString("login"), rs.getString("senha"), rs.getString("perfil"));
                // precisei de uma variável auxiliar pois não estava permitindo retornar o u diretamente;
            }
            
            throw new AcessoAoBancoException("Falha ao logar! Verifique senha e/ou usuário ou se o servidor está disponível!"); 
          
        }catch(Exception e){
            throw new RuntimeException(e);
            
        }
    }
    
    
    private boolean isValido(Usuario u) {
        return (!u.getNome().isEmpty() && !u.getLogin().isEmpty() && !u.getSenha().isEmpty());
    }
    
        

    @Override
    public int cadastrar(Usuario u) { // RETORNA O ID DO USUÁRIO
        PreparedStatement pst = null;
        ResultSet rs = null;

        String adicionar = "insert into tbusuarios (iduser, usuario, telefone, login, senha, perfil) "
        + "values (default, ?, ?, ?, ?, ?)";        

        try {
            if (isValido(u)) {
                pst = conexao.prepareStatement(adicionar);
                // substituindo os campos ? da query adicionar;
                pst.setString(1, u.getNome());
                pst.setString(2, u.getFone());
                pst.setString(3, u.getLogin());
                pst.setString(4, u.getSenha());
                pst.setString(5, u.getPerfil());
                
                pst.executeUpdate(); // retorna 1 para DML (Insert, Update or Delete);
                String buscaID = "select iduser from tbusuarios where login = ?";
                PreparedStatement buscadora = conexao.prepareStatement(buscaID);
                buscadora.setString(1, u.getLogin()); // pois login é UNIQUE
                rs = buscadora.executeQuery();
                rs.next(); // é necessário inicializar o ResultSet para manipulá-lo.
                return rs.getInt("iduser");
            }else{
            throw new DadosInvalidosException("Não foi possível cadastrar o usuário. \n Verifique os campos obrigatórios.");
            }
        } catch (java.sql.SQLIntegrityConstraintViolationException e) {
            throw new ChaveVioladaException("Este login já está sendo utilizado, favor informe outro login.");
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
    }

    @Override
    public Usuario pesquisar(int id) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        String pesquisar = "select * from tbusuarios where iduser = ?";
        Usuario encontrado = null;
        

        try {
            pst = conexao.prepareStatement(pesquisar);
            pst.setString(1, Integer.toString(id));
            rs = pst.executeQuery();

            if (rs.next()) {

                Usuario u = new Usuario(id,rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                encontrado = u;

            } else {
                
                throw new UsuarioNaoEncontradoException("iduser não encontrado");
                
            }

        } catch (SQLException e) {
            throw new AcessoAoBancoException("erro de acesso ao Banco de Dados. Favor verifique a comunicação com o servidor.");
            
        } catch (java.lang.NumberFormatException f){
            throw new FormatoInvalidoDeDadosException("informe um número inteiro para pesquisar.");
        }

        return encontrado;
    }

    @Override
    public int editar(Usuario u) {

        PreparedStatement pst = null;
        ResultSet rs = null;
        int editado = 0;

        String editar = "update tbusuarios set usuario =?, telefone=?, login=?, senha=?, perfil=? where iduser=?";
        try {
            if (isValido(u)) {

                pst = conexao.prepareStatement(editar);
                pst.setString(1, u.getNome());
                pst.setString(2, u.getFone());
                pst.setString(3, u.getLogin());
                pst.setString(4, u.getSenha());
                pst.setString(5, u.getPerfil());
                pst.setString(6, Integer.toString(u.getId()));

                int verificador = pst.executeUpdate();
                if (verificador > 0) { // se a execução for exitosa de uma DML (insert, upedate, delete or drop)
                    editado = verificador;

                }
                return editado;

            } else {
                throw new DadosInvalidosException("Preencha todos os campos obrigatórios para editar este usuário!");
            }
        } catch (SQLException e) {
            throw new AcessoAoBancoException("Falha ao editar o usuário. Verifique a comunicação com o Banco de Dados.");
        }

    }

    @Override
    public int remover(Usuario u) {

        PreparedStatement pst = null;
        ResultSet rs = null;
        
  
        try {
            if(isValido(u)){
            String remover = "delete from tbusuarios where iduser=?";
            pst = conexao.prepareStatement(remover);
            pst.setString(1, Integer.toString(u.getId()));
            int verificador = pst.executeUpdate(); // retorna 1 para DML (insert, update, delete, drop);
            return verificador;
                
            }
                throw new DadosInvalidosException("Não altere os dados do usuário a ser removido! Faça a busca novamente");
                
            
        } catch (Exception e) {
            throw new FalhaNaOperacaoException("Falha ao remover o usuário" + e.getMessage());
        
        }

    }

    @Override
    public void imprimirRelatorioClientes() {
        try {
                
                 JasperPrint print = JasperFillManager.fillReport("Clientes.jasper",null, conexao );
                 JasperViewer.viewReport(print, false);
                
            } catch (Exception e) {
                throw new RuntimeException();
            }
    }

    @Override
    public void imprimirRelatorioServicos() {
        try {
                
                 JasperPrint print = JasperFillManager.fillReport("Servicos.jasper",null, conexao );
                 JasperViewer.viewReport(print, false);
                
            } catch (Exception e) {
                throw new RuntimeException();
            }
    }

}
