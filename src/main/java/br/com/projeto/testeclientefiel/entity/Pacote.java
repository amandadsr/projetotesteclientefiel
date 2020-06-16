package br.com.projeto.testeclientefiel.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;


@Entity
public class Pacote implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idPacote;
	
	@NotNull
	private String nomePacote;
	
	@OneToMany
	private List<Imagem> imagem;

	public Long getIdPacote() {
		return idPacote;
	}

	public void setIdPacote(Long idPacote) {
		this.idPacote = idPacote;
	}

	public List<Imagem> getImagem() {
		return imagem;
	}

	public void setImagem(List<Imagem> imagem) {
		this.imagem = imagem;
	}

	public String getNomePacote() {
		return nomePacote;
	}

	public void setNomePacote(String nomePacote) {
		this.nomePacote = nomePacote;
	}

	
	
	
}
