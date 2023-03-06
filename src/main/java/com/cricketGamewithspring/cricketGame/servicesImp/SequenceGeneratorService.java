package com.cricketGamewithspring.cricketGame.servicesImp;

import com.cricketGamewithspring.cricketGame.model.DatabaseSequence;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

@Data
@Service

public class SequenceGeneratorService {
    @Autowired
    private MongoOperations mongoOperations;

    public int generateSequence(String seqName) {
        DatabaseSequence counter = mongoOperations.findAndModify(
                query(where("_id").is(seqName)),
                new Update().inc("seq", 1),
                options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return counter.getSeq();
    }
}
