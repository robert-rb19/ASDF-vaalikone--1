package com.robert.election_machine.dao;

import java.util.Map;

public class Candidate {
    private int id;
    private String name;
    private String surname;
    private String party;
    private String interests;
    private String motto;
    private Map<Integer, Integer> answers;

    // (for see-candidates)
    public Candidate(int id, String name, String surname, String party, String interests, String motto) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.party = party;
        this.interests = interests;
        this.motto = motto;
    }

    // (for edit-candidates with answers)
    public Candidate(int id, String name, String surname, String party, String interests, String motto, Map<Integer, Integer> answers) {
        this(id, name, surname, party, interests, motto);
        this.answers = answers;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getParty() {
        return party;
    }

    public String getInterests() {
        return interests;
    }

    public String getMotto() {
        return motto;
    }

    public Map<Integer, Integer> getAnswers() {
        return answers;
    }
}
