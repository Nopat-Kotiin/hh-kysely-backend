package fi.hh.swd22.HHkysely.domain;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Kysymys {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long kysymysId;
	
	private String kysymys;

	@JsonBackReference
	@ManyToOne
	@JoinColumn(name = "kyselyId")
	private Kysely kysely;
	
	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "vastaus")
	private List<Vastaus> vastaukset;

	public Kysymys() {}
	
	public Kysymys(String kysymys, Kysely kysely) {
		super();
		this.kysymys = kysymys;
		this.kysely = kysely;
	}

	public long getId() {
		return kysymysId;
	}

	public void setId(long kysymysId) {
		this.kysymysId = kysymysId;
	}

	public String getKysymys() {
		return kysymys;
	}

	public void setKysymys(String kysymys) {
		this.kysymys = kysymys;
	}

	public void setKysely(Kysely kysely) {
		this.kysely = kysely;
	}

	public Kysely getKysely() {
		return kysely;
	}

	public List<Vastaus> getVastaukset() {
		return vastaukset;
	}

	public void setVastaukset(List<Vastaus> vastaukset) {
		this.vastaukset = vastaukset;
	}

	public void dismissKysely() {
        this.kysely.dismissKysymys(this); //SYNCHRONIZING THE OTHER SIDE OF RELATIONSHIP
        this.kysely = null;
    }

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Kysymys)) {
			return false;
		}
		Kysymys k = (Kysymys) o;
		return kysymysId == k.kysymysId && kysymys.equals(k.kysymys) && Objects.equals(vastaukset, k.vastaukset);
	}

	@Override
	public String toString() {
		return "Kysymys [kysymysId=" + kysymysId + ", kysymys=" + kysymys + ", kysely=" + kysely + "]";
	}
}
