import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Execute01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1. Step: Registration to the driver
        Class.forName("org.postgresql.Driver");

        //2. Step: Create connection with database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/batch108","postgres","1");

        //3. Step: Create statement
        Statement st = con.createStatement();

        //4. Step: Execute the query
            //Example: Create a workers table with the columns worker_id,worker_name, worker_salary
        boolean sqlResult = st.execute("CREATE TABLE workers (worker_id VARCHAR(50), worker_name VARCHAR(50), worker_salary INT )");
        //====>in this exp. we can execute sql query in IntelliJ
        System.out.println(sqlResult);//false
        /*
        execute() method can be used in DDL(Table creation, drop table, alter table) and DQL(Select)
        1)If you use execute() method in DDL you will get false everytime.
        2)If you use execute() method in DQL you will get false or true
            When you use execute() method in DQL, if you get ResultSet Object as return you will get true
            otherwise you will get false.
         */

            //Example: Alter table by adding worker_address column into the workers table
        String sql2 ="ALTER TABLE workers ADD worker_address VARCHAR(50)";
        st.execute(sql2);

            //Example: Drop the table
        String sql3 = "DROP TABLE workers";
        st.execute(sql3);

        //5. Step: Close the connection and statement
        con.close();
        st.close();




    }
}
