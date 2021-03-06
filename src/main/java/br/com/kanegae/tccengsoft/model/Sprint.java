package br.com.kanegae.tccengsoft.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Sprint {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	private String objetivo;
	private String descricao;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Calendar dataInicial;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Calendar dataFinal;
	@ManyToOne
	@JoinColumn(name = "usuario_codigo", referencedColumnName = "codigo", nullable = false)
	private Usuario dono;
	@OneToMany(mappedBy = "sprint", cascade = CascadeType.ALL)
	private List<Tarefa> sprintBacklog;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Calendar getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(Calendar dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Calendar getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(Calendar dataFinal) {
		this.dataFinal = dataFinal;
	}
	
	public Usuario getDono() {
		return dono;
	}

	public void setDono(Usuario dono) {
		this.dono = dono;
	}

	public List<Tarefa> getSprintBacklog() {
		return sprintBacklog;
	}

	public void setSprintBacklog(List<Tarefa> sprintBacklog) {
		this.sprintBacklog = sprintBacklog;
	}
}