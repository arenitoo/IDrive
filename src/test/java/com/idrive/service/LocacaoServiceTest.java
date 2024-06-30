/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.idrive.service;

import com.idrive.daos.LocacaoDAO;
import com.idrive.models.Locacao;
import com.idrive.models.Cliente;
import com.idrive.models.Veiculo;
import java.sql.ResultSet;
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
    
    private LocacaoService locacaoService;
    private LocacaoDAO mockLocacaoDao;
    
    public LocacaoServiceTest() {
    }
    
    @BeforeEach
    public void setUp() {
        mockLocacaoDao = mock(LocacaoDAO.class);
        locacaoService = new LocacaoService();
        locacaoService.setLocacaoDao(mockLocacaoDao); // Injetando o mock
    }

    /**
     * Test of inserir method, of class locacaoService.
     */
    @Test
    public void testInserirLocacao() {
        Cliente cliente = new Cliente();
        Veiculo veiculo = new Veiculo();
        Date dataInicio = new Date();
        Date dataTermino = new Date(dataInicio.getTime() + (1000 * 60 * 60 * 24));
        Locacao locacao = new Locacao(999999 ,cliente, veiculo, dataInicio, dataTermino, 100.0, 200.0);
        // Simulate behavior of LocacaoDao.inserir(locacao)
        doNothing().when(mockLocacaoDao).inserir(locacao);
        locacaoService.inserir(locacao);
        // Verify that LocacaoDao.inserir(locacao) was called exactly once
        verify(mockLocacaoDao, times(1)).inserir(locacao);
    }

    /**
     * Test of excluir method, of class locacaoService.
     */
    @Test
    public void testExcluirLocacao() {
        Locacao locacao = new Locacao();
        locacao.setId(1);
        // Simulate behavior of LocacaoDao.excluir(locacao)
        doNothing().when(mockLocacaoDao).excluir(locacao);
        locacaoService.excluir(locacao);
        // Verify that LocacaoDao.excluir(locacao) was called exactly once
        verify(mockLocacaoDao, times(1)).excluir(locacao);
    }

    /**
     * Test of editar method, of class locacaoService.
     */
    @Test
    public void testEditarLocacao() {
        Cliente cliente = new Cliente();
        Veiculo veiculo = new Veiculo();
        Date dataInicio = new Date();
        Date dataTermino = new Date(dataInicio.getTime() + (1000 * 60 * 60 * 24));
        Locacao locacao = new Locacao(999999 ,cliente, veiculo, dataInicio, dataTermino, 100.0, 200.0);
        locacao.setId(2);
        // Simulate behavior of LocacaoDao.editar(locacao)
        doNothing().when(mockLocacaoDao).editar(locacao);
        locacaoService.editar(locacao);
        // Verify that LocacaoDao.editar(locacao) was called exactly once
        verify(mockLocacaoDao, times(1)).editar(locacao);
    }

    /**
     * Test of getClienteByLocacao method, of class locacaoService.
     */
    @Test
    public void testGetClienteByLocacao() {
        int locacaoId = 1;
        ResultSet mockResultSet = mock(ResultSet.class);
        // Simulate behavior of LocacaoDao.getClienteByLocacao(locacaoId)
        when(mockLocacaoDao.getClienteByLocacao(locacaoId)).thenReturn(mockResultSet);
        ResultSet result = locacaoService.getClienteByLocacao(locacaoId);
        // Verify that LocacaoDao.getClienteByLocacao(locacaoId) was called exactly once
        verify(mockLocacaoDao, times(1)).getClienteByLocacao(locacaoId);
        // Verify the result is as expected
        assertEquals(mockResultSet, result);
    }
    
}
