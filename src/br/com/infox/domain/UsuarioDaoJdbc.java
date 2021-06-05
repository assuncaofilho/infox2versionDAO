// As implementações das interfaces DAO (neste caso as classes DAOJdbc) correspondem ao Control do modelo MVC?
package br.com.infox.domain;

import br.com.infox.connection.ConexaoUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

class UsuarioDaoJdbc implements UsuarioDao {

    private Connection conexao = ConexaoUtil.getConnection();
    
    

    public Usuario logar(String login, String senha) throws AcessoAoBancoException {
        PreparedStatement pst = null;
        ResultSet rs = null;
        String sql = "select * from tbusuarios where login =?  and senha = ?";

        try {
            pst = conexao.prepareStatement(sql);
            pst.setString(1, login);
            pst.setString(2, senha);
            rs = pst.executeQuery();

            if (rs.next()) {
                Usuario usuarioLogado = new Usuario(rs.getString("nome"), rs.getString("telefone"), rs.getString("login"), rs.getString("senha"), rs.getString("perfil"));
                Usuario.setUsuarioLogado(usuarioLogado); // guarda na variável estática o usuario logado;
                return usuarioLogado;
            }
            
            throw new AcessoAoBancoException("Falha ao logar! Verifique senha e/ou usuário ou se o servidor está disponível!"); 
          
        }catch(Exception e){
            throw new RuntimeException(e);
            
        }
    }

    @Override
    public void deslogar() {
        Usuario.setUsuarioLogado(null);
    }

    @Override
    public boolean isLogado() {
        return Usuario.getUsuarioLogado() != null;
    }
    
    private boolean isValido(Usuario u) {
        return (!u.getNome().isEmpty() && !u.getLogin().isEmpty() && !u.getSenha().isEmpty());
    }

    @Override
    public Usuario obterUsuarioLogado() {
        return Usuario.getUsuarioLogado();
    }

    @Override
    public int cadastrar(Usuario u) { // RETORNA O ID DO USUÁRIO
        PreparedStatement pst = null;
        ResultSet rs = null;

        String adicionar = "insert into tbusuarios (iduser, nome, telefone, login, senha, perfil) "
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
        
        

        try {
            pst = conexao.prepareStatement(pesquisar);
            pst.setString(1, Integer.toString(id));
            rs = pst.executeQuery();

            if (rs.next()) {

                Usuario u = new Usuario(id,rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
                Usuario uLogado = obterUsuarioLogado();
                if (u.getPerfil().equals("admin") && !uLogado.getPerfil().equals("admin")) {
                    throw new PermissaoAcessoException("Você não possui permissão para consultar este usuário.");
                }
                return u;

            } else {
                
                throw new UsuarioNaoEncontradoException("iduser não encontrado");
                
            }

        } catch (SQLException e) {
            throw new AcessoAoBancoException("erro de acesso ao Banco de Dados. Favor verifique a comunicação com o servidor.");
            
        } catch (java.lang.NumberFormatException f){
            throw new FormatoInvalidoDeDadosException("informe um número inteiro para pesquisar.");
        }
    }

    @Override
    public int editar(Usuario u) {

        PreparedStatement pst = null;
        ResultSet rs = null;
        int editado = 0;

        String editar = "update tbusuarios set nome =?, telefone=?, login=?, senha=?, perfil=? where iduser=?";
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
    public List<Cliente> listarClientes() {
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Cliente> clientes = new ArrayList();
        String buscaCli = "select * from tbclientes ";
        try {
            pst = conexao.prepareStatement(buscaCli);
            rs = pst.executeQuery();
            while (rs.next()) {

                Cliente c = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                clientes.add(c);
            }
            return clientes;
        } catch (SQLException ex) {
            throw new FalhaNaOperacaoException("não foi possível listar os clientes. Falha na consulta!");
        }

    }

    @Override
    public List<Os> listarServicos() {
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<Os> lista_os = new ArrayList();
        String buscaOs = "SELECT * FROM tbos where tipo = 'OS'";
        try {
            pst = conexao.prepareStatement(buscaOs);
            rs = pst.executeQuery();
            while (rs.next()) {

                Os o = new Os(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getDouble(9), rs.getInt(10));
                lista_os.add(o);
            }
            return lista_os;
        } catch (SQLException ex) {
            throw new FalhaNaOperacaoException("não foi possível listar os serviços. Falha na consulta!");
        }

    }

}
