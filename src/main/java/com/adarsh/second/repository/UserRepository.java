package com.adarsh.second.repository;

import com.adarsh.second.entity.JournalEntry;
import com.adarsh.second.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface UserEntryRepository extends MongoRepository<User, ObjectId> {
}
