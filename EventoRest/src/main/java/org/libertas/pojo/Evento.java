package org.libertas.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="evento")
public class Evento {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int idevento;
	private String nome;
	private String telefone;
	private String email;
	private String localevento;
	public int getIdevento() {
		return idevento;
	}
	public void setIdevento(int idevento) {
		this.idevento = idevento;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLocalevento() {
		return localevento;
	}
	public void setLocalevento(String localevento) {
		this.localevento = localevento;
	}
	
	
}
