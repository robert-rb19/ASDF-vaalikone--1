package com.robert.election_machine.web;

import com.robert.election_machine.dao.Candidate;
import com.robert.election_machine.dao.CandidateDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class SeeCandidatesServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CandidateDAO candidateDAO = new CandidateDAO();

        // Fetch the list of candidates
        List<Candidate> candidates = candidateDAO.getAllCandidates();
        System.out.println("Fetched " + candidates.size() + " candidates."); // Debugging

        // Set candidates as a request attribute
        request.setAttribute("candidates", candidates);

        // Forward to JSP
        request.getRequestDispatcher("/WEB-INF/seeCandidates.jsp").forward(request, response);
    }
}
