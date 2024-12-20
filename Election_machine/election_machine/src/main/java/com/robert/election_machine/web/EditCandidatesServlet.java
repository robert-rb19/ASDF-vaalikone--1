package com.robert.election_machine.web;

import com.robert.election_machine.dao.Candidate;
import com.robert.election_machine.dao.CandidateDAO;
import com.robert.election_machine.dao.Question;
import com.robert.election_machine.dao.QuestionDAO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditCandidatesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private CandidateDAO candidateDAO = new CandidateDAO();
    private QuestionDAO questionDAO = new QuestionDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Fetch all candidates and questions
            List<Candidate> candidates = candidateDAO.getAllCandidatesWithAnswers();
            List<Question> questions = questionDAO.getAllQuestions();

            // Pass both candidates and questions to JSP
            request.setAttribute("candidates", candidates);
            request.setAttribute("questions", questions);

            // Forward to editCandidates.jsp
            request.getRequestDispatcher("/WEB-INF/editCandidates.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unable to load candidates or questions.");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String candidateIdParam = request.getParameter("id");

        if (candidateIdParam != null && !candidateIdParam.trim().isEmpty()) {
            try {
                int candidateId = Integer.parseInt(candidateIdParam);
                System.out.println("Deleting candidate with ID: " + candidateId);
                candidateDAO.deleteCandidate(candidateId);
            } catch (NumberFormatException e) {
                System.err.println("Invalid candidate ID: " + candidateIdParam);
                e.printStackTrace();
            }
        }

        response.sendRedirect("edit-candidates");
    }
}
