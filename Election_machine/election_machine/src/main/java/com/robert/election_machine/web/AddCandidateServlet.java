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
import java.util.HashMap;
import java.util.List;

public class AddCandidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    private CandidateDAO candidateDAO = new CandidateDAO();
    private QuestionDAO questionDAO = new QuestionDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("AddCandidateServlet: Displaying empty form for new candidate");

        // Fetch questions for the form
        List<Question> questions = questionDAO.getAllQuestions();

        // Set empty candidate and questions as attributes
        request.setAttribute("candidate", new Candidate(0, "", "", "", "", "", new HashMap<>()));
        request.setAttribute("questions", questions);

        // Forward to the form JSP
        request.getRequestDispatcher("/WEB-INF/editCandidateForm.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Fetch input parameters
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String party = request.getParameter("party");
            String interests = request.getParameter("interests");
            String motto = request.getParameter("motto");

            // Create the new candidate and insert into database
            Candidate newCandidate = new Candidate(0, name, surname, party, interests, motto, new HashMap<>());
            int newCandidateId = candidateDAO.insertCandidate(newCandidate);

            // Save answers for the new candidate
            List<Question> questions = questionDAO.getAllQuestions();
            for (Question question : questions) {
                String answerParam = request.getParameter("answer_" + question.getId());
                int answer = (answerParam != null) ? Integer.parseInt(answerParam) : 0;
                candidateDAO.updateCandidateAnswer(newCandidateId, question.getId(), answer);
            }

            response.sendRedirect("edit-candidates");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("edit-candidates");
        }
    }
}
