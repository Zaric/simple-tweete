<#import "/spring.ftl" as spring />
<!DOCTYPE HTML>
<html>
<head>
    <title>Tweete for SoftwareMill</title>
    <link href="resources/css/bootstrap.css" rel="stylesheet">
    <link href="resources/css/bootstrap-responsive.css" rel="stylesheet">
    <link href="resources/css/style.css" rel="stylesheet">
    <script src="resources/js/bootstrap.min.js"></script>
    <script src="resources/js/code.js"></script>
</head>
<body>
<div class="container">
    <h2>Welcome to Twitter killer</h2>
    <form id="mainForm" class=".form-inline" action="saveTweet" modelAttribute="tweet" method="POST">
        Listen world what I want to say!<br />
        <@spring.formTextarea path="tweet.value" attributes="class='input-xxlarge' placeholder='Say something...' onkeypress='runScript(event)'"/>
        <@spring.showErrors separator=", " />
        <input type="submit" value="Submit" class="btn"/>
    </form>
    <h3>Tweets history</h3>
    <ul class="unstyled prettyprint">
    <#list tweets as tweet>
        <li>${tweet}</li>
    </#list>
    </ul>
</div>
</body>
</html>