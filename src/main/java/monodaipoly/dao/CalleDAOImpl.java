/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package monodaipoly.dao;

import com.google.appengine.api.datastore.Key;
import monodaipoly.persistencia.Calle;
import org.springframework.stereotype.Repository;

/**
 *
 * @author instalador
 */
@Repository
public class CalleDAOImpl  extends GenericDAOImpl <Calle,Key> implements CalleDAO{

}
