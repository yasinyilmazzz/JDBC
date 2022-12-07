import java.sql.*;

public class ExecuteUpdate01 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/batch108","postgres","1");
        Statement st = con.createStatement();


        //Example: Update the number of employees to 16000
        // if the number of employees is less than the average number of employees
        String sql1 = "UPDATE companies SET number_of_employees = 16000\n" +
                "WHERE number_of_employees < (SELECT AVG(number_of_employees) FROM companies)";

        int numberOfRecordsUpdated = st.executeUpdate(sql1);
        System.out.println("numberOfRecordsUpdated : "+ numberOfRecordsUpdated);

        String sql2 = "SELECT * FROM companies";
        ResultSet resultSet = st.executeQuery(sql2);
        while (resultSet.next()){
            System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getInt(3));
        }

    }
}
