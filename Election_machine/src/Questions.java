public class Questions {
	protected int id;
	protected String questions;
	
	
	public Questions() {
		
	}
	
	public Questions(int id) {
		this.id = id;
	}
	
	public Questions (int id, String questions) {
		this(questions);
		this.id = id;
	}
	
	public Questions (String questions) {
		this.questions = questions;
		
	}
	
	public int getId() {
		return id;
	}
	
	public void SetId(int id) {
		this.id = id;
	}
	
	public String getQuestions() {
		return questions;
	}
	
	public void SetQuestions(String questions) {
		this.questions = questions;
	}
}