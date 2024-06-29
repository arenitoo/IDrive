/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.idrive.service;

import com.idrive.models.Locacao;
import java.sql.ResultSet;
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
public class LocacaoServiceTest {
    
    public LocacaoServiceTest() {
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
     * Test of inserir method, of class locacaoService.
     */
    @Test
    public void testInserir() {
        System.out.println("inserir");
        Locacao locacao = null;
        locacaoService instance = new locacaoService();
        instance.inserir(locacao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of excluir method, of class locacaoService.
     */
    @Test
    public void testExcluir() {
        System.out.println("excluir");
        Locacao locacao = null;
        locacaoService instance = new locacaoService();
        instance.excluir(locacao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of editar method, of class locacaoService.
     */
    @Test
    public void testEditar() {
        System.out.println("editar");
        Locacao locacao = null;
        locacaoService instance = new locacaoService();
        instance.editar(locacao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClienteByLocacao method, of class locacaoService.
     */
    @Test
    public void testGetClienteByLocacao() {
        System.out.println("getClienteByLocacao");
        int locacaoId = 0;
        locacaoService instance = new locacaoService();
        ResultSet expResult = null;
        ResultSet result = instance.getClienteByLocacao(locacaoId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
