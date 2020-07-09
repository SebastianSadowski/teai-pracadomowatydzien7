package pl.sadowski.teaipracadomowatydzien7.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

@Bean
    public Docket getDocket() {
    return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(getApiInfo())
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(getPaths())
            .build();
    }
private Predicate<String> getPaths(){
    return or(
            regex("/cars"),
            regex("/cars.*")
    );
}
private ApiInfo getApiInfo(){
    return new ApiInfoBuilder()
            .title("Tworzenie efektywnych aplikacji internetowych")
            .description("Praca domowa tydzien 7")
            .contact(new Contact("Sebastian Sadowski", "null", "null"))
            .version("FINAL")
            .build();

};
}
