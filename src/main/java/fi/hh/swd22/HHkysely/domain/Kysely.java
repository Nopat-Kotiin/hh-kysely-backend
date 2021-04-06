package fi.hh.swd22.HHkysely.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Kysely {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long kyselyId;
	
	private String nimi;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kysely")
	private List<Kysymys> kysymykset;
		
	public Kysely() {}
	
	public Kysely(String nimi) {
		
		super();
		this.nimi = nimi;
	}


	public long getKyselyId() {
		return kyselyId;
	}


	public void setKyselyId(long kyselyId) {
		this.kyselyId = kyselyId;
	}


	public String getNimi() {
		return nimi;
	}


	public void setNimi(String nimi) {
		this.nimi = nimi;
	}


	@Override
	public String toString() {
		return "Kysely [kyselyId=" + kyselyId + ", nimi=" + nimi + "]";
	}
	
}
