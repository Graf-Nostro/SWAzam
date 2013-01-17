package main.tuwien.ac.at.swazam.server.core;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import java.sql.SQLException;
import java.util.logging.Logger;

import main.tuwien.ac.at.swazam.server.user.SongRequest;
import main.tuwien.ac.at.swazam.server.user.User;

public class CoreUserManagement {
	private final Logger LOG = Logger.getLogger(CoreUserManagement.class.getName());
	private final static String DATABASE_URL = "jdbc:sqlite:user";
	
	private Dao<User, Integer> userDao;
	private Dao<SongRequest, Integer> songDao;
	
	/***** CONSTRUCTOR CASE HANDLING********/
	public CoreUserManagement() {
	}
	
	public CoreUserManagement(User user) {
		
	}
	
	public CoreUserManagement(Integer userid) {
		
	}
	
	public CoreUserManagement(String username, String password) {
		
	}
	
	private void doMain(String[] args) throws Exception {
		JdbcConnectionSource connectionSource = null;
		try {
			// create our data source
			connectionSource = new JdbcConnectionSource(DATABASE_URL);
			// setup our database and DAOs
			setupDatabase(connectionSource);

			songDao = DaoManager.createDao(connectionSource, User.class);
			LOG.info("\n\nDatabase Connection seems to have worked\n\n");
		} finally {
			// destroy the data source which should close underlying connections
			if (connectionSource != null) {
				connectionSource.close();
			}
		}
	}
	
	/**
	 * Setup our database
	 */
	private void setupDatabase(ConnectionSource connectionSource) throws Exception {
		// if you need to create the table
		TableUtils.createTableIfNotExists(connectionSource, User.class);
		TableUtils.createTableIfNotExists(connectionSource, SongRequest.class);
	}
	
	private boolean createUser(String username, String password) {
		// TODO: Check if user exists before ?
		User user = new User(username, new String(Base64.encode(password.getBytes())));
		// persist the account object to the database
        try {
			userDao.create(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.warning(e.toString());
			return false;
		}
		return true;
	}
	
	private User getUserbyId(Integer userid) {
		User user = null;
		// retrieve the account from the database by its id field (name)
        try {
			user = userDao.queryForId(userid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return user;
	}
}
