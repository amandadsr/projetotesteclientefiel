package br.com.projeto.testeclientefiel.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.ForeignKey;


@Entity
public class Imagem implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	private String frase;
	
	@NotNull
	private int fonte;
	
	@NotNull
	@Lob
	private byte[] foto;
	
	private Long idPacote;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFrase() {
		return frase;
	}

	public void setFrase(String frase) {
		this.frase = frase;
	}

	public int getFonte() {
		return fonte;
	}

	public void setFonte(int fonte) {
		this.fonte = fonte;
	}

	public Long getIdPacote() {
		return idPacote;
	}

	public void setIdPacote(Long idPacote) {
		this.idPacote = idPacote;
	}

	public byte[] getFoto() {
		return foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}


		
}
