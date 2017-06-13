using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Data;
public partial class game : System.Web.UI.Page
{
    ClsAdmin objAdmin = new ClsAdmin();
    protected void Page_Load(object sender, EventArgs e)
    {
        if(!IsPostBack)
        {
            Bindata();
        }
    }

    private void Bindata()
    {
        DataTable dt = new DataTable();
        dt = objAdmin.BindMotel();
        rptmotel.DataSource = dt;
        rptmotel.DataBind();
    }
}