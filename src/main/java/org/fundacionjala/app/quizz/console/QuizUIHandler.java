package org.fundacionjala.app.quizz.console;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fundacionjala.app.quizz.model.Answer;
import org.fundacionjala.app.quizz.model.Question;
import org.fundacionjala.app.quizz.model.Quiz;
import org.fundacionjala.app.quizz.model.QuizAnswers;
import org.fundacionjala.app.quizz.console.util.InputReader;
import org.fundacionjala.app.quizz.persistence.IncorrectPathException;
import org.fundacionjala.app.quizz.persistence.QuizPersistence;

public class QuizUIHandler {

    public static Quiz createQuiz() {
       Quiz quiz = new QuizUIMenu().handleCreateQuiz();
        QuizPersistence.writeJsonFile(quiz);
        return quiz;
    }

    public static QuizAnswers fillQuiz(Quiz quiz) {
        QuizAnswers quizAnswers = new QuizAnswers(quiz);
        QuestionInputHandler questionHandler = new QuestionInputHandler();

        System.out.println("Quiz: " + quiz.getTitle());
        for (Question question : quiz.getQuestions()) {
            Set<String> answers = questionHandler.askQuestionValue(question);
            Answer answer = new Answer(question, answers);
            quizAnswers.addAnswer(answer);
        }

        return quizAnswers;
    }

    public static void showQuiz(QuizAnswers quizAnswers) {
        System.out.println(quizAnswers.getQuiz().getTitle());
        System.out.println("=============================================");

        for (Answer answer : quizAnswers.getAnswers()) {
            System.out.println(answer);
        }

        System.out.println("=============================================");
        System.out.println("Press ENTER to continue");
        InputReader.readLine();
    }

    public static Quiz loadQuizFromJson() {
        
        System.out.println("please write the quiz path");
        String path = InputReader.readLine();

        try {
            return QuizPersistence.readJsonFile(path);
        } catch (IncorrectPathException ex) {
            System.out.println(ex.getMessage());
        }
        return null;


    }
}
