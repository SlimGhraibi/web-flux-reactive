package org.sid.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.time.Instant;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transaction {
    @Id
    private String id;
    private Instant instant;
    private double price;
    // @DBRef
    // @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Societe societe;
}
