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
	
	private Dao<User, String> userDao = null;
	private Dao<SongRequest, Integer> songDao = null;
	
	/***** CONSTRUCTOR CASE HANDLING
	 * @throws Exception ********/
	public CoreUserManagement() throws Exception {
		doMain();
	}
	
	public CoreUserManagement(User user) {
		
	}
	
	public CoreUserManagement(Integer userid) {
		
	}
	
	public CoreUserManagement(String username, String password) throws Exception {
	}
	
	private void doMain() throws Exception {
		JdbcConnectionSource connectionSource = null;
		try {
			/* create our data source
			 * New connections are created on demand only if there are no dormant connections
			 * available. JdbcPooledConnectionSource is also synchronized and can be used by multiple
			 * threads. It has settings for the maximum number of free connections before they are closed
			 * as well as a maximum age before a connection is closed. 
			 */
			connectionSource = new JdbcConnectionSource(DATABASE_URL);
			// setup our database and DAOs
			setupDatabase(connectionSource);
			//userDao = DaoManager.lookupDao(connectionSource, User.class);
			//songDao = DaoManager.lookupDao(connectionSource, SongRequest.class);
			if (userDao == null) userDao = DaoManager.createDao(connectionSource, User.class);
			if (songDao == null) songDao = DaoManager.createDao(connectionSource, SongRequest.class);
			
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
	
	public boolean createUser(String username, String password) {
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
	
	public boolean deleteUser(String userid) {
        try {
			userDao.delete(getUserbyId(userid));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.warning(e.toString());
			return false;
		}
		return true;
	}
	
	public boolean updateUser(User user) {
		// persist the account object to the database
        try {
			userDao.update(user);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.warning(e.toString());
			return false;
		}
		return true;
	}
	
	public boolean checkLogin(String username, String password) {
		User usr = null;
		if ((usr = getUserbyId(username)) != null) {
			String decodedPW = new String(Base64.encode(password.getBytes()));
			if (usr.getPassword().equals(decodedPW)) {
				return true;
			}
		}
		return false;
	}
	
	public User getUserbyId(String userid) {
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
