package cz.filipunk.servicebook.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Auta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String znacka;
    private String model;
    private int rok_vyroby;
    private int pocet_km;
    
	@ManyToOne
	@JoinColumn(name = "uzivatel_id")
	private Users uzivatel;

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getZnacka() {
		return znacka;
	}

	public void setZnacka(String znacka) {
		this.znacka = znacka;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getRok_vyroby() {
		return rok_vyroby;
	}

	public void setRok_vyroby(int rok_vyroby) {
		this.rok_vyroby = rok_vyroby;
	}

	public int getPocet_km() {
		return pocet_km;
	}

	public void setPocet_km(int pocet_km) {
		this.pocet_km = pocet_km;
	}

	public Users getUzivatel() {
		return uzivatel;
	}

	public void setUzivatel(Users user) {
		uzivatel = user;
	}
	
	
	
}
