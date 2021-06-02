public class Candidate {
	protected int id;
	protected String fullname;
	protected String email;
	protected String password;
public Candidate() {
	
}

public Candidate(int id) {
	this.id = id;
}

public Candidate (int id, String fullname,String email,String password) {
	this(fullname, email, password);
	this.id = id;
}

public Candidate (String fullname,String email,String password) {
	this.fullname = fullname;
	this.email = email;
	this.password = password;
}

public int getId() {
	return id;
}

public void setId(int id) {
	this.id = id;
}

public String getFullname() {
	return fullname;
}

public void setFullname(String fullname) {
	this.fullname = fullname;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}




}