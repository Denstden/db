package ua.kiev.unicyb.tcct.corba;

import java.rmi.Remote;

/**
 * @Author Denys Storozhenko.
 */
public interface RemoteDatabaseService extends Remote {
	void createDatabase(String dbName);
}
