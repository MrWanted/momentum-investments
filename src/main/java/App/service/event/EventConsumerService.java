package App.service.event;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class EventConsumerService {
   // @KafkaListener(topics = "investor_withdrawals")
    public void receive(String message) {
        System.out.println(message);
    }
}
