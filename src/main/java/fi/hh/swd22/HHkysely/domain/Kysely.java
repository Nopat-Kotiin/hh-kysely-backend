package fi.hh.swd22.HHkysely.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Kysely {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String nimi;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "kysely")
	private List<Kysymys> kysymykset;
		
	public Kysely() {}
	
	public Kysely(String nimi) {
		super();
		this.nimi = nimi;
		this.kysymykset = new ArrayList<>();
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public String getNimi() {
		return nimi;
	}


	public void setNimi(String nimi) {
		this.nimi = nimi;
	}

	public List<Kysymys> getKysymykset() {
		return kysymykset;
	}

	public void setKysymykset(List<Kysymys> kysymykset) {
		this.kysymykset = kysymykset;
	}

	public void addKysymys(Kysymys k) {
		this.kysymykset.add(k);
	}

	public void dismissKysymys(Kysymys k) {
		this.kysymykset.remove(k);
	}

	public void dismissKysymyses() {
	   this.kysymykset.forEach(k -> k.dismissKysely()); // SYNCHRONIZING THE OTHER SIDE OF RELATIONSHIP 
	   this.kysymykset.clear();
	}

	@Override
	public String toString() {
		return "Kysely [kyselyId=" + id + ", nimi=" + nimi + "]";
	}
	
}
