from flask_sqlalchemy import SQLAlchemy
from werkzeug import generate_password_hash, check_password_hash
from sqlalchemy.orm import relationship
from sqlalchemy import ForeignKey
from itsdangerous import TimedJSONWebSignatureSerializer as Serializer
from flask import current_app
 
db = SQLAlchemy()
 
class User(db.Model):
  __tablename__ = 'users'
  id = db.Column(db.Integer, primary_key = True)
  name = db.Column(db.String(255))
  email = db.Column(db.String(255), unique=True)
  mobile = db.Column(db.String(20))
  password = db.Column(db.String(54))
  def __init__(self, name, mobile, email, password):
    self.name = name.title()
    self.mobile = mobile.title()
    self.email = email.lower()
    self.set_password(password)
     
  def set_password(self, password):
    self.password = generate_password_hash(password)
   
  def check_password(self, password):
    return check_password_hash(self.password, password)
  def get_token(self, expiration=1800):
        s = Serializer(current_app.config['SECRET_KEY'], expiration)
        return s.dumps({'user': self.id}).decode('utf-8')

  @staticmethod
  def verify_token(token):
      s = Serializer(current_app.config['SECRET_KEY'])
      try:
          data = s.loads(token)
      except:
          return None
      id = data.get('user')
      if id:
          return User.query.get(id)
      return None
class Game(db.Model):
  __tablename__ = 'games'
  id = db.Column(db.Integer, primary_key = True)
  name = db.Column(db.String(255))
  description = db.Column(db.String(255))
  category = db.Column(db.String(20))
  thumbnail = db.Column(db.String(255))
  type = db.Column(db.Integer)
  rate = db.Column(db.Integer)

class Comment(db.Model):
  __tablename__ = 'comments'
  id = db.Column(db.Integer, primary_key = True)
  comment = db.Column(db.String(255))
  game_id = db.Column(db.Integer)
  user_name = db.Column(db.String(255))