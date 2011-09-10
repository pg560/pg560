package no.nith.pg560.interfaces.web;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import no.nith.pg560.application.UserServiceBean;
import no.nith.pg560.common.Pg560PageNavigation;
import no.nith.pg560.domain.Users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
@SessionScoped
@SuppressWarnings("serial")
public class LoginBackingBean implements Serializable {
	private static final long serialVersionUID = 7965455427888195913L;
	private Logger logger = LoggerFactory.getLogger(LoginBackingBean.class);

	private String username;
	private String password;

	@Inject
	private UserServiceBean userService;

	private Users currentUser;

	public String login() {
		try {
			List<Users> users = userService.getUsers(getUsername(), getPassword());
			if (users.size() == 0) {
				logger.info("No users found, returning to login page");
				return Pg560PageNavigation.LOGIN_PAGE;
			}
			else {
				logger.info("User found, going to main page");
				return Pg560PageNavigation.MAIN_PAGE;
			}
		} catch (Exception e) {
			// e.printStackTrace();
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

	/*
	@Produces
	@LoggedIn
	public Users getCurrentUser() {
		return currentUser;
	}
	*/

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