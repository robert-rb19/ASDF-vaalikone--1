package com.robert.election_machine.web;

import com.robert.election_machine.dao.Question;
import com.robert.election_machine.dao.QuestionDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AddQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private QuestionDAO questionDAO = new QuestionDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Forward to addQuestion.jsp
        request.getRequestDispatcher("/WEB-INF/addQuestion.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String questionText = request.getParameter("text");
        
        System.out.println("Received question text: " + questionText); // Debug print

        if (questionText != null && !questionText.trim().isEmpty()) {
            // Create a new Question object
            Question newQuestion = new Question();
            newQuestion.setText(questionText);

            // Save to database
            boolean saved = questionDAO.saveQuestion(newQuestion);
            if (saved) {
                System.out.println("New question saved: " + questionText);
            } else {
                System.err.println("Failed to save the new question.");
            }
        } else {
            System.out.println("Empty question text, not saved.");
        }

        response.sendRedirect("edit-questions");
    }
}
