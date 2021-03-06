package modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "ESPECIE")
@Entity
public class Especie implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1593030897560368773L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String especie;

	@Column
	private String linhegem;

	@Column
	private String hibrido;

	@Column
	private String genero;

	@Column
	private String toleranciaSalinidade;

	@Column
	private String toleranciaFrio;

	@Column
	private BigDecimal maturacaoSexual;
	
	@Column
	private Boolean status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getLinhegem() {
		return linhegem;
	}

	public void setLinhegem(String linhegem) {
		this.linhegem = linhegem;
	}

	public String getHibrido() {
		return hibrido;
	}

	public void setHibrido(String hibrido) {
		this.hibrido = hibrido;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getToleranciaSalinidade() {
		return toleranciaSalinidade;
	}

	public void setToleranciaSalinidade(String toleranciaSalinidade) {
		this.toleranciaSalinidade = toleranciaSalinidade;
	}

	public String getToleranciaFrio() {
		return toleranciaFrio;
	}

	public void setToleranciaFrio(String toleranciaFrio) {
		this.toleranciaFrio = toleranciaFrio;
	}

	public BigDecimal getMaturacaoSexual() {
		return maturacaoSexual;
	}

	public void setMaturacaoSexual(BigDecimal maturacaoSexual) {
		this.maturacaoSexual = maturacaoSexual;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
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
		Especie other = (Especie) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


}