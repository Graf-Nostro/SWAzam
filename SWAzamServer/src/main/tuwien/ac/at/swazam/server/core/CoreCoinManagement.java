package main.tuwien.ac.at.swazam.server.core;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import main.tuwien.ac.at.swazam.server.user.SongRequest;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
public class CoreCoinManagement {
	private final Logger LOG = Logger.getLogger(CoreCoinManagement.class.getName());

	private final static String DATABASE_URL = "jdbc:sqlite:user";
	
	private Dao<SongRequest, Integer> songDao;
	
	/***** CONSTRUCTOR CASE HANDLING
	 * @throws Exception ********/
	public CoreCoinManagement() throws Exception {
		doMain();
	}
	
	private void doMain() throws Exception {
		JdbcPooledConnectionSource connectionSource = null;
		try {
			connectionSource = new JdbcPooledConnectionSource(DATABASE_URL);
			// setup our database and DAOs
			setupDatabase(connectionSource);
			
			songDao = DaoManager.createDao(connectionSource, SongRequest.class);
			
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
		TableUtils.createTableIfNotExists(connectionSource, SongRequest.class);
	}
	
	public boolean addSongRequest(SongRequest song) {
		// persist the account object to the database
        try {
			songDao.createIfNotExists(song);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.warning(e.toString());
			return false;
		}
		return true;
	}
	
	public boolean updateSongRequest(SongRequest song) {
		// persist the account object to the database
        try {
			songDao.update(song);
			songDao.refresh(song);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			LOG.warning(e.toString());
			return false;
		}
		return true;
	}

	public List<SongRequest> getAllSongs() {
		List<SongRequest> songs = new ArrayList<SongRequest>();
		CloseableIterator<SongRequest> iterator = songDao.closeableIterator();
		try {
			while (iterator.hasNext()) {
				SongRequest song = iterator.next();
				songs.add(song);
			}
		} finally {
			try {
				iterator.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return songs;
	}
}
