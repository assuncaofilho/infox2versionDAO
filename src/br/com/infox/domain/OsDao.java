package br.com.infox.domain;



public interface OsDao {

    Os cadastrar(Os o); 

    Os pesquisar(int id);

    int editar(Os o);

    int remover(Os o);

    

}
