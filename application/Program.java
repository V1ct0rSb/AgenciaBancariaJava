package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

import entities.Conta;
import entities.Usuario;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		ArrayList<Usuario> usuarios = new ArrayList<>();
		ArrayList<Conta> contas = new ArrayList<>();

		int opcao = -1;

		while (opcao != 0) {
			System.out.println("=-=-=-=-Menu=-=-=-=-");
			System.out.println("1 - Criar conta");
			System.out.println("2 - Depositar");
			System.out.println("3 - Sacar");
			System.out.println("4 - Transferir");
			System.out.println("5 - Relatório");

			System.out.println("0 - Sair");
			System.out.print("Escolha uma opção do menu: ");
			opcao = sc.nextInt();

			switch (opcao) {
			default:
				System.out.println("Opção invalida!!");
				break;
			case 0:
				System.out.println("Volte Sempre!!");
				break;
			case 1:
				cadastrarConta(contas, usuarios, sc);
				break;
			case 2:
				cadastrarDeposito(contas, sc);
				break;
			case 3:
				cadastrarSaque(contas, sc);
				break;
			case 4:
				cadastrarTransferencia(contas, sc);
				break;
			case 5:
				relatorio(contas, sc);
				break;
			}

		}
		sc.close();
	}

	public static void cadastrarConta(ArrayList<Conta> contas, ArrayList<Usuario> usuarios, Scanner sc) {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		System.out.println();
		System.out.println("=-=-=-Criar Conta-=-=-=");
		System.out.print("Nome: ");
		sc.nextLine();
		String nome = sc.nextLine();

		System.out.print("Cpf: ");
		int cpf = sc.nextInt();

		// Verificar se tem cpf iguais
		for (Usuario u : usuarios) {
			if (u.getCpf().equals(cpf)) {
				System.out.println("Cpf já cadastrado!!");
				return;
			}
		}

		// Tratamento de erro de data invalida
		LocalDate dataNascimento = null;
		boolean dataValida = false;
		while (!dataValida) {
			try {
				System.out.print("Data de Nascimento (dd/MM/yyyy): ");
				dataNascimento = LocalDate.parse(sc.next(), fmt);
				dataValida = true;
			} catch (DateTimeParseException e) {
				System.out.println("Data Inválida! Digite novamente.");
			}
		}

		System.out.print("Senha: ");
		sc.nextLine();
		String senha = sc.nextLine();

		// Comparando senhas
		boolean senhaValida = false;
		while (!senhaValida) {
			System.out.print("Digite novamente a senha: ");
			String senha2 = sc.nextLine();
			if (senha.equals(senha2)) {
				senhaValida = true;
			} else {
				System.out.println("Senha Invalida!! Digite novamente.");
			}
		}

		// Estanciar
		Usuario usuario = new Usuario(nome, dataNascimento, cpf);
		Conta conta = new Conta(0.0, senha, usuario);

		// Add
		contas.add(conta);
		usuarios.add(usuario);

		System.out.println("Conta criada com sucesso!!!");
	}

	private static void cadastrarDeposito(ArrayList<Conta> contas, Scanner sc) {
		System.out.println();
		System.out.println("=-=-=-Deposito-=-=-=");

		System.out.print("Número da conta: ");
		int numConta = sc.nextInt();

		// Verificar se tem conta cadastrada no sistema
		boolean contaEncontrada = false;
		for (Conta c : contas) {
			if (c.getNumeroConta().equals(numConta)) {
				contaEncontrada = true;

				// Deposito
				System.out.print("Valor para depósito: R$");
				double valor = sc.nextDouble();
				c.depositar(valor);

				if (valor > 0) {
					System.out.print("Digite a senha para realizar o depósito: ");
					sc.nextLine();
					String numSenha = sc.nextLine();

					// Confirmar deposito com a senha
					if (c.getSenha().equals(numSenha)) {
						System.out.println("Depósito realizado com sucesso!!");
					} else {
						System.out.println("Senha incorreta!");
					}
				}

				break; // Sai do loop após encontrar a conta correspondente
			}
		}

		if (!contaEncontrada) {
			System.out.println("Número da conta não encontrado no sistema!!");
			return;
		}

	}

	private static void cadastrarSaque(ArrayList<Conta> contas, Scanner sc) {
		System.out.println();
		System.out.println("=-=-=-Saque-=-=-=");
		
		

		System.out.print("Número da conta: ");
		int numConta = sc.nextInt();

		boolean encontrarConta = false;
		for (Conta c : contas) {
			if (c.getNumeroConta().equals(numConta)) {
				encontrarConta = true;

				System.out.println("Saldo Disponível: R$" + c.getSaldo());
				System.out.print("Valor para Saque: R$");
				double valor = sc.nextDouble();
				c.sacar(valor);

				// Informar a senha para efetuar o saque
				if (valor > 0 && valor <= c.getSaldo()) {
					System.out.print("Digite a senha para efetuar o saque: ");
					sc.nextLine();
					String numSenha = sc.nextLine();

					if (c.getSenha().equals(numSenha)) {
						System.out.println("Saque realizado com sucesso!!");
					} else {
						System.out.println("Senha incorreta!!");
						return;
					}
				}

				break;
			}

		}
		if (!encontrarConta) {
			System.out.println("Número da conta não encontrado no sistema!!");
		}

	}

	public static void cadastrarTransferencia(ArrayList<Conta> contas, Scanner sc) {
		System.out.println();
		System.out.println("=-=-=-Transferencia-=-=-=");
		System.out.print("Número da conta: ");
		int numConta = sc.nextInt();

		boolean encontrarConta = false;
		
		//Conta Origem
		Conta contaOrigem = null;
		for (Conta c : contas) {
			if (c.getNumeroConta().equals(numConta)) {
				encontrarConta = true;
				contaOrigem = c;
				break;
			}
		}

		if (encontrarConta) {
			System.out.print("Informe o número da conta que receberá a transferência: ");
			int numConta2 = sc.nextInt();

			//Conta destino
			if (numConta != numConta2) {
				Conta contaDestino = null;

				for (Conta c : contas) {
					if (c.getNumeroConta().equals(numConta2)) {
						contaDestino = c;
						break;
					}
				}

				if (contaDestino != null) {
					System.out.println("Saldo disponivel: " + contaOrigem.getSaldo());
					System.out.print("Valor da Transferencia: R$");
					double valor = sc.nextDouble();

					contaOrigem.transferir(valor, contaDestino);

					// Senha
					if (valor <= contaOrigem.getSaldo() && valor > 0) {
						System.out.print("Digite a senha para efetuar o saque: ");
						sc.nextLine();
						String numSenha = sc.nextLine();

						if (contaOrigem.getSenha().equals(numSenha)) {
							System.out.println("Transferência realizado com sucesso!!");
						} else {
							System.out.println("Senha incorreta!!");
							return;
						}
					}

				} else {
					System.out.println("Conta de destino não encontrada.");
				}
			} else {
				System.out.println("Número da conta de destino deve ser diferente da conta de origem.");
			}
		} else {
			System.out.println("Número da conta não encontrado no sistema!!");
		}

	}

	public static void relatorio(ArrayList<Conta> contas, Scanner sc) {
		System.out.println();
		System.out.println("=-=-=-Relatório-=-=-=");
		
		//Se tiver vazio
		if(contas.isEmpty()) {
			System.out.println("Não possui conta cadastrada!!");
		}
		
		for (Conta c : contas) {
			System.out.println(c.toString());
			System.out.println();
		}
	}

}
