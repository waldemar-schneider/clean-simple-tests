package de.flaviait.user.domain;

import de.flaviait.user.mother.DomainMother;
import org.junit.Before;
import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.TruthJUnit.assume;
import static de.flaviait.user.domain.UserRegistrar.ROLE_ADMIN;
import static de.flaviait.user.domain.UserRegistrar.ROLE_USER;
import static de.flaviait.user.mother.DomainMother.supply;
import static de.flaviait.user.mother.DomainMother.with;

public class UserRegistrarTruthTest {

    private DomainMother mother = new DomainMother();

    private UserRegistrar userRegistrar;

    @Before
    public void setUp() throws Exception {
        userRegistrar = new UserRegistrar();
    }

    @Test
    public void registerUserRegistersSuccessfulNewUser() {
        // given
        User user = mother.givenAnUnpersistedUser();

        // when
        User registerUser = userRegistrar.registerUser(user);

        // then
        assume().withFailureMessage("User should be registred!")
                .that(userRegistrar.registration)
                .containsKey(registerUser.getId());
    }

    @Test
    public void registerUserAdd1RoleToUser() {
        // given
        User user = mother.givenAnUnpersistedUser();
        Role role = mother.givenAnUnpersistedRole();
        role.setName("ROLE_ADMIN");
        supply(user, with(role));

        // when
        User registerUser = userRegistrar.registerUser(user);

        // then
        assertThat(registerUser.getRoles()).named("hasRole").hasSize(2);
    }

    @Test
    public void registerUserAddUserRole() {
        // given
        User user = mother.givenAnUnpersistedUser();

        // when
        User registerUser = userRegistrar.registerUser(user);

        // then
        assertThat(registerUser.getRoles()).contains(ROLE_USER);
        assertThat(registerUser.getRoles()).doesNotContain(ROLE_ADMIN);
    }

    @Test
    public void registerUserActivatesUser() {
        // given
        User user = mother.givenAnUnpersistedUser();

        // when
        User registerUser = userRegistrar.registerUser(user);

        // then
        assertThat(registerUser.isActive()).isTrue();
    }

    @Test
    public void registerUserSetIdentifier() {
        // given
        User user = mother.givenAnUnpersistedUser();

        // when
        User registerUser = userRegistrar.registerUser(user);

        // then
        assertThat(registerUser.getId()).isNotNull();
        assertThat(registerUser.getId()).isAtLeast(1L);
        assertThat(registerUser.getId()).isAtMost(2L);
        assume().that(registerUser).isNotNull();
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Test
    public void registerAdminAddUserRole() {
        // given
        User user = mother.givenAnUnpersistedUser();

        // when
        User registerUser = userRegistrar.registerAdmin(user);

        // then
        assertThat(registerUser.getRoles()).contains(ROLE_USER);
    }

    @Test
    public void registerAdminAddAdminRole() {
        // given
        User user = mother.givenAnUnpersistedUser();

        // when
        User registerUser = userRegistrar.registerAdmin(user);

        // then
        assertThat(registerUser.getRoles()).contains(ROLE_ADMIN);
    }

    @Test
    public void registerAdminSetsBothRoles() {
        // given
        User user = mother.givenAnUnpersistedUser();

        // when
        User registerUser = userRegistrar.registerAdmin(user);

        // then
        assertThat(registerUser.getRoles()).containsExactly(ROLE_USER, ROLE_ADMIN).inOrder();
    }

}