package br.eti.amazu.infra.view.bean;

import java.io.Serializable;
import java.util.Locale;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.eti.amazu.infra.util.FacesUtil;

@Named
@SessionScoped
public class UserSessionInBean implements Serializable {

	private static final long serialVersionUID = -2765013996497920460L;
	
	//Dados de usuario comum do sistema	
	private String pessoaLogged;
	private String userLogged;	
	private boolean usuarioDevLogado;		
	private Locale locale;
	
	public UserSessionInBean(){		
		this.setLocale(FacesUtil.getLocale()); //Obtem o idioma local.	
	}
   
	/* Um botao de acao de uma pagina qualquer pode mudar o idioma,
	 * mas tem de passar um parametro para qual idioma a ser mudado.
	 *------------------------------------------------------------*/
	public void mudarIdioma(){
		String localeStr = FacesUtil.getParam("locale");

		if(localeStr.length() == 2){
			locale = new Locale(localeStr);
			
		}else{			
			locale = new Locale(localeStr.substring(0,2), localeStr.substring(3));
		}

		FacesUtil.setLocale(locale);		
		System.out.println(FacesUtil.getMessage("MGL064",new String[]{localeStr}));
	}
	
	/* *********************
	 * Licao 13 em diante...
	 * Utiliza o componente IdleMonitor do PrimeFaces para invalidar a sess�o. 
	 ***********************/
	public void idleListener() {				
		FacesUtil.redirect  ("/pages/errorpages/viewExpired");
		FacesUtil.sessionInvalidate();			
	}
	
	/*--------
	 * get/set
	 --------*/
	public String getPessoaLogged() {
		return pessoaLogged;
	}

	public void setPessoaLogged(String pessoaLogged) {
		this.pessoaLogged = pessoaLogged;
	}

	public String getUserLogged() {
		return userLogged;
	}

	public void setUserLogged(String userLogged) {
		this.userLogged = userLogged;
	}

	public boolean isUsuarioDevLogado() {
		return usuarioDevLogado;
	}

	public void setUsuarioDevLogado(boolean usuarioDevLogado) {
		this.usuarioDevLogado = usuarioDevLogado;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
	}
		
}
