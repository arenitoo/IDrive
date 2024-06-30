package com.idrive.service;

import com.idrive.daos.ClienteDAO;
import com.idrive.models.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
/**
 *
 * @author Matheus
 */
public class ClienteServiceTest {
    
    private ClienteService clienteService;
    private ClienteDAO mockClienteDao;
    private Cliente cliente;
    
    public ClienteServiceTest() {
    }
        
    @BeforeEach
    public void setUp() {
        mockClienteDao = mock(ClienteDAO.class);
        clienteService = new ClienteService();
        clienteService.setClienteDAO(mockClienteDao); // Injetando o mock
        cliente = new Cliente(1,"MATHEUS EDUARDO", "123.456.789.-10", "9876-5432", "Rua ABC");

    }     

    /**
     * Test of inserir method, of class ClienteService.
     */
    @Test
    public void testInserirCliente() {
        // Simulate behavior of ClienteDao.inserir(cliente)
        doNothing().when(mockClienteDao).inserir(cliente);
        clienteService.inserir(cliente);
        // Verify that ClienteDao.inserir(cliente) was called exactly once
        verify(mockClienteDao, times(1)).inserir(cliente);
    }

    /**
     * Test of excluir method, of class ClienteService.
     */
    @Test
    public void testExcluirCliente() {
        // Simulate behavior of ClienteDao.excluir(cliente)
        doNothing().when(mockClienteDao).excluir(cliente);
        clienteService.excluir(cliente);
        // Verify that ClienteDao.excluir(cliente) was called exactly once
        verify(mockClienteDao, times(1)).excluir(cliente);
    }

    /**
     * Test of editar method, of class ClienteService.
     */
    @Test
    public void testEditarCliente() {
        // Simulate behavior of ClienteDao.editar(cliente)
        doNothing().when(mockClienteDao).editar(cliente);
        clienteService.editar(cliente);
        // Verify that ClienteDao.editar(cliente) was called exactly once
        verify(mockClienteDao, times(1)).editar(cliente);
    }

    /**
     * Test of mostrarCliente method, of class ClienteService.
     */
    @Test
    public void testMostrarCliente() {
        // Simulate behavior of ClienteDao.mostrarDadosCliente(cliente)
        when(mockClienteDao.mostrarDadosCliente(cliente)).thenReturn(null);
        clienteService.mostrarCliente(cliente);
        // Verify that ClienteDao.mostrarDadosCliente(cliente) was called exactly once
        verify(mockClienteDao, times(1)).mostrarDadosCliente(cliente);
    }
    
}
