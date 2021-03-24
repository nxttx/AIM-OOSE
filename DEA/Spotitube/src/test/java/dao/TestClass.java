package dao;

import org.junit.jupiter.api.BeforeAll;

import javax.sql.DataSource;

public class TestClass {
    DataSource dataSource;
    @BeforeAll
    private void beforeAll(){
        com.dbaccess.BasicDataSource ds = new com.dbaccess.BasicDataSource();
        ds.setServerName("grinder");
        ds.setDatabaseName("CUSTOMER_ACCOUNTS");
        ds.setDescription("Customer accounts database for billing");
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUsername("username");
        dataSource.setPassword("password");
        dataSource.setUrl("jdbc:mysql://<host>:<port>/<database>");
        dataSource.setMaxActive(10);
        dataSource.setMaxIdle(5);
        dataSource.setInitialSize(5);
        dataSource.setValidationQuery("SELECT 1");
    }

}
