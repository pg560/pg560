package no.nith.pg560.infrastructure;

import no.nith.pg560.common.CommonInfrastructureIT;
import no.nith.pg560.domain.User;
import no.nith.pg560.infrastructure.UserJpaRepository;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserJpaRepositoryTest extends CommonInfrastructureIT {

	@Test
	public void testGetUser() {
		UserJpaRepository userRepo = new UserJpaRepository(getEntityManager());
		User userResult = userRepo.getUser("Ola", "Ola");
		assertEquals(userResult.getUsername(), "Ola");
		assertEquals(userResult.getPassword(), "Ola");

		userResult = userRepo.getUser("Finnes ikke", "Finnes ikke");
		assertNull(userResult);
	}

}
