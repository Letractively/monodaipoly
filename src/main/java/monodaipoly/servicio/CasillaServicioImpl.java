
package monodaipoly.servicio;

import com.google.appengine.api.datastore.Key;
import javax.annotation.PostConstruct;
import monodaipoly.dao.CalleDAO;
import monodaipoly.dao.CasillaDAO;
import monodaipoly.persistencia.Calle;
import monodaipoly.persistencia.Casilla;
import monodaipoly.persistencia.Jugador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;

@Service
public class CasillaServicioImpl implements CasillaServicio{
    
   private CasillaDAO casillaDAO;
   private CalleDAO calleDAO;
   
    @Autowired
    @Required
    public void setCasillaDAO(CasillaDAO casillaDAO){
        this.casillaDAO=casillaDAO;
    }
    @Autowired
    @Required
    public void setCalleDAO(CalleDAO calleDAO){
        this.calleDAO=calleDAO;
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
        casillaDAO.insert(casilla0);

        Casilla casilla1 = new Casilla();
        Calle calle1=new Calle();
        calle1.setColor("gris");
        calle1.setMulta(50);
        calle1.setPrecio(150);
        casilla1.setTipoCasilla(calle1.getIdCalle());
        casilla1.setIdCasilla(1);
        casilla1.setNombre("Windows 95");
        calleDAO.insert(calle1);
        casillaDAO.insert(casilla1);

        Casilla casilla2 = new Casilla();
        Calle calle2=new Calle();
        calle2.setColor("gris");
        calle2.setMulta(50);
        calle2.setPrecio(150);
        casilla2.setTipoCasilla(calle2.getIdCalle());
        casilla2.setIdCasilla(2);
        casilla2.setNombre("Windows 98");
        calleDAO.insert(calle2);
        casillaDAO.insert(casilla2);

        Casilla casilla3=new Casilla();
        casilla3.setIdCasilla(3);
        casilla3.setNombre("Suerte");
        casilla3.setTipoCasilla(null);
         casillaDAO.insert(casilla3);

        Casilla casilla4 = new Casilla();
        Calle calle4=new Calle();
        calle4.setColor("gris");
        calle4.setMulta(50);
        calle4.setPrecio(150);
        casilla4.setTipoCasilla(calle4.getIdCalle());
        casilla4.setIdCasilla(4);
        casilla4.setNombre("Windows 2000");
        calleDAO.insert(calle4);
        casillaDAO.insert(casilla4);

        Casilla casilla5=new Casilla();
        casilla5.setIdCasilla(5);
        casilla5.setNombre("Estacion");
        casilla5.setTipoCasilla(null);
         casillaDAO.insert(casilla5);

        Casilla casilla6 = new Casilla();
        Calle calle6=new Calle();
        calle6.setColor("azul");
        calle6.setMulta(100);
        calle6.setPrecio(300);
        casilla6.setTipoCasilla(calle6.getIdCalle());
        casilla6.setIdCasilla(6);
        casilla6.setNombre("Windows XP");
        calleDAO.insert(calle6);
        casillaDAO.insert(casilla6);

        Casilla casilla7 = new Casilla();
        Calle calle7=new Calle();
        calle7.setColor("azul");
        calle7.setMulta(100);
        calle7.setPrecio(300);
        casilla7.setTipoCasilla(calle7.getIdCalle());
        casilla7.setIdCasilla(7);
        casilla7.setNombre("Windows Vista");
        calleDAO.insert(calle7);
        casillaDAO.insert(casilla7);

        Casilla casilla8 = new Casilla();
        Calle calle8=new Calle();
        calle8.setColor("azul");
        calle8.setMulta(100);
        calle8.setPrecio(300);
        casilla8.setTipoCasilla(calle8.getIdCalle());
        casilla8.setIdCasilla(8);
        casilla8.setNombre("Windows Seven");
        calleDAO.insert(calle8);
        casillaDAO.insert(casilla8);

        Casilla casilla9=new Casilla();
        casilla9.setIdCasilla(9);
        casilla9.setNombre("Solo visitas");
        casilla9.setTipoCasilla(null);
         casillaDAO.insert(casilla9);


     }
    
}
