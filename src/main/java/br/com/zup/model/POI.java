package br.com.zup.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "poi")
public class POI {

	@Id
	@GeneratedValue
	private Long id;

	private String nome;
	private Integer x;
	private Integer y;

	public POI() {
		super();
	}
	
	public POI(String nome, Integer x, Integer y) {
		this.nome = nome;
		this.x = x;
		this.y = y;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "POI [id=" + id + ", nome=" + nome + ", x=" + x + ", y=" + y + "]";
	}

}
