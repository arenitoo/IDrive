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
    
    private veiculoService veiculoService;
    private VeiculoDAO mockVeiculoDao;
    private Veiculo veiculo;
    
    public VeiculoServiceTest() {
    }
    
    @BeforeEach
    public void setUp() {
        mockVeiculoDao = mock(VeiculoDAO.class);
        veiculoService = new veiculoService();
        veiculoService.setVeiculoDAO(mockVeiculoDao);
        veiculo = new Veiculo(1, "Toyota", "Corolla", 2022, "ABC1234", true);

    }
    
    /**
     * Teste de inserir método do VeiculoService.
     */
    @Test
    public void testInserirVeiculo() {
        doNothing().when(mockVeiculoDao).inserir(veiculo);
        veiculoService.inserir(veiculo);
        verify(mockVeiculoDao, times(1)).inserir(veiculo);
    }

    /**
     * Teste de excluir método do VeiculoService.
     */
     @Test
    public void testExcluirVeiculo() {
        doNothing().when(mockVeiculoDao).excluir(veiculo);
        veiculoService.excluir(veiculo);
        verify(mockVeiculoDao, times(1)).excluir(veiculo);
    }

    /**
     * Teste de editar método do VeiculoService.
     */
     @Test
    public void testEditarVeiculo() {
        doNothing().when(mockVeiculoDao).editar(veiculo);
        veiculoService.editar(veiculo);
        verify(mockVeiculoDao, times(1)).editar(veiculo);
    }

    /**
     * Teste de quantidadeVeiculoPorMarca do VeiculoService.
     */
     @Test
    public void testQuantidadeVeiculoPorMarca() throws SQLException {
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockVeiculoDao.quantidadeVeiculoPorMarca(veiculo)).thenReturn(mockResultSet);
        veiculoService.quantidadeVeiculoPorMarca(veiculo);
        verify(mockVeiculoDao, times(1)).quantidadeVeiculoPorMarca(veiculo);
    }

    /**
     * Teste de disponibilidadade do VeiculoService.
     */
     @Test
    public void testIsDisponivel() throws SQLException {
        Date dataInicio = new Date(2024, 6, 1); 
        Date dataTermino = new Date(2024, 6, 8);
        when(mockVeiculoDao.isDisponivel(1, dataInicio, dataTermino)).thenReturn(true);
        boolean disponivel = veiculoService.isDisponivel(veiculo.getId(), dataInicio, dataTermino);
        verify(mockVeiculoDao, times(1)).isDisponivel(veiculo.getId(), dataInicio, dataTermino);
        assertEquals(true, disponivel);
    }
 
}
