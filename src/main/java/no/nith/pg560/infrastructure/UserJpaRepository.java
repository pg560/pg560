package no.nith.pg560.infrastructure;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import no.nith.pg560.common.CommonRepository;
import no.nith.pg560.domain.Users;

public class UserJpaRepository extends CommonRepository<Users> {

	@PersistenceContext
	private EntityManager em;

	public UserJpaRepository() {
		super(Users.class);
	}

	public UserJpaRepository(EntityManager em) {
		super(Users.class, em);
	}

	@SuppressWarnings("unchecked")
	public List<Users> getUsers() {
		return getEntityManager().createQuery("select u from Users u")
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Users> getUsers(String username, String password) {
		return getEntityManager()
				.createQuery(
						"select u from Users u where u.username=:username and u.password=:password")
				.setParameter("username", username)
				.setParameter("password", password).getResultList();
	}

}