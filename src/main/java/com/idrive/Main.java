package com.idrive;

import com.idrive.models.Veiculo;
import com.idrive.service.ClienteService;
import com.idrive.service.LocacaoService;
import com.idrive.service.VeiculoService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static ClienteService clienteService = new ClienteService();
    private static LocacaoService locacaoService = new LocacaoService();
    private static VeiculoService veiculoService = new VeiculoService();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Bem vindo a Idrive!");
            System.out.println("Escolha uma opção:");
            System.out.println("1. Retornar dados do cliente que efetuou uma locação");
            System.out.println("2. Retornar a quantidade de veículos locados de uma determinada marca");
            System.out.println("3. Verificar se um veículo está disponível para ser locado em um determinado momento");
            System.out.println("4. Sair");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    retornarDadosCliente(scanner);
                    break;
                case 2:
                    retornarQuantidadeVeiculos(scanner);
                    break;
                case 3:
                    verificarDisponibilidadeVeiculo(scanner);
                    break;
                case 4:
                    System.out.println("Saindo...");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private static void retornarDadosCliente(Scanner scanner) {
        System.out.println("Informe o ID da locação:");
        int locacaoId = scanner.nextInt();
        scanner.nextLine();

        ResultSet rs = locacaoService.getClienteByLocacao(locacaoId);
        try {
            if (rs.next()) {
                System.out.println("Dados do Cliente:");
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Nome: " + rs.getString("nome"));
                System.out.println("CPF: " + rs.getString("cpf"));
                System.out.println("Telefone: " + rs.getString("telefone"));
                System.out.println("Endereço: " + rs.getString("endereco"));
            } else {
                System.out.println("Nenhum cliente encontrado para esta locação.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void retornarQuantidadeVeiculos(Scanner scanner) {
        System.out.println("Informe a marca do veículo:");
        String marca = scanner.nextLine();

        Veiculo veiculo = new Veiculo();
        veiculo.setMarca(marca);

        ResultSet rs = veiculoService.quantidadeVeiculoPorMarca(veiculo);
        try {
            if (rs.next()) {
                System.out.println("Quantidade de veículos locados da marca " + marca + ": " + rs.getInt(1));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private static void verificarDisponibilidadeVeiculo(Scanner scanner) {
        System.out.println("Informe o ID do veículo:");
        int veiculoId = scanner.nextInt();
        scanner.nextLine(); // Consumir a nova linha

        System.out.println("Informe a data de início (yyyy-MM-dd):");
        String dataInicioStr = scanner.nextLine();
        java.util.Date dataInicio = java.sql.Date.valueOf(dataInicioStr);

        System.out.println("Informe a data de término (yyyy-MM-dd):");
        String dataTerminoStr = scanner.nextLine();
        java.util.Date dataTermino = java.sql.Date.valueOf(dataTerminoStr);

        boolean disponivel = veiculoService.isDisponivel(veiculoId, dataInicio, dataTermino);

        if (disponivel) {
            System.out.println("O veículo está disponível para locação.");
        } else {
            System.out.println("O veículo não está disponível para locação.");
        }
    }
}
