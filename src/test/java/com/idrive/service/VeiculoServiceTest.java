/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.idrive.service;

import com.idrive.models.Veiculo;
import java.sql.ResultSet;
import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Matheus
 */
public class VeiculoServiceTest {
    
    public VeiculoServiceTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of inserir method, of class VeiculoService.
     */
    @Test
    public void testInserir() {
        System.out.println("inserir");
        Veiculo veiculo = null;
        VeiculoService instance = new VeiculoService();
        instance.inserir(veiculo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of excluir method, of class VeiculoService.
     */
    @Test
    public void testExcluir() {
        System.out.println("excluir");
        Veiculo veiculo = null;
        VeiculoService instance = new VeiculoService();
        instance.excluir(veiculo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editar method, of class VeiculoService.
     */
    @Test
    public void testEditar() {
        System.out.println("editar");
        Veiculo veiculo = null;
        VeiculoService instance = new VeiculoService();
        instance.editar(veiculo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of quantidadeVeiculoPorMarca method, of class VeiculoService.
     */
    @Test
    public void testQuantidadeVeiculoPorMarca() {
        System.out.println("quantidadeVeiculoPorMarca");
        Veiculo veiculo = null;
        VeiculoService instance = new VeiculoService();
        ResultSet expResult = null;
        ResultSet result = instance.quantidadeVeiculoPorMarca(veiculo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isDisponivel method, of class VeiculoService.
     */
    @Test
    public void testIsDisponivel() {
        System.out.println("isDisponivel");
        int veiculoId = 0;
        Date dataInicio = null;
        Date dataTermino = null;
        VeiculoService instance = new VeiculoService();
        boolean expResult = false;
        boolean result = instance.isDisponivel(veiculoId, dataInicio, dataTermino);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
