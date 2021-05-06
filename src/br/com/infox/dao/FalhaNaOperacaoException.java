/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.infox.dao;

/**
 *
 * @author usuario
 */
public class FalhaNaOperacaoException extends RuntimeException {

    public FalhaNaOperacaoException(String msg) {
        super(msg);
    }
    
}
