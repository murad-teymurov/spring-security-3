package com.company.ApiLogin.security;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties("security.jwt") // if you write wrong give 403 forbidden!!!
public class JwtProperties {

    private String secretKey;
}
