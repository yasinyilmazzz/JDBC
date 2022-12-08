public class Runner {
    public static void main(String[] args) {

        //1. Step: Registration to the driver
        //2. Step: Create connection with database

        JdbcUtils.connectToDatabase("localhost","batch108", "postgres","1");

        //3. Step: Create statement
        JdbcUtils.createStatement();

        //4. Step: Execute the query
        JdbcUtils.createTable("students","name varchar(50)","id INT", "address VARCHAR(50)","tel BIGINT");

        JdbcUtils.insertDataTable("students","name 'John'");
        JdbcUtils.insertDataTable("students","name 'Mark'","id 123","address 'Paris_Champ-Elise'","tel 555-5533");

        //5. Step: Close the connection and statement
        JdbcUtils.closeConnectionAndStatement();

    }
}
