
package monodaipoly.servicio;

import com.google.appengine.api.datastore.Key;
import javax.annotation.PostConstruct;
import monodaipoly.dao.CasillaDAO;
import monodaipoly.persistencia.Casilla;
import monodaipoly.persistencia.Jugador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

@Service
public class CasillaServicioImpl implements CasillaServicio{
    
   private CasillaDAO casillaDAO;
   
    @Autowired
    @Required
    public void setCasillaDAO(CasillaDAO casillaDAO){
        this.casillaDAO=casillaDAO;
    }
    
    
    public void crear(Casilla casilla) {
        casillaDAO.insert(casilla);
    }

   
    public void buscar(int idCasilla) {
        casillaDAO.find(Casilla.class, idCasilla);
    }

 
    public void borrar(Casilla casilla) {
        casillaDAO.remove(casilla);
    }

    @Override
    @PostConstruct
    public void preload_casillas() {
        casillaDAO.removeAll(Casilla.class);
        Casilla casilla0 = new Casilla();
        casilla0.setIdCasilla(0);
        casilla0.setNombre("Salida");
        casilla0.setTipoCasilla(null);
        casillaDAO.insert(casilla0)
        Casilla casilla1 = new Casilla();
        casilla1.setIdCasilla(0);
        casilla1.setNombre("Salida");
        casilla1.setTipoCasilla(null);
        Casilla casilla2 = new Casilla();
        Casilla casilla3 = new Casilla();
        Casilla casilla4 = new Casilla();
        Casilla casilla5 = new Casilla();


     }
    
}
