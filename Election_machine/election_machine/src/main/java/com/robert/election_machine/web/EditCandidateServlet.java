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

public class EditCandidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private CandidateDAO candidateDAO = new CandidateDAO();
    private QuestionDAO questionDAO = new QuestionDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idParam = request.getParameter("id");

        if (idParam != null && !idParam.trim().isEmpty()) {
            try {
                int candidateId = Integer.parseInt(idParam);

                // Fetch candidate details and questions
                Candidate candidate = candidateDAO.getCandidateById(candidateId);
                List<Question> questions = questionDAO.getAllQuestions();

                // Set attributes for JSP
                request.setAttribute("candidate", candidate);
                request.setAttribute("questions", questions);

                // Forward to edit form JSP
                request.getRequestDispatcher("/WEB-INF/editCandidateForm.jsp").forward(request, response);

            } catch (NumberFormatException e) {
                System.out.println("Invalid candidate ID: " + idParam);
                response.sendRedirect("edit-candidates");
            }
        } else {
            System.out.println("Candidate ID missing, redirecting to edit-candidates");
            response.sendRedirect("edit-candidates");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String party = request.getParameter("party");
            String interests = request.getParameter("interests");
            String motto = request.getParameter("motto");

            // Update candidate
            Candidate updatedCandidate = new Candidate(id, name, surname, party, interests, motto);
            candidateDAO.updateCandidate(updatedCandidate);

            // Update answers
            List<Question> questions = questionDAO.getAllQuestions();
            for (Question question : questions) {
                String answerParam = request.getParameter("answer_" + question.getId());
                int answer = (answerParam != null) ? Integer.parseInt(answerParam) : 0;
                candidateDAO.updateCandidateAnswer(id, question.getId(), answer);
            }

            response.sendRedirect("edit-candidates");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("edit-candidates");
        }
    }
}
