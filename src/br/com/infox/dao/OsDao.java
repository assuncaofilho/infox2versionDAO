package br.com.infox.dao;

import br.com.infox.entity.Os;
import java.sql.ResultSet;

public interface OsDao {

    ResultSet cadastrarOs(Os o);

    Os pesquisarOs(int id_os);

    void editarOs(Os o);

    void removerOs(Os o);

    void imprimirOs(Os o);

}
