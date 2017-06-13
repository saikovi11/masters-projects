from flask_wtf import Form
from wtforms import TextField, TextAreaField, SubmitField, PasswordField, IntegerField, DateTimeField, RadioField, DateField
from wtforms import validators
from models import db, User
from sqlalchemy import and_
import datetime

class ExistingUser(object):
    def __init__(self, message="Email doesn't exists"):
        self.message = message

    def __call__(self, form, field):
        if not User.query.filter_by(email=field.data).first():
            raise ValidationError(self.message)


class SignupForm(Form):
  name = TextField("Name",  [validators.Required("Please enter your name.")])
  mobile = TextField("Mobile",  [validators.Required("Please enter your mobile.")])
  email = TextField("Email",  [validators.Required("Please enter your email address."), validators.Email("Please enter your email address.")])
  password = PasswordField('Password', [validators.Required("Please enter a password.")])
  submit = SubmitField("Register")
 
  def __init__(self, *args, **kwargs):
    Form.__init__(self, *args, **kwargs)
 
  def validate(self):
    if not Form.validate(self):
      return False
     
    user = User.query.filter_by(email = self.email.data.lower()).first()
    if user:
      self.email.errors.append("That email is already taken")
      return False
    else:
      return True

class SigninForm(Form):
  email = TextField("Email",  [validators.Required("Please enter your email address."), validators.Email("Please enter your email address.")])
  password = PasswordField('Password', [validators.Required("Please enter a password.")])
  submit = SubmitField("Sign In")
   
  def __init__(self, *args, **kwargs):
    Form.__init__(self, *args, **kwargs)
 
  def validate(self):
    if not Form.validate(self):
      return False
     
    user = User.query.filter_by(email = self.email.data.lower()).first()
    if user and user.check_password(self.password.data):
      return True
    else:
      self.email.errors.append("Invalid e-mail or password")
      return False

class ForgotPassword(Form):
    email = TextField("Email",  [validators.Required("Please enter your email address."), validators.Email("Please enter your email address.")])
    submit = SubmitField("Request")
    def __init__(self, *args, **kwargs):
      Form.__init__(self, *args, **kwargs)

    def validate(self):
      if not Form.validate(self):
        return False
       
      user = User.query.filter_by(email = self.email.data.lower()).first()
      if user:
        return True
      else:
        self.email.errors.append("Email not exist")
        return False
class ResetPasswordForm(Form):
    password = PasswordField('Password', [validators.Required("Please enter a password."), validators.EqualTo('confirm', message='Passwords must match')] )
    confirm = PasswordField('Confirm Password')
    submit = SubmitField("Reset")

    def __init__(self, *args, **kwargs):
      Form.__init__(self, *args, **kwargs)

    def validate(self):
      if not Form.validate(self):
        return False
       
      return True;