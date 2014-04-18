<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${release}/css/styles.css">

    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
    <script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
    <script src="${release}/js/home.js"></script>

    <meta charset="UTF-8">

    <script> var release = ${release} </script>
</head>
<body>
<div class="flex-container">
    <div class="flex-item-header" style="height: 10vh; padding: 0em;">
        <div class="inline" style="width: 100%; position: fixed;">
            <div style="background: #FF9226; width: 33.3333333%; height: 10vh; float:left; padding: 0em; margin: 0em; text-align: left;">
                <a id="backLink" href='#'><img id="backImage" src="${release}/images/back.png" height="50%" style="padding-top:3%; padding-left: 15%; visibility:hidden;"></a>
            </div>
            <div id="header" style="background: #FF9226; width: 33.3333333%; height: 10vh; float:left; padding: 0em; margin: 0em; line-height: 10vh; text-align: center; color: #FFFFFF; font-size:6vh;">Home</div>
            <div style="background: #FF9226; width: 33.3333333%; height: 10vh; float:left; padding: 0em; margin: 0em; line-height: 10vh; text-align: right;">
                <a href="/logout"><img id="logoutImage" src="${release}/images/exit.png" height="50%" style="padding-top:3%; padding-right: 15%"></a></div>
        </div>
    </div>
</div>
<p> </p>
<div id="footer">
    <div style="background: #FF9226; width: 50%; height: 10vh; float:left; padding: 0em; margin: 0em; text-align: left;">
        <a href="#" id="historyLink"><img id="historyImage" src="${release}/images/history.png" height="50%" style="padding-top:3%; padding-left: 15%"></a>
    </div>
    <div style="background: #FF9226; width: 50%; height: 10vh; float:left; padding: 0em; margin: 0em; text-align: right;">
        <a href="#" id="settingsLink"><img id="settingsImage" src="${release}/images/settings.png" height="50%" style="padding-top:3%; padding-right: 15%"></a>
    </div>
</div>

</body>
</html>
