<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Fluxtream Self Report</title>
    <link href="${release}/css/styles.css" rel="stylesheet">
    <script type="text/javascript" src="${release}/js/welcome.js"></script>
    <link rel="shortcut icon" href="/favicon.ico">
</head>
<body>

<div id="login">
    <div class="cont cf">
        <form method="post" action="/signIn?target=/sreport/home" id="loginForm">
            <table>
                <tr>
                    <td><label class="username_txt" for="f_username">Username/email:</label></td>
                    <td><label class="password_txt" for="f_password">Password:</label></td>
                    <td rowspan="2"><button class="login_btn">Login</button></td>
                </tr>
                <tr>
                    <td><input type="text" onkeypress="if(event.which==13) document.forms[0].submit();" class="username_input" id="f_username" name="f_username"></td>
                    <td><input type="password" onkeypress="if(event.which==13) document.forms[0].submit();" class="password_input" id="f_password" name="f_password"></td>
                </tr>
                <tr>
                    <td></td>
                    <td><a href="#" class="forgotPassword" id="lostPasswdLink">Forgot password?</a></td>
                </tr>
            </table>
        </form>
    </div>
</div>

</body>
</html>
