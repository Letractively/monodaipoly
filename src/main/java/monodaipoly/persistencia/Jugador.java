package monodaipoly.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

@Entity
public class Jugador implements Serializable {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Key claveJugador;
	
	@Basic
	private String nick;
        
	@Basic
	private int posicion;
        
        
        @OneToMany(mappedBy="dueno")
        private List<Casilla> casillas;

        @Basic
        private Key partida;
	
	
	//calles..... 
	public Jugador(){}
	public Jugador(Usuario usuario){
		this.nick=usuario.getNick();
		this.posicion=0;
                this.casillas=null;
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
	public String getIdString(){
            System.out.println("Aqui Jugador getIdString() clave jugador  " + claveJugador);
            return KeyFactory.keyToString(claveJugador);
        }
	
}
