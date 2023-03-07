package com.flexpag.paymentscheduler.springdoc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class SpringDocConfiguration {
    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("PaymentSchedule")
                        .description("O projeto consiste em uma API de agendamento de pagamento. A API possui um CRUD de pagamentos que atualiza os pagamentos agendados todos os dias às 00:00.")
                        .contact(new Contact()
                                .name("Gabriel Moraes")
                                .email("Gabriel@loglink.net.br"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://voll.med/api/licenca")));
    }
}
