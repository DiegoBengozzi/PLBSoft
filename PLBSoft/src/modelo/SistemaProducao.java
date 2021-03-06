package modelo;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name="SISTEMA_PRODUCAO")
@Entity
public class SistemaProducao implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 116420191546674699L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String sistemaProducao;
	
	@Column
	private BigDecimal biomassaEconomica;
	
	@Column
	private BigDecimal biomasaCritica;
	
	@Column
	private BigDecimal capacidadeSuporte;
	
	@Column
	private Boolean status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSistemaProducao() {
		return sistemaProducao;
	}

	public void setSistemaProducao(String nomeCapacidadeSuporte) {
		this.sistemaProducao = nomeCapacidadeSuporte;
	}

	public BigDecimal getBiomassaEconomica() {
		return biomassaEconomica;
	}

	public void setBiomassaEconomica(BigDecimal biomassaEconomica) {
		this.biomassaEconomica = biomassaEconomica;
	}

	public BigDecimal getBiomasaCritica() {
		return biomasaCritica;
	}

	public void setBiomasaCritica(BigDecimal biomasaCritica) {
		this.biomasaCritica = biomasaCritica;
	}

	public BigDecimal getCapacidadeSuporte() {
		return capacidadeSuporte;
	}

	public void setCapacidadeSuporte(BigDecimal capacidadeSuporte) {
		this.capacidadeSuporte = capacidadeSuporte;
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
		SistemaProducao other = (SistemaProducao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
