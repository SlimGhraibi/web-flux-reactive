package org.sid.web;

import org.sid.dao.SocieteRepository;
import org.sid.entities.Societe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController

public class SocieteReactiveRestController {
    @Autowired
    private SocieteRepository societeRepository;

    @GetMapping(value = "/societes")
    public Flux<Societe> findAll() {
        return societeRepository.findAll();
    }

    @GetMapping(value = "/societes/{id}")
    public Mono<Societe> findOne(@PathVariable String id) {
        return societeRepository.findById(id);
    }

    @PostMapping(value = "/societe")
    public Mono<Societe> save(@RequestBody Societe societe) {
        return societeRepository.save(societe);
    }

    @DeleteMapping(value = "/societe/{id}")
    public Mono<Void> save(@PathVariable String id) {
        return societeRepository.deleteById(id);
    }

    @PutMapping(value = "/societe/{id}")
    public Mono<Societe> update(@RequestBody Societe societe, @PathVariable String id) {
        societe.setId(id);
        return societeRepository.save(societe);
    }

}