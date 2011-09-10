package no.nith.pg560.application;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;

import no.nith.pg560.domain.Users;
import no.nith.pg560.infrastructure.UserJpaRepository;

@Stateless
public class UserServiceBean {
	@Inject
	private UserJpaRepository userRepository;	

	public List<Users> getUsers() {
		return userRepository.getUsers();
	}

	public List<Users> getUsers(String username, String password) {
		return userRepository.getUsers(username, password);
	}
}