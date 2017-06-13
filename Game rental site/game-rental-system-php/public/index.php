<?php
use \Psr\Http\Message\ServerRequestInterface as Request;
use \Psr\Http\Message\ResponseInterface as Response;

require '../vendor/autoload.php';

$config = [
    'settings' => [
        'displayErrorDetails' => true
    ],
];
define('KEY_ENCRYPT', 'sfdsdf2343354543');
define('SALT_HASH', 'rental@@');
session_start();

function getConnection() {
  $db = new PDO('sqlite:' . realpath(dirname(dirname(__FILE__))) . '/rental.db');
  return $db;
}

$app = new \Slim\App($config);

$container = $app->getContainer();

// Register component on container
$container['view'] = function ($container) {
    $view = new \Slim\Views\Twig('../templates', [
        'cache' => false
    ]);
    $view->addExtension(new \Slim\Views\TwigExtension(
        $container['router'],
        $container['request']->getUri()
    ));

    return $view;
};

// Register provider
$container['flash'] = function () {
    return new \Slim\Flash\Messages();
};

// Add route callbacks
$app->get('/', function ($request, $response, $args) use ($app) {
  if (isset($_SESSION['user'])) {
    return $response->withRedirect('/games');
  } else {
    return $response->withRedirect('/login'); 
  }
});

$app->any('/games', function ($request, $response, $args) use ($app) {
  if (isset($_SESSION['user'])) {
    $user = $_SESSION['user'];
    $search = '';
    if (isset($_POST['search'])) {
      $search = $_POST['search'];
    }
    $type = isset($_GET['type']) ? $_GET['type'] : 1;
    $db = getConnection();
    $query = 'SELECT * FROM games WHERE type = :type';
    $arr = ['type' => $type];
    if ($search) {
      $query .= ' and name LIKE :search';
      $arr['search'] = '%' . $search . '%';
    }
    $games = $db->prepare($query);
    $games->execute($arr);
    
    $games = $games->fetchAll();
    $db = null;
    return $this->view->render($response, 'home.html', ['user' => $user, 'games' => $games, 'search'=>$search]);
  } else {
    return $response->withRedirect('/login'); 
  }
});

$app->post('/games/{id}/comment', function ($request, $response, $args) use ($app) {
  $comment = $_POST['comment'];
  $id = $args['id'];
  if ($comment) {
    $db = getConnection();
    $stm = $db->prepare('INSERT INTO comments (comment, game_id, user_name) VALUES (:comment, :game_id, :user_name)');
    
    $stm->execute(array(
      'comment' => $comment,
      'game_id' => $id,
      'user_name' => $_SESSION['user']['name']
    ));
    $db = null;
  }
  return $response->withRedirect('/games/' . $id);
});

$app->get('/games/{id}', function ($request, $response, $args) use ($app) {
  if (isset($_SESSION['user'])) {
    $user = $_SESSION['user'];
    $db = getConnection();
    $games = $db->prepare('SELECT * FROM games WHERE id = :id');
    $games->execute(array('id' => $args['id']));
    $game = $games->fetch();
    $comments = $db->prepare('SELECT * from comments where game_id = :id');
    $comments->execute(array('id' => $args['id']));
    $comments = $comments->fetchAll();
    $db = null;
    // var_dump($games);return;
    return $this->view->render($response, 'detail.html', ['user' => $user, 'game' => $game, 'comments' => $comments]);
  } else {
    return $response->withRedirect('/login'); 
  }
});

$router = $app->getContainer()->get('router');

$app->get('/login', function($request, $response, $args) {
  if (count($this->flash->getMessage('error'))) {
    $error = $this->flash->getMessage('error');
  }
  if (count($this->flash->getMessage('message'))) {
    $messages = $this->flash->getMessage('message');
  }
  if (isset($_SESSION['email'])) {
    $email = $_SESSION['email'];
  }
  
  return $this->view->render($response, 'signin.html', ['error' => $error, 'email' => $email, 'messages' => $messages]);
});

