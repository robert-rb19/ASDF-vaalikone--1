package com.robert.election_machine.web;

import com.robert.election_machine.dao.Candidate;
import com.robert.election_machine.dao.CandidateDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SeeResultsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private CandidateDAO candidateDAO = new CandidateDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("Processing answers in SeeResultsServlet");

        // Extract answers from form
        Map<Integer, Integer> userAnswers = new HashMap<>();
        try {
            for (String param : request.getParameterMap().keySet()) {
                if (param.startsWith("question_")) {
                    int questionId = Integer.parseInt(param.replace("question_", ""));
                    int answer = Integer.parseInt(request.getParameter(param));
                    userAnswers.put(questionId, answer);
                }
            }
            System.out.println("User Answers: " + userAnswers);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("test");
            return;
        }

        // Find the best match candidate
        Candidate bestMatch = candidateDAO.findBestMatch(userAnswers);

        if (bestMatch != null) {
            request.setAttribute("bestMatch", bestMatch);
            request.getRequestDispatcher("/WEB-INF/results.jsp").forward(request, response);
        } else {
            System.out.println("No match found.");
            response.sendRedirect("test");
        }
    }
}
