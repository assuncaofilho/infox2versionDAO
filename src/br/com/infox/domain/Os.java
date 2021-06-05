
package br.com.infox.domain;

public class Os {
    
    private int id;
    private String data_os;
    private String tipo;
    private String situacao;
    private String equipamento;
    private String defeito;
    private String servico;
    private String tecnico;
    private double valor;
    private int idcli;

    public Os(int id, String data_os, String tipo, String situacao, String equipamento, String defeito, String servico, String tecnico, double valor, int idcli) {
        this.id = id;
        this.data_os = data_os;
        this.tipo = tipo;
        this.situacao = situacao;
        this.equipamento = equipamento;
        this.defeito = defeito;
        this.servico = servico;
        this.tecnico = tecnico;
        this.valor = valor;
        this.idcli = idcli;
    }
    
    public Os(String tipo, String situacao, String equipamento, String defeito, String servico, String tecnico, double valor, int idcli) {
        this.tipo = tipo;
        this.situacao = situacao;
        this.equipamento = equipamento;
        this.defeito = defeito;
        this.servico = servico;
        this.tecnico = tecnico;
        this.valor = valor;
        this.idcli = idcli;
    }
    
    

    @Override
    public String toString() {
        return "Os{" + "id_os=" + id + ", data_os=" + data_os + ", tipo=" + tipo + ", situacao=" + situacao + ", equipamento=" + equipamento + ", defeito=" + defeito + ", servico=" + servico + ", tecnico=" + tecnico + ", valor=" + valor + ", id_cliente=" + idcli + '}';
    }
    
    
    public int getId() {
        return id;
    }

    public String getData_os() {
        return data_os;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getEquipamento() {
        return equipamento;
    }

    public void setEquipamento(String equipamento) {
        this.equipamento = equipamento;
    }

    public String getDefeito() {
        return defeito;
    }

    public void setDefeito(String defeito) {
        this.defeito = defeito;
    }

    public String getServico() {
        return servico;
    }

    public void setServico(String servico) {
        this.servico = servico;
    }

    public String getTecnico() {
        return tecnico;
    }

    public void setTecnico(String tecnico) {
        this.tecnico = tecnico;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getIdcli() {
        return idcli;
    }

    public void setIdcli(int idcli) {
        this.idcli = idcli;
    }   
    
}
