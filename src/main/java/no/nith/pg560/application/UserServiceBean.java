package no.nith.pg560.application;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import no.nith.pg560.domain.User;
import no.nith.pg560.infrastructure.UserJpaRepository;

@Stateless
public class UserServiceBean {
	@Inject
	private UserJpaRepository userRepository;	

	public User getUser(String username, String password) {
		return userRepository.getUser(username, password);
	}
}