package br.com.kanegae.tccengsoft.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Projeto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;
	private String nome;
	private String descricao;
	@ManyToOne
	@JoinColumn(name = "usuario_codigo", referencedColumnName = "codigo", nullable = false)
	private Usuario dono;
	@OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
	private List<Tarefa> productBacklog;
	@OneToMany(mappedBy = "projeto", cascade = CascadeType.ALL)
	private List<Sprint> sprints;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Usuario getDono() {
		return dono;
	}

	public void setDono(Usuario dono) {
		this.dono = dono;
	}

	public List<Tarefa> getProductBacklog() {
		return productBacklog;
	}

	public void setProductBacklog(List<Tarefa> productBacklog) {
		this.productBacklog = productBacklog;
	}

	public List<Sprint> getSprints() {
		return sprints;
	}

	public void setSprints(List<Sprint> sprints) {
		this.sprints = sprints;
	}
}
