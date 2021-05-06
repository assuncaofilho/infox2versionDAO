
package br.com.infox.dao;

import br.com.infox.entity.Cliente;
import java.sql.ResultSet;
import java.util.List;

public interface ClienteDao {
    
    int cadastrar(Cliente c); // RETORNA O ID DO CLIENTE CADASTRADO
    
    List<Cliente> pesquisar(String p);
    
    Cliente pesquisarById(int id);

    int editar(Cliente c);

    int remover(Cliente c); 

    List<Cliente> listar();

}
