package monodaipoly.dao;

import com.google.appengine.api.datastore.Key;
import javax.persistence.Query;

import monodaipoly.persistencia.Rol;
import org.springframework.stereotype.Repository;

@Repository
public class RolDAOImpl extends GenericDAOImpl<Rol, Key> implements RolDAO {


    @Override
    public Rol buscarRol(String nombre){

       Query query=em.createQuery("SELECT r FROM Rol r WHERE r.nombre = :nombre");
       query.setParameter("nombre",nombre);
       return (Rol)query.getSingleResult();

    }
    @Override
    public int removeAll(Class<Rol> typeClass) {
        String sql = "DELETE FROM " + typeClass.getSimpleName() + " c";
        return em.createQuery(sql).executeUpdate();
    }
}
