package monodaipoly.dao;

import java.util.List;
import javax.persistence.Query;
import monodaipoly.persistencia.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAOImpl extends GenericDAOImpl <Usuario, String> implements UsuarioDAO{
    
    @Override
    public List<Usuario> conseguirUsuarios(){
        Query query=em.createQuery("SELECT u FROM Usuario u ORDER BY u.partidasGanadas DESC");
        return (List<Usuario>)query.getResultList();
    }
}
