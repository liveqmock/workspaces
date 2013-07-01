/*---------------------------------------------------------------------------*\
|  Subject:       AppAjax Class                                               |
|  Version:       1.0.0                                                       |
|  Author:        DarGoner                                                    |
|  FileName:      AppAjax.js                                                  |
|  Created:       2007-8-19                                                   |
|  Modify:        2007-8-19                                                   |
|  Comment:       封装了Ajax对于JSON的调用,此类依赖于 prototype.js                 |
\*---------------------------------------------------------------------------*/

AppAjax = Class.create();

AppAjax.constant = {
	message : {
		open : true,
		image : "images/ajaxload.gif",
		text : "数据正在处理中,请稍候..."
	}
};

AppAjax.prototype = {
	initialize : function(action, callback, vcallback, ecallback) {
		this.action = (action) ? action : null;
		this.callback = (callback) ? callback : null;
		this.vcallback = (vcallback) ? vcallback : null;
		this.ecallback = (ecallback) ? ecallback : null;
		this.updater = null;
		this.evalScript = false;
		this.evalResult = true;
		this.param = [];
		this.block = true;
		this.xml = false;
		this.asynchronous = true;
		this.message = {
			open : AppAjax.constant.message.open,
			text : AppAjax.constant.message.text
		};
	},

	setAction : function(bizAction) {
		this.action = bizAction;
		return this;
	},

	setXml : function(xml) {
		this.xml = xml;
		return this;
	},

	setAsyn : function(as) {
		this.asynchronous = as;
		return this;
	},

	setCallBack : function(callback) {
		this.callback = callback;
		return this;
	},

	setVCallBack : function(vcallback) {
		this.vcallback = vcallback;
		return this;
	},

	setECallBack : function(ecallback) {
		this.ecallback = ecallback;
		return this;
	},

	setEvalResult : function(evalOk) {
		this.evalResult = evalOk;
		return this;
	},

	setBlock : function(isBlock) {
		this.block = isBlock;
		return this;
	},

	setMessage : function(isopen, message) {
		this.message.open = isopen;
		if (typeof message == "string" && message != "") {
			this.message.text = message;
		}
		return this;
	},

	setUpdater : function(updaterElement) {
		this.updater = updaterElement;
		return this;
	},

	setEvalScript : function(evalScript) {
		this.evalScript = evalScript;
		return this;
	},

	add : function(name, value) {
		if (name instanceof Object) {
			for ( var o in name) {
				if (typeof o == "function")
					continue;
				this.param[this.param.length] = [ o, name[o] ];
			}
		} else {
			this.param[this.param.length] = [ name,
					(typeof value == "undefined") ? $F(name) : value ];
		}
		return this;
	},

	clear : function() {
		this.param = [];
		return this;
	},

	submit : function() {
		return this._submit(AppAjaxUtils.toQueryString(this.param));
	},

	submitForm : function(frmName) {
		return this._submit(Form.serialize(frmName));
	},

	_submit : function(param) {
		AppAjaxUtils.showMessage(this.message.text);
		if (!this.action) {
			alert("\u8bf7\u5148\u8bbe\u7f6e\u63d0\u4ea4\u7684 Action !");
			return false;
		}
		if (this.block && Ajax.activeRequestCount > 0) {
			alert("\u8bf7\u5148\u7b49\u5f85\u5df2\u8fd0\u884c\u5904\u7406\u5b8c\u6210 !");
			return false;
		}
		var options = {
			method : "post",
			requestHeaders : [ "if-modifed-since", "0", "ajax_request", "true" ],
			parameters : param,
			asynchronous : this.asynchronous,
			onLoading : this.onLoading.bind(this),
			onComplete : this.onSuccess.bind(this),
			onFailure : this.onFailure.bind(this),
			onException : this.onException.bind(this)
		};
		if (!this.updater) {
			new Ajax.Request(this.action, options);
		} else {
			if (this.evalScript) {
				options.evalScripts = true;
			}
			new Ajax.Updater(this.updater, this.action, options);
		}
		return true;
	},

	onLoading : function(request) {
		if (this.message.open) {
			AppAjaxUtils.showMessage(this.message.text);
		}
	},

	onSuccess : function(request) {
		if (!request.status || (request.status < 200 || request.status >= 300)) {
			return;
		}
		if (this.message.open) {
			AppAjaxUtils.hideMessage();
		}
		try {
			try {

				if (request.responseText.indexOf("<script>") >= 0) {
					eval(AppAjaxUtils.removeHtml(request.responseText)); //主要是防止登录超时时的页面定位问题
				}
			} catch (e) {
				//don't do it
			}
			if (this.callback) {
				if (!this.updater) {
					try {

						if (this.evalResult && !this.xml) {
							request = eval('(' + request.responseText + ')');
						} else {
							request = request.responseXML;
						}
					} catch (e) {
					}
				}
				this.callback(request);
			}
		} catch (e) {
		}
		if (this.vcallback) {
			this.vcallback();
		}
	},

	onFailure : function(request) {
		if (this.message.open) {
			AppAjaxUtils.hideMessage();
		}
		if (this.ecallback) {
			this.ecallback(request);
		}
		//错误信息提示
		alert("处理异常,请检查系统配置");
	},
	onException : function(loader, e) {
		if (e && e.message) {
			alert(e.message);
		}
		if (Ajax.activeRequestCount > 0) {
			Ajax.activeRequestCount--;
		}
	}
};

