<%@ Page Language="C#" AutoEventWireup="true" CodeFile="signup.aspx.cs" Inherits="signup" %>

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
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="http://ajax.aspnetcdn.com/ajax/jquery.ui/1.8.23/jquery-ui.min.js" type="text/javascript"></script>
<link href="http://ajax.aspnetcdn.com/ajax/jquery.ui/1.8.9/themes/Blitzer/jquery-ui.css" rel="stylesheet" type="text/css" />
     <script src="js/backstretch.min.js"></script>
     <script type="text/javascript">
        $(document).ready(function () {
            window.setTimeout(function () {
                $(".alert-success").fadeTo(500, 0).slideUp(500, function () {
                    $(this).remove();
                });
            }, 4000);
            $.backstretch("img/bg3.jpg");
        });
    </script>
     <style>
        .bg {
            position: fixed;
            top: 0;
            left: 0;
            /* Preserve aspet ratio */
            min-width: 100%;
            min-height: 100%;
        }
        .fit-img-bg {
            width:100%
        }
    </style>
</head>
<body class="login-page">
    <div class="register-box">
        <div class="register-logo">
            Game Rental System
 
        </div>
        <div class="register-box-body">
            <p class="login-box-msg">Register a new membership</p>
            <div class="text-danger"></div>
            <form id="form1" runat="server">
                <div id="validation_dialog" style="display: none">
        <asp:ValidationSummary ID="ValidationSummary1" runat="server" />
    </div>
                  <div id="dvMessage" class="alert alert-info alert-dismissable" runat="server" visible="false">
                        <a class="panel-close close" data-dismiss="alert">×</a>
                    </div>
                <div class="form-group has-feedback">
                    <asp:TextBox CssClass="form-control" ID="txtname" runat="server" placeholder="Full name" />
                    <span class="glyphicon glyphicon-user form-control-feedback"></span>
                    <asp:RequiredFieldValidator ID="RequiredFieldValidator1" Display="None" ControlToValidate="txtname"
        runat="server" ErrorMessage="Full Name is required."></asp:RequiredFieldValidator>
                </div>
                <div class="form-group has-feedback">
                    <asp:TextBox CssClass="form-control" runat="server" ID="txtemail" placeholder="Email" />
                    <span class="glyphicon glyphicon-envelope form-control-feedback"></span>
                    <asp:RequiredFieldValidator ID="RequiredFieldValidator3" Display="None" ControlToValidate="txtemail"
        runat="server" ErrorMessage="Email is required."></asp:RequiredFieldValidator>
                      <asp:RegularExpressionValidator ID="RequiredFieldValidator4" Display="None" ControlToValidate="txtemail"
        runat="server" ErrorMessage="Email is Not valid." ValidationExpression="\w+([-+.']\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*"></asp:RegularExpressionValidator>
                </div>
                <div class="form-group has-feedback">
                    <asp:TextBox CssClass="form-control" ID="txtmobile" runat="server" placeholder="Mobile" />
                    <span class="glyphicon glyphicon-phone form-control-feedback"></span>
                     <asp:RequiredFieldValidator ID="RequiredFieldValidator2" Display="None" ControlToValidate="txtmobile"
        runat="server" ErrorMessage="Phone no is required."></asp:RequiredFieldValidator>
                </div>
                <div class="form-group has-feedback">
                    <asp:TextBox class="form-control" runat="server" ID="txtpassword" placeholder="Password" TextMode="Password" />
                    <span class="glyphicon glyphicon-lock form-control-feedback"></span>
                    <asp:RequiredFieldValidator ID="RequiredFieldValidator5" Display="None" ControlToValidate="txtpassword"
        runat="server" ErrorMessage="Password is required."></asp:RequiredFieldValidator>
                </div>
                <div class="form-group has-feedback">
                    <asp:TextBox class="form-control" runat="server" ID="txtRpassword" placeholder="Retype Password" TextMode="Password" />
                    <span class="glyphicon glyphicon-log-in form-control-feedback"></span>
                    <asp:RequiredFieldValidator ID="RequiredFieldValidator6" Display="None" ControlToValidate="txtRpassword"
        runat="server" ErrorMessage="Retype Password is required."></asp:RequiredFieldValidator>
                     <asp:CompareValidator ID="CompareValidator7" Display="None" ControlToCompare="txtpassword" ControlToValidate="txtRpassword"
        runat="server" ErrorMessage="Retype Password is match." ></asp:CompareValidator>
                </div>
                <div class="form-group pull-right">
                    <asp:Button CssClass="btn btn-primary btn-block btn-flat" ID="BtnSubmit" runat="server" Text="Register" OnClick="BtnSubmit_Click" Width="111px" style="align-self:flex-end" />
                </div>

            </form>
            <br />
            <br />
            <a href="signin.aspx" class="text-center">I already have a membership</a>
        </div>
    </div>
    <script type="text/javascript">
        function WebForm_OnSubmit() {
            if (typeof (ValidatorOnSubmit) == "function" && ValidatorOnSubmit() == false) {
                $("#validation_dialog").dialog({
                    title: "Validation Error!",
                    modal: true,
                    resizable: false,
                    buttons: {
                        Close: function () {
                            $(this).dialog('close');
                        }
                    }
                });
                return false;
            }
            return true;
        }
</script>
</body>
</html>
