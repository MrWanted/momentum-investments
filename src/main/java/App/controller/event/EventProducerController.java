package App.controller.event;

import App.service.event.KafkaProducerService;
import App.utils.AppConstants;
import App.vo.WithdrawVO;
import lombok.Data;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;


@Data
@RequestMapping("/rest/api/events")
@RestController
public class EventProducerController {
    private final KafkaProducerService service;

    @GetMapping("/publish/")
    public ResponseEntity publish(@RequestBody WithdrawVO message){
        service.sendMessage(message);
        return ResponseEntity.ok("Message sent to kafka topic");
    }
}
