package com.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

@Configuration
public class TokenStoreConfig {

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Bean
    @ConditionalOnProperty(prefix = "security.oauth2.token.store", name = "type", havingValue="redis", matchIfMissing = false)
    public TokenStore redisTokenStore(){
        return new RedisTokenStore(redisConnectionFactory);
    }


    @Configuration
    @ConditionalOnProperty(prefix = "security.oauth2.token.store", name = "type", havingValue="jwt", matchIfMissing = true)
    public static class JwtTokenStoreConfig {
        @Bean
        public TokenStore jwtTokenStore(JwtAccessTokenConverter jwtAccessTokenConverter) {
            return new JwtTokenStore(jwtAccessTokenConverter);
        }

        // 必须另外取个名字 不然jwtAccessTokenConverter和TokenEnhancer会冲突
        @Bean(name = "jwtAccessTokenConverter")
        public JwtAccessTokenConverter jwtAccessTokenConverter() {
            JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
            jwtAccessTokenConverter.setSigningKey("wxy");
            return jwtAccessTokenConverter;
        }

        @Bean(name = "jwtTokenEnhancer")
        @ConditionalOnMissingBean(name = "jwtTokenEnhancer")
        public TokenEnhancer jwtTokenEnhancer() {
            return new MyTokenEnhancer();
        }
    }

}
