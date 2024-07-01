/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.idrive.service;

import com.idrive.daos.VeiculoDAO;
import com.idrive.models.Veiculo;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author Matheus
 */
public class VeiculoServiceTest {
    
    private VeiculoService veiculoService;
    private VeiculoDAO mockVeiculoDao;
    private Veiculo veiculo;
    
    public VeiculoServiceTest() {
    }
    
    @BeforeEach
    public void setUp() {
        mockVeiculoDao = mock(VeiculoDAO.class);
        veiculoService = new VeiculoService();
        veiculoService.setVeiculoDAO(mockVeiculoDao);
        veiculo = new Veiculo(1, "Toyota", "Corolla", 2022, "ABC1234", true);

    }
    
    /**
     * Test of inserir method, of class VeiculoService.
     */
    @Test
    public void testInserirVeiculo() {
        // Simulate behavior of VeiculoDao.inserir(veiculo)
        doNothing().when(mockVeiculoDao).inserir(veiculo);
        veiculoService.inserir(veiculo);
        // Verify that VeiculoDao.inserir(veiculo) was called exactly once
        verify(mockVeiculoDao, times(1)).inserir(veiculo);
    }

    /**
     * Test of excluir method, of class VeiculoService.
     */
     @Test
    public void testExcluirVeiculo() {
        // Simulate behavior of VeiculoDao.excluir(veiculo)
        doNothing().when(mockVeiculoDao).excluir(veiculo);
        veiculoService.excluir(veiculo);
        // Verify that VeiculoDao.excluir(veiculo) was called exactly once
        verify(mockVeiculoDao, times(1)).excluir(veiculo);
    }

    /**
     * Test of editar method, of class VeiculoService.
     */
     @Test
    public void testEditarVeiculo() {
        // Simulate behavior of VeiculoDao.editar(veiculo)
        doNothing().when(mockVeiculoDao).editar(veiculo);
        veiculoService.editar(veiculo);
        // Verify that VeiculoDao.editar(veiculo) was called exactly once
        verify(mockVeiculoDao, times(1)).editar(veiculo);
    }

    /**
     * Test of quantidadeVeiculoPorMarca method, of class VeiculoService.
     */
     @Test
    public void testQuantidadeVeiculoPorMarca() throws SQLException {
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockVeiculoDao.quantidadeVeiculoPorMarca(veiculo)).thenReturn(mockResultSet);
        veiculoService.quantidadeVeiculoPorMarca(veiculo);
        // Verify that VeiculoDao.quantidadeVeiculoPorMarca(veiculo) was called exactly once
        verify(mockVeiculoDao, times(1)).quantidadeVeiculoPorMarca(veiculo);
    }

    /**
     * Test of isDisponivel method, of class VeiculoService.
     */
     @Test
    public void testIsDisponivel() throws SQLException {
        // Simulate behavior of VeiculoDao.isDisponivel(veiculo)
        when(mockVeiculoDao.isDisponivel(1)).thenReturn(true);
        boolean disponivel = veiculoService.isDisponivel(veiculo.getId());
        // Verify that VeiculoDao.isDisponivel(veiculo) was called exactly once
        verify(mockVeiculoDao, times(1)).isDisponivel(veiculo.getId());
        assertEquals(true, disponivel);
    }
    
    
    
}
