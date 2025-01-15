package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Note;
import com.example.demo.model.User;
import com.example.demo.repository.NoteRepository;
import com.example.demo.repository.UserRepository;

import java.util.List;

@Service
public class NoteService {
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    public NoteService(NoteRepository noteRepository, UserRepository userRepository) {
        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
    }

    public Note createNote(String noteText, String username) {
        User owner = userRepository.findByUsername(username);
        if (owner == null) {
            throw new RuntimeException("User not found");
        }
        Note note = new Note();
        note.setNoteText(noteText);
        note.setOwner(owner);
        return noteRepository.save(note);
    }

    public List<Note> getNotes(String username) {

        User owner = userRepository.findByUsername(username);
        if (owner == null) {
            throw new RuntimeException("User not found");
        }
        return noteRepository.findByOwner(owner);
    }

    public Note updateNote(Long noteId, String newText, String username) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));
        if (!note.getOwner().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized");
        }
        note.setNoteText(newText);
        return noteRepository.save(note);
    }

    public void deleteNote(Long noteId, String username) {
        Note note = noteRepository.findById(noteId)
                .orElseThrow(() -> new RuntimeException("Note not found"));
        if (!note.getOwner().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized");
        }
        noteRepository.delete(note);
    }
}
