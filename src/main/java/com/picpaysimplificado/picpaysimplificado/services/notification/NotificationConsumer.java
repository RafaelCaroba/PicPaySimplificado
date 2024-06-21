//package com.picpaysimplificado.picpaysimplificado.services.notification;
//
//import com.picpaysimplificado.picpaysimplificado.dtos.NotificationDTO;
//import com.picpaysimplificado.picpaysimplificado.model.exception.NotificationException;
//import com.picpaysimplificado.picpaysimplificado.model.transaction.Transaction;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.annotation.KafkaListener;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestClient;
//
//@Service
//public class NotificationConsumer {
//
//    @Autowired
//    private RestClient restClient;
//
//    public NotificationConsumer(RestClient.Builder builder) {
//        this.restClient = builder
//                .baseUrl("https://util.devi.tools/api/v1/notify")
//                .build();
//    }
//
//    @KafkaListener(topics = "transaction-notification", groupId = "picpaysimplificado")
//    public void receiveNotification(Transaction transaction) {
//        var response = restClient.get()
//                .retrieve()
//                .toEntity(NotificationDTO.class);
//
//        if (response.getStatusCode().isError() || response.getBody().status().equals("fail")){
//            throw new NotificationException("Error sending notification!");
//        }
//    }
//}
