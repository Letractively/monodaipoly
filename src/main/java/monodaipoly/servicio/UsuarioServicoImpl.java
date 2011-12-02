/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package monodaipoly.servicio;

import com.google.appengine.api.datastore.Key;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import monodaipoly.dao.*;
import monodaipoly.dao.UsuarioDAO;
import monodaipoly.persistencia.Rol;
import monodaipoly.persistencia.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.dao.DataAccessException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;


import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author adler
 */
@Service("usuarioServicio")
public class UsuarioServicoImpl implements UsuarioServicio{
    private UsuarioDAO usuarioDAO;
    private RolDAO rolDAO;

    @Autowired
    @Required
    public void setUsuarioDAO(UsuarioDAO usuarioDAO){
        this.usuarioDAO=usuarioDAO;
    }

    @Autowired
    @Required
    public void setRolDAO(RolDAO rolDAO){
        this.rolDAO=rolDAO;
    }

    public Usuario getUsuario(String idUsuario) {
        return usuarioDAO.find(Usuario.class, idUsuario);
    }

    public Collection<Rol> getRoles(Usuario usuario) {
        Collection<Rol> roles = new ArrayList<Rol>();
        for (Key keyRol : usuario.getRoles()) {
            Rol rol = rolDAO.find(Rol.class, keyRol);
            roles.add(rol);
        }
        return roles;
    }


    @Override
    public void crear(Usuario usuario) {
        usuarioDAO.insert(usuario);
    }

    @Override
    public Usuario buscar(String nick) {
        return usuarioDAO.find(Usuario.class, nick);
    }

    @Override
    public void borrar(Usuario usuario) {
        usuarioDAO.remove(usuario);
    }
    @Override
    public void borrarPorClave(String nick) {
        usuarioDAO.remove(Usuario.class, nick);
    }


    @Override
    public void actualizar(Usuario usuario) {
        usuarioDAO.update(usuario);
    }

    @Override
    @PostConstruct
    public void preload_usuarios() {

        rolDAO.removeAll(Rol.class);
        Rol r1 = new Rol();
        r1.setNombre("ROLE_ADMIN");
        Rol r2 = new Rol();
        r2.setNombre("ROLE_USER");
        rolDAO.insert(r1);
        rolDAO.insert(r2);
        
        
        
        Usuario u1=new Usuario();
        Usuario u2=new Usuario();
        Usuario u3=new Usuario();
        Usuario u4=new Usuario();
        Usuario u5=new Usuario();
        Usuario u6=new Usuario();
        Usuario u7=new Usuario();
        u2.setNick("user2");
        u2.setNombre("Pepe");
        u2.setContrasena("123456");
        u3.setNick("user3");
        u3.setNombre("Pepe");
        u3.setContrasena("123456");
        u4.setNick("user4");
        u4.setNombre("Pepe");
        u4.setContrasena("123456");
        u5.setNick("user5");
        u5.setNombre("Pepe");
        u5.setContrasena("123456");
        u6.setNick("user6");
        u6.setNombre("Pepe");
        u6.setContrasena("123456");
        u7.setNick("user7");
        u7.setNombre("Pepe");
        u7.setContrasena("123456");
        this.anadirRol(u2, r1);
        this.anadirRol(u3, r1);
        this.anadirRol(u4, r1);
        this.anadirRol(u5, r1);
        this.anadirRol(u6, r1);
        this.anadirRol(u7, r1);
        this.crear(u2);
        this.crear(u3);
        this.crear(u4);
        this.crear(u5);
        this.crear(u6);
        this.crear(u7);
              
        
        
        u1.setNick("admin");
        u1.setNombre("Pepe");
        u1.setContrasena("123456");
       
        
       
        this.anadirRol(u1, r1);
        this.crear(u1);
    }

    @Override
    public void anadirRol(Usuario usuario, Rol rol) {
        
        System.out.println(rol.getId());
        usuario.getRoles().add(rol.getId());
    }

    @Override
    public Rol buscarRol(String nombre){
        return rolDAO.buscarRol(nombre);
    }

    @Override
    public Usuario getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.isAuthenticated()) {
            String username = authentication.getName();
            return getUsuario(username);
        } else {
            return null;
        }
    }

    @PreAuthorize("isAuthenticated()")
    public boolean isAdmin() {
        final String ROLE_ADMIN = "ROLE_ADMIN";
        boolean isAdmin = false;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth != null) {
            User user = ((User) auth.getPrincipal());
            Collection<GrantedAuthority> roles = user.getAuthorities();
            if (roles != null) {
                Iterator<GrantedAuthority> it = roles.iterator();
                while (!isAdmin && it.hasNext()) {
                    GrantedAuthority rol = it.next();
                    if (rol.getAuthority().equals(ROLE_ADMIN)) {
                        isAdmin = true;
                    }
                }
            }
        }
        return isAdmin;
    }

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException, DataAccessException {
        System.out.println("AQUI UsuarioServiceImpl loadUserByUsername usuarioDao="+usuarioDAO);
        Usuario usuario = usuarioDAO.find(Usuario.class, nombreUsuario);
        if (usuario == null) {
            throw new UsernameNotFoundException("User not found: " + nombreUsuario);
        } else {
            return makeUser(usuario);
        }
    }
    
    private org.springframework.security.core.userdetails.User makeUser(Usuario usuario) {
        return new org.springframework.security.core.userdetails.User(usuario.getNick(), usuario.getContrasena(),
                true, true, true, true, makeGrantedAuthorities(usuario));
    }
    
    private Collection<GrantedAuthority> makeGrantedAuthorities(Usuario usuario) {
        Collection<GrantedAuthority> result = new ArrayList<GrantedAuthority>();
        int i = 0;
        for (Rol rol : getRoles(usuario)) {
            result.add(new GrantedAuthorityImpl(rol.getNombre()));
        }
        return result;
    }
  
    @Override
    public List conseguirUsuarios(){
       return usuarioDAO.getAll(Usuario.class);
    }


}
