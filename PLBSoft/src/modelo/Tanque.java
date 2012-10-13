package modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Table(name = "TANQUE")
@Entity
public class Tanque implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5445981170097635735L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String nome;

	@Column
	private String descricao;

	@Column
	private String acessibilidade;

	@Column
	private BigDecimal laminaAgua;

	@Column
	private BigDecimal profundidade;

	@Column
	private Boolean status;

	@ManyToOne
	private TipoTanque tipoTanqueId;

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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getAcessibilidade() {
		return acessibilidade;
	}

	public void setAcessibilidade(String acessibilidade) {
		this.acessibilidade = acessibilidade;
	}

	public BigDecimal getLaminaAgua() {
		return laminaAgua;
	}

	public void setLaminaAgua(BigDecimal laminaAgua) {
		this.laminaAgua = laminaAgua;
	}

	public BigDecimal getProfundidade() {
		return profundidade;
	}

	public void setProfundidade(BigDecimal profundidade) {
		this.profundidade = profundidade;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public TipoTanque getTipoTanqueId() {
		return tipoTanqueId;
	}
	
	public void setTipoTanqueId(TipoTanque tipoTanqueId) {
		this.tipoTanqueId = tipoTanqueId;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tanque other = (Tanque) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}
