package com.PicPaySImplificado.services;

import com.PicPaySImplificado.domain.User.User;
import com.PicPaySImplificado.dtos.NotificationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NotificationService {
    @Autowired
    private RestTemplate restTemplate;

    public void sendNotification(User user, String message) throws Exception {
        String email = user.getEmail();
        NotificationDTO notificationResquet = new NotificationDTO(email,message);

        ResponseEntity<String> notificationResponse =  restTemplate.postForEntity("https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6", notificationResquet,String.class);

        if(!(notificationResponse.getStatusCode() == HttpStatus.OK)){
            System.out.println("erro ao enviar notificação");
            throw new Exception("Serviço fora do ar");
        }
    }
}
