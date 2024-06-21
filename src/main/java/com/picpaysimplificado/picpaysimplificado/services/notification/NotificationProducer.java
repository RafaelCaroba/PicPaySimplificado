//package com.picpaysimplificado.picpaysimplificado.services.notification;
//
//import com.picpaysimplificado.picpaysimplificado.dtos.NotificationDTO;
//import com.picpaysimplificado.picpaysimplificado.model.transaction.Transaction;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class NotificationProducer {
//
//    @Autowired
//    private KafkaTemplate<String, Transaction> kafkaTemplate;
//
//    public void sendNotification(Transaction transaction) {
//        kafkaTemplate.send("transaction-notification", transaction);
//    }
//}
