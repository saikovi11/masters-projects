using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class signin : System.Web.UI.Page
{
    ClsAdmin objAdmin = new ClsAdmin();
    protected void Page_Load(object sender, EventArgs e)
    {

    }
    protected void BtnSubmit_Click(object sender, EventArgs e)
    {
        Page.Validate();
        if (Page.IsValid == true)
        {
            LoginAdmin(txtemail.Text.Trim(), txtpassword.Text.Trim());
        }
    }

    private void LoginAdmin(string email, string password)
    {
        DataSet dsAdmin = new DataSet();
        dsAdmin = objAdmin.CheckUserLogin(email, password);
        if (dsAdmin.Tables.Count > 0)
        {
            Response.Redirect("~/game.aspx");
        }
        else
        {
            dvMessage.InnerHtml = "Invalid Email or Password.";
            dvMessage.Attributes.Add("class", "alert alert-danger");
            dvMessage.Visible = true;
        }
    }
}