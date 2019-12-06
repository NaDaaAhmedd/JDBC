package eg.edu.alexu.csd.oop.jdbc.cs67;

import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.Vector;
import eg.edu.alexu.csd.oop.db.databasee;
import eg.edu.alexu.csd.oop.db.databasetables;
import eg.edu.alexu.csd.oop.db.table;

public class Statement implements java.sql.Statement {

	private Vector<String> list = new Vector<String>();
	private databasee db = new databasee();
	private int QueryTimeout;
	private boolean isClosed = false;
	private Connection MyConnection;

	@Override
	public void addBatch(String sql) throws SQLException {
		if (isClosed)
			throw new IllegalStateException("Statement is closed.");
		list.add(sql);
	}

	@Override
	public void clearBatch() throws SQLException {
		if (isClosed)
			throw new IllegalStateException("Statement is closed.");
		list.removeAllElements();

	}

	@Override
	public boolean execute(String sql) throws SQLException {
		if (isClosed)
			throw new IllegalStateException("Statement is closed.");
		return db.executeStructureQuery(sql);
	}

	@Override
	public ResultSet executeQuery(String sql) throws SQLException { ///////??????????????????????????????????
		if (isClosed)
			throw new IllegalStateException("Statement is closed.");
		db.executeQuery(sql);
		String[] arr = db.getNameSelect(sql);
		String Tablename = arr[1];
		databasetables d = db.getCurrentDB();
		table t = new table();
		t = d.getTable(Tablename);
		ResultSet rs = new ResultSet(t);
		rs.setStatement(this);	
		return rs;
	}

	@Override
	public int executeUpdate(String sql) throws SQLException {
		if (isClosed)
			throw new IllegalStateException("Statement is closed.");
		return db.executeUpdateQuery(sql);
	}

	@Override
	public void close() throws SQLException {
		isClosed = true;
	}

	@Override
	public int getQueryTimeout() throws SQLException {
		if (isClosed)
			throw new IllegalStateException("Statement is closed.");
		return QueryTimeout;
	}

	@Override
	public void setQueryTimeout(int seconds) throws SQLException {
		if (isClosed)
			throw new IllegalStateException("Statement is closed.");
		QueryTimeout = seconds;
	}

	@Override
	public int[] executeBatch() throws SQLException {
		Vector<Integer> c = new Vector<Integer>();
		for (int i = 0; i < list.size(); i++) {
			String s = list.elementAt(i).toLowerCase();
			if (s.contains("create") || s.contains("drop")) {
				boolean b = execute(list.elementAt(i));
				if (!b) {
					return null;
				}
			} else {
				c.add(executeUpdate(list.elementAt(i)));
			}
		}
		int[] counts = new int[c.size()];
		for (int j = 0; j < c.size(); j++) {
			counts[j] = c.elementAt(j);
		}
		return counts;
	}

	@Override
	public Connection getConnection() throws SQLException {
        if (isClosed){
            throw new IllegalStateException("Statement is closed.");
            }
        else
            return MyConnection;
    }
 
    public void SetConnection(Connection c)
    {
        MyConnection = c ;
    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getMaxFieldSize() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaxFieldSize(int max) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getMaxRows() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setMaxRows(int max) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setEscapeProcessing(boolean enable) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void cancel() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public SQLWarning getWarnings() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearWarnings() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setCursorName(String name) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public ResultSet getResultSet() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getUpdateCount() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getMoreResults() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setFetchDirection(int direction) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getFetchDirection() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setFetchSize(int rows) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int getFetchSize() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getResultSetConcurrency() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getResultSetType() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean getMoreResults(int current) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResultSet getGeneratedKeys() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int executeUpdate(String sql, String[] columnNames) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean execute(String sql, int[] columnIndexes) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean execute(String sql, String[] columnNames) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int getResultSetHoldability() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isClosed() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setPoolable(boolean poolable) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isPoolable() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void closeOnCompletion() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isCloseOnCompletion() throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

}
