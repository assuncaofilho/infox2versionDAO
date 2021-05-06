
package br.com.infox.dao;

import br.com.infox.entity.Cliente;
import br.com.infox.entity.Os;
import br.com.infox.entity.Usuario;
import java.sql.ResultSet;
import java.util.List;

public interface UsuarioDao {
    
    Usuario logar(String usuario, String senha); // retorna o objeto Usuario que logou
    
    
    // apenas ADM e GER
    
    int cadastrar(Usuario u);         
    
    Usuario pesquisar(int id_usuario);
    
    int editar(Usuario u);
    
    int remover(Usuario u);
    
    
    // apenas ADM
    
    void imprimirRelatorioClientes();
    
    void imprimirRelatorioServicos();
    
    
    
    
}
