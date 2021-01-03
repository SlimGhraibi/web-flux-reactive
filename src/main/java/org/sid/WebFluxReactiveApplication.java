package org.sid;

import org.sid.dao.SocieteRepository;
import org.sid.dao.TransactionRepository;
import org.sid.entities.Societe;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class WebFluxReactiveApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebFluxReactiveApplication.class, args);
    }

    // Bean de test au démarrage de l'application
    @Bean
    CommandLineRunner start(SocieteRepository societeRepository, TransactionRepository transactionRepository) {
        return args -> {
            Stream.of("SG", "HSBC", "BOURSORAMA", "AXA").forEach(bank -> {
                societeRepository.save(new Societe(bank, bank, 100 + Math.random() * 900)).subscribe(societe -> {
                    System.out.println(societe.toString());
                });
            });
        };
    }

}
