package pl.dmcs.common;

import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;

@Getter
@ToString
public class JwtAuthenticationConfig {

    @Value("${custom.security.jwt.url:/login}")
    private String url;

    @Value("${custom.security.jwt.header:Authorization}")
    private String header;

    @Value("${custom.security.jwt.prefix:Bearer}")
    private String prefix;

    @Value("${custom.security.jwt.expiration:#{24*60*60}}")
    private int expiration; // default 24 hours

    @Value("${custom.security.jwt.secret}")
    private String secret;
}