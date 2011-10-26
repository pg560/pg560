package no.nith.pg560.infrastructure;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger; 

import no.nith.pg560.common.CommonRepository;
import no.nith.pg560.domain.User;

/**
 * Repository class handling integration with the PG560_User database. 
 */
public class UserJpaRepository extends CommonRepository<User> {
	private Logger logger = Logger.getLogger(UserJpaRepository.class); 


	@PersistenceContext
	private EntityManager em;

	public UserJpaRepository() {
		super(User.class);
	}

	public UserJpaRepository(EntityManager em) {
		super(User.class, em);
	}

	public User getUser(String username) {
		TypedQuery<User> query = getEntityManager()
				.createQuery(
						"select u from User u where u.username=:username",
						User.class);
		query.setParameter("username", username);
		
		User user = null;
		try {
			user = query.getSingleResult();
		} catch (NoResultException e) {
			logger.info("Fikk ingen treff");
		}

		return user;
	}

}