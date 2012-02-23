
package monodaipoly.servicio;

import com.google.appengine.api.datastore.Key;
import java.util.List;
import javax.annotation.PostConstruct;
import monodaipoly.dao.CalleDAO;
import monodaipoly.dao.CasillaDAO;
import monodaipoly.dao.UsuarioDAO;
import monodaipoly.persistencia.Calle;
import monodaipoly.persistencia.Casilla;
import monodaipoly.persistencia.Jugador;
import monodaipoly.persistencia.Usuario;
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
    
    
    @Override
    public void crear(Casilla casilla) {
        casillaDAO.insert(casilla);
    }

   
    @Override
    public void buscar(Key idCasilla) {
        casillaDAO.find(Casilla.class, idCasilla);
    }

 
    @Override
    public void borrar(Casilla casilla) {
        casillaDAO.remove(casilla);
    }

    @Override
    @PostConstruct
    public void preload_casillas() {
        casillaDAO.removeAll(Casilla.class);
        calleDAO.removeAll(Calle.class);

        Casilla casilla0 = new Casilla();
        casilla0.setNumeroCasilla(0);
        casilla0.setNombre("Salida");
        casilla0.setTipoCasilla(null);
        casillaDAO.insert(casilla0);

        Casilla casilla1 = new Casilla();
        Calle calle1=new Calle();
        calle1.setColor("gris");
        calle1.setMulta(50);
        calle1.setPrecio(150);
        casilla1.setNumeroCasilla(1);
        casilla1.setNombre("Windows 95");
        calleDAO.insert(calle1);
         casilla1.setTipoCasilla(calle1.getIdCalle());
        casillaDAO.insert(casilla1);

        Casilla casilla2 = new Casilla();
        Calle calle2=new Calle();
        calle2.setColor("gris");
        calle2.setMulta(50);
        calle2.setPrecio(150);      
        casilla2.setNumeroCasilla(2);
        casilla2.setNombre("Windows 98");
        calleDAO.insert(calle2);
        casilla2.setTipoCasilla(calle2.getIdCalle());
        casillaDAO.insert(casilla2);

        Casilla casilla3=new Casilla();
        casilla3.setNumeroCasilla(3);
        casilla3.setNombre("Suerte");
        casilla3.setTipoCasilla(null);
         casillaDAO.insert(casilla3);

        Casilla casilla4 = new Casilla();
        Calle calle4=new Calle();
        calle4.setColor("gris");
        calle4.setMulta(50);
        calle4.setPrecio(150);
        
        casilla4.setNumeroCasilla(4);
        casilla4.setNombre("Windows 2000");
        calleDAO.insert(calle4);
        casilla4.setTipoCasilla(calle4.getIdCalle());
        casillaDAO.insert(casilla4);

        Casilla casilla5=new Casilla();
        casilla5.setNumeroCasilla(5);
        casilla5.setNombre("Estacion");
        casilla5.setTipoCasilla(null);
         casillaDAO.insert(casilla5);

        Casilla casilla6 = new Casilla();
        Calle calle6=new Calle();
        calle6.setColor("azul");
        calle6.setMulta(100);
        calle6.setPrecio(300);
        casilla6.setNumeroCasilla(6);
        casilla6.setNombre("Windows XP");
        calleDAO.insert(calle6);
         casilla6.setTipoCasilla(calle6.getIdCalle());
        casillaDAO.insert(casilla6);

        Casilla casilla7 = new Casilla();
        Calle calle7=new Calle();
        calle7.setColor("azul");
        calle7.setMulta(100);
        calle7.setPrecio(300);
        
        casilla7.setNumeroCasilla(7);
        casilla7.setNombre("Windows Vista");
        calleDAO.insert(calle7);
        casilla7.setTipoCasilla(calle7.getIdCalle());
        casillaDAO.insert(casilla7);

        Casilla casilla8 = new Casilla();
        Calle calle8=new Calle();
        calle8.setColor("azul");
        calle8.setMulta(100);
        calle8.setPrecio(300);
       
        casilla8.setNumeroCasilla(8);
        casilla8.setNombre("Windows Seven");
        calleDAO.insert(calle8);
         casilla8.setTipoCasilla(calle8.getIdCalle());
        casillaDAO.insert(casilla8);

        Casilla casilla9=new Casilla();
        casilla9.setNumeroCasilla(9);
        casilla9.setNombre("Solo visitas");
        casilla9.setTipoCasilla(null);
         casillaDAO.insert(casilla9);

          Casilla casilla10 = new Casilla();
        casilla10.setNumeroCasilla(10);
        casilla10.setNombre("Xubuntu");
        Calle calle10=new Calle();
        calle10.setColor("Rosa");
        calle10.setPrecio(450);
        calle10.setMulta(150);
        calleDAO.insert(calle10);
        casilla10.setTipoCasilla(calle10.getIdCalle());
        casillaDAO.insert(casilla10);

        Casilla casilla11 = new Casilla();
        casilla11.setNumeroCasilla(11);
        casilla11.setNombre("Ubuntu");
        Calle calle11=new Calle();
        calle11.setColor("Rosa");
        calle11.setPrecio(450);
        calle11.setMulta(150);
        calleDAO.insert(calle11);
        casilla11.setTipoCasilla(calle11.getIdCalle());
        casillaDAO.insert(casilla11);

        Casilla casilla12 = new Casilla();
        casilla12.setNumeroCasilla(12);
        casilla12.setNombre("Hidroelectrica");
        casilla12.setTipoCasilla(null);
        casillaDAO.insert(casilla12);

        Casilla casilla13 = new Casilla();
        casilla13.setNumeroCasilla(13);
        casilla13.setNombre("Estacion");
        casilla13.setTipoCasilla(null);
        casillaDAO.insert(casilla13);

        Casilla casilla14 = new Casilla();
        casilla14.setNumeroCasilla(14);
        casilla14.setNombre("Suse");
        Calle calle14=new Calle();
        calle14.setColor("Naranja");
        calle14.setPrecio(600);
        calle14.setMulta(200);
        calleDAO.insert(calle14);
        casilla14.setTipoCasilla(calle14.getIdCalle());
        casillaDAO.insert(casilla14);

        Casilla casilla15 = new Casilla();
        casilla15.setNumeroCasilla(15);
        casilla15.setNombre("RedHat");
        Calle calle15=new Calle();
        calle15.setColor("Naranja");
        calle15.setPrecio(600);
        calle15.setMulta(200);
        calleDAO.insert(calle15);
        casilla15.setTipoCasilla(calle15.getIdCalle());
        casillaDAO.insert(casilla15);

        Casilla casilla16 = new Casilla();
        casilla16.setNumeroCasilla(16);
        casilla16.setNombre("Suerte");
        casilla16.setTipoCasilla(null);
        casillaDAO.insert(casilla16);

        Casilla casilla17 = new Casilla();
        casilla17.setNumeroCasilla(17);
        casilla17.setNombre("Debian");
        Calle calle17=new Calle();
        calle17.setColor("Naranja");
        calle17.setPrecio(600);
        calle17.setMulta(200);
        calleDAO.insert(calle17);
        casilla17.setTipoCasilla(calle17.getIdCalle());
        casillaDAO.insert(casilla17);

        Casilla casilla18 = new Casilla();
        casilla18.setNumeroCasilla(18);
        casilla18.setNombre("Free");
        casilla18.setTipoCasilla(null);
        casillaDAO.insert(casilla18);

        Casilla casilla19=new Casilla();
        casilla19.setNombre("iTunes");
        casilla19.setNumeroCasilla(19);
        Calle calle19=new Calle();
        calle19.setColor("Rojo");
        calle19.setMulta(250);
        calle19.setPrecio(750);
        calleDAO.insert(calle19);
        casilla19.setTipoCasilla(calle19.getIdCalle());
        casillaDAO.insert(casilla19);

        Casilla casilla20=new Casilla();
        casilla20.setNombre("iMac");
        casilla20.setNumeroCasilla(20);
        Calle calle20=new Calle();
        calle20.setColor("Rojo");
        calle20.setMulta(250);
        calle20.setPrecio(750);
        calleDAO.insert(calle20);
        casilla20.setTipoCasilla(calle20.getIdCalle());
        casillaDAO.insert(casilla20);

        Casilla casilla21 = new Casilla();
        casilla21.setNumeroCasilla(21);
        casilla21.setNombre("Suerte");
        casilla21.setTipoCasilla(null);
        casillaDAO.insert(casilla21);

        Casilla casilla22=new Casilla();
        casilla22.setNombre("Mac Book Pro");
        casilla22.setNumeroCasilla(22);
        Calle calle22=new Calle();
        calle22.setColor("Rojo");
        calle22.setMulta(250);
        calle22.setPrecio(750);
        calleDAO.insert(calle22);
        casilla22.setTipoCasilla(calle22.getIdCalle());
        casillaDAO.insert(casilla22);

        Casilla casilla23 = new Casilla();
        casilla23.setNumeroCasilla(23);
        casilla23.setNombre("Estacion");
        casilla23.setTipoCasilla(null);
        casillaDAO.insert(casilla23);

        Casilla casilla24=new Casilla();
        casilla24.setNombre("iPod");
        casilla24.setNumeroCasilla(24);
        Calle calle24=new Calle();
        calle24.setColor("Amarillo");
        calle24.setMulta(300);
        calle24.setPrecio(900);
        calleDAO.insert(calle24);
        casilla24.setTipoCasilla(calle24.getIdCalle());
        casillaDAO.insert(casilla24);

        Casilla casilla25=new Casilla();
        casilla25.setNombre("iPad");
        casilla25.setNumeroCasilla(25);
        Calle calle25=new Calle();
        calle25.setColor("Amarillo");
        calle25.setMulta(300);
        calle25.setPrecio(900);
        calleDAO.insert(calle25);
        casilla25.setTipoCasilla(calle25.getIdCalle());
        casillaDAO.insert(casilla25);

        Casilla casilla26=new Casilla();
        casilla26.setNombre("iPhone");
        casilla26.setNumeroCasilla(26);
        Calle calle26=new Calle();
        calle26.setColor("Amarillo");
        calle26.setMulta(300);
        calle26.setPrecio(900);
        calleDAO.insert(calle26);
        casilla26.setTipoCasilla(calle26.getIdCalle());
        casillaDAO.insert(casilla26);

        Casilla casilla27 = new Casilla();
        casilla27.setNumeroCasilla(27);
        casilla27.setNombre("Carcel");
        casilla27.setTipoCasilla(null);
        casillaDAO.insert(casilla27);

        Casilla casilla28=new Casilla();
        casilla28.setNombre("Mac OS X Tiger");
        casilla28.setNumeroCasilla(28);
        Calle calle28=new Calle();
        calle28.setColor("Verde");
        calle28.setMulta(350);
        calle28.setPrecio(1050);
        calleDAO.insert(calle28);
        casilla28.setTipoCasilla(calle28.getIdCalle());
        casillaDAO.insert(casilla28);

        Casilla casilla29=new Casilla();
        casilla29.setNombre("Mac OS X Leopard");
        casilla29.setNumeroCasilla(29);
        Calle calle29=new Calle();
        calle29.setColor("Verde");
        calle29.setMulta(350);
        calle29.setPrecio(1050);
        calleDAO.insert(calle29);
        casilla29.setTipoCasilla(calle29.getIdCalle());
        casillaDAO.insert(casilla29);

        Casilla casilla30 = new Casilla();
        casilla30.setNumeroCasilla(30);
        casilla30.setNombre("Hidroelectrica");
        casilla30.setTipoCasilla(null);
        casillaDAO.insert(casilla30);

        Casilla casilla31=new Casilla();
        casilla31.setNombre("Mac OS X Lion");
        casilla31.setNumeroCasilla(31);
        Calle calle31=new Calle();
        calle31.setColor("Verde");
        calle31.setMulta(350);
        calle31.setPrecio(1050);
        calleDAO.insert(calle31);
        casilla31.setTipoCasilla(calle31.getIdCalle());
        casillaDAO.insert(casilla31);

        Casilla casilla32 = new Casilla();
        casilla32.setNumeroCasilla(32);
        casilla32.setNombre("Estacion");
        casilla32.setTipoCasilla(null);
        casillaDAO.insert(casilla32);

        Casilla casilla33=new Casilla();
        casilla33.setNombre("32 bits");
        casilla33.setNumeroCasilla(33);
        Calle calle33=new Calle();
        calle33.setColor("Azul Oscuro");
        calle33.setMulta(400);
        calle33.setPrecio(1200);
        calleDAO.insert(calle33);
        casilla33.setTipoCasilla(calle33.getIdCalle());
        casillaDAO.insert(casilla33);

        Casilla casilla34 = new Casilla();
        casilla34.setNumeroCasilla(34);
        casilla34.setNombre("PAGA");
        casilla34.setTipoCasilla(null);
        casillaDAO.insert(casilla34);

        Casilla casilla35=new Casilla();
        casilla35.setNombre("64 bits");
        casilla35.setNumeroCasilla(35);
        Calle calle35=new Calle();
        calle35.setColor("Azul Oscuro");
        calle35.setMulta(400);
        calle35.setPrecio(1200);
        calleDAO.insert(calle35);
        casilla35.setTipoCasilla(calle35.getIdCalle());
        casillaDAO.insert(casilla35);

     }

    @Override
    public List<Casilla> getAll() {
        return casillaDAO.getAll(Casilla.class);
    }

    @Override
    public Casilla buscarPorNumero(int posicion){
        return casillaDAO.buscarPorNumero(posicion);
    }

    @Override
    public Casilla buscarPorCalle(Key idCalle){
        return casillaDAO.buscarPorCalle(idCalle);
    }
    
}
