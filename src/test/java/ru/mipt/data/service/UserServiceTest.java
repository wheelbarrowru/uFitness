package ru.mipt.data.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.data.domain.*;
import org.springframework.data.repository.query.FluentQuery;
import ru.mipt.data.Role;
import ru.mipt.data.dto.UserDTO;
import ru.mipt.data.model.Tags;
import ru.mipt.data.model.User;
import ru.mipt.data.model.Workout;
import ru.mipt.data.repository.UserRepository;

import java.util.*;
import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;
import static ru.mipt.data.Role.USER;

class UserServiceTest {
    public ArrayList<User> arraylist = new ArrayList<>();
    public ArrayList<User> arraylist2 = new ArrayList<>();
    public ArrayList<User> arraylist3 = new ArrayList<>();
    public ArrayList<User> arraylist4 = new ArrayList<>();

    @InjectMocks
    @SuppressWarnings({"all"})
    private UserRepository userRepository = new UserRepository() {

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
            Page<User> page = new PageImpl(new ArrayList());
            return page;
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
            return (S) entity;
        }

        @Override
        public <S extends User> List<S> saveAll(Iterable<S> entities) {
            return null;
        }

        @Override
        public Optional<User> findById(Integer integer) {
            User user = new User();
            user.setEmail("test@mail.ru");
            user.setUsername("username");
            user.setFirstname("test");
            user.setLastname("test2");
            user.setPassword("test");
            user.setId(0);
            Set<Role> roles = new HashSet<>();
            roles.add(USER);
            user.setRoles(roles);
            Workout workout = new Workout();
            workout.setId(0);
            workout.setRating(5);
            workout.setDescription("test");
            workout.setTitle("test");
            workout.setAuthorId(0);
            HashSet<Tags> hashSet = new HashSet<>();
            workout.setWorkoutTags(hashSet);
            Set<Workout> workouts = new HashSet<>();
            workouts.add(workout);
            user.setFavoriteWorkouts(workouts);
            return integer == 0 ? Optional.of(user) : Optional.empty();
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
            User user = new User();
            user.setEmail("test@mail.ru");
            user.setUsername("username");
            user.setFirstname("test");
            user.setLastname("test");
            user.setPassword("test");
            user.setId(0);
            Set<Role> roles = new HashSet<>();
            roles.add(USER);
            user.setRoles(roles);
            Workout workout = new Workout();
            workout.setId(0);
            workout.setRating(5);
            workout.setDescription("test");
            workout.setTitle("test");
            workout.setAuthorId(0);
            HashSet<Tags> hashSet = new HashSet<>();
            workout.setWorkoutTags(hashSet);
            Set<Workout> workouts = new HashSet<>();
            workouts.add(workout);
            user.setFavoriteWorkouts(workouts);
            array.add(user);

            if (username.equals(array.get(0).getUsername())) {
                return user;
            } else {
                return null;
            }
        }

        @Override
        public User findUserById(int id) {
            User user = new User();
            user.setEmail("test@mail.ru");
            user.setUsername("username");
            user.setFirstname("test");
            user.setLastname("test");
            user.setPassword("test");
            user.setId(id);
            Set<Role> roles = new HashSet<>();
            roles.add(USER);
            user.setRoles(roles);
            Workout workout = new Workout();
            workout.setId(0);
            workout.setRating(5);
            workout.setDescription("test");
            workout.setTitle("test");
            workout.setAuthorId(0);
            HashSet<Tags> hashSet = new HashSet<>();
            workout.setWorkoutTags(hashSet);
            Set<Workout> workouts = new HashSet<>();
            workouts.add(workout);
            user.setFavoriteWorkouts(workouts);
            return user;
        }

        @Override
        public User findUserByEmail(String email) {
            ArrayList<User> array = new ArrayList<User>();
            User user = new User();
            user.setEmail("test@mail.ru");
            user.setUsername("username");
            user.setFirstname("test");
            user.setLastname("test");
            user.setPassword("test");
            user.setId(0);
            Set<Role> roles = new HashSet<>();
            roles.add(USER);
            user.setRoles(roles);
            Workout workout = new Workout();
            workout.setId(0);
            workout.setRating(5);
            workout.setDescription("test");
            workout.setTitle("test");
            workout.setAuthorId(0);
            HashSet<Tags> hashSet = new HashSet<>();
            workout.setWorkoutTags(hashSet);
            Set<Workout> workouts = new HashSet<>();
            workouts.add(workout);
            user.setFavoriteWorkouts(workouts);
            array.add(user);

            if (email.equals(array.get(0).getEmail())) {
                return user;
            } else {
                return null;
            }
        }

        @Override
        public void addFavoriteWorkouts(int userId, int workoutId) {
            Set<Workout> set = arraylist4.get(userId).getFavoriteWorkouts();
            Workout workout1 = new Workout();
            workout1.setId(workoutId);
            workout1.setRating(5);
            workout1.setDescription("test1");
            workout1.setTitle("test1");
            workout1.setAuthorId(0);
            HashSet<Tags> hashSet = new HashSet<>();
            workout1.setWorkoutTags(hashSet);
            set.add(workout1);
            arraylist4.get(0).setFavoriteWorkouts(set);
        }

        @Override
        public void removeFavoriteWorkouts(int userId, int workoutId) {
            Set<Workout> set = arraylist3.get(0).getFavoriteWorkouts();
            set.removeIf(x -> x.getId() == workoutId);
            arraylist3.get(0).setFavoriteWorkouts(set);
        }

