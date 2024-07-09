package cz.filipunk.servicebook.entity;

import java.time.LocalDateTime;

import cz.filipunk.servicebook.entity.Auta;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Opravy {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
   private LocalDateTime datum_opravy;
   private String popis_opravy;
   private String opravar;
   private int najezd_pri_oprave;
   private int cena;
   
	@ManyToOne
	@JoinColumn(name = "carId")
	private Auta car;
	
	@ManyToOne
	@JoinColumn(name = "uzivatel_id")
	private Users uzivatel;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getDatum_opravy() {
		return datum_opravy;
	}

	public void setDatum_opravy(LocalDateTime datum_opravy) {
		this.datum_opravy = datum_opravy;
	}

	public String getPopis_opravy() {
		return popis_opravy;
	}

	public void setPopis_opravy(String popis_opravy) {
		this.popis_opravy = popis_opravy;
	}

	public String getOpravar() {
		return opravar;
	}

	public void setOpravar(String opravar) {
		this.opravar = opravar;
	}

	public int getNajezd_pri_oprave() {
		return najezd_pri_oprave;
	}

	public void setNajezd_pri_oprave(int najezd_pri_oprave) {
		this.najezd_pri_oprave = najezd_pri_oprave;
	}

	public int getCena() {
		return cena;
	}

	public void setCena(int cena) {
		this.cena = cena;
	}

	public Auta getCar() {
		return car;
	}

	public void setCar(Auta car) {
		this.car = car;
	}

	public Users getUzivatel() {
		return uzivatel;
	}

	public void setUzivatel(Users uzivatel) {
		this.uzivatel = uzivatel;
	}
}
