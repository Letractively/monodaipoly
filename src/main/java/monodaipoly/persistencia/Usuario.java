package monodaipoly.persistencia;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import monodaipoly.dao.JugadorDAO;
import monodaipoly.dao.JugadorDAOImpl;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;
import java.io.Serializable;

@Entity
public class Usuario implements Serializable{
	@Id
	 private String nick;
	@Basic
	 private String contrasena;
	@Basic
	 private int partidasGanadas;
	@Basic
	 private int partidasJugadas;
	 @Basic
	 private String nombre;
	 @Basic
	 private String apellido;
	 @Basic
	 private String fechaNacimiento;
	 @OneToMany(mappedBy ="usuario")
	 private List<Jugador> jugadores;

    public Usuario() {
    }
	 

	 
	 public Usuario(String nick, String contrasena,String nombre,String apellido,String fechaDia,String fechaMes,String fechaAno){
		 this.nick=nick;
		 this.contrasena=contrasena;
		 this.nombre=nombre;
		 this.apellido=apellido;
		 this.fechaNacimiento=fechaDia+"/"+fechaMes+"/"+fechaAno;
		 this.partidasGanadas=0;
		 this.partidasJugadas=0;
		 this.jugadores= new ArrayList<Jugador>();
	
	 }
	 public Usuario(String nick,String contrasena)
	 {
		 this.nick=nick;
		 this.contrasena=contrasena;
		 this.nombre="";
		 this.apellido="";
		 this.fechaNacimiento="1/1/1";
		 this.partidasGanadas=0;
		 this.partidasJugadas=0;
		 this.jugadores= new ArrayList<Jugador>();
		

	 }
	 
	 /*public Usuario(){
		 this.nick="";
		 this.contrasena="";
		 this.nombre="";
		 this.apellido="";
		 this.fechaNacimiento=null;
		 this.partidasGanadas=0;
		 this.partidasJugadas=0;
		 this.jugadores= new ArrayList<Jugador>();
		

	 }*/
	 
	 
	
	
	public List<Jugador> getJugadores() {
		return jugadores;
	}
	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public void setNick(String nick){
		 this.nick=nick;
	 }

	public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public int getPartidasGanadas() {
		return partidasGanadas;
	}

	public void setPartidasGanadas(int partidasGanadas) {
		this.partidasGanadas = partidasGanadas;
	}

	public int getPartidasJugadas() {
		return partidasJugadas;
	}

	public void setPartidasJugadas(int partidasJugadas) {
		this.partidasJugadas = partidasJugadas;
	}

        public String getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(String fechaDia, String fechaMes, String fechaAno) {
		this.fechaNacimiento=fechaDia+"/"+fechaMes+"/"+fechaAno;
	}

	public String getNick() {
		return nick;
	}
	
	
	
    @Override
	public String toString(){
		String s;
		s= "Nick: " + this.nick + " Contrasena: " + this.contrasena + " Partidas Ganadas: " + this.partidasGanadas + " Partidas Jugadas: " + this.partidasJugadas;
		return s;
	}
	
	public int estadisticas(){
		int estadisticas;
		estadisticas=this.partidasGanadas/this.partidasJugadas;
		return estadisticas;
	}
	
	/*
	 * Devuelve positivo si el Usuario this tiene mejor estadisticas que el usuario 2
	 * Negativo si el Usuario 2 tiene mejores estadisticas
	 * y 0 si tienen las mismas estadisticas
	 * 
	 */
	public int compareTo(Usuario user2){
		return (this.estadisticas()-user2.estadisticas());
	}
	
	public boolean equals(Usuario user2){
		if(this.nick.equals(user2.nick) && this.contrasena.equals(user2.contrasena) && this.partidasGanadas==user2.partidasGanadas && this.partidasJugadas==user2.partidasJugadas){
			return true;
		}
		return false;
	}
	
	public Jugador crearJugador(){
		Jugador jugador= new Jugador(this);
		this.jugadores.add(jugador);
		return jugador;
	}

}
