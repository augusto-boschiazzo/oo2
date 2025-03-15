package ar.edu.unlp.info.oo2.repaso_ej1;

import java.util.List;
import java.util.LinkedList;

public class Twitter {
	
	private List<Usuario> usuarios;
	
	public Twitter() {
		usuarios = new LinkedList<Usuario>();
	}
	
	public void createUsuario(String screenName) {
		if (this.usuarios.stream().map(u -> u.getScreenName()).filter(n -> n == screenName).count() == 0) {
			Usuario usuario = new Usuario(screenName);
			this.usuarios.add(usuario);
		}
	}
	

}
