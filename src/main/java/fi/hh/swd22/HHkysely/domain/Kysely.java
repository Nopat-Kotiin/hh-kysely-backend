package fi.hh.swd22.HHkysely.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
public class Kysely {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long kyselyId;
	
	private String nimi;
		
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
