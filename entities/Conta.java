package entities;

import java.time.format.DateTimeFormatter;

public class Conta {
	private static int proximoNumeroConta = 1;
	private DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	private Usuario usuario;
	private Integer numeroConta;
	private Double saldo;
	private String senha;

	public Conta() {

	}

	public Conta(Double saldo, String senha, Usuario usuario) {
		this.numeroConta = proximoNumeroConta++;
		this.saldo = saldo;
		this.senha = senha;
		this.usuario = usuario;

	}

	public Integer getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(Integer numeroConta) {
		this.numeroConta = numeroConta;
	}

	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	// Método
	public void depositar(double valor) {
		if (valor > 0) {
			saldo += valor;

		} else {
			System.out.println("Valor informado para deposito é invalido!!");
			return;
		}
	}

	public void sacar(double valor) {
		if (valor > 0 && valor <= saldo) {
			saldo -= valor;
		} else if (valor > saldo) {
			System.out.println("Saldo para a saque é insuficiente!!");
		} else {
			System.out.println("Valor informado é invalido para saque!!");
			return;
		}
	}

	public void transferir(double valor, Conta contaDestino) {
		if (valor <= saldo && valor > 0) {
			saldo -= valor;
			contaDestino.saldo = contaDestino.getSaldo() + valor;
		} else if (valor > saldo) {
			System.out.println("Saldo para a transferência é insuficiente!!");
		} else {
			System.out.println("Valor informado é invalido para transferência!!");
		}
	}

	@Override
	public String toString() {
		return "Número da Conta: " + numeroConta + "\nNome do cliente: " + this.usuario.getNome()
				+ "\nData de Nasacimento: " + fmt.format(this.usuario.getDataNascimento()) + "\nCPF: "
				+ this.usuario.getCpf() + "\nSaldo: R$" + saldo;

	}

}
