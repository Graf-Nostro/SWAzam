package main.tuwien.ac.at.swazam.server.user;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "songrequest")
public class SongRequest {
	
	public static final String NAME_FIELD_NAME = "name";
	public static final String USER_ID_FIELD_NAME = "userid";
	public static final String COINS_FIELD_NAME = "coins";
	public static final String RECOGNIZED_FIELD_NAME = "recognizedSong";
	
	@DatabaseField(generatedId=true)
    private int id;
	
	@DatabaseField(columnName = NAME_FIELD_NAME, canBeNull = false)
	private	String name = "";

	@DatabaseField(columnName = COINS_FIELD_NAME)
	private Integer coins = 0;
	
	@DatabaseField(columnName = RECOGNIZED_FIELD_NAME)
	private boolean recognizedSong = false;
	
	@DatabaseField(foreign = true, foreignAutoRefresh = true, columnName = USER_ID_FIELD_NAME)
	private User user;
	
	public SongRequest() { 
	}
	
	public SongRequest(String name, Integer costs, boolean recognized) {
		this.name = name;
		this.coins = costs;
		this.recognizedSong = recognized;
	}
	
	public boolean isRecognizedSong() {
		return recognizedSong;
	}

	public void setRecognizedSong(boolean recognizedSong) {
		this.recognizedSong = recognizedSong;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setCoins(Integer coins) {
		this.coins = coins;
	}

	public Integer getCoins() {
		return coins;
	}
}
