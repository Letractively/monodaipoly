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
        
        
        @Basic
        private List<Key> calles;


        @Basic
        private Key partida;

        @Basic
        private Boolean estoyJugando;

        @Basic
        private int dinero;
	
	//calles..... 
	public Jugador(){}
	public Jugador(Usuario usuario){
		this.nick=usuario.getNick();
		this.posicion=0;
                this.calles=null;
                this.estoyJugando=false;
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

        public int getDinero() {
		return dinero;
	}
	public void setDinero(int dinero) {
		this.dinero = dinero;
	}

	public String getIdString(){
            System.out.println("Aqui Jugador getIdString() clave jugador  " + claveJugador);
            return KeyFactory.keyToString(claveJugador);
        }

        public List<Key> getCalles() {
            return calles;
        }

        public void setCalles(List<Key> calles) {
            this.calles = calles;
        }

    public Boolean getEstoyJugando() {
        return estoyJugando;
    }

    public void setEstoyJugando(Boolean estoyJugando) {
        this.estoyJugando = estoyJugando;
    }

    public Key getPartida() {
        return partida;
    }

    public void setPartida(Key partida) {
        this.partida = partida;
    }      




	
}
