package no.nith.pg560.infrastructure;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import no.nith.pg560.common.CommonRepository;
import no.nith.pg560.domain.User;

/**
 * Repository class handling integration with the PG560_User database. 
 */
public class UserJpaRepository extends CommonRepository<User> {
	private Logger logger = LoggerFactory.getLogger(UserJpaRepository.class);	

	@PersistenceContext
	private EntityManager em;

	public UserJpaRepository() {
		super(User.class);
	}

	public UserJpaRepository(EntityManager em) {
		super(User.class, em);
	}

	public User getUser(String username, String password) {
		TypedQuery<User> query = getEntityManager()
				.createQuery(
						"select u from User u where u.username=:username and u.password=:password",
						User.class);
		query.setParameter("username", username);
		query.setParameter("password", password);
		User user = null;
		try {
			user = query.getSingleResult();
		} catch (NoResultException e) {
			logger.info("Fikk ingen treff");
		}

		return user;
	}

}