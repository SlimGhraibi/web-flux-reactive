package org.sid.entities;

import lombok.AllArgsConstructor;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
// @Entity pour le mapping O/R
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Societe {
    @Id
    private String id;
    private String name;
    private double price;
}
