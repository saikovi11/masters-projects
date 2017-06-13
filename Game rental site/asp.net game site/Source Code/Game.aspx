<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Game.aspx.cs" Inherits="game" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/AdminLTE.min.css">
    <link rel="stylesheet" href="css/skins/_all-skins.min.css">
    <script src="js/jquery.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script src="js/app.min.js"></script>
</head>
<body class="skin-red layout-top-nav">
    <form id="form1" runat="server">
    <div class="">
        <header class="main-header">
            <nav class="navbar navbar-static-top">
                <div class="container">
                    <div class="navbar-header">
                        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar-collapse">
                            <i class="fa fa-bars"></i>
                        </button>
                    </div>
                    <div class="collapse navbar-collapse pull-left" id="navbar-collapse">
                    </div>
                    <div class="navbar-custom-menu">
                        <ul class="nav navbar-nav">
                            <li class="active"><a href="#">Comming soon games<span class="sr-only">(current)</span></a></li>
                            <li class="dropdown user user-menu">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                    <img src="img/user2-160x160.jpg" class="user-image" alt="User Image">
                                    <span class="hidden-xs">
                                        <asp:Label ID="lblName" runat="server"></asp:Label></span>
                                </a>
                                <ul class="dropdown-menu">
                                    <!-- Menu Body -->
                                    <!-- Menu Footer-->
                                    <li class="user-footer">
                                        <div class="pull-left">
                                        </div>
                                        <div class="pull-right">
                                            <a href="signin.aspx">Logout</a>
                                        </div>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
        </header>
        <!-- Full Width Column -->
        <div class="container">
            <div class="row" style="margin-top:50px;">
                <div class="col-md-12">
                    <asp:Repeater ID="rptmotel" runat="server">
                        <ItemTemplate>
                            <div class="col-md-3">
                                <div class="col-md-12">
                                    <a href="Detail.aspx?id=<%# Eval("Id")%>">
                                        <img alt="" src='<%# Eval("Image")%>' class="img-responsive"/></a>
                                </div>
                                <div class="col-md-12" style="text-align:left">
                                    <a href="Detail.aspx?id=<%# Eval("Id")%>">
                                        <h4><%# Eval("Title")%></h4>
                                    </a>
                                </div>
                                   <div class="col-md-12" style="text-align:left; height:100px;overflow:hidden" >
                                        <%# Eval("Descrption")%>
                                    </a>
                                </div>
                                   <div class="col-md-12" style="text-align:left;margin-top:10px; margin-bottom:20px;" >
                                       <asp:Button CssClass="btn btn-primary btn-block btn-flat" ID="BtnViewDetail" runat="server" Text="ViewDetail" Width="111px" />
                                    </a>
                                </div>
                            </div>
                        </ItemTemplate>
                    </asp:Repeater>
                </div>
            </div>
        </div>


        <!-- /.content-wrapper -->
        <footer class="main-footer">
            <div class="container">
                <div class="pull-right hidden-xs">
                </div>
                <strong>Copyright &copy; 2014-2015.</strong> All rights
      reserved.
   
            </div>
            <!-- /.container -->
        </footer>
    </div>
        </form>
</body>
</html>
