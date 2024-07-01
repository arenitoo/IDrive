package com.idrive;

import com.idrive.daos.clienteDAO;
import com.idrive.daos.locacaoDAO;
import com.idrive.daos.VeiculoDAO;
import com.idrive.models.Cliente;
import com.idrive.models.Locacao;
import com.idrive.models.Veiculo;
import com.idrive.service.clienteService;
import com.idrive.service.locacaoService;
import com.idrive.service.veiculoService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Veiculo> veiculos = new ArrayList<>();
    private static List<Locacao> locacoes = new ArrayList<>();

    private static clienteService clienteService = new clienteService();
    private static veiculoService veiculoService = new veiculoService();
    private static locacaoService locacaoService = new locacaoService();

    private static clienteDAO clienteDAO = new clienteDAO();
    private static VeiculoDAO veiculoDAO = new VeiculoDAO();
    private static locacaoDAO locacaoDAO = new locacaoDAO();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean sair = false;

        while (!sair) {
            carregarDados();
            exibirMenuPrincipal();
            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    gerenciarClientes();
                    break;
                case 2:
                    gerenciarVeiculos();
                    break;
                case 3:
                    gerenciarLocacoes();
                    break;
                case 4:
                    retornarDadosClienteLocacao();
                    break;
                case 5:
                    retornarQuantidadeVeiculosMarca();
                    break;
                case 6:
                    verificarDisponibilidadeVeiculo();
                    break;
                case 7:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida. Digite novamente.");
                    break;
            }
        }

        System.out.println("Programa encerrado.");
        scanner.close();
    }

    private static void carregarDados() {
    try {
        ResultSet clientesResultSet = clienteDAO.listar();
        while (clientesResultSet.next()) {
            int id = clientesResultSet.getInt("id");
            String nome = clientesResultSet.getString("nome");
            String cpf = clientesResultSet.getString("cpf");
            String telefone = clientesResultSet.getString("telefone");
            String endereco = clientesResultSet.getString("endereco");
            Cliente cliente = new Cliente(id, nome, cpf, telefone, endereco);
            clientes.add(cliente);
        }

        ResultSet veiculosResultSet = veiculoDAO.listar();
        while (veiculosResultSet.next()) {
            int id = veiculosResultSet.getInt("id");
            String marca = veiculosResultSet.getString("marca");
            String modelo = veiculosResultSet.getString("modelo");
            int ano = veiculosResultSet.getInt("ano");
            String placa = veiculosResultSet.getString("placa");
            boolean disponibilidade = veiculosResultSet.getBoolean("disponibilidade");
            Veiculo veiculo = new Veiculo(id, marca, modelo, ano, placa, disponibilidade);
            veiculos.add(veiculo);
        }

        ResultSet locacoesResultSet = locacaoDAO.listar();
        while (locacoesResultSet.next()) {
            int id = locacoesResultSet.getInt("id");
            int clienteId = locacoesResultSet.getInt("cliente_id");
            int veiculoId = locacoesResultSet.getInt("veiculo_id");
            Date dataInicio = locacoesResultSet.getDate("data_inicio");
            Date dataTermino = locacoesResultSet.getDate("data_termino");
            double valorDiaria = locacoesResultSet.getDouble("valor_diaria");
            double valorTotal = locacoesResultSet.getDouble("valor_total");

            // Busca os objetos cliente e veiculo correspondentes aos IDs
            Cliente cliente = clienteService.getById(clienteId);
            Veiculo veiculo = veiculoService.getById(veiculoId);

            Locacao locacao = new Locacao(id, cliente, veiculo, dataInicio, dataTermino, valorDiaria, valorTotal);
            locacoes.add(locacao);
        }
    } catch (SQLException e) {
        System.out.println("Erro ao carregar dados: " + e.getMessage());
    }
}


    private static void exibirMenuPrincipal() {
        System.out.println("----- MENU PRINCIPAL -----");
        System.out.println("1. Gerenciar clientes");
        System.out.println("2. Gerenciar veículos");
        System.out.println("3. Gerenciar locações");
        System.out.println("4. Retornar dados do cliente que efetuou uma locação");
        System.out.println("5. Retornar a quantidade de veículos locados de uma determinada marca");
        System.out.println("6. Verificar se um veículo está disponível para ser locado em um determinado momento");
        System.out.println("7. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void gerenciarClientes() {
        boolean voltar = false;

        while (!voltar) {
            exibirClientes();
            System.out.println("A. Adicionar novo cliente");
            System.out.println("S. Voltar ao menu principal");
            System.out.print("Escolha uma opção ou selecione um cliente pelo número: ");

            String opcao = scanner.nextLine().trim();

            if (opcao.equalsIgnoreCase("a")) {
                adicionarCliente();
            } else if (opcao.equalsIgnoreCase("s")) {
                voltar = true;
            } else {
                try {
                    int indice = Integer.parseInt(opcao) - 1;
                    if (indice >= 0 && indice < clientes.size()) {
                        Cliente clienteSelecionado = clientes.get(indice);
                        editarOuExcluirCliente(clienteSelecionado);
                    } else {
                        System.out.println("Índice inválido. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            }
        }
    }

    private static void exibirClientes() {
        System.out.println("----- LISTA DE CLIENTES -----");
        for (int i = 0; i < clientes.size(); i++) {
            Cliente cliente = clientes.get(i);
            System.out.println((i + 1) + ". " + cliente.getNome());
        }
    }

    private static void adicionarCliente() {
        System.out.println("----- ADICIONAR NOVO CLIENTE -----");
        try {
            System.out.print("Nome: ");
            String nome = scanner.nextLine().trim();

            System.out.print("CPF: ");
            String cpf = scanner.nextLine().trim();

            System.out.print("Telefone: ");
            String telefone = scanner.nextLine().trim();

            System.out.print("Endereço: ");
            String endereco = scanner.nextLine().trim();

            Cliente novoCliente = new Cliente(0, nome, cpf, telefone, endereco);
            clienteService.inserir(novoCliente);
            clientes.add(novoCliente);

            System.out.println("Novo cliente adicionado com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar cliente: " + e.getMessage());
        }
    }

    private static void editarOuExcluirCliente(Cliente cliente) {
        System.out.println("----- EDITAR OU EXCLUIR CLIENTE -----");
        System.out.println("Cliente selecionado: " + cliente.getNome());
        System.out.println("1. Editar cliente");
        System.out.println("2. Excluir cliente");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); 

        switch (opcao) {
            case 1:
                editarCliente(cliente);
                break;
            case 2:
                excluirCliente(cliente);
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                break;
        }
    }

    private static void editarCliente(Cliente cliente) {
        System.out.println("----- EDITAR CLIENTE -----");
        try {
            System.out.print("Novo nome (deixe em branco para manter o atual): ");
            String novoNome = scanner.nextLine().trim();
            if (!novoNome.isEmpty()) {
                cliente.setNome(novoNome);
            }

            System.out.print("Novo CPF (deixe em branco para manter o atual): ");
            String novoCpf = scanner.nextLine().trim();
            if (!novoCpf.isEmpty()) {
                cliente.setCpf(novoCpf);
            }

            System.out.print("Novo telefone (deixe em branco para manter o atual): ");
            String novoTelefone = scanner.nextLine().trim();
            if (!novoTelefone.isEmpty()) {
                cliente.setTelefone(novoTelefone);
            }

            System.out.print("Novo endereço (deixe em branco para manter o atual): ");
            String novoEndereco = scanner.nextLine().trim();
            if (!novoEndereco.isEmpty()) {
                cliente.setEndereco(novoEndereco);
            }

            clienteService.editar(cliente);
            System.out.println("Cliente editado com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao editar cliente: " + e.getMessage());
        }
    }

    private static void excluirCliente(Cliente cliente) {
        System.out.println("----- EXCLUIR CLIENTE -----");
        try {
            clienteService.excluir(cliente);
            clientes.remove(cliente);
            System.out.println("Cliente excluído com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao excluir cliente: " + e.getMessage());
        }
    }

    private static void gerenciarVeiculos() {
        boolean voltar = false;

        while (!voltar) {
            exibirVeiculos();
            System.out.println("A. Adicionar novo veículo");
            System.out.println("S. Voltar ao menu principal");
            System.out.print("Escolha uma opção ou selecione um veículo pelo número: ");

            String opcao = scanner.nextLine().trim();

            if (opcao.equalsIgnoreCase("a")) {
                adicionarVeiculo();
            } else if (opcao.equalsIgnoreCase("s")) {
                voltar = true;
            } else {
                try {
                    int indice = Integer.parseInt(opcao) - 1;
                    if (indice >= 0 && indice < veiculos.size()) {
                        Veiculo veiculoSelecionado = veiculos.get(indice);
                        editarOuExcluirVeiculo(veiculoSelecionado);
                    } else {
                        System.out.println("Índice inválido. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            }
        }
    }

    private static void exibirVeiculos() {
        System.out.println("----- LISTA DE VEÍCULOS -----");
        for (int i = 0; i < veiculos.size(); i++) {
            Veiculo veiculo = veiculos.get(i);
            System.out.println((i + 1) + ". " + veiculo.getMarca() + " " + veiculo.getModelo());
        }
    }

    private static void adicionarVeiculo() {
        System.out.println("----- ADICIONAR NOVO VEÍCULO -----");
        try {
            System.out.print("Marca: ");
            String marca = scanner.nextLine().trim();

            System.out.print("Modelo: ");
            String modelo = scanner.nextLine().trim();

            System.out.print("Ano: ");
            int ano = scanner.nextInt();
            scanner.nextLine(); 

            System.out.print("Placa: ");
            String placa = scanner.nextLine().trim();

            Veiculo novoVeiculo = new Veiculo(0, marca, modelo, ano, placa, true);
            veiculoService.inserir(novoVeiculo);
            veiculos.add(novoVeiculo);

            System.out.println("Novo veículo adicionado com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao adicionar veículo: " + e.getMessage());
        }
    }

    private static void editarOuExcluirVeiculo(Veiculo veiculo) {
        System.out.println("----- EDITAR OU EXCLUIR VEÍCULO -----");
        System.out.println("Veículo selecionado: " + veiculo.getMarca() + " " + veiculo.getModelo());
        System.out.println("1. Editar veículo");
        System.out.println("2. Excluir veículo");
        System.out.print("Escolha uma opção: ");

        int opcao = scanner.nextInt();
        scanner.nextLine(); 

        switch (opcao) {
            case 1:
                editarVeiculo(veiculo);
                break;
            case 2:
                excluirVeiculo(veiculo);
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                break;
        }
    }

    private static void editarVeiculo(Veiculo veiculo) {
        System.out.println("----- EDITAR VEÍCULO -----");
        try {
            System.out.print("Nova marca (deixe em branco para manter a atual): ");
            String novaMarca = scanner.nextLine().trim();
            if (!novaMarca.isEmpty()) {
                veiculo.setMarca(novaMarca);
            }

            System.out.print("Novo modelo (deixe em branco para manter o atual): ");
            String novoModelo = scanner.nextLine().trim();
            if (!novoModelo.isEmpty()) {
                veiculo.setModelo(novoModelo);
            }

            System.out.print("Novo ano (deixe em branco para manter o atual): ");
            String novoAnoStr = scanner.nextLine().trim();
            if (!novoAnoStr.isEmpty()) {
                int novoAno = Integer.parseInt(novoAnoStr);
                veiculo.setAno(novoAno);
            }

            System.out.print("Nova placa (deixe em branco para manter a atual): ");
            String novaPlaca = scanner.nextLine().trim();
            if (!novaPlaca.isEmpty()) {
                veiculo.setPlaca(novaPlaca);
            }

            veiculoService.editar(veiculo);
            System.out.println("Veículo editado com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao editar veículo: " + e.getMessage());
        }
    }

    private static void excluirVeiculo(Veiculo veiculo) {
        System.out.println("----- EXCLUIR VEÍCULO -----");
        try {
            veiculoService.excluir(veiculo);
            veiculos.remove(veiculo);
            System.out.println("Veículo excluído com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro ao excluir veículo: " + e.getMessage());
        }
    }

    private static void gerenciarLocacoes() {
        boolean voltar = false;

        while (!voltar) {
            exibirLocacoes();
            System.out.println("S. Voltar ao menu principal");
            System.out.print("Escolha uma locação pelo número para visualizar detalhes ou selecione S para voltar: ");

            String opcao = scanner.nextLine().trim();

            if (opcao.equalsIgnoreCase("s")) {
                voltar = true;
            } else {
                try {
                    int indice = Integer.parseInt(opcao) - 1;
                    if (indice >= 0 && indice < locacoes.size()) {
                        Locacao locacaoSelecionada = locacoes.get(indice);
                        exibirDetalhesLocacao(locacaoSelecionada);
                    } else {
                        System.out.println("Índice inválido. Tente novamente.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Opção inválida. Tente novamente.");
                }
            }
        }
    }

    private static void exibirLocacoes() {
        System.out.println("----- LISTA DE LOCAÇÕES -----");
        for (int i = 0; i < locacoes.size(); i++) {
            Locacao locacao = locacoes.get(i);
            System.out.println((i + 1) + ". Locação ID: " + locacao.getId());
        }
    }

    private static void exibirDetalhesLocacao(Locacao locacao) {
        System.out.println("----- DETALHES DA LOCAÇÃO -----");
        System.out.println("Cliente: " + locacao.getCliente().getNome());
        System.out.println("Veículo: " + locacao.getVeiculo().getMarca() + " " + locacao.getVeiculo().getModelo());
        System.out.println("Data de início: " + locacao.getDataInicio());
        System.out.println("Data de término: " + locacao.getDataTermino());
        System.out.println("Valor total: " + locacao.getValorTotal());

        System.out.println("1. Voltar");
        System.out.print("Escolha uma opção: ");
        scanner.nextLine(); 
    }

    private static void retornarDadosClienteLocacao() {
        System.out.println("----- RETORNAR DADOS DO CLIENTE QUE EFETUOU UMA LOCAÇÃO -----");
        exibirLocacoes();
        System.out.print("Escolha uma locação pelo número para visualizar o cliente associado: ");

        try {
            int indice = scanner.nextInt() - 1;
            scanner.nextLine(); 

            if (indice >= 0 && indice < locacoes.size()) {
                Locacao locacaoSelecionada = locacoes.get(indice);
                Cliente cliente = locacaoSelecionada.getCliente();
                System.out.println("Cliente da locação selecionada: " + cliente.getNome());
            } else {
                System.out.println("Índice inválido. Tente novamente.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void retornarQuantidadeVeiculosMarca() {
        System.out.println("----- RETORNAR QUANTIDADE DE VEÍCULOS LOCADOS DE UMA DETERMINADA MARCA -----");
        exibirMarcasVeiculos();
        System.out.print("Escolha uma marca pelo número para verificar a quantidade de veículos locados: ");

        try {
            int indice = scanner.nextInt() - 1;
            scanner.nextLine(); 

            if (indice >= 0 && indice < veiculos.size()) {
                Veiculo veiculoSelecionado = veiculos.get(indice);
                ResultSet resultSet = veiculoService.quantidadeVeiculoPorMarca(veiculoSelecionado);
                // pra processar o ResultSet para obter a quantidade de veículos locados
                // adaptar o null do quantidadeVeiculoPorMarca
                System.out.println("Quantidade de veículos locados da marca " + veiculoSelecionado.getMarca() + ": ???");
            } else {
                System.out.println("Índice inválido. Tente novamente.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static void exibirMarcasVeiculos() {
        System.out.println("----- LISTA DE MARCAS DE VEÍCULOS -----");
        for (int i = 0; i < veiculos.size(); i++) {
            Veiculo veiculo = veiculos.get(i);
            System.out.println((i + 1) + ". " + veiculo.getMarca());
        }
    }

    private static void verificarDisponibilidadeVeiculo() {
        System.out.println("----- VERIFICAR DISPONIBILIDADE DE VEÍCULO -----");
        exibirVeiculos();
        System.out.print("Escolha um veículo pelo número para verificar a disponibilidade: ");

        try {
            int indice = scanner.nextInt() - 1;
            scanner.nextLine(); 

            if (indice >= 0 && indice < veiculos.size()) {
                Veiculo veiculoSelecionado = veiculos.get(indice);
                System.out.print("Insira a data de início da locação (dd/MM/yyyy HH:mm): ");
                Date dataInicio = lerData();

                System.out.print("Insira a data de término da locação (dd/MM/yyyy HH:mm): ");
                Date dataTermino = lerData();

                boolean disponivel = veiculoService.isDisponivel(veiculoSelecionado.getId(), dataInicio, dataTermino);
                String mensagem = disponivel ? "Está disponível!" : "Não está disponível!";
                System.out.println("Veículo " + veiculoSelecionado.getMarca() + " " + veiculoSelecionado.getModelo() + ": " + mensagem);
            } else {
                System.out.println("Índice inválido. Tente novamente.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Opção inválida. Tente novamente.");
        }
    }

    private static Date lerData() {
        Date data = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        boolean dataValida = false;

        while (!dataValida) {
            String input = scanner.nextLine().trim();

            try {
                data = dateFormat.parse(input);
                dataValida = true;
            } catch (ParseException e) {
                System.out.println("Formato de data inválido. Informe no formato dd/MM/yyyy HH:mm");
            }
        }

        return data;
    }
}
