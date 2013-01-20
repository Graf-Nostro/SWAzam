package main.tuwien.ac.at.swazam.server.core;

import java.sql.SQLException;
import java.util.logging.Logger;

import main.tuwien.ac.at.swazam.server.user.User;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class CoreUserManagement {
	private final Logger LOG = Logger.getLogger(CoreUserManagement.class.getName());
	private final static String DATABASE_URL = "jdbc:sqlite:user";
	
	private Dao<User, String> userDao;
	
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
		JdbcPooledConnectionSource connectionSource = null;
		try {
			/* create our data source
			 * New connections are created on demand only if there are no dormant connections
			 * available. JdbcPooledConnectionSource is also synchronized and can be used by multiple
			 * threads. It has settings for the maximum number of free connections before they are closed
			 * as well as a maximum age before a connection is closed. 
			 */
			connectionSource = new JdbcPooledConnectionSource(DATABASE_URL);
			// setup our database and DAOs
			setupDatabase(connectionSource);
			//userDao = DaoManager.lookupDao(connectionSource, User.class);
			//songDao = DaoManager.lookupDao(connectionSource, SongRequest.class);
			userDao = DaoManager.createDao(connectionSource, User.class);
			
			LOG.info("\n\nDatabase Connection seems to have worked\n\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		/*finally {
			// destroy the data source which should close underlying connections
			if (connectionSource != null) {
				connectionSource.close();
			}
		}*/
	}
	
	/**
	 * Setup our database
	 */
	private void setupDatabase(ConnectionSource connectionSource) throws Exception {
		// if you need to create the table
		TableUtils.createTableIfNotExists(connectionSource, User.class);
	}
		
	public boolean createUser(String username, String password) {
		User user = new User(username, new String(Base64.encode(password.getBytes())));
		// persist the account object to the database
        try {
			userDao.createIfNotExists(user);
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
			userDao.refresh(user);
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
        	//QueryBuilder<User, String> queryBuilder = userDao.queryBuilder();
        	//user = queryBuilder.where().eq(User.NAME_FIELD_NAME, userid);
        	user = userDao.queryForId(userid);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return user;
	}
}
