package com.picpaysimplificado.picpaysimplificado.services;

import com.picpaysimplificado.picpaysimplificado.dtos.TransactionDTO;
import com.picpaysimplificado.picpaysimplificado.model.exception.TransactionAuthorizationException;
import com.picpaysimplificado.picpaysimplificado.model.transaction.Transaction;
import com.picpaysimplificado.picpaysimplificado.model.user.User;
import com.picpaysimplificado.picpaysimplificado.repositories.TransactionRepository;
import com.picpaysimplificado.picpaysimplificado.services.notification.NotificationService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private AuhtorizationService auhtorizationService;

    @Transactional
    public Transaction createTransaction(TransactionDTO transaction) throws Exception {
        // Busca os usuários que enviam e recebem a transação
        User sender = userService.findUserById(transaction.senderId());
        User receiver = userService.findUserById(transaction.receiverId());

        // Valida a transação
        userService.validateTransaction(sender, receiver, transaction.value());
        // Chama o mock de serviço externo para autorizar a transação
        if (!auhtorizationService.authorizeTransaction(sender, transaction.value())){
            throw new TransactionAuthorizationException("Transação não autorizada.");
        }

        Transaction newTransaction = new Transaction(transaction, sender, receiver);
        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));
        transactionRepository.save(newTransaction);

        //ATUALIZANDO os Users envolvidos na transação
        userService.saveUser(sender);
        userService.saveUser(receiver);

        // Notifica os usuários envolvidos na transação
//        notificationService.sendNotification(sender, "Transação realizada com sucesso");
//        notificationService.sendNotification(receiver, "Transação realizada com sucesso");
        return newTransaction;
    }

    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }




}
