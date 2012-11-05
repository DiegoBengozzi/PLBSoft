package modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Table(name = "ALOJAMENTO")
@Entity
public class Alojamento implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3370238110369265511L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Boolean status;
	
	@ManyToOne
	@NotNull
	private Lote loteA;
	
	@ManyToOne
	@NotNull
	private Tanque tanqueA;
	
	@ManyToOne
	private TanqueRede tanqueRedeA;
	
	@ManyToOne
	private Hapa hapaA;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Lote getLoteA() {
		return loteA;
	}

	public void setLoteA(Lote loteA) {
		this.loteA = loteA;
	}

	public Tanque getTanqueA() {
		return tanqueA;
	}

	public void setTanqueA(Tanque tanqueA) {
		this.tanqueA = tanqueA;
	}

	public TanqueRede getTanqueRedeA() {
		return tanqueRedeA;
	}

	public void setTanqueRedeA(TanqueRede tanqueRedeA) {
		this.tanqueRedeA = tanqueRedeA;
	}

	public Hapa getHapaA() {
		return hapaA;
	}

	public void setHapaA(Hapa hapaA) {
		this.hapaA = hapaA;
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
		Alojamento other = (Alojamento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
