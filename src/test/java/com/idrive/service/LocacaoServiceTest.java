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
    private Cliente cliente;
    private Veiculo veiculo;
    private Locacao locacao;
    
    public LocacaoServiceTest() {
    }
    
    @BeforeEach
    public void setUp() {
        mockLocacaoDao = mock(LocacaoDAO.class);
        locacaoService = new LocacaoService();
        locacaoService.setLocacaoDao(mockLocacaoDao); // Injetando o mock
        cliente = new Cliente(1,"MATHEUS EDUARDO", "123.456.789.-10", "9876-5432", "Rua ABC");
        veiculo = new Veiculo(1, "Toyota", "Corolla", 2022, "ABC1234", true);
        locacao = new Locacao(1, cliente, veiculo, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() + 7 * 24 * 60 * 60 * 1000L), 100.0, 200.0);

    }

    /**
     * Test of inserir method, of class locacaoService.
     */
    @Test
    public void testInserirLocacao() {
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
        ResultSet mockResultSet = mock(ResultSet.class);
        // Simulate behavior of LocacaoDao.getClienteByLocacao(locacaoId)
        when(mockLocacaoDao.getClienteByLocacao(locacao.getId())).thenReturn(mockResultSet);
        ResultSet result = locacaoService.getClienteByLocacao(locacao.getId());
        // Verify that LocacaoDao.getClienteByLocacao(locacaoId) was called exactly once
        verify(mockLocacaoDao, times(1)).getClienteByLocacao(locacao.getId());
        // Verify the result is as expected
        assertEquals(mockResultSet, result);
    }
    
}
