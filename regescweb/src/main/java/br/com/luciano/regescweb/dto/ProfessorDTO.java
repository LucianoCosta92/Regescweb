package br.com.luciano.regescweb.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import br.com.luciano.regescweb.models.Professor;
import br.com.luciano.regescweb.models.StatusProfessor;

/* DTO (DATA TRANSFER OBJECT):
 * A sua função é obter e armazenar dados. Quando estamos trabalhando 
 * com uma interface remota, cada chamada ao servidor pode custar muito 
 * tempo de processamento, a depender da quantidade de dados. Com o DTO, 
 * podemos filtrar quais dados serão transmitidos e assim reduzir esse problema. 
 * O DTO é bastante utilizado também quando não queremos expor todos os dados da
 * nossa camada de persistência mas precisamos exibir ao nosso cliente estes 
 * mesmos dados.
 * */
public class ProfessorDTO {
	
	@NotBlank
	@NotNull
	private String nome;
	
	@NotNull
	@DecimalMin("0.0")
	private BigDecimal salario;
	
	private StatusProfessor statusProfessor;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public BigDecimal getSalario() {
		return salario;
	}
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
	public StatusProfessor getStatusProfessor() {
		return statusProfessor;
	}
	public void setStatusProfessor(StatusProfessor statusProfessor) {
		this.statusProfessor = statusProfessor;
	}
	
	@Override
	public String toString() {
		return "ProfessorDTO [nome=" + nome + ", salario=" + salario + ", statusProfessor=" + statusProfessor + "]";
	}
	
	public Professor toProfessor() {
		Professor professor = new Professor();
		professor.setNome(this.nome);
		professor.setSalario(this.salario);
		professor.setStatusProfessor(this.statusProfessor);
		
		return professor;
	}
	
	
	
	
	
}
