package no.nith.pg560.application;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import no.nith.pg560.domain.User;
import no.nith.pg560.infrastructure.UserJpaRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
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
        User user = userServiceBean.getUser("Ola", "Ola");
        //assertNotNull(user);
    }	

}
