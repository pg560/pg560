package no.nith.pg560.interfaces.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import no.nith.pg560.application.UserServiceBean;
import no.nith.pg560.common.Pg560PageNavigation;
import no.nith.pg560.domain.User;

import org.apache.log4j.Logger; 

/**
 * This backingbean handles logon/logout. 
 */
@Named
@SessionScoped
public class LoginBackingBean implements Serializable {
	private static final long serialVersionUID = 7965455427888195913L;
	private Logger logger = Logger.getLogger(LoginBackingBean.class);

	private String username;
	private String password;

	@Inject
	private UserServiceBean userService;

	private User currentUser;

	public String login() {
		try {
			currentUser = userService.getUser(getUsername(), getPassword());
			if (currentUser == null) {
				logger.info("No users found, returning to login page" + username);
				FacesContext.getCurrentInstance().addMessage(null,	new FacesMessage("Feil ved paalogging")); 				
				return Pg560PageNavigation.LOGIN_PAGE;
			}
			else {
				logger.info("User found, going to main page");
				return Pg560PageNavigation.MAIN_PAGE;
			}
		} catch (Exception e) {
			logger.error("Feil i kall mot UserService", e);
            return Pg560PageNavigation.LOGIN_PAGE;
		}
	}
	
    public String hovedSide() {
        return Pg560PageNavigation.MAIN_PAGE;
    }	

	public String logout() {
		currentUser = null;
		return Pg560PageNavigation.LOGIN_PAGE;
	}

	public boolean isLoggedIn() {		
		return currentUser != null;
	}

	@Produces
	@LoggedIn
	public User getCurrentUser() {
		return currentUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}