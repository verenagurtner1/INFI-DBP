package model;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;

public class FahrradverleihTest {
	

    Connection conn;
    Statement stmt;

	@org.junit.Test
	public void Verbindungaufbauen() {
		System.out.println("Connection aufbauen");
	}
	
	@org.junit.Test
	public void Verbindungabbauen() {
		System.out.println("Connection trennen");
	}
	
	@Test
	public void Fahrradeinfügen() {
		System.out.println("Fahrrad einfügen");
	}


	    // Setup methods
	    @BeforeClass
	    public void beforeClass() {
	        conn = null;
	        stmt = null;
	    }

	    // clean up method
	    @AfterClass
	    public void releaseResource() {
	        if (stmt != null) {
	        	DBManager.closeStatement(stmt);
	        }
	        if (conn != null) {
	        	DBManager.releaseConnection(conn);
	        }
	    }


	    // Test case to check close statement using ASE database
	    @Before
	    public void openConnBeforerStmtTestASE() throws SQLException {
	        conn = DBManager.getConnection();
	        stmt = conn.createStatement();
	    }

	    @After
	    public void closeConnAfterStmtTestASE() {
	        if (conn != null) {
	            DBManager.releaseConnection(conn);
	        }
	    }
	    

}
