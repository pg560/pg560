package no.nith.pg560.interfaces.web;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import no.nith.pg560.application.UserServiceBean;
import no.nith.pg560.domain.Users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
@ConversationScoped
public class LoginBackingBean implements Serializable {
	private static final long serialVersionUID = 7965455427888195913L;
	private Logger logger = LoggerFactory.getLogger(LoginBackingBean.class);

	private String username;
	private String password;
	
    @Inject
    private Conversation conversation; 	

	@Inject
	private UserServiceBean userService;

	private Users currentUser;

	public void login() {
		startConversation();
		List<Users> results = null;
		try {
			results = userService.getUsers(getUsername(), getPassword());
		} catch (Exception e) {
			// e.printStackTrace();
			logger.error("Feil i kall mot UserService", e);
		}

		if (!results.isEmpty()) {
			currentUser = results.get(0);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Welcome, " + currentUser.getName()));
		} else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Feil i paalogging"));
		}
	}

	private void endConversation() {
		if (!conversation.isTransient()) {
			conversation.end();
		}
	}

	private void startConversation() {
		endConversation();
		conversation.begin();
	}

	public void logout() {
		endConversation();
		currentUser = null;
	}

	public boolean isLoggedIn() {		
		return currentUser != null;
	}

	@Produces
	@LoggedIn
	public Users getCurrentUser() {
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