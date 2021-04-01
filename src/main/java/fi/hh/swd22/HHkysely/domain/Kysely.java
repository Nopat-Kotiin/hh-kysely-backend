package fi.hh.swd22.HHkysely.domain;

public class Kysely {

	
	
	private long kyselyId;
	private String nimi;
	
	
	
	
	public Kysely() {}
	
	
	public Kysely(long kyselyId, String nimi) {
		
		super();
		this.kyselyId = kyselyId;
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
