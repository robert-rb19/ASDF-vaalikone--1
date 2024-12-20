package com.robert.election_machine.web;

import com.robert.election_machine.dao.Question;
import com.robert.election_machine.dao.QuestionDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditQuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private QuestionDAO questionDAO = new QuestionDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        System.out.println("EditQuestionServlet: Received GET request with ID: " + idParam);

        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                int questionId = Integer.parseInt(idParam);

                // Fetch the question
                Question question = questionDAO.getQuestionById(questionId);
                if (question != null) {
                    System.out.println("Question fetched: " + question.getText());
                    request.setAttribute("question", question);

                    // Forward to the form JSP
                    request.getRequestDispatcher("/WEB-INF/editQuestionForm.jsp").forward(request, response);
                } else {
                    System.out.println("Question not found with ID: " + questionId);
                    response.sendRedirect("edit-questions");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid question ID format: " + idParam);
                response.sendRedirect("edit-questions");
            }
        } else {
            System.out.println("Missing ID parameter in GET request");
            response.sendRedirect("edit-questions");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");
        String questionText = request.getParameter("text");

        System.out.println("EditQuestionServlet: Received POST request. ID: " + idParam + ", Text: " + questionText);

        if (questionText != null && !questionText.trim().isEmpty()) {
            try {
                if (idParam != null && !idParam.trim().isEmpty()) {
                    int questionId = Integer.parseInt(idParam);

                    // Update the question
                    Question updatedQuestion = new Question(questionId, questionText);
                    boolean success = questionDAO.saveQuestion(updatedQuestion);

                    if (success) {
                        System.out.println("Question successfully updated: ID = " + questionId);
                    } else {
                        System.out.println("Failed to update question: ID = " + questionId);
                    }
                } else {
                    // New question scenario (Add)
                    Question newQuestion = new Question(0, questionText);
                    boolean success = questionDAO.saveQuestion(newQuestion);

                    if (success) {
                        System.out.println("New question successfully added: " + questionText);
                    } else {
                        System.out.println("Failed to add new question");
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid question ID format: " + idParam);
                e.printStackTrace();
            }
        } else {
            System.out.println("Question text is missing or invalid");
        }

        response.sendRedirect("edit-questions");
    }
}
