package dev.joseluis.ticket.service;

import dev.joseluis.ticket.exception.UserException;
import dev.joseluis.ticket.model.User;
import dev.joseluis.ticket.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public void createUserByAdmin(User user) throws UserException {
        createUser(user, new String[]{"ROLE_ROOT", "ROLE_ADMIN"});
    }

    public void createUserByRoot(User user) throws UserException {
        createUser(user, new String[]{"ROLE_ROOT"});
    }

    private void createUser(User user, String[] rolesForbiddenForCreation) throws UserException{
        try {
            // Check if email already exists
            boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();
            if(userExists)
                throw new UserException("email already exists");
            // Generate random password
            user.setPassword(UUID.randomUUID().toString());
            // Roles forbidden to create new users
            if(Arrays.stream(rolesForbiddenForCreation).anyMatch(role -> user.getRole().contentEquals(role))){
                throw new UserException("user do not have necessary permissions for this operation");
            }
            // Save user to database
            userRepository.save(user);
            // Show generated password
            logger.info("email: " + user.getEmail() + " | generated password: " + user.getPassword());
        } catch (UserException e) {
            throw e;
        } catch (Exception e){
            throw new UserException("error creating a new user", e);
        }
    }

    public User getUserByEmail(String email) throws UserException{
        return userRepository.findByEmail(email).orElseThrow(() -> new UserException("email not found"));
    }

    public List<User> getUsersByAdmin(){
        return userRepository.findAllByRoleNotContainingAndRoleNotContaining("ROLE_ROOT", "ROLE_ADMIN");
    }

    public List<User> getUsersByRoot(){
        return userRepository.findAllByRoleNotContaining("ROLE_ROOT");
    }


    public void updateProfile(User user) throws UserException {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User updatedUser = userRepository.findByEmail(userDetails.getUsername())
                    .orElseThrow(() -> new UserException("email of authenticated user do no exists"));
            // Map only necessary elements
            updatedUser.setEmail(user.getEmail());
            updatedUser.setName(user.getName());
            updatedUser.setSurname(user.getSurname());
            userRepository.save(updatedUser);
        }catch (UserException ex){
            throw ex;
        }catch (Exception ex){
            throw new UserException("error updating user profile", ex);
        }

    }
    public void changePassword(User user) throws UserException {
        try{
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            User updatedUser = userRepository.findByEmail(userDetails.getUsername())
                    .orElseThrow(() -> new UserException("email of authenticated user do no exists"));
            // Map only necessary elements
            updatedUser.setPassword(user.getPassword());
            userRepository.save(updatedUser);
        }catch (UserException ex){
            throw ex;
        }catch (Exception ex){
            throw new UserException("error  user profile", ex);
        }

    }

}
