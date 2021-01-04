package org.sid.web;

import org.sid.dao.TransactionRepository;
import org.sid.entities.Societe;
import org.sid.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class TransactionReactiveRestController {
    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping("/transactions")
    public Flux<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @GetMapping("/transactions/{id}")
    public Mono<Transaction> findOne(@PathVariable String id) {
        return transactionRepository.findById(id);
    }

    @PostMapping("/transactions")
    public Mono<Transaction> save(@RequestBody Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @DeleteMapping("/transactions/{id}")
    public Mono<Void> save(@PathVariable String id) {
        return transactionRepository.deleteById(id);
    }

    @PutMapping("/transactions/{id}")
    public Mono<Transaction> update(@RequestBody Transaction transaction, @PathVariable String id) {
        transaction.setId(id);
        return transactionRepository.save(transaction);
    }

    // Cette methode va retourner les données sous format TEXT_EVENT_STREAM_VALUE (stream)
    // On recoit les data un par un
    @GetMapping(value = "/streamTransactions", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Transaction> StreamTransactions() {
        return transactionRepository.findAll();
    }

    @GetMapping(value = "/transactionBySoc/{id}")
    public Flux<Transaction> transactionBySoc(@PathVariable String id) {
        Societe societe = new Societe();
        societe.setId(id);
        return transactionRepository.findBySociete(societe);
    }
}
