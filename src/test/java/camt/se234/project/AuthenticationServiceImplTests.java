package camt.se234.project;
import camt.se234.project.dao.UserDao;
import camt.se234.project.entity.User;
import camt.se234.project.service.AuthenticationServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthenticationServiceImplTests {

    UserDao userDao;
    AuthenticationServiceImpl authenticationService;


    @Before
    public void setup(){
        userDao = mock(UserDao.class);
        authenticationService = new  AuthenticationServiceImpl();
        authenticationService.setUserDao(userDao);
    }
    @Test
    public void testAuthentication(){
        List<User> mockUser = new ArrayList<>();
        when(userDao.getUser("hello","5558")).thenReturn(new User("hello","5558","Hi"));
        assertThat(authenticationService.authenticate("hello","5558"), is(new User("hello","5558","Hi")));

    }
}
