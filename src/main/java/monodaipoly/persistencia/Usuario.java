package monodaipoly.persistencia;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;


import com.google.appengine.api.datastore.Key;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@Entity
public class Usuario implements Serializable {

    @Id
    private String nick;
    @Column(nullable = false)
    private String contrasena;
    @Basic
    private int partidasGanadas;
    @Basic
    private int partidasJugadas;
    @Basic(fetch=FetchType.EAGER)
    private String nombre;
    @Basic(fetch=FetchType.EAGER)
    private String apellido;
    @Basic
    private String fechaNacimiento;
    @Basic
    private Key jugador;
    @Basic
    List<Key> roles = new ArrayList<Key>();

    //Tenemos que hacer una relacion sin dueño
    @Basic
    List<Key>bandejaEntrada =new ArrayList<Key>();


    public Usuario(){
        
    }
    public Usuario(String nick, String contrasena, String nombre, String apellido, String fechaDia, String fechaMes, String fechaAno) {
        this.nick = nick;
        this.contrasena = contrasena;
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaDia + "/" + fechaMes + "/" + fechaAno;
        this.partidasGanadas = 0;
        this.partidasJugadas = 0;
        this.jugador=null;

    }

    public Usuario(String nick, String contrasena) {
        this.nick = nick;
        this.contrasena = contrasena;
        this.nombre = "";
        this.apellido = "";
        this.fechaNacimiento = "1/1/1";
        this.partidasGanadas = 0;
        this.partidasJugadas = 0;
        this.jugador = null;


    }
    public Key getJugador() {
        return jugador;
    }

    public void setJugador(Key jugador) {
        this.jugador = jugador;
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

    public void setNick(String nick) {
        this.nick = nick;
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
        this.fechaNacimiento = fechaDia + "/" + fechaMes + "/" + fechaAno;
    }

    public String getNick() {
        return nick;
    }

    public List<Key> getRoles() {
        return roles;
    }

    public void setRoles(List<Key> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        String s;
        s = "Nombre: " + this.nombre + " Apellido: " + this.apellido;
        return s;
    }

    public List<Mensaje> getBandejaEntrada() {
        return bandejaEntrada;
    }

    public void setBandejaEntrada(List<Mensaje> bandejaEntrada) {
        this.bandejaEntrada = bandejaEntrada;
    }
    

    public int estadisticas() {
        int estadisticas;
        estadisticas = this.partidasGanadas / this.partidasJugadas;
        return estadisticas;
    }

    /*
     * Devuelve positivo si el Usuario this tiene mejor estadisticas que el usuario 2
     * Negativo si el Usuario 2 tiene mejores estadisticas
     * y 0 si tienen las mismas estadisticas
     *
     */
    public int compareTo(Usuario user2) {
        return (this.estadisticas() - user2.estadisticas());
    }

    public boolean equals(Usuario user2) {
        if (this.nick.equals(user2.nick) && this.contrasena.equals(user2.contrasena) && this.partidasGanadas == user2.partidasGanadas && this.partidasJugadas == user2.partidasJugadas) {
            return true;
        }
        return false;
    }

}
