package it.univr.is.observer.persistenza;

import it.univr.is.database.MioDriver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	public Usr(ResultSet rs) throws SQLException {
		this.email = rs.getString("email");
		this.nome = rs.getString("nome");
		this.cognome = rs.getString("cognome");
		this.password = rs.getString("password");
		this.gestore = rs.getInt("gestore");
	}

	public Usr() {
		this.gestore = 2;
	}

	// ==== Methods
	// ========================================================================

	/**
	 * Ritorno l'elenco dei veicoli associati all'email "email"
	 * 
	 * @param email
	 * @return
	 */
	public static List<Veicolo> getUserVeicoliUtente(String email) {
		List<Veicolo> res = new ArrayList<Veicolo>();

		int livelloPrivilegi = -1;
		String query = "";
		Object[] params = null;
		MioDriver driver;
		ResultSet rs = null;

		// Ottengo livello di privilegi dell'utente
		try {
			driver = MioDriver.getInstance();
			query = "select usr.gestore from usr where usr.email = ?";
			params = new Object[1];
			params[0] = email;
			rs = driver.execute(query, params);
			if (rs != null) {
				rs.next();
				livelloPrivilegi = rs.getInt("gestore");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			driver = MioDriver.getInstance();
			switch (livelloPrivilegi) {
			// se livelloPrivilegi = 0 -> Admin -> visualizzo tutti i veicoli
			case 0:
				query = "select uv.email, v.targa, v.marca, v.modello, v.gestore from "
						+ "usr_veicolo uv, veicolo v where uv.targa = v.targa and "
						+ "uv.inizio < current_date and (uv.fine >= current_date "
						+ "OR uv.fine is null)";

				params = null;
				
				break;
			// se livelloPrivilegi = 1 -> GestoreFlotta
			case 1:
				query = "select uv.email, v.targa, v.marca, v.modello, v.gestore from "
						+ "usr_veicolo uv, veicolo v where uv.targa = v.targa and "
						+ "uv.inizio < current_date and (uv.fine >= current_date "
						+ "OR uv.fine is null) and v.gestore = ?";
				params = new Object[1];
				params[0] = email;
				break;
			// se livelloPrivilegi = 2 -> Utente
			case 2:
				query = "select uv.email, v.targa, v.marca, v.modello, v.gestore from "
						+ "usr_veicolo uv, veicolo v where uv.targa = v.targa and "
						+ "uv.inizio < current_date and (uv.fine >= current_date "
						+ "OR uv.fine is null) and uv.email = ?";
				params = new Object[1];
				params[0] = email;
				break;
			default:
				// TODO: Gestione errore
				System.err.println("Errore inserimento");
			}
				rs = driver.execute(query, params);
				while (rs.next())
					res.add(new Veicolo(rs));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return res;
	}

	/**
	 * Ritorno i dati dell'utente con email "email"
	 * 
	 * @param email
	 * @return
	 */
	public static Usr getUserData(String email) {
		Usr res = null;
		try {
			MioDriver driver = MioDriver.getInstance();
			String query = "select * from usr where email = ?";
			Object[] params = new Object[1];
			params[0] = email;
			ResultSet rs = driver.execute(query, params);
			if (rs.next())
				res = new Usr(rs);
		} catch (SQLException e) {
			System.out
					.println("Select failed: An Exception has occurred! " + e);
		}
		return res;
	}
	
	
	/**
	 * Ritorno i dati di tutti gli "utenti"
	 * 
	 * @param email
	 * @return
	 */
	public static List<Usr> getUsers() {
		List<Usr> res = new ArrayList<>();
		try {
			MioDriver driver = MioDriver.getInstance();
			String query = "select * from usr where gestore = 2";
			ResultSet rs = driver.execute(query, null);
			while (rs.next())
				res.add(new Usr(rs));
		} catch (SQLException e) {
			System.out
					.println("Select failed: An Exception has occurred! " + e);
		}
		return res;
	}
	
	

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
