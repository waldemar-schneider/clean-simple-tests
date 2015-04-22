package de.flaviait.user.domain;

import de.flaviait.user.mother.DomainMother;
import org.junit.Before;
import org.junit.Test;

import static de.flaviait.user.mother.DomainMother.supply;
import static de.flaviait.user.mother.DomainMother.with;
import static de.flaviait.user.testutil.RoleMatcher.hasRole;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;

public class UserRegistrarHamcrestTest {

    private DomainMother mother = new DomainMother();

    private UserRegistrar userRegistrar;

    @Before
    public void setUp() throws Exception {
        userRegistrar = new UserRegistrar();
    }

    @Test
    public void registerUserSuccessfulRegistersUser() throws Exception {
        // given
        User user = mother.givenAnUnpersistedUser();

        // when
        User registerUser = userRegistrar.registerUser(user);

        // then
        assertThat(registerUser, hasProperty("id", notNullValue()));
    }

    @Test
    public void registerUserCreatesDefaultRole() {
        // given
        User user = mother.givenAnUnpersistedUser();
        Role role = mother.givenAnUnpersistedRole();
        supply(user, with(role));

        // when
        User registerUser = userRegistrar.registerUser(user);

        // then
        assertThat(registerUser, hasProperty("roles", hasSize(2)));
        assertThat(registerUser, not(hasRole("ROLE_SUPER_ADMIN")));
    }
}