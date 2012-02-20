package monodaipoly.persistencia;

import java.util.List;

import com.google.appengine.api.datastore.KeyFactory;
import java.util.ArrayList;

import com.google.appengine.api.datastore.Key;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;



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
    @Basic
    private boolean completa;
    @Basic
    private Key turno;
    @Basic
    private long fechaTurno;
    @Basic
    private boolean haTirado;

   public Partida(){
       this.jugador1=null;
       this.jugador2=null;
       this.jugador3=null;
       this.jugador4=null;
       this.casillas= new ArrayList<Key>();
       this.fechaTurno=0;
       this.turno=null;
       this.completa=false;
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

    public boolean getHaTirado() {
        return haTirado;
    }

    public void setHaTirado(boolean haTirado) {
        this.haTirado = haTirado;
    }
    

    public Key getTurno() {
        return turno;
    }

    public void setTurno(Key turno) {
        this.turno = turno;
    }

    public long getFechaTurno() {
        return fechaTurno;
    }

    public void setFechaTurno(long fechaTurno) {
        this.fechaTurno = fechaTurno;
    }
    

    public String getIdString(){
            return KeyFactory.keyToString(idpartida);
        }

    public boolean isCompleta() {
        return completa;
    }

    public void setCompleta(boolean completa) {
        this.completa = completa;
    }



    







}
