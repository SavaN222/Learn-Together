package com.nakaradasava.learntogether.service.quiz;

import com.nakaradasava.learntogether.entity.quiz.Answer;
import com.nakaradasava.learntogether.repository.quiz.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswerService {

    private AnswerRepository answerRepository;

    @Autowired
    public AnswerService(AnswerRepository answerRepository) {
        this.answerRepository = answerRepository;
    }

    /**
     * Map all fields to answer object
     * Find which one is correct and set value to true
     * @param answer1 answer field1
     * @param answer2 answer field2
     * @param answer3 answer field3
     * @param answer4 answer field4
     * @param correctAnswer correct answer
     * @return list with answers
     */
    public List<Answer> prepareAnswers(String answer1,
                                      String answer2,
                                      String answer3,
                                      String answer4,
                                      Integer correctAnswer
                                    ) {


        List<Answer> answers = List.of(answer1, answer2, answer3, answer4)
                .stream()
                .map(Answer::new)
                .collect(Collectors.toList());

        for (int i = 0; i < answers.size(); i++) {
            if (correctAnswer == i) {
                answers.get(i).setValue(true);
            }
        }

        return answers;
    }

    public void save(Answer answer) {
        answerRepository.save(answer);
    }
}