<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="ie=edge">

<script type="text/javascript" src="./resources/js/jquery-1.11.0.min.js"></script>

<link rel="stylesheet" href="./resources/css/login.css" type="text/css">

<title>Login</title>
</head>
<body>
  <div class="login-wrapp">
    <div class="login-top">
      <a href="#">
        <img src="./resources/img/img_avatar.png">
      </a>
    </div>
    <div class="login-box">
      <form action="action_page.php">
        <h1 class="login-title">LOGIN</h1>
        <ul class="login-form">
          <li>
            <strong class="login-guide">EMAIL</strong>
            <input type="text" placeholder="example@regyu.com" name="uname">
            <!-- <input type="text" placeholder="example@regyu.com" name="uname" required> -->
          </li>
          <li>
            <strong class="login-guide">PASSWORD</strong>
            <input type="password" placeholder="****" name="psw">
            <!-- <input type="password" placeholder="****" name="psw" required> -->
          </li>
        </ul>
        <button type="submit">Login</button>
        <label class="login-guide">
          <input type="checkbox" checked="checked" name="remember">Remember me
        </label>
        <a href="#" class="login-guide"> Forgot password</a>
      </form>
    </div>
  </div>
</body>
</html>