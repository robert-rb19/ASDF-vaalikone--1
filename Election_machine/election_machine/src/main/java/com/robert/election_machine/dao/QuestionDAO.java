package com.robert.election_machine.dao;

import com.robert.election_machine.utils.JDBCUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {

    // Retrieve a question by ID
    public Question getQuestionById(int id) {
        String sqlQuery = "SELECT id, text FROM questions WHERE id = ?";
        Question question = null;

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sqlQuery)) {

            pstmt.setInt(1, id);
            try (ResultSet resultSet = pstmt.executeQuery()) {
                if (resultSet.next()) {
                    question = new Question(
                        resultSet.getInt("id"),
                        resultSet.getString("text")
                    );
                    System.out.println("Fetched Question by ID: " + id + ", Text: " + question.getText());
                } else {
                    System.out.println("Question ID " + id + " not found.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching question by ID: " + id);
            e.printStackTrace();
        }

        return question;
    }

    // Retrieve all questions
    public List<Question> getAllQuestions() {
        List<Question> questions = new ArrayList<>();
        String sqlQuery = "SELECT id, text FROM questions";

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sqlQuery);
             ResultSet resultSet = pstmt.executeQuery()) {

            while (resultSet.next()) {
                questions.add(new Question(
                    resultSet.getInt("id"),
                    resultSet.getString("text")
                ));
            }
            System.out.println("Fetched All Questions: Total = " + questions.size());
        } catch (SQLException e) {
            System.err.println("Error fetching all questions.");
            e.printStackTrace();
        }

        return questions;
    }

    // Save or update a question
    public boolean saveQuestion(Question question) {
        String sqlQuery;

        if (question.getId() > 0) {
            // Update existing question
            sqlQuery = "UPDATE questions SET text = ? WHERE id = ?";
        } else {
            // Insert new question
            sqlQuery = "INSERT INTO questions (text) VALUES (?)";
        }

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sqlQuery)) {

            pstmt.setString(1, question.getText());

            if (question.getId() > 0) {
                pstmt.setInt(2, question.getId());
                System.out.println("Updating Question ID: " + question.getId());
            } else {
                System.out.println("Inserting New Question: " + question.getText());
            }

            return pstmt.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error saving/updating question: " + question.getText());
            e.printStackTrace();
        }

        return false;
    }

    // Delete a question by ID
    public boolean deleteQuestion(int id) {
        String sqlQuery = "DELETE FROM questions WHERE id = ?";

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sqlQuery)) {

            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Deleted Question ID: " + id);
                return true;
            } else {
                System.out.println("No Question found with ID: " + id);
            }

        } catch (SQLException e) {
            System.err.println("Error deleting question ID: " + id);
            e.printStackTrace();
        }

        return false;
    }
}
