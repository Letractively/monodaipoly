package monodaipoly.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class GenericDAOImpl <T, PK extends Serializable> implements GenericDAO<T, PK>{
	
	@PersistenceContext
         EntityManager em;


	@Override
	public void insert(T object) {
		em.persist(object);
		
	}

	@Override
	public void update(T object) {
		em.merge(object);
		
	}

	@Override
	public void remove(T object) {
		em.remove(object);
		
	}

	@Override
	public void remove(Class<T> typeClass, PK clave) {
		T object = em.find(typeClass, clave);
		em.remove(em.merge(object));
		
	}

	@Override
	public T find(Class<T> typeClass, PK clave) {
		
		return em.find(typeClass, clave);
	}

	@Override
	public List<T> getAll(Class<T> typeClass) {
		final StringBuilder sql=new StringBuilder("select c from").append(typeClass.getSimpleName()).append(" c");
		return (List<T>) em.createQuery(sql.toString()).getResultList();
	}
        
        
    @Override
    public int removeAll(Class<T> typeClass) {
        String sql = "DELETE FROM " + typeClass.getSimpleName() + " c";
        return em.createQuery(sql).executeUpdate();
    }

	

}
