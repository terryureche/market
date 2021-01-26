package mini.market.demo;

import mini.market.demo.entities.User;
import mini.market.demo.repository.UserRepository;
import mini.market.demo.services.UserService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.MockitoAnnotations.initMocks;

public class UserServiceTest {
    @Mock
    private UserRepository mockUserRepository;

    @Mock
    private BCryptPasswordEncoder mockBCryptPasswordEncoder;

    private UserService userServiceUnderTest;
    private User user;

    @Before
    public void setUp() {
        initMocks(this);

        userServiceUnderTest = new UserService(
                mockUserRepository,
                mockBCryptPasswordEncoder
        );

        user = User.builder()
                .user_id("1")
                .username("gigel")
                .enabled(false)
                .build();

        Mockito.when(mockUserRepository.save(any())).thenReturn(user);
        Mockito.when(mockUserRepository.findByUsername(anyString())).thenReturn(user);
    }

    @Test
    public void testFindByUserName() {
        final String username = "gigel";

        User result = userServiceUnderTest.saveUser(User.builder().build());
System.out.println(result);
        assertEquals(username, result.getUsername());
    }
}
