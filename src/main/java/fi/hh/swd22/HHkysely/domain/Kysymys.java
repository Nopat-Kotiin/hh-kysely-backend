package fi.hh.swd22.HHkysely.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Kysymys {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long kysymysId;
	
	private String kysymys;
	private String vastaus;
	
	
	public Kysymys() {}
	
	
	public Kysymys(long kysymysId, String kysymys, String vastaus) {
		
		super();
		this.kysymysId = kysymysId;
		this.kysymys = kysymys;
		this.vastaus = vastaus;
		
	}


	public long getKysymysId() {
		return kysymysId;
	}


	public void setKysymysId(long kysymysId) {
		this.kysymysId = kysymysId;
	}


	public String getKysymys() {
		return kysymys;
	}


	public void setKysymys(String kysymys) {
		this.kysymys = kysymys;
	}


	public String getVastaus() {
		return vastaus;
	}


	public void setVastaus(String vastaus) {
		this.vastaus = vastaus;
	}


	@Override
	public String toString() {
		return "Kysymys [kysymysId=" + kysymysId + ", kysymys=" + kysymys + ", vastaus=" + vastaus + "]";
	}
	
}
