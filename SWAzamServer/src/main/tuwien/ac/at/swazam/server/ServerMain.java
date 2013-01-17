package main.tuwien.ac.at.swazam.server;

import java.sql.SQLException;

import main.tuwien.ac.at.swazam.server.user.User;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

public class ServerMain {

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		// this uses h2 by default but change to match your database
        String databaseUrl = "jdbc:sqlite:user";
        // create a connection source to our database
        ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);

        // instantiate the dao
        Dao<User, String> userDao = DaoManager.createDao(connectionSource, User.class);

        // if you need to create the 'accounts' table make this call
        TableUtils.createTable(connectionSource, User.class);

        // create an instance of Account
        User user = new User();
        user.setName("Jim Coakley");

        // persist the account object to the database
        userDao.create(user);

        // retrieve the account from the database by its id field (name)
        User user2 = userDao.queryForId("1");
        System.out.println("User: " + user2.getName());

        // close the connection source
        connectionSource.close();

	}

}
