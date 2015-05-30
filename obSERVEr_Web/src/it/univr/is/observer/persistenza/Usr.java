package it.univr.is.observer.persistenza;

/*email character varying(50) PRIMARY KEY,
 nome character varying(20),
 cognome character varying(20),
 password character(32),
 gestore SMALLINT NOT NULL DEFAULT 1*/

public class Usr {
	
	// ==== Field
	// ========================================================================

	private String email;
	private String nome;
	private String cognome;
	private String password;
	private int gestore;

	// ==== Constructor
	// ========================================================================

	public Usr(String email, String nome, String cognome, String password,
			int gestore) {
		this.email = email;
		this.nome = nome;
		this.cognome = cognome;
		this.password = password;
		this.gestore = gestore;
	}

	public Usr() {
		this.gestore = 1;
	}

	// ==== Methods
	// ========================================================================

	// ==== Getter & Setter
	// ========================================================================

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getGestore() {
		return gestore;
	}

	public void setGestore(int gestore) {
		this.gestore = gestore;
	}

}
