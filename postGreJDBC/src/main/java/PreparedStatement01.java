import java.sql.*;

public class PreparedStatement01 {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/batch108","postgres","1");
        Statement st = con.createStatement();

        //Example: Update the number of employees to 9999 if the company name is IBM by using prepared statement
        //1. Step: Create Prepared Statement Query
        String sql1 = "UPDATE companies SET number_of_employees = ? WHERE company = ?";
        //2. Step: Create Prepared Statement Object
        PreparedStatement pst1 = con.prepareStatement(sql1);

        //3. Step: Assign the values by using 'setInt(), setString() ...' methods.
        pst1.setInt(1, 9999);
        pst1.setString(2, "IBM");

        //4. Step: Execute the Query
        int numOfRecordsUpdated = pst1.executeUpdate();
        System.out.println("numOfRecordsUpdated = " + numOfRecordsUpdated);

        String sql2 = "SELECT * FROM companies";
        ResultSet resultSet1 = st.executeQuery(sql2);

        while (resultSet1.next()) {
            System.out.println(resultSet1.getInt(1) + "--" + resultSet1.getString(2) + "--" + resultSet1.getInt(3));
        }

        //Example: Update the number of employees to 5555 if the company name is GOOGLE by using prepared statement
        pst1.setInt(1,5555);
        pst1.setString(2,"GOOGLE");

        int numOfRecordsUpdated2 = pst1.executeUpdate();
        System.out.println("numOfRecordsUpdated2: "+ numOfRecordsUpdated2);

        while (resultSet1.next()){
            System.out.println(resultSet1.getInt(1) + "--" + resultSet1.getString(2) + "--" + resultSet1.getInt(3));
        }
        //===> in this example output is blank. Because, in previous example cursor located at end point of table.
        //===> Cursor can not go back, Because of that create a new resultSet
        ResultSet resultSet2 = st.executeQuery(sql2);
        while (resultSet2.next()){
            System.out.println(resultSet2.getInt(1) + "--" + resultSet2.getString(2) + "--" + resultSet2.getInt(3));
        }
        con.close();
        st.close();
        pst1.close();
}}
