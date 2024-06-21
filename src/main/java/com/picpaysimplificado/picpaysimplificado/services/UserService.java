package com.picpaysimplificado.picpaysimplificado.services;

import com.picpaysimplificado.picpaysimplificado.dtos.UserDTO;
import com.picpaysimplificado.picpaysimplificado.model.exception.InvalidTransactionException;
import com.picpaysimplificado.picpaysimplificado.model.exception.UserNotFoundException;
import com.picpaysimplificado.picpaysimplificado.model.user.User;
import com.picpaysimplificado.picpaysimplificado.model.user.UserType;
import com.picpaysimplificado.picpaysimplificado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void validateTransaction(User sender, User receiver, BigDecimal amount) throws InvalidTransactionException{
        if (sender.getUserType() == UserType.MERCHANT){
            throw new InvalidTransactionException
                    ("Usuario do tipo lojista não está autorizado a realizar transferência.");
        }

        if (sender.getId().equals(receiver.getId())) {
            throw new InvalidTransactionException("Usuário não pode fazer uma transferência para a própria conta.");
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new InvalidTransactionException("O saldo do usuário não é suficiente para completar a operação.");
        }
    }

    public User createUser(UserDTO userRecord) {
        User newUser = new User(userRecord);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) throws UserNotFoundException {
        return userRepository.findUserById(id).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado"));
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
