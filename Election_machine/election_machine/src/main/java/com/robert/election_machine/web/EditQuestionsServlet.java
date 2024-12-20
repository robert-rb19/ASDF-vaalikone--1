package com.robert.election_machine.web;

import com.robert.election_machine.dao.Question;
import com.robert.election_machine.dao.QuestionDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditQuestionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private QuestionDAO questionDAO = new QuestionDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Fetch all questions
        List<Question> questions = questionDAO.getAllQuestions();

        // Set questions as request attribute
        request.setAttribute("questions", questions);

        // Forward to JSP
        request.getRequestDispatcher("/WEB-INF/editQuestions.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                int questionId = Integer.parseInt(idParam);
                questionDAO.deleteQuestion(questionId);
                System.out.println("Deleted question ID: " + questionId);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        response.sendRedirect("edit-questions");
    }
}
