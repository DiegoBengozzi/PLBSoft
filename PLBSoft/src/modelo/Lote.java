package modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Table
@Entity
public class Lote implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9183777642107279965L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Boolean status;
	
	@Column
	private Date dataInicioLote;
	
	@Column
	private Date dataFimLote;
	
	@Column
	private String nome;
	
	@Column
	private String descricao;
	
	@Column
	private BigDecimal quantidadePeixe;
	
	@ManyToOne
	private Safra safraId;
	
	@ManyToOne
	private Especie especieId;
	
	@ManyToMany
	@Cascade(value={CascadeType.ALL})
	@JoinTable(name="loteOrigem",
		joinColumns={
			@JoinColumn(name="lote_id", referencedColumnName = "id")
		},
		inverseJoinColumns={
			@JoinColumn(name="lotePai_id", referencedColumnName = "id")
		}
	)
	private List<Lote> listaLote = new ArrayList<Lote>();

	@ManyToOne
	private Tanque tanqueId;
	
	@ManyToOne
	private TanqueRede tanqueRedeId;
	
	@ManyToOne
	private Hapa hapaId;
	
	public TanqueRede getTanqueRedeId() {
		return tanqueRedeId;
	}

	public void setTanqueRedeId(TanqueRede tanqueRedeId) {
		this.tanqueRedeId = tanqueRedeId;
	}

	public Hapa getHapaId() {
		return hapaId;
	}

	public void setHapaId(Hapa hapaId) {
		this.hapaId = hapaId;
	}

	public Tanque getTanqueId() {
		return tanqueId;
	}

	public void setTanqueId(Tanque tanqueId) {
		this.tanqueId = tanqueId;
	}

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

	public Date getDataInicioLote() {
		return dataInicioLote;
	}

	public void setDataInicioLote(Date dataInicioLote) {
		this.dataInicioLote = dataInicioLote;
	}

	public Date getDataFimLote() {
		return dataFimLote;
	}

	public void setDataFimLote(Date dataFimLote) {
		this.dataFimLote = dataFimLote;
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

	public BigDecimal getQuantidadePeixe() {
		return quantidadePeixe;
	}

	public void setQuantidadePeixe(BigDecimal quantidadePeixe) {
		this.quantidadePeixe = quantidadePeixe;
	}

	public Safra getSafraId() {
		return safraId;
	}

	public void setSafraId(Safra safraId) {
		this.safraId = safraId;
	}

	public Especie getEspecieId() {
		return especieId;
	}

	public void setEspecieId(Especie especieId) {
		this.especieId = especieId;
	}

	public List<Lote> getListaLote() {
		return listaLote;
	}

	public void setListaLote(List<Lote> listaLote) {
		this.listaLote = listaLote;
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
		Lote other = (Lote) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

}
