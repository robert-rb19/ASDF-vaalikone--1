package com.robert.election_machine.dao;

import com.robert.election_machine.utils.JDBCUtils;

import java.sql.*;
import java.util.*;

public class CandidateDAO {

    // Fetch all candidates
    public List<Candidate> getAllCandidates() {
        List<Candidate> candidates = new ArrayList<>();
        String query = "SELECT * FROM candidates";

        try (Connection conn = JDBCUtils.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                candidates.add(new Candidate(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("party"),
                        rs.getString("interests"),
                        rs.getString("motto")
                ));
            }
            System.out.println("Fetched " + candidates.size() + " candidates.");
        } catch (SQLException e) {
            System.err.println("Error fetching all candidates.");
            e.printStackTrace();
        }
        return candidates;
    }

    // Fetch all candidates with answers
    public List<Candidate> getAllCandidatesWithAnswers() {
        List<Candidate> candidates = new ArrayList<>();
        String candidateQuery = "SELECT * FROM candidates";
        String answersQuery = "SELECT * FROM candidate_answers WHERE candidate_id = ?";

        try (Connection conn = JDBCUtils.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(candidateQuery)) {

            while (rs.next()) {
                int candidateId = rs.getInt("id");
                Map<Integer, Integer> answers = new HashMap<>();

                try (PreparedStatement pstmt = conn.prepareStatement(answersQuery)) {
                    pstmt.setInt(1, candidateId);
                    ResultSet answerRs = pstmt.executeQuery();
                    while (answerRs.next()) {
                        answers.put(answerRs.getInt("question_id"), answerRs.getInt("answer"));
                    }
                }

                candidates.add(new Candidate(
                        candidateId,
                        rs.getString("name"),
                        rs.getString("surname"),
                        rs.getString("party"),
                        rs.getString("interests"),
                        rs.getString("motto"),
                        answers
                ));
            }
            System.out.println("Fetched candidates with answers: " + candidates.size());
        } catch (SQLException e) {
            System.err.println("Error fetching candidates with answers.");
            e.printStackTrace();
        }
        return candidates;
    }

    // Fetch a candidate by ID
    public Candidate getCandidateById(int id) {
        String candidateQuery = "SELECT * FROM candidates WHERE id = ?";
        String answersQuery = "SELECT * FROM candidate_answers WHERE candidate_id = ?";
        Candidate candidate = null;

        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement candidateStmt = conn.prepareStatement(candidateQuery)) {

            candidateStmt.setInt(1, id);
            try (ResultSet rs = candidateStmt.executeQuery()) {
                if (rs.next()) {
                    Map<Integer, Integer> answers = new HashMap<>();

                    try (PreparedStatement answersStmt = conn.prepareStatement(answersQuery)) {
                        answersStmt.setInt(1, id);
                        ResultSet answerRs = answersStmt.executeQuery();
                        while (answerRs.next()) {
                            answers.put(answerRs.getInt("question_id"), answerRs.getInt("answer"));
                        }
                    }

                    candidate = new Candidate(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("surname"),
                            rs.getString("party"),
                            rs.getString("interests"),
                            rs.getString("motto"),
                            answers
                    );
                    System.out.println("Fetched candidate ID: " + id);
                } else {
                    System.out.println("Candidate ID " + id + " not found.");
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching candidate by ID.");
            e.printStackTrace();
        }
        return candidate;
    }

    // Find the best matching candidate based on user answers
    public Candidate findBestMatch(Map<Integer, Integer> userAnswers) {
        List<Candidate> candidates = getAllCandidatesWithAnswers();
        Candidate bestMatch = null;
        int bestScore = Integer.MAX_VALUE;

        for (Candidate candidate : candidates) {
            Map<Integer, Integer> candidateAnswers = candidate.getAnswers();
            int score = 0;

            for (Map.Entry<Integer, Integer> userAnswer : userAnswers.entrySet()) {
                int questionId = userAnswer.getKey();
                int userResponse = userAnswer.getValue();
                int candidateResponse = candidateAnswers.getOrDefault(questionId, 3); 

                score += Math.abs(userResponse - candidateResponse); 
            }

            if (score < bestScore) { 
                bestScore = score;
                bestMatch = candidate;
            }
        }

        return bestMatch;
    }


    // Update candidate details
    public boolean updateCandidate(Candidate candidate) {
        String query = "UPDATE candidates SET name = ?, surname = ?, party = ?, interests = ?, motto = ? WHERE id = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, candidate.getName());
            pstmt.setString(2, candidate.getSurname());
            pstmt.setString(3, candidate.getParty());
            pstmt.setString(4, candidate.getInterests());
            pstmt.setString(5, candidate.getMotto());
            pstmt.setInt(6, candidate.getId());

            int rowsUpdated = pstmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.err.println("Error updating candidate.");
            e.printStackTrace();
        }
        return false;
    }
    
 // Update candidate's answer for a specific question
    public void updateCandidateAnswer(int candidateId, int questionId, int answer) {
        String query = "INSERT INTO candidate_answers (candidate_id, question_id, answer) " +
                       "VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE answer = ?";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, candidateId);
            pstmt.setInt(2, questionId);
            pstmt.setInt(3, answer);
            pstmt.setInt(4, answer);

            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Updated answer for Candidate ID: " + candidateId + ", Question ID: " + questionId + 
                               " - Rows affected: " + rowsAffected);
        } catch (SQLException e) {
            System.err.println("Error updating candidate answer. Candidate ID: " + candidateId + ", Question ID: " + questionId);
            e.printStackTrace();
        }
    }

    // Delete a candidate and their answers
    public boolean deleteCandidate(int id) {
        String deleteAnswersQuery = "DELETE FROM candidate_answers WHERE candidate_id = ?";
        String deleteCandidateQuery = "DELETE FROM candidates WHERE id = ?";
        try (Connection conn = JDBCUtils.getConnection()) {
            try (PreparedStatement deleteAnswersStmt = conn.prepareStatement(deleteAnswersQuery);
                 PreparedStatement deleteCandidateStmt = conn.prepareStatement(deleteCandidateQuery)) {

                deleteAnswersStmt.setInt(1, id);
                deleteAnswersStmt.executeUpdate();

                deleteCandidateStmt.setInt(1, id);
                return deleteCandidateStmt.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.err.println("Error deleting candidate.");
            e.printStackTrace();
        }
        return false;
    }

    // Insert a new candidate and return the generated ID
    public int insertCandidate(Candidate candidate) {
        String query = "INSERT INTO candidates (name, surname, party, interests, motto) VALUES (?, ?, ?, ?, ?)";
        int generatedId = -1;

        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, candidate.getName());
            pstmt.setString(2, candidate.getSurname());
            pstmt.setString(3, candidate.getParty());
            pstmt.setString(4, candidate.getInterests());
            pstmt.setString(5, candidate.getMotto());

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1);
                    }
                }
            }

        } catch (SQLException e) {
            System.err.println("Error inserting new candidate.");
            e.printStackTrace();
        }
        return generatedId;
    }
}
