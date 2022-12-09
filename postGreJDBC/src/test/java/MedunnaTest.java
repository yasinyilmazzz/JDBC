import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.sql.*;
public class MedunnaTest {
    /*
   Given
     User connects to the database
     (Host name: medunna.com, Database name: medunna_db, Usename: medunna_user, Password: medunna_pass_987))

   When
     User sends the query to get the names of created_by column from "room" table

   Then
     Verify that there are some rooms created by "john doe".

   And
     User closes the connection
  */

    @Test
    public void medunnaTest() throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        Connection con = DriverManager.getConnection("jdbc:postgresql://medunna.com:5432/medunna_db","medunna_user","medunna_pass_987");

        Statement st = con.createStatement();

        String sql = "SELECT created_by FROM room ";
        ResultSet resultSet= st.executeQuery(sql);

//        while (resultSet.next()){
//            System.out.println("resultSet.getString(1) = " + resultSet.getString(1));
//        }
        //Verify that there are some rooms created by "john doe".
        String sql2 = "SELECT created_by FROM room WHERE created_by = 'john_doe' ";
        ResultSet resultSet1 = st.executeQuery(sql2);
        List<String> resultSetSize=new ArrayList<>();
        while(resultSet1.next()){
            System.out.println("resultSet1.getString(1) = " + resultSet1.getString(1));
            resultSetSize.add(resultSet1.getString(1));
        }
        System.out.println(resultSetSize.size());









    }
}
