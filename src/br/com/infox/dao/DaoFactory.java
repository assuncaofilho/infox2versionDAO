package br.com.infox.dao;

public abstract class DaoFactory {
    
    /*O retorno é do tipo Interface contudo na hora de devolver o programa devolve uma instância da interface;
    Deste modo, o programa não conhece a implementação apenas a interface; Mas a partir desta interface "instanciada"
    consigo acessar aos métodos implementados em ClienteDaoJdbc;*/

    public static ClienteDao createClienteDao() {
        return new ClienteDaoJdbc(); 
    }

    public static UsuarioDao createUsuarioDao() {
        return new UsuarioDaoJdbc();
    }

    public static OsDao createOsDao() {
        return new OsDaoJdbc();
    }
}