$app->post('/login', function($request, $response, $args) use ($app) {
  $email = $_POST['email'];
  $password = $_POST['password'];
  $_SESSION['email'] = $email;
  if (!empty($email) && !filter_var($email, FILTER_VALIDATE_EMAIL) === false) {
    $db = getConnection();
    $stm = $db->prepare('SELECT * FROM users WHERE email = :email');
    $stm->execute(array('email' => $email));
    $user = $stm->fetch();
    $hasPass = hash_hmac('sha256', $password, SALT_HASH);
    if (!$user) {
      $this->flash->addMessage('error', 'Email or password incorrect!');
      return $response->withRedirect('/login');
    }

    if ($user['password'] != $hasPass) {
      $this->flash->addMessage('error', 'Email or password incorrect!');
      return $response->withRedirect('/login');
    } else {
      $_SESSION['user'] = $user;
    }
    $db = null;
    return $response->withRedirect('/');
  } else {
    $this->flash->addMessage('error', 'Please input valid email');
    return $response->withRedirect('/login');
  }
})->setName('login');

$app->get('/signup', function($request, $response, $args) {
  if (count($this->flash->getMessage('error'))) {
    $error = $this->flash->getMessage('error');
  }
  if (isset($_SESSION['form_data'])) {
    $form_data = $_SESSION['form_data'];
  }
  return $this->view->render($response, 'signup.html', ['error' => $error, 'form_data' => $form_data]);
});

$app->post('/signup', function($request, $response, $args) {
  $email = $_POST['email'];
  $password = $_POST['password'];
  $mobile = $_POST['mobile'];
  $name = $_POST['name'];
  $hasError = false;
  $_SESSION['form_data'] = $_POST;
  if (empty($email) || filter_var($email, FILTER_VALIDATE_EMAIL) === false) {
    $this->flash->addMessage('error', 'Please input valid email');
    $hasError = true;
  }
  if ($password == '') {
    $this->flash->addMessage('error', 'Please input the password');
    $hasError = true;
  }

  if ($name == '') {
    $this->flash->addMessage('error', 'Please input your name');
    $hasError = true;
  }

  if ($mobile == '') {
    $this->flash->addMessage('error', 'Please input your mobile');
    $hasError = true;
  }
  if ($hasError) {
    return $response->withRedirect('/signup');
  } else {
    $db = getConnection();
    $checkUser = $db->prepare('SELECT id FROM users WHERE email = :email');
    $checkUser->execute(array('email' => $email));
    $user = $checkUser->fetch();
    if ($user) {
      $this->flash->addMessage('error', 'This email already used');
      $db = null;
      return $response->withRedirect('/signup');
    }
    $hasPass = hash_hmac('sha256', $password, SALT_HASH);
    // var_dump($db);
    $stm = $db->prepare('INSERT INTO users (email, name, mobile, password) VALUES (:email, :name, :mobile, :password)');
    $stm->execute(array(
      'email' => $email,
      'mobile' => $mobile,
      'password' => $hasPass,
      'name' => $name
    ));
    $this->flash->addMessage('msg', 'Register success');
    unset($_SESSION['form_data']);
    $db = null;
    return $response->withRedirect('/login');
  }
});

$app->get('/reset-password', function($request, $response, $args) {
  $token = $_GET['token'];
  // return $token;
  $ciphertext_dec = base64_decode($token);
  // var_dump($ciphertext_dec);
  $iv_dec = substr($ciphertext_dec, 0, 16);
  // return $iv_dec;
  $ciphertext_dec = substr($ciphertext_dec, 16);
  $id = mcrypt_decrypt(MCRYPT_RIJNDAEL_128, KEY_ENCRYPT,
                                    $ciphertext_dec, MCRYPT_MODE_CBC, $iv_dec);
  if (!$id) {
    $this->flash->addMessage('error', 'Token invalid');
    return $response->withRedirect('/login');
  }
  
  return $this->view->render($response, 'reset-password.html', ['token' => $token]);
});

$app->get('/logout', function($request, $response, $args) {
  session_unset();
  return $response->withRedirect('/login');
});

$app->run();