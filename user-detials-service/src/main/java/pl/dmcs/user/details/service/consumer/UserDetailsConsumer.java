package pl.dmcs.user.details.service.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import pl.dmcs.user.details.service.model.UserDetails;
import pl.dmcs.user.details.service.service.UserDetailsService;

@Component
public class UserDetailsConsumer {

    private UserDetailsService userDetailsService;


    public UserDetailsConsumer(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @RabbitListener(queues = "${queue.name}")
    private void addUserDetails(UserDetails userDetails) {
        userDetailsService.addUserDetails(userDetails);
    }
}
