package no.nith.pg560.application;

import javax.ejb.Stateless;
import javax.inject.Inject;

import no.nith.pg560.domain.User;
import no.nith.pg560.infrastructure.UserJpaRepository;

/**
 * UserServiceBean exposes functionality for checking if a user exist in the database. 
 */
@Stateless
public class UserServiceBean {
	@Inject
	private UserJpaRepository userRepository;	

	public User getUser(String username) {
		return userRepository.getUser(username);
	}
}