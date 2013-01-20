package main.tuwien.ac.at.swazam.server.user;

import com.j256.ormlite.dao.ForeignCollection;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "user")
public class User {
	
	public static final String NAME_FIELD_NAME = "name";
	public static final String PASSWORD_FIELD_NAME = "passwd";
	public static final String COINS_FIELD_NAME = "coins";
	
	@DatabaseField(columnName = NAME_FIELD_NAME, canBeNull = false, id = true)
	private	String name = "";
	
	@DatabaseField(columnName = PASSWORD_FIELD_NAME, canBeNull = false)
	private	String password = "";

	@DatabaseField(columnName = COINS_FIELD_NAME)
	private Integer coins = 50;
	
	@ForeignCollectionField(eager = false)
    private ForeignCollection<SongRequest> songRequests;
	
	public User() { 
	}
	
	public User(String name, String password) {
		this.name = name;
		this.password = password;
		this.coins = 50;
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
	
	public void setCoins(Integer coins) {
		this.coins = coins;
	}

	public Integer getCoins() {
		return coins;
	}
	
	public ForeignCollection<SongRequest> getSongRequests() {
		return songRequests;
	}

	@Override
	public int hashCode() {
		return name.hashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || other.getClass() != getClass()) {
			return false;
		}
		return name.equals(((User) other).name);
	}
}
