package app.entities;

public abstract class User {
	protected int id;
	protected String login;
	protected String password;
	protected Roles role;
	
	public User (int id, String login, String password, Roles role) {
		this.id = id;
		this.login = login;
		this.password = password;
		this.role = role;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Roles getRole() {
		return role;
	}
	public void setRole(Roles role) {
		System.out.println("Setting role " + role);
		this.role = role;
		System.out.println("Done setting role " + role);
	}
	public void login() {}
	public void logout() {}
}
