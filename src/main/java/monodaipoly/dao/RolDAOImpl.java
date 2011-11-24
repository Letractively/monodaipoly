package monodaipoly.dao;

import com.google.appengine.api.datastore.Key;
import monodaipoly.persistencia.Rol;
import org.springframework.stereotype.Repository;

@Repository
public class RolDAOImpl extends GenericDAOImpl<Rol, Key> implements RolDAO {

}
