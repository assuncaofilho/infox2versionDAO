
package br.com.infox.domain;

import java.util.List;


public interface UsuarioDao {
    
    Usuario logar(String login, String senha);
    void deslogar(); 
    boolean isLogado();
    
    Usuario obterUsuarioLogado();
    
    // apenas ADM e GER
    
    int cadastrar(Usuario u);         
    
    Usuario pesquisar(int id_usuario);
    
    int editar(Usuario u);
    
    int remover(Usuario u);
    
    
    // apenas ADM
    
    List<Cliente> listarClientes();
    
    List<Os> listarServicos();
        
}
