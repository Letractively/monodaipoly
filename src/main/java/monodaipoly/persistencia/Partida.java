package monodaipoly.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import java.util.ArrayList;


@Entity
public class Partida implements Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Key idpartida;

    @Basic
    private Key jugador1;

    @Basic
    private Key jugador2;

    @Basic
    private Key jugador3;

    @Basic
    private Key jugador4;

    @Basic
    private List<Key> casillas;


   public Partida(){
       this.jugador1=null;
       this.jugador2=null;
       this.jugador3=null;
       this.jugador4=null;
       this.casillas=null;
   }

    public List<Key> getCasillas() {
        return casillas;
    }

    public void setCasillas(List<Key> casillas) {
        this.casillas = casillas;
    }

    public Key getJugador1() {
        return jugador1;
    }

    public void setJugador1(Key jugador1) {
        this.jugador1 = jugador1;
    }

    public Key getJugador2() {
        return jugador2;
    }

    public void setJugador2(Key jugador2) {
        this.jugador2 = jugador2;
    }

    public Key getJugador3() {
        return jugador3;
    }

    public void setJugador3(Key jugador3) {
        this.jugador3 = jugador3;
    }

    public Key getJugador4() {
        return jugador4;
    }

    public void setJugador4(Key jugador4) {
        this.jugador4 = jugador4;
    }

    public Key getIdpartida() {
        return idpartida;
    }

    public void setIdpartida(Key idpartida) {
        this.idpartida = idpartida;
    }

    public String getIdString(){
            System.out.println("Aqui Jugador getIdString() clave partida  " + idpartida);
            return KeyFactory.keyToString(idpartida);
        }

    







}
