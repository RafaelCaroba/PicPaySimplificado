package com.picpaysimplificado.picpaysimplificado.services;

import com.picpaysimplificado.picpaysimplificado.dtos.UserDTO;
import com.picpaysimplificado.picpaysimplificado.model.user.User;
import com.picpaysimplificado.picpaysimplificado.model.user.UserType;
import com.picpaysimplificado.picpaysimplificado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception{
        if (sender.getUserType() == UserType.MERCHANT){
            throw new Exception("Usuario do tipo lojista não está autorizado a realizar transferência.");
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("O saldo do usuário não é suficiente para completar a operação.");
        }
    }

    public User createUser(UserDTO user) {
        User newUser = new User(user);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User findUserById(Long id) throws Exception {
        return userRepository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}
