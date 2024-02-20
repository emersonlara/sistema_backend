package com.rocket.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.rocket.domain.enums.Formapagamento;
import com.rocket.domain.enums.Prioridade;
import com.rocket.domain.enums.Status;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataAbertura;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDateTime dataFechamento;

	private Integer tipodocumento;
	private Integer prioridade;
	private Integer status;
	private String observacao;
	private Integer formapagamento;
	private double valorservico; 
		


	@ManyToOne
	@JoinColumn(name = "tecnico_id")
	private Tecnico tecnico;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	public OS() {
		super();
		this.setDataAbertura(LocalDateTime.now());
		this.setPrioridade(Prioridade.BAIXA);
		this.setStatus(Status.ABERTO);
	}

	public OS(Integer id, 
			  Integer tipodocumento, 
			  Prioridade prioridade, 
			  Status status, 
			  String observacao,
			  Formapagamento formapagamento,
			  double valorservico,
			  Tecnico tecnico, 
			   Cliente cliente) {
		super();
		this.id = id;
		this.setDataAbertura(LocalDateTime.now());
		// this.dataFechamento = dataFechamento;
		this.tipodocumento = tipodocumento;
		this.prioridade = (prioridade == null) ? 0 : prioridade.getCod();
		this.status = (status == null) ? 0 : status.getCod();
		this.observacao = observacao;
		this.formapagamento = (formapagamento == null) ? 0 : formapagamento.getCod();
		this.valorservico = valorservico;
		this.tecnico = tecnico;
		this.cliente = cliente;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDateTime dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDateTime getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDateTime dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Integer getTipodocumento() {
		return tipodocumento;
	}

	public void setTipodocumento(Integer tipodocumento) {
		this.tipodocumento = tipodocumento;
	}

	public Prioridade getPrioridade() {
		return Prioridade.toEnum(this.prioridade);
	}

	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade.getCod();
	}

	public Status getStatus() {
		return Status.toEnum(this.status);
	}

	public void setStatus(Status status) {
		this.status = status.getCod();
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	

	public double getValorservico() {
		return valorservico;
	}

	public void setValorservico(double valorservico) {
		this.valorservico = valorservico;
	}
	

	
	
	public Formapagamento getformapagamentos() {
		return Formapagamento.toEnum(this.formapagamento);
	}

	public void setformapagamento(Formapagamento formapagamento) {
		this.formapagamento = formapagamento.getCod();
	}
		
	

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OS other = (OS) obj;
		return Objects.equals(id, other.id);
	}

}
