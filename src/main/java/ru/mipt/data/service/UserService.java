package ru.mipt.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mipt.data.dto.UserDTO;
import ru.mipt.data.dto.WorkoutDTO;
import ru.mipt.data.model.User;
import ru.mipt.data.model.Workout;
import ru.mipt.data.repository.UserRepository;

import java.util.Optional;
import java.util.Set;

/**
 * UserService class is responsible for interacting with UserDTO and User with <b>UserRepository</b>
 */
@Service
public class UserService {
    private final UserRepository userRepository;

    /**
     * Constructor of UserService
     *
     * @param repository UserRepository
     */
    @Autowired
    public UserService(UserRepository repository) {
        this.userRepository = repository;
    }

    /**
     * Method get user which was found by id
     *
     * @param id user's id
     * @return User
     * @see UserRepository#findById(Object)
     */
    public Optional<User> get(int id) {
        return userRepository.findById(id);
    }

    /**
     * Method for returning updated userRepository with saving changes in user entity
     *
     * @param entity UserDTO
     * @return updated userRepository
     * @see UserRepository#save(Object)
     * @see UserService#convertToUser(UserDTO)
     */
    public User update(UserDTO entity) {
        return userRepository.save(convertToUser(entity));
    }

    /**
     * Method for deleting user by id
     *
     * @param id User's id
     * @see UserRepository#deleteById(Object)
     */
    public void delete(int id) {
        userRepository.deleteById(id);
    }

    /**
     * This Method return List of pageable users
     *
     * @param pageable Workout
     * @return User
     * @see UserRepository#findAll()
     */
    public Page<User> list(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    /**
     * This method count amount of users
     *
     * @return amount of Users
     */
    public int count() {
        return (int) userRepository.count();
    }

    /**
     * Method for checking is username used or not
     *
     * @param username User's username
     * @return boolean
     * @see UserRepository#findByUsername(String)
     */
    public boolean checkNotExistUsername(String username) {
        return userRepository.findByUsername(username) == null;
    }

    /**
     * Method for checking is email used or not
     *
     * @param email User's email
     * @return boolean
     * @see UserRepository#findUserByEmail(String)
     */
    public boolean checkNotExistEmail(String email) {
        return userRepository.findUserByEmail(email) == null;
    }

    /**
     * Method for converting UserDTO into User
     *
     * @param userDTO UserDTO entity
     * @return User
     * @see UserDTO#getFirstName()
     * @see UserDTO#getLastName()
     * @see UserDTO#getUsername()
     * @see UserDTO#getEmail() ()
     * @see UserDTO#getPassword()
     * @see UserDTO#getRoles()
     * @see UserDTO#getFirstName()
     * @see User#setLastname(String)
     * @see User#setFirstname(String)
     * @see User#setEmail(String)
     * @see User#setPassword(String)
     * @see User#setUsername(String)
     * @see User#setRoles(Set)
     */
    private User convertToUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setFirstname(userDTO.getFirstName());
        user.setLastname(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setRoles(userDTO.getRoles());
        return user;
    }

    /**
     * Method for converting User into UserDTO
     *
     * @param user User entity
     * @return UserDTO
     * @see User#getFirstname()
     * @see User#getLastname()
     * @see User#getUsername()
     * @see User#getEmail() ()
     * @see User#getPassword()
     * @see User#getId()
     */
    protected static UserDTO convertToUserDTO(User user) {
        return new UserDTO(user.getId(),
                user.getUsername(),
                user.getFirstname(),
                user.getLastname(),
                user.getEmail(),
                user.getPassword());
    }

    /**
     * Method for returning UserDTO from User
     *
     * @param id user's id
     * @return UserDTO
     * @see UserService#convertToUserDTO(User)
     */
    public UserDTO getDTO(int id) {
        return convertToUserDTO(get(id).orElse(new User()));
    }

    //TODO test me

    /**
     * Get set of workoutDTOs of favorite workouts
     *
     * @param id user's id
     * @return favorite workoutDTO set
     */
    public Set<WorkoutDTO> getFavoriteWorkouts(int id) {
        User user = userRepository.findUserById(id);
        return WorkoutService.convertToWorkoutDTOSet(
                user.getFavoriteWorkouts()
        );
    }
//TODO test me

    /**
     * Add favorite workout
     *
     * @param userId    id
     * @param workoutId id
     */
    @Transactional
    public void addFavoriteWorkout(int userId, int workoutId) {
        userRepository.addFavoriteWorkouts(userId, workoutId);
    }
//TODO test me

    /**
     * Remove workout from favorites
     *
     * @param userId    id
     * @param workoutId id
     */
    @Transactional
    public void removeFavoriteWorkouts(int userId, int workoutId) {
        userRepository.removeFavoriteWorkouts(userId, workoutId);
    }
    //TODO test me

    /**
     * Check if user added workout as favorite
     *
     * @param workoutId workout's id for test
     * @param userId    user's for test
     * @return boolean check result
     */
    @Transactional
    public boolean checkFavorite(int workoutId, int userId) {
        Set<Workout> workouts = get(userId).orElse(new User()).getFavoriteWorkouts();
        for (Workout workout : workouts) {
            if (workout.getId() == workoutId) {
                return true;
            }
        }
        return false;
    }


}
