package com.example.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import com.example.demo.model.Note;
import com.example.demo.service.NoteService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public Note createNote(@AuthenticationPrincipal UserDetails userDetails,
            @RequestBody Map<String, String> request) {
        return noteService.createNote(request.get("noteText"), userDetails.getUsername());
    }

    @GetMapping
    public List<Note> getNotes(@AuthenticationPrincipal UserDetails userDetails) {
        return noteService.getNotes(userDetails.getUsername());
    }

    @PatchMapping("/{noteId}")
    public Note updateNote(@AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long noteId,
            @RequestBody Map<String, String> request) {
        return noteService.updateNote(noteId, request.get("noteText"), userDetails.getUsername());
    }

    @DeleteMapping("/{noteId}")
    public Map<String, String> deleteNote(@AuthenticationPrincipal UserDetails userDetails,
            @PathVariable Long noteId) {
        noteService.deleteNote(noteId, userDetails.getUsername());
        return Map.of("message", "Note deleted");
    }
}
