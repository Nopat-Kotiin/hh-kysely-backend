package fi.hh.swd22.HHkysely.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Kysymys {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String kysymys;
	private String vastaus;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "kyselyId")
	private Kysely kysely;
	
	
	public Kysymys() {}
	
	
	public Kysymys(String kysymys, Kysely kysely) {
		super();
		this.kysymys = kysymys;
		this.vastaus = "";
		this.kysely = kysely;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
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

	public void setKysely(Kysely kysely) {
		this.kysely = kysely;
	}

	public Kysely getKysely() {
		return kysely;
	}


	@Override
	public String toString() {
		return "Kysymys [kysymysId=" + id + ", kysymys=" + kysymys + ", vastaus=" + vastaus + "]";
	}
	
}
