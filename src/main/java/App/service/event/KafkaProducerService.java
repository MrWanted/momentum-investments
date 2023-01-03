package App.service.event;

import App.utils.AppConstants;
import App.vo.WithdrawVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class KafkaProducerService {
    private KafkaTemplate<String, WithdrawVO> kafkaTemplate;

    public void sendMessage(WithdrawVO message){
       log.info(String.format("Message sent -> %s", message));
        kafkaTemplate.send(AppConstants.TOPIC_NAME, message);
    }
}
