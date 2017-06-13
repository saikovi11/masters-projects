import os
import sqlite3
from flask import Flask, request, session, g, redirect, url_for, abort, \
     render_template, flash, jsonify
from forms import SignupForm, SigninForm;
from models import db, User, Game, Comment;
import datetime

# create our little application :)
app = Flask(__name__, static_url_path='')
app.config.from_object(__name__)
app.config.from_pyfile('application.cfg', silent=True)
# Load default config and override config from an environment variable
app.config.update(dict(
    DATABASE=os.path.join(app.root_path, 'rental.db'),
    SECRET_KEY='rental',
    USERNAME='admin',
    PASSWORD='123456',
    SQLALCHEMY_DATABASE_URI='sqlite:///'+ os.path.join(app.root_path, 'rental.db')
))

db.init_app(app)

def init_db():
    db = get_db()
    with app.open_resource('schema.sql', mode='r') as f:
        db.cursor().executescript(f.read())
    db.commit()

@app.cli.command('initdb')
def initdb_command():
    """Initializes the database."""
    init_db()
    print 'Initialized the database.'
    
def connect_db():
    """Connects to the specific database."""
    rv = sqlite3.connect(app.config['DATABASE'])
    rv.row_factory = sqlite3.Row
    return rv

def get_db():
    """Opens a new database connection if there is none yet for the
    current application context.
    """
    if not hasattr(g, 'sqlite_db'):
        g.sqlite_db = connect_db()
    return g.sqlite_db

@app.cli.command('migrationdb')
def migration():
  db = get_db()
  with app.open_resource('migrations.sql', mode='r') as f:
        db.cursor().executescript(f.read())
  db.commit()
  print 'Migration completed'

@app.teardown_appcontext
def close_db(error):
    """Closes the database again at the end of the request."""
    if hasattr(g, 'sqlite_db'):
        g.sqlite_db.close()

@app.route('/')
def index():
  if 'email' not in session:
    return redirect(url_for('signin'))
  else:
    return redirect(url_for('games', type=1))
@app.route('/games')
@app.route('/games/<id>')
def games(id = None):
  if 'email' not in session:
    return redirect(url_for('signin'))
  else:
    if not id:
      t = request.args.get('type')
      if not t:
        games = Game.query.all()
      else:
        games = Game.query.filter_by(type=t).all()
      return render_template('home.html', games=games)
    else:
      game = Game.query.get(id)
      return render_template('detail.html', game=game)


@app.route('/signup', methods=['GET', 'POST'])
def signup():
  form = SignupForm()
  if request.method == 'POST':
    if form.validate() == False:
      return render_template('signup.html', form=form)
    else:
      newuser = User(form.name.data, form.mobile.data, form.email.data, form.password.data)
      db.session.add(newuser)
      db.session.commit()
      session['message'] = 'Welcome';
      return redirect(url_for('signin'))
  elif request.method == 'GET':
    return render_template('signup.html', form=form)

@app.route('/signin', methods=['GET', 'POST'])
def signin():
  form = SigninForm()
  if request.method == 'POST':
    if form.validate() == False:
      return render_template('signin.html', form=form)
    else:
      session['email'] = form.email.data
      session['user_name'] = User.query.filter_by(email=form.email.data).first().name
      return redirect(url_for('index'))
  elif request.method == 'GET':
    return render_template('signin.html', form=form)

@app.route('/logout')
def signout():
  if 'email' not in session:
    return redirect(url_for('signin'))
     
  session.pop('email', None)
  return redirect(url_for('index'))

@app.route('/detail/<id>')
def detail(id):
  # id = request.args.get('id')
  name = 'Hotel ' + id
  img = '/img/0' + id  + '.jpg'
  return render_template('detail.html', name=name, img=img)

def send_mail(subject, to, message, html):
  msg = Message(subject, recipients=[to])
  msg.body = message;
  msg.html = html
  mail.send(msg)


if __name__ == "__main__":
    app.run()