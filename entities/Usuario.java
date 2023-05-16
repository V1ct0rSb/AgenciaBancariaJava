package entities;

import java.time.LocalDate;

public class Usuario {
	private String nome;
	private LocalDate dataNascimento;
	private Integer cpf;

	public Usuario() {

	}

	public Usuario(String nome, LocalDate dataNascimento, Integer cpf) {
		this.nome = nome;
		this.dataNascimento = dataNascimento;
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Integer getCpf() {
		return cpf;
	}

	public void setCpf(Integer cpf) {
		this.cpf = cpf;
	}

}
