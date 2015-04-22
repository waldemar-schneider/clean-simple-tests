package de.flaviait.user.domain;

import de.flaviait.user.mother.DomainMother;
import org.junit.Before;
import org.junit.Test;

import static de.flaviait.user.domain.UserAssert.userAssert;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class UserRegistrarAssertJTest {

    private DomainMother mother = new DomainMother();

    private UserRegistrar userRegistrar;

    @Before
    public void setUp() throws Exception {
        userRegistrar = new UserRegistrar();
    }

    @Test
    public void registerUserSetsId() {
        // given
        User user = mother.givenAnUnpersistedUser();

        // when
        User registerUser = userRegistrar.registerUser(user);

        // then
        userAssert(registerUser).hasId().isActiveUser().hasSurName(user.getSurName());
    }

    @Test
    public void registerUserWithNoFirstnameThrowsException() {
        // given
        User user = mother.givenAnUnpersistedUser();
        user.setFirstName(null);

        // when
        Throwable throwable = catchThrowable(userRegistrar::notImplemented);

        // then
        assertThat(throwable)
                .isNotNull()
                .hasMessage("not implemented yet");
    }
}