/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.dao;

import br.com.infox.connection.ConexaoUtil;
import br.com.infox.entity.Os;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author usuario
 */
class OsDaoJdbc implements OsDao {
    
    private Connection conexao = ConexaoUtil.getConnection();
    
    private boolean isValido(Os o){
        return(!(Integer.toString(o.getId_cliente()).isEmpty()) &&!o.getEquipamento().isEmpty() && !o.getDefeito().isEmpty() && !o.getServico().isEmpty() && !(Double.toString(o.getValor()).isEmpty()));
    }
    
    @Override
    public Os cadastrar(Os o) {
        PreparedStatement pst = null;
        ResultSet rs = null;

        String cadastrar = "insert into tbos (os, data_os, tipo, situacao, equipamento, defeito, servico, tecnico, valor, idcli) "
                + "values (default, default, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            if (isValido(o)) {
                pst = conexao.prepareStatement(cadastrar);

                pst.setString(1, o.getTipo());
                pst.setString(2, o.getSituacao());
                pst.setString(3, o.getEquipamento());
                pst.setString(4, o.getDefeito());
                pst.setString(5, o.getServico());
                pst.setString(6, o.getTecnico());
                pst.setString(7, Double.toString(o.getValor()));
                pst.setString(8, Integer.toString(o.getId_cliente()));

                pst.executeUpdate(); 
                
                return o;
            } else {
                throw new DadosInvalidosException("Não foi possível cadastrar esta OS ou orçamento. \n Verifique os campos obrigatórios.");
            }

        } catch (java.sql.SQLException e1) {
            e1.printStackTrace();
            throw new AcessoAoBancoException("erro de acesso ao Banco de Dados. Favor verifique a comunicação com o servidor.");
        } catch (Exception e2) {
            throw new RuntimeException(e2);
        }
    }

    @Override
    public Os pesquisar(int id) {
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        Os encontrada = null;
        
        String pesquisar = "select * from tbos where os=?";
        
        try {
            pst = conexao.prepareStatement(pesquisar);
            pst.setString(1, Integer.toString(id));
            rs = pst.executeQuery();
            if(rs.next()){
                Os o = new Os(rs.getInt("os"), rs.getString("data_os"), rs.getString("tipo"),rs.getString("situacao"), rs.getString("equipamento"), rs.getString("defeito"),rs.getString("servico"), rs.getString("tecnico"), rs.getDouble("valor"), rs.getInt("idcli"));
                encontrada = o;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return encontrada;
    }

    @Override
    public int editar(Os o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int remover(Os o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void imprimir(Os o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



  
}
