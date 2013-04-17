<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>绿岸网络运营管理平台</title>
	
	<style type="text/css">
	#loading {
	  position: absolute;
	  left: 45%;
	  top: 40%;
	  margin-left: -45px;
	  padding: 2px;
	  z-index: 20001;
	  height: auto;
	  border: 1px solid #ccc;
	}
	
	#loading a {
	  color: #225588;
	}
	
	#loading .loading-indicator {
	  background: white;
	  color: #444;
	  font: bold 14px tahoma, arial, helvetica;
	  padding: 10px;
	  margin: 0;
	  height: auto;
	}
	
	#loading .loading-indicator img {
	  margin-right:8px;
	  float:left;
	  vertical-align:top;
	}
	
	#loading-msg {
	  font: normal 10px arial, tahoma, sans-serif;
	  width: 200px;
	}
	
	</style>
</head>
<body>
	<script type="text/javascript">
		var xportalConfig={"maxMenu":"10"};
	</script>
	
	<div id="loading">
	    <div class="loading-indicator">
	    <img src="loading.gif" width="32" height="32" align="bottom"/>绿&nbsp;&nbsp;&nbsp;&nbsp;岸&nbsp;&nbsp;&nbsp;&nbsp;网&nbsp;&nbsp;&nbsp;&nbsp;络<br />
	    <span id="loading-msg">加载中，稍候……</span>
	    </div>
	</div>
	<script type="text/javascript" language="javascript" src="XPortal/XPortal.nocache.js"></script>
	<iframe src="javascript:''" id="__gwt_historyFrame" tabIndex='-1' style="position: absolute; width: 0;height: 0; border: 0;"></iframe>
	<noscript>
		<div style="width: 22em; position: absolute; left: 50%; margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif;">
			Your web browser must have JavaScript enabled
			in order for this application to display correctly.
		</div>
	</noscript>	
</body>
</html>