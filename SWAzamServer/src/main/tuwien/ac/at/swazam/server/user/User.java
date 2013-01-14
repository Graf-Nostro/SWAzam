package main.tuwien.ac.at.swazam.server.user;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "user")
public class User {
	
	public static final String NAME_FIELD_NAME = "name";
	public static final String PASSWORD_FIELD_NAME = "passwd";
	public static final String EMAIL_FIELD_NAME = "email";
	public static final String COINS_FIELD_NAME = "coins";
	
	@DatabaseField(generatedId=true)
    private int id;
	
	@DatabaseField(columnName = NAME_FIELD_NAME, canBeNull = false)
	private	String name = "";
	
	@DatabaseField(columnName = PASSWORD_FIELD_NAME)
	private	String password = "";

	@DatabaseField(columnName = EMAIL_FIELD_NAME)
	private String email = "";

	@DatabaseField(columnName = COINS_FIELD_NAME)
	private Integer coins = 0;

	public User() { 
	}
	
	public User(String name, String password) {
		this.name = name;
		this.password = password;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setCoins(Integer coins) {
		this.coins = coins;
	}

	public Integer getCoins() {
		return coins;
	}

	@Override
	public String toString() {
		return new StringBuffer(name).append(" <").append(email).append(">").toString();
	}
}
