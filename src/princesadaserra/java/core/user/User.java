package princesadaserra.java.core.user;

import java.util.ArrayList;

public class User {

	private int id;
	private String login;
	private String name;
	private String email;
	private String phone;
	private String cpf;
	private ArrayList<Permission> permissions;
	private Role role;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public ArrayList<Permission> getPermissions() {
		return permissions;
	}

	public void setPermissions(ArrayList<Permission> permissions) {
		this.permissions = permissions;
	}
}
