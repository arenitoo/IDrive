package com.idrive.service;

import com.idrive.daos.ClienteDAO;
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
        doNothing().when(mockClienteDao).inserir(cliente);
        clienteService.inserir(cliente);
        verify(mockClienteDao, times(1)).inserir(cliente);
    }

    /**
     * Test of excluir method, of class ClienteService.
     */
    @Test
    public void testExcluirCliente() {
        doNothing().when(mockClienteDao).excluir(cliente);
        clienteService.excluir(cliente);
        verify(mockClienteDao, times(1)).excluir(cliente);
    }

    /**
     * Test of editar method, of class ClienteService.
     */
    @Test
    public void testEditarCliente() {
        doNothing().when(mockClienteDao).editar(cliente);
        clienteService.editar(cliente);
        verify(mockClienteDao, times(1)).editar(cliente);
    }

    /**
     * Test of mostrarCliente method, of class ClienteService.
     */
    @Test
    public void testMostrarCliente() throws SQLException {
        when(mockClienteDao.getById(cliente.getId())).thenReturn(null);
        clienteService.getById(cliente.getId());
        verify(mockClienteDao, times(1)).getById(cliente.getId());
    }
    
}
