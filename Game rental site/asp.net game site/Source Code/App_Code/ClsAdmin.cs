using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Web;

/// <summary>
/// Summary description for ClsAdmin
/// </summary>
public class ClsAdmin
{
    SqlConnection objConnection = new SqlConnection(System.Configuration.ConfigurationManager.ConnectionStrings["constr"].ConnectionString);

    public int InsertUser(string name, string phone, string email, string password)
    {
        object objId = new object();
        SqlCommand objCommand = new SqlCommand("usp_tblUser_Insert");
        objCommand.CommandType = CommandType.StoredProcedure;
        objCommand.Parameters.AddWithValue("@name", name);
        objCommand.Parameters.AddWithValue("@phone", phone);
        objCommand.Parameters.AddWithValue("@email", email);
        objCommand.Parameters.AddWithValue("@password", password);
        objCommand.Connection = objConnection;
        objConnection.Open();
        objId = objCommand.ExecuteScalar();
        objConnection.Close();
        return Convert.ToInt32(objId);
    }
    public DataSet CheckUserLogin(string email, string password)
    {
        SqlCommand objcommand = new SqlCommand("usp_tblUser_Login", objConnection);
        objcommand.CommandType = CommandType.StoredProcedure;
        objcommand.Parameters.AddWithValue("@email", email);
        objcommand.Parameters.AddWithValue("@password", password);
        SqlDataAdapter objAdapter = new SqlDataAdapter(objcommand);
        DataSet objds = new DataSet();
        objAdapter.Fill(objds);
        return objds;
    }
    public DataTable BindMotel()
    {
        SqlCommand objcommand = new SqlCommand("usp_tblGame_Select", objConnection);
        objcommand.CommandType = CommandType.StoredProcedure;
        SqlDataAdapter objAdapter = new SqlDataAdapter(objcommand);
        DataSet objds = new DataSet();
        objAdapter.Fill(objds);
        return objds.Tables[0];
    }

}