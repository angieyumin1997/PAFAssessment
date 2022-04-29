package vttp2022.paf.assessment.server.models;

import java.util.LinkedList;
import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

// Do not change this class

public class User {

	private String userId;
	private String username;
	private String name;
	private List<Task> tasks=new LinkedList<>();

	public List<Task> getTasks() {return tasks;}
	public void setTasks(List<Task> tasks) {this.tasks = tasks;}
	public void addTask(Task task) { this.tasks.add(task); }

	public User() { }

	public String getUserId() { return this.userId; }
	public void setUserId(String userId) { this.userId = userId; }

	public String getUsername() { return this.username; }
	public void setUsername(String username) { this.username = username; }

	public String getName() { return this.name; }
	public void setName(String name) { this.name = name; }

	@Override
	public String toString() {
		return "user_id: %s, username: %s, name: %s"
				.formatted(userId, username, name);
	}

	
    public static User create(SqlRowSet rs){

        User user = new User();
        user.setUserId(rs.getString("user_id"));
        user.setUsername(rs.getString("username"));
        
        return user;
    } 
}
