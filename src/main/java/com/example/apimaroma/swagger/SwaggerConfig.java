package com.example.apimaroma.swagger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("public-api")
                .apiInfo(apiInfo()).select().paths(postPaths()).build();
    }

    private Predicate<String> postPaths() {
        return or(regex("/api/v1.*"));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Maroma API")
                .description("Mais en fait, qu'est-ce qu'une API REST ?\n" +
                        "API signifie Application Programming Interface (Interface de programmation d'application). Une API est un ensemble de règles qui permettent aux programmes de communiquer entre eux, exposant les données et les fonctionnalités sur Internet dans un format cohérent.\n" +
                        "\n" +
                        "REST signifie Representational State Transfer (Transfert d'état de représentation). Il s'agit d'un modèle architectural qui décrit comment les systèmes distribués peuvent exposer une interface cohérente. Lorsque les gens utilisent le terme « API REST », ils font généralement référence à une API accessible via le protocole HTTP à un ensemble prédéfini d'URL.\n" +
                        "\n" +
                        "Ces URL représentent diverses ressources : toute information ou tout contenu accessible à cet emplacement, qui peut être renvoyé sous forme de fichiers JSON, HTML, audio ou images. Souvent, les ressources ont une ou plusieurs méthodes qui peuvent être exécutées sur elles via HTTP, comme GET, POST, PUT et DELETE. L'action représentée par la première et la dernière de ces méthodes est claire, mais POST et PUT ont des significations spécifiques. La façon dont elles sont définies est complexe, mais la règle générale est la suivante : utilisez POST pour créer des ressources et PUT pour mettre à jour des ressources.\n" +
                        "\n" )
                .version("1.0")
                .build();
    }

}