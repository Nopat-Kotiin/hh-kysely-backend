package fi.hh.swd22.HHkysely.domain;

public class Kysymys {

	
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
