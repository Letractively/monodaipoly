package monodaipoly.persistencia;

import com.google.appengine.api.datastore.Key;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Mensaje implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Key idMensaje;
    
    @Basic
    private String contenido;
    
    @Basic
    private String autor;
    
    @Basic
    private String destinatario;
    
    

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Key getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(Key idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }
    
    
    
    
    
    
}