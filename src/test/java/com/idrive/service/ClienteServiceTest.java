package com.idrive.service;

import com.idrive.daos.clienteDAO;
import com.idrive.models.Cliente;
import java.sql.SQLException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
/**
 *
 * @author Matheus
 */
public class ClienteServiceTest {
    
    private clienteService clienteService;
    private clienteDAO mockClienteDao;
    private Cliente cliente;
    
    public ClienteServiceTest() {
    }
        
    @BeforeEach
    public void setUp() {
        mockClienteDao = mock(clienteDAO.class);
        clienteService = new clienteService();
        clienteService.setClienteDAO(mockClienteDao); // Injetando o mock
        cliente = new Cliente(1,"MATHEUS EDUARDO", "123.456.789.-10", "9876-5432", "Rua ABC");

    }     

    /**
     * Teste de inserir método do ClienteService.
     */
    @Test
    public void testInserirCliente() {
        doNothing().when(mockClienteDao).inserir(cliente);
        clienteService.inserir(cliente);
        verify(mockClienteDao, times(1)).inserir(cliente);
    }

    /**
     * Teste de excluir método do ClienteService.
     */
    @Test
    public void testExcluirCliente() {
        doNothing().when(mockClienteDao).excluir(cliente);
        clienteService.excluir(cliente);
        verify(mockClienteDao, times(1)).excluir(cliente);
    }

    /**
     * Teste de editar método do ClienteService.
     */
    @Test
    public void testEditarCliente() {
        doNothing().when(mockClienteDao).editar(cliente);
        clienteService.editar(cliente);
        verify(mockClienteDao, times(1)).editar(cliente);
    }
}
