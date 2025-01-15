package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.model.Question;
import com.example.demo.model.User;
import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.model.Role;

import java.util.List;

@Service
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public QuestionService(QuestionRepository questionRepository, UserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    public Question createQuestion(String username, Question question) {
        User creator = userRepository.findByUsername(username);
        if (creator == null) {
            throw new RuntimeException("User not found");
        }
        if (!creator.getRole().equals(Role.INSTRUCTOR)) {
            throw new RuntimeException("Only instructors can create questions");
        }
        question.setCreatedBy(creator);
        return questionRepository.save(question);
    }

    public Question updateQuestion(Long id, String username, Question updatedQuestion) {
        Question existingQuestion = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        if (!existingQuestion.getCreatedBy().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized");
        }
        existingQuestion.setDescription(updatedQuestion.getDescription());
        existingQuestion.setAnswer(updatedQuestion.getAnswer());
        existingQuestion.setOptions(updatedQuestion.getOptions());
        existingQuestion.setExplanation(updatedQuestion.getExplanation());
        return questionRepository.save(existingQuestion);
    }

    public void deleteQuestion(Long id, String username) {
        Question question = questionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Question not found"));
        if (!question.getCreatedBy().getUsername().equals(username)) {
            throw new RuntimeException("Unauthorized");
        }
        questionRepository.delete(question);
    }
}
