package main.tuwien.ac.at.swazam.server.core;

import com.j256.ormlite.dao.CloseableIterator;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import main.tuwien.ac.at.swazam.server.user.Peer;

public class CorePeerManagement {
	private final Logger LOG = Logger.getLogger(CorePeerManagement.class.getName());
	private final static String DATABASE_URL = "jdbc:sqlite:user";
	
	private Dao<Peer, String> peerDao;
	
	/***** CONSTRUCTOR CASE HANDLING
	 * @throws Exception ********/
	public CorePeerManagement() throws Exception {
		doMain();
	}	
	
	private void doMain() throws Exception {
		JdbcPooledConnectionSource connectionSource = null;
			connectionSource = new JdbcPooledConnectionSource(DATABASE_URL);
			setupDatabase(connectionSource);
			peerDao = DaoManager.createDao(connectionSource, Peer.class);
			
			LOG.info("\n\nDatabase Connection seems to have worked\n\n");
	}
	
	/**
	 * Setup our database
	 */
	private void setupDatabase(ConnectionSource connectionSource) throws Exception {
		TableUtils.createTableIfNotExists(connectionSource, Peer.class);
	}
	
	public boolean registerPeer(Peer peer) {
        try {
			peerDao.createIfNotExists(peer);
		} catch (SQLException e) {
			LOG.warning(e.toString());
			return false;
		}
		return true;
	}
	
	public boolean deletePeer(String peerId) {
        try {
			peerDao.delete(getPeerById(peerId));
		} catch (SQLException e) {
			LOG.warning(e.toString());
			return false;
		}
		return true;
	}
	
	public boolean updatePeer(Peer peer) {
        try {
			peerDao.update(peer);
		} catch (SQLException e) {
			LOG.warning(e.toString());
			return false;
		}
		return true;
	}
	
	public Peer getPeerById(String peerId) {
		Peer peer= null;
        try {
			peer = peerDao.queryForId(peerId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
        return peer;
	}
	
	public Peer getRandomPeer() {
		List<Peer> peers = new ArrayList<Peer>();
        peers = getPeers();
        Random random = new Random();
        return peers.get(random.nextInt(peers.size()));
	}
	
	public List<Peer> getPeers() {
		List<Peer> peers = new ArrayList<Peer>();
		CloseableIterator<Peer> iterator = peerDao.closeableIterator();
		try {
			while (iterator.hasNext()) {
				Peer peer = iterator.next();
				peers.add(peer);
			}
		} finally {
			try {
				iterator.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return peers;
	}
}
