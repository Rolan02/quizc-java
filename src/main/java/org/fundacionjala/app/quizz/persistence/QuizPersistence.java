/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fundacionjala.app.quizz.persistence;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.stream.JsonReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import org.fundacionjala.app.quizz.model.Quiz;

/**
 *
 * @author roland
 */
public class QuizPersistence {

    private static String DEFAULT_PATH = "./myQuiz.json";
    public static Quiz readJsonFile(String path) throws IncorrectPathException {
        Gson gson = new Gson();
        Quiz quiz = null;
        try ( JsonReader reader = new JsonReader(new FileReader(path))) {
            quiz = gson.fromJson(reader, Quiz.class);
        } catch (FileNotFoundException exception) {

            throw new IncorrectPathException(exception.getMessage());

        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return quiz;
    }

    public static Quiz GetQuiz() throws IncorrectPathException {
        return readJsonFile(DEFAULT_PATH);
    }
    public static void writeJsonFile(Quiz quiz) {
        Gson gson = new Gson();
        try ( Writer writer = new FileWriter(DEFAULT_PATH)) {
            gson.toJson(quiz, writer);
        } catch (JsonIOException | IOException exception) {
            exception.printStackTrace();
        }
    }
}
