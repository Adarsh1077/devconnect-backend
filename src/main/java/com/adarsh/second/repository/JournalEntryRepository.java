package com.adarsh.second.repository;

import com.adarsh.second.entity.JournalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface JournalEntryRepository extends MongoRepository<JournalEntry, ObjectId> {
}
