package sit.int202.classicmodels_2567.configs;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sit.int202.classicmodels_2567.filters.AuthenticationFilter;
import sit.int202.classicmodels_2567.filters.LoggerFilter;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean loggerFilter() {
        final FilterRegistrationBean reg = new FilterRegistrationBean(new LoggerFilter());
        reg.addUrlPatterns("/products");
        reg.addUrlPatterns("/offices");

        reg.setOrder(1); //defines filter execution order
        return reg;
    }
    @Bean
    public FilterRegistrationBean authenticationFilter() {
        final FilterRegistrationBean reg = new FilterRegistrationBean(new AuthenticationFilter());
        reg.addUrlPatterns("/products");
        reg.addUrlPatterns("/offices");
        reg.addUrlPatterns("/customers");
        reg.setOrder(0); //defines filter execution order
        return reg;
    }
}
