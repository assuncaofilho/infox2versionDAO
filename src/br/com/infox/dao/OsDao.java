package br.com.infox.dao;

import br.com.infox.entity.Os;
import java.sql.ResultSet;

public interface OsDao {

    Os cadastrar(Os o); // Retorna a OS cadastrada;

    Os pesquisar(int id);

    int editar(Os o);

    int remover(Os o);

    void imprimir(Os o);

}
/*
    int cadastrar(Cliente c); // RETORNA O ID DO CLIENTE CADASTRADO
    
    List<Cliente> pesquisar(String p);
    
    Cliente pesquisarById(int id);

    int editar(Cliente c);

    int remover(Cliente c); 

    List<Cliente> listar();*/