        @Override
        public void updateUserInfo(int userId, String username, String firstname, String lastName, String email) {
            arraylist2.get(0).setFirstname("test1");
            arraylist2.get(0).setEmail("test1");
            arraylist2.get(0).setLastname("test1");
            arraylist2.get(0).setUsername("username1");
        }

    };


    private final UserService userService = new UserService(userRepository);


    @Test
    void get() {
        User user = new User();
        user.setEmail("test@mail.ru");
        user.setUsername("username");
        user.setFirstname("test");
        user.setLastname("test2");
        user.setPassword("test");
        user.setId(0);
        Set<Role> roles = new HashSet<>();
        roles.add(USER);
        user.setRoles(roles);
        Workout workout = new Workout();
        workout.setId(0);
        workout.setRating(5);
        workout.setDescription("test");
        workout.setTitle("test");
        workout.setAuthorId(0);
        HashSet<Tags> hashSet = new HashSet<>();
        workout.setWorkoutTags(hashSet);
        Set<Workout> workouts = new HashSet<>();
        workouts.add(workout);
        user.setFavoriteWorkouts(workouts);
        Assertions.assertEquals(Optional.of(user), userService.get(0));
    }

    @Test
    void convertToUser() {
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
        Workout workout = new Workout();
        workout.setId(0);
        workout.setRating(5);
        workout.setDescription("test");
        workout.setTitle("test");
        workout.setAuthorId(0);
        HashSet<Tags> hashSet = new HashSet<>();
        workout.setWorkoutTags(hashSet);
        Set<Workout> workouts = new HashSet<>();
        workouts.add(workout);
        user1.setFavoriteWorkouts(workouts);
        Assertions.assertEquals(UserService.convertToUser(userDTO), user1);
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
        userService.delete(0);
        ArrayList<User> ArrayList = new ArrayList<>();
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
        Assertions.assertEquals(UserService.convertToUserDTO(user1), userDTO);
    }

    @Test
    void getDTO() {
        UserDTO userDTO = new UserDTO(0, "username", "test",
                "test2", "test@mail.ru", "test");
        UserDTO userDTO1 = new UserDTO();
        Assertions.assertEquals(UserService.convertToUserDTO(userService.get(0).orElse(new User())), userDTO);
        Assertions.assertEquals(UserService.convertToUserDTO(userService.get(222).orElse(new User())), userDTO1);
    }

    @Test
    void checkNotExistUsernameWithDifferentID() {
        if ((userService.checkNotExistUsername("test")) | userRepository.findUserById(0).equals(userRepository.findByUsername("test"))) {
            assertTrue(userService.checkNotExistUsernameWithDifferentID("test", 0));
        } else {
            assertFalse(userService.checkNotExistUsernameWithDifferentID("test", 0));
        }
    }

    @Test
    void checkNotExistEmailWithDifferentID() {
        if ((userService.checkNotExistEmail("test")) | userRepository.findUserById(0).equals(userRepository.findUserByEmail("test"))) {
            assertTrue(userService.checkNotExistEmailWithDifferentID("test", 0));
        } else {
            assertFalse(userService.checkNotExistEmailWithDifferentID("test", 0));
        }
    }

    @Test
    void getFavoriteWorkouts() {
        User user = userRepository.findUserById(0);
        Assertions.assertEquals(userService.getFavoriteWorkouts(0), WorkoutService.convertToWorkoutDTOSet(
                user.getFavoriteWorkouts()));

    }

    @Test
    void addFavoriteWorkout() {
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
        Workout workout1 = new Workout();
        workout1.setId(0);
        workout1.setRating(5);
        workout1.setDescription("test1");
        workout1.setTitle("test1");
        workout1.setAuthorId(0);
        HashSet<Tags> hashSet = new HashSet<>();
        workout1.setWorkoutTags(hashSet);
        arraylist4.add(user1);
        userService.addFavoriteWorkout(0, 0);
        Set<Workout> workouts1 = new HashSet<>();
        workouts1.add(workout1);
        assertEquals(arraylist4.get(0).getFavoriteWorkouts(), workouts1);
    }

    @Test
    void removeFavoriteWorkouts() {
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
        Workout workout = new Workout();
        workout.setId(0);
        workout.setRating(5);
        workout.setDescription("test");
        workout.setTitle("test");
        workout.setAuthorId(0);
        Workout workout1 = new Workout();
        workout1.setId(1);
        workout1.setRating(5);
        workout1.setDescription("test1");
        workout1.setTitle("test1");
        workout1.setAuthorId(0);
        HashSet<Tags> hashSet = new HashSet<>();
        workout.setWorkoutTags(hashSet);
        workout1.setWorkoutTags(hashSet);
        Set<Workout> workouts = new HashSet<>();
        workouts.add(workout);
        workouts.add(workout1);
        Set<Workout> workouts1 = new HashSet<>();
        workouts1.add(workout1);
        user1.setFavoriteWorkouts(workouts);
        arraylist3.add(user1);
        userService.removeFavoriteWorkouts(0, 0);
        Assertions.assertEquals(arraylist3.get(0).getFavoriteWorkouts(), workouts1);
    }

    @Test
    void checkFavorite() {
        assertTrue(userService.checkFavorite(0, 0));
        assertFalse(userService.checkFavorite(222, 0));

    }

    @Test
    void updateUserInfo() {
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
        arraylist2.add(user1);
        User user = new User();
        user.setEmail("test@mail.ru");
        user.setUsername("username1");
        user.setFirstname("test1");
        user.setLastname("test1");
        user.setPassword("test1");
        user.setId(0);
        Set<Role> roles1 = new HashSet<>();
        roles.add(USER);
        user1.setRoles(roles1);
        userService.updateUserInfo(0, "test1", "test1", "test", "test");
        Assertions.assertEquals(arraylist2.get(0), user);
    }

    @Test
    @SuppressWarnings("all")
    void list() {
        Page<User> page = new PageImpl(new ArrayList());
        Assertions.assertEquals(userService.list(Pageable.unpaged()), page);
    }
}