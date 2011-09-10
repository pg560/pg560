package no.nith.pg560.infrastructure;

import java.util.List;

import no.nith.pg560.common.CommonInfrastructureIT;
import no.nith.pg560.domain.Users;
import no.nith.pg560.infrastructure.UserJpaRepository;

import org.junit.Test;


import static org.junit.Assert.*;

public class UserJpaRepositoryTest extends CommonInfrastructureIT {

    @Test
    public void readUsers() {
    	UserJpaRepository userRepo = new UserJpaRepository(getEntityManager());
    	List <Users> users = userRepo.getUsers();
        assertEquals(users.get(0).getName(),"Ola");
        assertEquals(users.get(0).getUsername(),"Ola");
    }

}
