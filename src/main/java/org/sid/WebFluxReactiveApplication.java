package org.sid;

import org.sid.dao.SocieteRepository;
import org.sid.dao.TransactionRepository;
import org.sid.entities.Societe;
import org.sid.entities.Transaction;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Instant;
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
            societeRepository.deleteAll().subscribe(null, null, () -> {
                Stream.of("A", "B", "C", "D").forEach(bank -> {
                    societeRepository.save(new Societe(bank, bank, 100 + Math.random() * 900)).subscribe(societe -> {
                        System.out.println(societe.toString());
                    });
                });
                transactionRepository.deleteAll().subscribe(null, null, ()-> {
                    Stream.of("A", "B", "C", "D").forEach(bank -> {
                        societeRepository.findById(bank).subscribe(soc -> {
                            for (int i = 0; i < 10; i++) {
                                Transaction transaction = new Transaction();
                                transaction.setInstant(Instant.now());
                                transaction.setPrice(soc.getPrice() * (1 + (Math.random() * 12 - 6) / 100));
                                transaction.setSociete(soc);
                                transactionRepository.save(transaction).subscribe(t -> {
                                    System.out.println("transaction => " + t.toString());
                                });
                            }
                        });
                    });
                });
            });
            System.out.println("....... passe pour éxécuter ce bloc, non bloquant");
        };
    }

}
