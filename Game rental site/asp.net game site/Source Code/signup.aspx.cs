using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

public partial class signup : System.Web.UI.Page
{
    ClsAdmin objAdmin = new ClsAdmin();
    protected void Page_Load(object sender, EventArgs e)
    {
        if (!Page.IsPostBack)
        {

        }
    }
    protected void BtnSubmit_Click(object sender, EventArgs e)
    {
        Page.Validate();
        if (Page.IsValid == true)
        {
            InsertUserDetials(txtname.Text.Trim(), txtmobile.Text.Trim(), txtemail.Text.Trim(), txtpassword.Text.Trim());
        }
    }

    private void InsertUserDetials(string name, string phone, string email, string password)
    {
        int Id = objAdmin.InsertUser(name,phone,email,password);
        if (Id > 0)
        {
            dvMessage.InnerHtml = "user created successfully.";
            dvMessage.Attributes.Add("class", "alert alert-success");
            dvMessage.Visible = true;
            ClearControl();
        }
        else
        {
            dvMessage.InnerHtml = "User already exists.";
            dvMessage.Attributes.Add("class", "alert alert-danger");
            dvMessage.Visible = true;
        }
    }

    private void ClearControl()
    {
        txtemail.Text=string.Empty;
        txtmobile.Text=string.Empty;
        txtname.Text=string.Empty;
        txtpassword.Text = string.Empty;
    }
}