/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.idrive.service;

import com.idrive.daos.locacaoDAO;
import com.idrive.models.Locacao;
import com.idrive.models.Cliente;
import com.idrive.models.Veiculo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Matheus
 */
public class LocacaoServiceTest {
    
    private locacaoService locacaoService;
    private locacaoDAO mockLocacaoDao;
    private Cliente cliente;
    private Veiculo veiculo;
    private Locacao locacao;
    
    public LocacaoServiceTest() {
    }
    
    @BeforeEach
    public void setUp() {
        mockLocacaoDao = mock(locacaoDAO.class);
        locacaoService = new locacaoService();
        locacaoService.setLocacaoDao(mockLocacaoDao); // Injetando o mock
        Date dataInicio = new Date(2024, 6, 1); 
        Date dataTermino = new Date(2024, 6, 8);
        cliente = new Cliente(1,"MATHEUS EDUARDO", "123.456.789.-10", "9876-5432", "Rua ABC");
        veiculo = new Veiculo(1, "Toyota", "Corolla", 2022, "ABC1234", true);
        locacao = new Locacao(1, cliente, veiculo, dataInicio, dataTermino, 100.0, 200.0);

    }

    /**
     * Teste de inserir método do LocacaoService.
     */
    @Test
    public void testInserirLocacao() {
        doNothing().when(mockLocacaoDao).inserir(locacao);
        locacaoService.inserir(locacao);
        verify(mockLocacaoDao, times(1)).inserir(locacao);
    }

    /**
     * Teste de excluir método do LocacaoService.
     */
    @Test
    public void testExcluirLocacao() {
        doNothing().when(mockLocacaoDao).excluir(locacao);
        locacaoService.excluir(locacao);
        verify(mockLocacaoDao, times(1)).excluir(locacao);
    }

    /**
     * Teste de editar método do LocacaoService.
     */
    @Test
    public void testEditarLocacao() {
        doNothing().when(mockLocacaoDao).editar(locacao);
        locacaoService.editar(locacao);
        verify(mockLocacaoDao, times(1)).editar(locacao);
    }

    /**
     * Teste do getClienteByLocacao method, da classe locacaoService.
     */
    @Test
    public void testGetClienteByLocacao() {
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockLocacaoDao.getClienteByLocacao(locacao.getId())).thenReturn(mockResultSet);
        ResultSet result = locacaoService.getClienteByLocacao(locacao.getId());
        verify(mockLocacaoDao, times(1)).getClienteByLocacao(locacao.getId());
        assertEquals(mockResultSet, result);
    }
    
}
