package monodaipoly.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO <T,PK extends Serializable> extends Serializable{

	public void insert(T object);
	public void update(T object);
	public void remove(T object);
	public void remove(Class<T> typeClass,PK clave);
	public T find(Class<T> typeClass, PK clave);
	List <T> getAll(Class<T> typeClass);
        public int removeAll(Class<T> typeClass);

	
}
