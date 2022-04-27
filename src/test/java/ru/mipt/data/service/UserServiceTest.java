package ru.mipt.data.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import ru.mipt.data.Role;
import ru.mipt.data.dto.UserDTO;
import ru.mipt.data.model.User;
import ru.mipt.data.repository.UserRepository;

import java.util.*;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static ru.mipt.data.Role.USER;

class UserServiceTest {
    public ArrayList<User> arraylist = new ArrayList<User>();
    @InjectMocks
    private UserRepository userRepository =new UserRepository(){

        @Override
        public List<User> findAll() {
            return null;
        }

        @Override
        public List<User> findAll(Sort sort) {
            return null;
        }

        @Override
        public Page<User> findAll(Pageable pageable) {
            return null;
        }

        @Override
        public List<User> findAllById(Iterable<Integer> integers) {
            return null;
        }

        @Override
        public long count() {
            return 0;
        }

        @Override
        public void deleteById(Integer integer) {
            arraylist.add(userRepository.findUserById(0));
            arraylist.remove(userRepository.findUserById(integer));



        }

        @Override
        public void delete(User entity) {

        }

        @Override
        public void deleteAllById(Iterable<? extends Integer> integers) {

        }

        @Override
        public void deleteAll(Iterable<? extends User> entities) {

        }

        @Override
        public void deleteAll() {

        }

        @Override
        public <S extends User> S save(S entity) {
            User user = entity;
            return (S) user;
        }

        @Override
        public <S extends User> List<S> saveAll(Iterable<S> entities) {
            return null;
        }

        @Override
        public Optional<User> findById(Integer integer) {
            User user =new User();
            user.setEmail("test@mail.ru");
            user.setUsername("username");
            user.setFirstname("test");
            user.setLastname("test2");
            user.setPassword("test");
            user.setId(0);
            Set<Role> roles = new HashSet<>();
            roles.add(USER);
            user.setRoles(roles);
            return integer==0? Optional.of(user) : Optional.empty();
        }

        @Override
        public boolean existsById(Integer integer) {
            return false;
        }

        @Override
        public void flush() {

        }

        @Override
        public <S extends User> S saveAndFlush(S entity) {
            return null;
        }

        @Override
        public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) {
            return null;
        }

        @Override
        public void deleteAllInBatch(Iterable<User> entities) {

        }

        @Override
        public void deleteAllByIdInBatch(Iterable<Integer> integers) {

        }

        @Override
        public void deleteAllInBatch() {

        }

        @Override
        public User getOne(Integer integer) {
            return null;
        }

        @Override
        public User getById(Integer integer) {
            return null;
        }

        @Override
        public <S extends User> Optional<S> findOne(Example<S> example) {
            return Optional.empty();
        }

        @Override
        public <S extends User> List<S> findAll(Example<S> example) {
            return null;
        }

        @Override
        public <S extends User> List<S> findAll(Example<S> example, Sort sort) {
            return null;
        }

        @Override
        public <S extends User> Page<S> findAll(Example<S> example, Pageable pageable) {
            return null;
        }

        @Override
        public <S extends User> long count(Example<S> example) {
            return 0;
        }

        @Override
        public <S extends User> boolean exists(Example<S> example) {
            return false;
        }

