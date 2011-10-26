package no.nith.pg560.interfaces.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import no.nith.pg560.application.UserServiceBean;
import no.nith.pg560.common.Pg560PageNavigation;
import no.nith.pg560.domain.User;

import org.apache.log4j.Logger;

/**
 * This backingbean shows Userdetails
 */
@Named
@SessionScoped
public class UserBackingBean implements Serializable {
	private static final long serialVersionUID = 7965455427888195913L;
	private Logger logger = Logger.getLogger(UserBackingBean.class);

	@Inject
	private UserServiceBean userService;
	
	private User currentUser;
	private String username;	

	public String retrieveUser() {
		try {
			username = FacesContext.getCurrentInstance().getExternalContext().getRemoteUser();
			logger.info("User logged in: " + username);
			currentUser = userService.getUser(username);

			if (currentUser == null) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Feil ved kall mot database"));
			} else {
				logger.info("User information found on user" + username);
			}

		} catch (Exception e) {
			logger.error("Feil i kall mot UserService", e);
		}
		return Pg560PageNavigation.INDEX_PAGE;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}