import java.sql.Connection;
import java.sql.Statement;

public class Runner {
    public static void main(String[] args) {

        //1. Step: Registration to the driver
        //2. Step: Create connection with database

        JdbsUtils.connectToDatabase("localhost","batch108", "postgres","1");

        //3. Step: Create statement
        JdbsUtils.createStatement();

        //4. Step: Execute the query
        JdbsUtils.createTable("students","name varchar(50)","id INT", "address VARCHAR(50)","tel BIGINT");

        JdbsUtils.insertDataTable("students","name 'John'");
        JdbsUtils.insertDataTable("students","name 'Mark'","id 123","address 'Paris_Champ-Elise'","tel 555-5533");

        //5. Step: Close the connection and statement
        JdbsUtils.closeConnectionAndStatement();

    }
}
