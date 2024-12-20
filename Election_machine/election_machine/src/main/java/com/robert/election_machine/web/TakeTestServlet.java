package com.robert.election_machine.web;

import com.robert.election_machine.dao.Question;
import com.robert.election_machine.dao.QuestionDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public class TakeTestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        QuestionDAO questionDAO = new QuestionDAO();
        List<Question> questions = questionDAO.getAllQuestions();

        request.setAttribute("questions", questions);
        request.getRequestDispatcher("/WEB-INF/takeTest.jsp").forward(request, response);
    }
}
