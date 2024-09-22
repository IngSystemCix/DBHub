package pe.edu.utp.DBHub.config;

import java.sql.ResultSet;

public interface DatabaseQuerys {
    /**
    * Definition of parameters
    * @param name_table the name table
    * @param name_column the name column
    * @param condition conditional for where statement
     */
    public ResultSet executeQuery(String name_table, String name_column,String condition);
    public ResultSet executeQuery(String name_table, String name_column);
    public ResultSet executeQuery(String name_table);
    /**
     * Definition of parameters
     * @param condition conditional for where statement
     * @param value_insert value for insertion or update statements
     * @param column_condition name of column for clause where
     */

     public int executeUpdate(String name_table, String name_column,String value_insert,String column_condition,String condition);
    /**
     * @param parameter_list this is a list that contains the parameters necessary to execute the procedure
     * @param name_procedure this is the name of the procedure
     */
    public void executeProcedure(String name_procedure,Object[] parameter_list);



}
