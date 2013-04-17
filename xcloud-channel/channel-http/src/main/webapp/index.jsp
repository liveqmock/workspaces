<%@ page contentType="text/html; charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>数据查询系统(xhttpservice])</title>
</head>
<body>
<b><font color=blue>Httpservice服务运行启动正常!</font></b><hr>
服务接口访问规则:
<br/>
<br/>
1.角色数据[get]:http://[host]/service/account/{pid}/{accountid}/getAccount<br><br>
2.短信服务[post]:http://[host]/service/sms/common/sendsms<br><br>
3.邮件服务[post]:http://[host]/service/mail/common/sendmail<br><br>
4.道具卡服务[post]:http://[host]/service/itemcard/{pid}/senditemcard<br><br>
5.踢人服务[post]:http://[host]/service/game/{pid}/sendkickuser<br><br>
6.账号封杀服务[post]:http://[host]/service/game/{pid}/sendlockaccount<br><br>
7.账号解封服务[post]:http://[host]/service/game/{pid}/sendunlockaccount<br><br>
8.禁言服务[post]:http://[host]/service/game/{pid}/sendnotalk<br><br>
9.解除禁言卡服务[post]:http://[host]/service/game/{pid}/sendtalk<br><br>
10.密保卡服务[post]:http://[host]/service/game/{pid}/sendsecuritycard<br><br>
<br><br>

接口详情见wiki:
<a href="http://wiki.iwgame.tec/pages/viewpage.action?pageId=9765182">点击进入</a>
