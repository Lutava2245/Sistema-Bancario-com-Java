import java.util.ArrayList;
import java.util.Scanner;

import entidades.*;
import operacoes.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static ArrayList<Cliente> clientes = new ArrayList<>();
    private static int id_conta = 0;

    public static Cliente localizarCliente(String cpf) {        
        for (Cliente cliente : clientes) {
            if (cliente.getCpf().equals(cpf)) {
                return cliente;
            }
        }

        return null;
    }

    public static Conta localizarConta(int numero_conta, Cliente cliente) {
        for (Conta conta_cliente : cliente.getContas()) {
            if (conta_cliente.getNumero() == numero_conta) {
                return conta_cliente;
            }
        }
        return null;
    }

    private static Cliente criarCliente(String nome, String cpf) {
        Cliente cliente = new Cliente(nome, cpf);
        clientes.add(cliente);
        return cliente;
    }

    private static void criarConta(Cliente cliente) {
        Conta conta = null;

        System.out.println("Qual tipo de conta deseja criar?\n1 - Corrente\n2 - Poupança");
        int opcao = scanner.nextInt();

        switch (opcao) {
            case 1:
                conta = new ContaPoupanca(id_conta);
                cliente.getContas().add(conta);
                id_conta++;
                break;
        
            case 2:
                conta = new ContaCorrente(id_conta);
                cliente.getContas().add(conta);
                id_conta++;
                break;

            default:
                System.out.println("Escolha uma das opcoes.");
        }
    }
    
    public static void main(String[] args) throws Exception {
        String menu = "=========MENU=========\n"+
                      "1 - Criar conta\n"+
                      "2 - Sacar\n"+
                      "3 - Depositar\n"+
                      "4 - Transferir\n"+
                      "5 - Verificar Extrato\n"+
                      "6 - Trocar usuario\n\n"+
                      "0 - Sair\n"+
                      "======================";
        int resposta = -1;

        do {
            Cliente cliente = null;
            Conta conta = null;
            Operacao operacao = null;
            float valor;

            System.out.println("Qual o CPF do cliente?\n(Apenas números)");
            String cpf = scanner.nextLine();
            if (resposta == 6) {
                cpf = scanner.nextLine();
            }
            cliente = localizarCliente(cpf);

            if (cliente == null) {
                System.out.println("Cliente não encontrado.\nDeseja registrar no sistema? (S/N)");
                String opcao = scanner.nextLine();
                opcao = opcao.toUpperCase();

                if (opcao.equals("S")) {
                    System.out.print("Digite o nome do cliente: ");
                    String nome = scanner.nextLine();
                    cliente = criarCliente(nome, cpf);
                    criarConta(cliente);
        
                } else if (opcao.equals("N")) {
                    continue;

                } else {
                    System.out.println("Escolha uma das opcoes.");
                    continue;
                }
                
            } else {
                if (cliente.getContas().isEmpty()) {
                    System.out.println("Conta não encontrada.\nDeseja criar uma? (S/N)");
                    String  opcao = scanner.nextLine();

                    switch (opcao) {
                        case "S":
                            criarConta(cliente);
                            break;
                    
                        case "N":
                            continue;
    
                        default:
                            System.out.println("Escolha uma das opcoes.");
                            continue;
                    }
                }
            }

            do {
                System.out.println(menu);
                resposta = scanner.nextInt();
    
                if ((resposta != 0) && (resposta != 1) && (resposta != 6)) {
                    System.out.println("Digite o numero da conta a ser usada.\n" + cliente.getContas());
                    int numero_conta = scanner.nextInt();
                    conta = localizarConta(numero_conta, cliente);

                    if (conta == null) {
                        System.out.println("Conta não encontrada");
                        continue;
                    }
                }
                
                switch (resposta) {
                    case 1:
                        criarConta(cliente);
                        break;
    
                    case 2:
                        System.out.println("Quanto deseja sacar?\nSaldo: R$"+conta.getSaldo());
                        valor = scanner.nextFloat();
    
                        if (conta.sacar(valor)) {
                            operacao = new Saque(valor);
                        }
                        break;
                
                    case 3:
                        System.out.println("Quanto deseja depositar?\nSaldo: R$"+conta.getSaldo());
                        valor = scanner.nextFloat();
                        conta.depositar(valor);
                        operacao = new Deposito(valor);
                        break;
    
                    case 4:
                        Conta conta_a_transferir = null;
    
                        System.out.println("Qual o numero da conta a qual deseja transferir?");
                        int numero_conta = scanner.nextInt();
    
                        for (Conta conta_cliente : cliente.getContas()) {
                            if (conta_cliente.getNumero() == numero_conta) {
                                conta_a_transferir = conta_cliente;
                                break;
                            }
                        }
                        
                        if (conta_a_transferir == null) {
                            System.out.println("Conta não encontrada");
                        } else if (conta_a_transferir.equals(conta)) {
                            System.out.println("Não é possível transferir para a mesma conta.");
                        } else {
                            System.out.println("Quanto deseja transferir?\nSaldo: R$"+conta.getSaldo());
                            valor = scanner.nextFloat();

                            if (conta.transferir(valor, conta_a_transferir)) {
                                operacao = new Transferencia(conta_a_transferir, valor);
                            }
                        }

                        break;

                    case 5:
                        String operacoes = "";

                        for (Operacao operacaoBancaria : conta.getOperacoes()) {
                            operacoes += operacaoBancaria;
                        }
                        System.out.println("===================" + 
                                           "\n"+ conta +
                                           "\nSaldo: " + conta.getSaldo() +
                                           "\nOperações: " + operacoes +
                                           "\n===================");
                        break;
                }
    
                if (operacao != null) {
                    conta.registrarOperacao(operacao);
                }
            } while ((resposta != 6) && (resposta != 0));
        } while (resposta != 0);
    }
}
