/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.idrive.service;

import com.idrive.daos.VeiculoDAO;
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
public class VeiculoServiceTest {
    
    private VeiculoService veiculoService;
    private VeiculoDAO mockVeiculoDao;
    
    public VeiculoServiceTest() {
    }
    
    @BeforeEach
    public void setUp() {
        mockVeiculoDao = mock(VeiculoDAO.class);
        veiculoService = new VeiculoService();
        veiculoService.setVeiculoDAO(mockVeiculoDao);
    }
    
    /**
     * Test of inserir method, of class VeiculoService.
     */
    @Test
    public void testInserirVeiculo() {
        Veiculo veiculo = new Veiculo(99999999, "Toyota", "Corolla", 2022, "ABC1234", true);
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
        Veiculo veiculo = new Veiculo();
        veiculo.setId(1);
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
        Veiculo veiculo = new Veiculo(2 ,"Honda", "Civic", 2021, "XYZ5678", true);
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
        Veiculo veiculo = new Veiculo();
        veiculo.setMarca("Ford");
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
        Veiculo veiculo = new Veiculo();
        veiculo.setId(3);
        // Simulate behavior of VeiculoDao.isDisponivel(veiculo)
        when(mockVeiculoDao.isDisponivel(0)).thenReturn(true);
        boolean disponivel = veiculoService.isDisponivel(veiculo.getId());
        // Verify that VeiculoDao.isDisponivel(veiculo) was called exactly once
        verify(mockVeiculoDao, times(1)).isDisponivel(veiculo.getId());
        assertEquals(true, disponivel);
    }
    
    
    
}
