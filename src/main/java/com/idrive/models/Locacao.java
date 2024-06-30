package com.idrive.models;

import java.util.Date;

public class Locacao {
    private int id;
    private Cliente cliente;
    private Veiculo veiculo;
    private Date dataInicio;
    private Date dataTermino;
    private double valorDiaria;
    private double valorTotal;

    public Locacao(int id, Cliente cliente, Veiculo veiculo, Date dataInicio, Date dataTermino, double valorDiaria, double valorTotal) {
        this.id = id;
        this.cliente = cliente;
        this.veiculo = veiculo;
        this.dataInicio = dataInicio;
        this.dataTermino = dataTermino;
        this.valorDiaria = valorDiaria;
        this.valorTotal = valorTotal;
    }

    public Locacao() {
        
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataTermino() {
        return dataTermino;
    }

    public void setDataTermino(Date dataTermino) {
        this.dataTermino = dataTermino;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }
}

