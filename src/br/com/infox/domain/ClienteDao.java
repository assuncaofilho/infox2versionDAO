
package br.com.infox.domain;

import java.util.List;

public interface ClienteDao {
    
    int cadastrar(Cliente c); // RETORNA O ID DO CLIENTE CADASTRADO
    
    List<Cliente> pesquisar(String p);
    
    Cliente pesquisarById(int id);

    int editar(Cliente c);

    int remover(Cliente c); 


}
