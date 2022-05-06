package com.generation.farmacia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table (name = "td_produtos")
public class Produto {
	
	@Id
	@GeneratedValue (strategy=GenerationType.IDENTITY)
	private Long id ;
	
	@NotBlank(message = "o nome é obrigatorio")
	@Size(min=5, max=100, message= "nomedeve conter no minino 5 e no maximo 100 caracteres ")
	private String nome;
	
	@NotBlank(message = "quantidade é obrigatorio")
	private int quantidade;
	
	@NotNull(message = "preco é obrigatorio")
	@JsonFormat(shape = JsonFormat.Shape.STRING)
	private double preco;
	
	@NotBlank
	private String laboratorio;
	
	@NotBlank
	private String descricao;
	
	@NotBlank
	private String foto;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public String getLaboratorio() {
		return laboratorio;
	}

	public void setLaboratorio(String laboratorio) {
		this.laboratorio = laboratorio;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}
	
	@ManyToOne        // criando relacionamento com a outra tabela de muito produtos pra uma categoria
	@JsonIgnoreProperties("produto") // pra não entrar em loping infinito
	private Categoria categoria ;

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	
	
	
	
}
