import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbsUtils {
    private static Connection connection;
    private static Statement statement;

    public static Connection connectToDatabase(String hostName, String databaseName, String username, String password){
        //1. Step: Registration to the driver
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //2. Step: Create connection with database
        try {
            connection = DriverManager.getConnection("jdbc:postgresql://"+hostName+":5432/"+databaseName,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Connection is success");
        return connection;
    }

    //3. Step: Create statement
    public static Statement createStatement (){
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Statement Created");
        return statement;
    }

    //4. Step: Execute the query
    public static boolean execute(String query){
        boolean isExecute;

        try {
            isExecute = statement.execute(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Query executed");
        return isExecute;
    }

    //5. Step: Close the connection and statement
    public static void closeConnectionAndStatement(){
        try {
            connection.close();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            if (connection.isClosed() && statement.isClosed()){
                System.out.println("Connection and Statement closed");
            }else {
                System.out.println("Connection and Statement not closed");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void dropTable (String tableName){

        try {
            statement.execute("DROP TABLE "+tableName);
            System.out.println(tableName+" table dropped");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createTable(String tableName,String... columnName_DataType){
        StringBuilder columnName_DataTypeString= new StringBuilder("");
        for (String w: columnName_DataType){
            columnName_DataTypeString.append(w).append(",");
        }
        columnName_DataTypeString.deleteCharAt(columnName_DataTypeString.lastIndexOf(","));

        try {
            statement.execute("CREATE TABLE "+tableName+" ("+columnName_DataTypeString+" )");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println(tableName+" table created");

    }

    public static void insertDataTable(String tableName,String... columnName_Value){

        StringBuilder columnNames = new StringBuilder("");
        StringBuilder values = new StringBuilder("");
        for (String w:columnName_Value){
            columnNames.append( w.split(" ")[0]).append(",");
            values.append(w.split(" ")[1]).append(",");
        }
        columnNames.deleteCharAt(columnNames.lastIndexOf(","));
        values.deleteCharAt(values.lastIndexOf(","));

        String query = "INSERT INTO "+tableName+"("+columnNames+") VALUES ("+values+")";

        try {
            statement.execute(query);
            System.out.println("Data inserted into table: "+tableName);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
