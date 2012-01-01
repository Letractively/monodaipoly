package monodaipoly.dao;

import com.google.appengine.api.datastore.Key;
import java.util.List;
import monodaipoly.persistencia.Calle;
import org.springframework.stereotype.Repository;
import javax.persistence.Query;


@Repository
public class CalleDAOImpl  extends GenericDAOImpl <Calle,Key> implements CalleDAO{

    @Override
    public List<Calle> buscarPorColor(String color) {
        Query query=em.createQuery("SELECT c FROM Calle c WHERE c.color = :color");
       query.setParameter("color",color);
       return (List<Calle>) query.getResultList();
    }

}
