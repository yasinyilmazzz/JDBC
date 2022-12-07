import java.sql.*;

public class ExecuteQuery02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //1. Step: Registration to the driver
        Class.forName("org.postgresql.Driver");

        //2. Step: Create connection with database
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/batch108","postgres","1");

        //3. Step: Create statement
        Statement st = con.createStatement();

                //Example: Find the company and number_of_employees whose number_of_employees
                // is the second highest from the companies table

        String sql1 = "SELECT company, number_of_employees FROM companies ORDER BY number_of_employees DESC OFFSET 1 ROW FETCH NEXT 1 ROW ONLY ";

        ResultSet resultSet = st.executeQuery(sql1);

        while (resultSet.next()){
            System.out.println(resultSet.getString("company") + " -- " + resultSet.getString("number_of_employees"));
        }

                //2. Way
        String sql2 = "SELECT    company, number_of_employees FROM companies\n" +
                "WHERE number_of_employees = (SELECT MAX(number_of_employees) FROM companies \n" +
                " WHERE number_of_employees < (SELECT MAX(number_of_employees) FROM companies))";
        ResultSet resultSet1 = st.executeQuery(sql2);

        while(resultSet1.next()){
            System.out.println(resultSet1.getString(1));
        }

                //Example: Find the company names and number of employees
                // whose number of employees is less than the average number of employees
        String sql3 = "SELECT company,number_of_employees FROM companies\n" +
                "WHERE number_of_employees < (SELECT AVG(number_of_employees) FROM companies)";

        ResultSet resultSet2 = st.executeQuery(sql3);
         while(resultSet2.next()){
             System.out.println(resultSet2.getString(1) + " " + resultSet2.getInt(2));
         }


         st.close();
         con.close();





    }
}
