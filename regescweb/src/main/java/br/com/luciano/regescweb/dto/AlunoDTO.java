package br.com.luciano.regescweb.dto;

import br.com.luciano.regescweb.models.Aluno;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AlunoDTO {
	
	@NotNull
	@NotBlank
	private String nome;
	@NotNull
	@NotBlank
	private String email;
	@NotNull
	@NotBlank
	private String telefone;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	@Override
	public String toString() {
		return "AlunoDTO [nome=" + nome + ", email=" + email + ", telefone=" + telefone + "]";
	}
	
	public Aluno toAluno() {
		Aluno aluno = new Aluno();
		aluno.setNome(this.nome);
		aluno.setEmail(this.email);
		aluno.setTelefone(this.telefone);
		
		return aluno;
	}
	
	public Aluno toAluno(Aluno aluno) {
		aluno.setNome(this.nome);
		aluno.setEmail(this.email);
		aluno.setTelefone(this.telefone);
		
		return aluno;
	}
	
	public void fromAluno(Aluno aluno) {
		this.nome = aluno.getNome();
		this.email = aluno.getEmail();
		this.telefone = aluno.getTelefone();
	}
}
