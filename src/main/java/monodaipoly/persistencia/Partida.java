package monodaipoly.persistencia;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;


/**
 *
 * @author instalador
 */

@Entity
public class Partida {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Key partida;

    @Basic
    private Key jugador1;

    @Basic
    private Key jugador2;

    @Basic
    private Key jugador3;

    @Basic
    private Key jugador4;

    @Basic
    private List<Integer> casillas;



}
