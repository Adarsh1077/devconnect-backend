package com.adarsh.second.controller;


import com.adarsh.second.entity.JournalEntry;
import com.adarsh.second.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    private final JournalEntryService journalEntryService;

    @Autowired
    public JournalEntryController(JournalEntryService journalEntryService) {
        this.journalEntryService = journalEntryService;
    }

    @GetMapping
    public List<JournalEntry> getAll() {
        return journalEntryService.getAll();
    }

    @PostMapping
    public JournalEntry createJournalEntry(@RequestBody JournalEntry journalEntry) {
        journalEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(journalEntry);
        return journalEntry;
    }

    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> findById(@PathVariable ObjectId myId) {
       Optional<JournalEntry> journalEntry = journalEntryService.findById(myId);
       if(journalEntry.isPresent()){
           return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public boolean deleteJournalEntry(@PathVariable ObjectId myId) {
        journalEntryService.deleteById(myId);
        return true;
    }

    @PutMapping("id/{myId}")
    public JournalEntry updateJournalEntry(@PathVariable ObjectId myId, @RequestBody JournalEntry myEntry) {
        JournalEntry journalEntry = journalEntryService.findById(myId).orElse(null);
        if(journalEntry != null){
            journalEntry.setTitle(myEntry.getTitle() != null && !myEntry.getTitle().isEmpty()? myEntry.getTitle() : journalEntry.getTitle());
            journalEntry.setContent(myEntry.getContent() != null && !myEntry.getContent().isEmpty()? myEntry.getContent() : journalEntry.getContent());
        }
        journalEntryService.saveEntry(journalEntry);
        return journalEntry;
    }
}