AppAjaxUtils = Class.create();

AppAjaxUtils.useLoadingMessage = function(isopen, message) {
	AppAjax.constant.message.open = isopen;
	if (typeof message == "string" && message != "") {
		AppAjax.constant.message.text = message;
	}
};

AppAjaxUtils.showMessage = function(message) {
	var loadingMessage = message || AppAjax.constant.message.text;
	var processZone = $("processZone");
	if (!processZone) {
		processZone = document.createElement("div");
		processZone.setAttribute("id", "processZone");
		var imageZone = document.createElement("img");
		imageZone.src = AppAjax.constant.message.image;
		imageZone.align = "absmiddle";
		var messageZone = document.createElement("span");
		messageZone.setAttribute("id", "messageZone");
		processZone.appendChild(imageZone);
		processZone.appendChild(messageZone);
		document.body.appendChild(processZone);
	}
	Element.update("messageZone", "&nbsp;" + loadingMessage);
	Element.show(processZone);

};

AppAjaxUtils.hideMessage = function() {
	if ($("processZone")) {
		Element.hide("processZone");
	}
};

AppAjaxUtils.toQueryString = function(array) {
	var dest = [];
	if (!array || !(array instanceof Array))
		return "";
	array.each(function(sub) {
		if (!(sub instanceof Array) || sub.length < 2)
			return;
		dest.push(sub[0] + "=" + encodeURIComponent(sub[1]));
	});
	return dest.join("&");
};
AppAjaxUtils.removeHtml = function(html) {
	if (html == null)
		return "";
	html = html.replace(/\<[^>]*>[1-9]/g, "<");
	html = html.replace(/\<[^>]*>[1-9]/g, "<");
	html = html.replace(/\<[^>]*>/g, "");
	html = html.replace(/&nbsp;/g, "");
	return html;
};

AppAjaxUtils.prototype = {
	initialize : function(data) {
		this.dom = data || {};
	},

	getData : function() {
		return this.dom;
	},

	getProperty : function(property) {
		if (property == null || property == "")
			return null;
		return this.dom[property];
	},

	getProperties : function(properties, flag) {
		var values = [];
		var l_flag = (typeof flag == "string") ? flag : ",";
		var loader = this;
		properties.split(l_flag).each(function(name) {
			var val = loader.getProperty(name);
			values[values.length] = (val == null) ? "" : val;
		});
		return values.join(l_flag);
	},

	setProperty : function(element, property) {
		if (typeof element != "object")
			return;
		var value = this.getProperty((property) ? property : element.name
				|| element.id);
		element.value = value ? value : element.value;
	},

	setForm : function(formName) {
		var loader = this;
		Form.getElements(formName).each(
				function(element) {
					var value = loader.getProperty((element.name || element.id)
							.toLowerCase());
					if (value == null)
						return;
					if (element.type == 'text' || element.type == 'hidden'
							|| element.type == 'textarea') {
						element.value = value;
					} else if (element.type == "checkbox") {
						element.checked = (element.value == value) ? true
								: false;
					} else if (element.type == "radio") {
						element.each(function(e) {
							if (e.value == value) {
								e.checked = true;
								return;
							}
						});
					} else if (element.type == "select-one") {
						$A(element.options).each(function(e) {
							if (e.value == value) {
								e.selected = true;
								return;
							}
						});
					} else if (element.type == "select-multiple") {
						//todo
					}
				});
	}
};