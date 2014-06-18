/**
 * 
 */
package com.ethan.testing.examples;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.naming.NamingException;

import com.ethan.testing.MultiThreadTestCase;
import com.ethan.testing.MultiThreadTestHandler;

/**
 * @author Ethan Tsui created date: 2014/6/18
 */
public class TestGetTransactionId extends MultiThreadTestCase {

    /**
     * @param args
     */
    public static void main(String[] args) {
        MultiThreadTestHandler handler = new MultiThreadTestHandler();
        handler.setExecutionTimes(50);
        handler.setNumbersOfThreads(3);
        handler.setTestingClass(TestGetTransactionId.class);
        handler.setOutputPath("/data/temp/transactionid/");
        // handler.setOutputCollision(true);
        handler.start();

    }

    /**
     * 
     */
    public TestGetTransactionId() {

    }

    /**
     * @see com.ethan.testing.MultiThreadTestCase#execute()
     */
    @Override
    public String execute() {

        return getInvoiceId();
    }

    
    Random random = new Random();
    
    private String getInvoiceId() {

        String data = null;
        try {
            Connection conn = getConnection();
            boolean error = true;

            conn.setAutoCommit(false);

            conn.setTransactionIsolation(Connection.TRANSACTION_REPEATABLE_READ);

            while (error) {
                try {
                    PreparedStatement ps1 = conn.prepareStatement("select min(invoice_id) from invoicetable where isused=0");
                    ResultSet rs1 = ps1.executeQuery();
                    if(rs1.next()) {
                        data = rs1.getString(1);
                    }
                    PreparedStatement psUpdate = conn.prepareStatement("update invoicetable set isused=1 where invoice_id=?");
                    psUpdate.setString(1, data);
                    psUpdate.executeUpdate();
                    
                    conn.commit();
                    
                    rs1.close();
                    ps1.close();
                    psUpdate.close();
                    
                    
                    error = false;
                } catch (Exception err) {
                    conn.rollback();
                    Thread.sleep(random.nextInt(50));
                }
            }
            conn.close();
        } catch (Exception err) {
            err.printStackTrace();
        }
        return data;
    }

    public Connection getConnection() throws SQLException, NamingException {

        Connection c = null;
        try {
            Class.forName("org.postgresql.Driver");
             c = DriverManager.getConnection("jdbc:postgresql://127.0.0.1/testinvoicedb", "invoiceuser", "invoice");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }
}