        @Override
        public <S extends User, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
            return null;
        }

        @Override
        public User findByUsername(String username) {
            ArrayList<User> array = new ArrayList<User>();
            User user =new User();
            user.setEmail("test@mail.ru");
            user.setUsername("username");
            user.setFirstname("test");
            user.setLastname("test");
            user.setPassword("test");
            user.setId(0);
            Set<Role> roles = new HashSet<>();
            roles.add(USER);
            user.setRoles(roles);
            array.add(user);

            if (username.equals(array.get(0).getUsername())) {
                return user;
            }
            else {
                return null;
            }
        }

        @Override
        public User findUserById(int id){
            User user =new User();
            user.setEmail("test@mail.ru");
            user.setUsername("username");
            user.setFirstname("test");
            user.setLastname("test");
            user.setPassword("test");
            user.setId(id);
            Set<Role> roles = new HashSet<>();
            roles.add(USER);
            user.setRoles(roles);
            return user;
        }

        @Override
        public User findUserByEmail(String email) {
            ArrayList<User> array = new ArrayList<User>();
            User user =new User();
            user.setEmail("test@mail.ru");
            user.setUsername("username");
            user.setFirstname("test");
            user.setLastname("test");
            user.setPassword("test");
            user.setId(0);
            Set<Role> roles = new HashSet<>();
            roles.add(USER);
            user.setRoles(roles);
            array.add(user);

            if (email.equals(array.get(0).getEmail())) {
                return user;
            }
            else {
                return null;
            }
        }

    };

    @Autowired
    private UserService userService = new UserService(userRepository);


    @Test
    void get() {
        User user =new User();
        user.setEmail("test@mail.ru");
        user.setUsername("username");
        user.setFirstname("test");
        user.setLastname("test2");
        user.setPassword("test");
        user.setId(0);
        Set<Role> roles = new HashSet<>();
        roles.add(USER);
        user.setRoles(roles);
        Assertions.assertEquals(Optional.of(user), userService.get(0));
    }

    @Test
    void convertToUser(){
        UserDTO userDTO = new UserDTO(0, "username", "test",
                "test", "test@mail.ru", "test");
        User user1 = new User();
        user1.setEmail("test@mail.ru");
        user1.setUsername("username");
        user1.setFirstname("test");
        user1.setLastname("test");
        user1.setPassword("test");
        user1.setId(0);
        Set<Role> roles = new HashSet<>();
        roles.add(USER);
        user1.setRoles(roles);
        Assertions.assertEquals(userService.convertToUser(userDTO), user1);
    }

    @Test
    void update() {
        UserDTO userDTO = new UserDTO(0, "username", "test",
                "test", "test@mail.ru", "password");
        User user1 = new User();
        user1.setEmail("test@mail.ru");
        user1.setUsername("username");
        user1.setFirstname("test");
        user1.setLastname("test");
        user1.setPassword("test");
        user1.setId(0);
        Set<Role> roles = new HashSet<>();
        roles.add(USER);
        user1.setRoles(roles);
        Assertions.assertEquals(user1, userService.update(userDTO));
    }

    @Test
    void delete() {
        userRepository.deleteById(0);
        ArrayList<User> ArrayList = new ArrayList<User>();
        Assertions.assertEquals(ArrayList, arraylist);
    }

    @Test
    void count() {
        Assertions.assertEquals(0, userService.count());
    }

    @Test
    void checkNotExistUsername() {
        User user1 = new User();
        user1.setEmail("test@mail.ru");
        user1.setUsername("username");
        user1.setFirstname("test");
        user1.setLastname("test");
        user1.setPassword("test");
        user1.setId(0);
        Set<Role> roles = new HashSet<>();
        roles.add(USER);
        user1.setRoles(roles);
        assertTrue(userService.checkNotExistUsername("fake name"));
        assertFalse(userService.checkNotExistUsername("username"));
    }

    @Test
    void checkNotExistEmail() {
        User user1 = new User();
        user1.setEmail("test@mail.ru");
        user1.setUsername("username");
        user1.setFirstname("test");
        user1.setLastname("test");
        user1.setPassword("test");
        user1.setId(0);
        Set<Role> roles = new HashSet<>();
        roles.add(USER);
        user1.setRoles(roles);
        assertTrue(userService.checkNotExistEmail("fake email"));
        assertFalse(userService.checkNotExistEmail("test@mail.ru"));
    }

    @Test
    void convertToUserDTO() {
        UserDTO userDTO = new UserDTO(0, "username", "test",
                "test", "test@mail.ru", "test");
        User user1 = new User();
        user1.setEmail("test@mail.ru");
        user1.setUsername("username");
        user1.setFirstname("test");
        user1.setLastname("test");
        user1.setPassword("test");
        user1.setId(0);
        Set<Role> roles = new HashSet<>();
        roles.add(USER);
        user1.setRoles(roles);
        Assertions.assertEquals(userService.convertToUserDTO(user1), userDTO);
    }

    @Test
    void getDTO() {
        UserDTO userDTO = new UserDTO(0, "username", "test",
                "test2", "test@mail.ru", "test");
        UserDTO userDTO1 = new UserDTO();
        Assertions.assertEquals(UserService.convertToUserDTO(userService.get(0).orElse(new User())), userDTO);
        Assertions.assertEquals(UserService.convertToUserDTO(userService.get(222).orElse(new User())), userDTO1);
    }
}