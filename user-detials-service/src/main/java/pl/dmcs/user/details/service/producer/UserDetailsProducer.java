package pl.dmcs.user.details.service.producer;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.dmcs.user.details.service.model.UserDetails;
import pl.dmcs.user.details.service.service.UserDetailsService;

@RestController
public class UserDetailsProducer {

    @Value("${queue.name}")
    private String queueName;

    private UserDetailsService userDetailsService;
    private RabbitTemplate rabbitTemplate;

    public UserDetailsProducer(UserDetailsService userDetailsService, RabbitTemplate rabbitTemplate) {
        this.userDetailsService = userDetailsService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @GetMapping(value = "/{id}")
    void getUserDetails(@PathVariable Integer id) {
        rabbitTemplate.convertAndSend(userDetailsService.getUserDetails(id));
    }

}
