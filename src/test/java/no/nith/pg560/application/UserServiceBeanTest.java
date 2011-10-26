package no.nith.pg560.application;

import static org.junit.Assert.assertNotNull;

import no.nith.pg560.domain.User;
import no.nith.pg560.infrastructure.UserJpaRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import static org.mockito.Mockito.when;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServiceBeanTest {
    @Mock
    private UserJpaRepository userJpaRepository;

    @InjectMocks
    private UserServiceBean userServiceBean;

    @Before
    public void setup() {
    	userServiceBean = new UserServiceBean();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void readUsers() throws Exception {
    	when(userJpaRepository.getUser("Ola")).thenReturn(buildUser());
        User user = userServiceBean.getUser("Ola");
        assertNotNull(user);
    }	
    
    private User buildUser() {
    	User user = new User();
    	user.setUsername("Ola");
    	return user;
    }

}
