package com.cricketGamewithspring.cricketGame.model;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
@Document(collection = "database_sequence")
public class DatabaseSequence {
    @Id
    private String id;
    private int seq;

}
