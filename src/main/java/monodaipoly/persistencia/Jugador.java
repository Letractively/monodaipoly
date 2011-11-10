package monodaipoly.persistencia;

import javax.persistence.*;

import com.google.appengine.api.datastore.Key;

@Entity
public class Jugador {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Key claveJugador;
	
	@Basic
	private String nick;
	@Basic
	private int posicion;
	@ManyToOne(fetch =FetchType.LAZY)
	private Usuario usuario;
	
	
	//calles..... 
	public Jugador(){
		
	}
	public Jugador(Usuario usuario){
		this.nick=usuario.getNick();
		this.posicion=0;
		this.usuario=usuario;
	}
	
	
	public Key getClaveJugador() {
		return claveJugador;
	}
	public void setClaveJugador(Key claveJugador) {
		this.claveJugador = claveJugador;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public int getPosicion() {
		return posicion;
	}
	public void setPosicion(int posicion) {
		this.posicion = posicion;
	}
	
	
}
