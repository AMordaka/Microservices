package pl.dmcs.security.auth;

import org.h2.tools.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import java.sql.SQLException;

@EnableDiscoveryClient
@SpringBootApplication
public class AuthCenterApplication {

    @Bean(initMethod = "start",destroyMethod = "stop")
    public Server inMemoryH2DatabaseServer() throws SQLException {
        return Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9090");
    }

    public static void main(String[] args) {
        SpringApplication.run(AuthCenterApplication.class, args);
    }
}
