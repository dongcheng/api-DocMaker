<%@page import="com.melonlee.api.bean.FunctionBean"%><%@page import="com.melonlee.api.bean.ModuleBean"%><%@page import="com.melonlee.api.bean.ProjectBean"%><%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%><!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><html xmlns="http://www.w3.org/1999/xhtml"><head><style type="text/css">body {	font-family: 微软雅黑;	font-size: 13px;}#content {	margin: 0 220px 0 180px;}a {	color: #333;	font-size: 13px;}a:hover {	color: Red;}#custom {	margin: 20px 0 10px 50px;	border: 1px solid #D7D7D7;	padding: 10px;}#directory {	margin: 20px 0 10px 50px;	border: 1px solid #D7D7D7;	padding: 10px;}#directory h4 {	margin: 6px 0 2px 0;}#directory a {	color: #0082CB;	text-decoration: none;}#directory a:hover {	text-decoration: underline;}#directory ul {	margin: 0;	list-style-type: decimal;}#directory li {	}#directory li a {	}#directory li a:hover {	}#itemContainer {	margin: 20px 0 10px 50px;}#itemContainer h2 {	background-color: #F0F0F0;	color: #333333;	padding: 6px 0 6px 6px;	font-size: 14px;}#itemContainer p {	margin-left: 25px;}#itemContainer p {	}#itemContainer table {	border-collapse: collapse;	border-spacing: 0;	border-color: #CCCCCC;}#itemContainer a {	color: #3366BB;	font-weight: bold;	text-decoration: none;}#itemContainer a:hover {	text-decoration: underline;}#itemContainer h4 {	margin-left: 25px;	font-size: 12px;	font-weight: normal;}#itemContainer h4 a {	font-size: 12px;	font-weight: normal;}#itemContainer h4 a:hover {	color: Red;}.code {	background-color: #FBFBE7;	border: 1px dashed #2F6FAB;	padding: 13px;	color: #666652;	line-height: 150%;	font-size: 12px;}p.MsoNormal,li.MsoNormal,div.MsoNormal {	margin: 0cm;	margin-bottom: .0001pt;	text-align: justify;	text-justify: inter-ideograph;	font-size: 10.5pt;	font-family: "Calibri", "sans-serif";}</style></head><%	ProjectBean project = (ProjectBean) request.getAttribute("pro");	ArrayList<FunctionBean> allFun = new ArrayList<FunctionBean>();%><title>"<%=project.getTitle()%>"API文档</title><body>	<div style="font-size: 24px; font-weight: bold;">		<center>			"<%=project.getTitle()%>"API文档v1.0		</center>	</div>	<a id="top" name="top"></a>	<div id="content">		<div id="custom">			<a id="auth_remark" name="auth_remark"></a>			<h4>项目说明</h4>			<div style="margin-left: 25px;">				<p>					项目BaseUrl :<span style="color: Red;"> <%=project.getBaseUrl()%></span>				</p>				<p>					备注:<span style="color: Red;"><%=project.getDescStr()%></span>				</p>			</div>		</div>		<!-- 模块 -->		<h2>目录</h2>		<div id="directory">			<%				for (ModuleBean module : project.getModules()) {			%>			<div>				<div>					<h3><%=module.getTitle()%></h3>				</div>				<ul>					<%						for (FunctionBean func : module.getFunctions()) {										allFun.add(func);					%>					<li><a href="#f<%=func.getId()%>"><%=func.getTitle()%></a>					</li>					<%						}					%>				</ul>			</div>			<%				}			%>		</div>		<!-- 接口 -->		<h2>接口</h2>				<%			int fCount = 0;			for(FunctionBean funBean :allFun){			fCount++;			%>			<div id="itemContainer">					<a id="f<%=funBean.getId() %>" name="f<%=funBean.getId() %>"></a>			<h2>				<span title="该接口已实现"					style="color: Red; font-weight: bold; visibility: ;">√</span> <span><%=funBean.getTitle() %></span>			</h2>			<p><%=funBean.getTitle() %></p>			<h2>Url地址</h2>			<p>				<a target="_blank"					href="http://show.sodao.com/apps/mclient1/my/isBlack"><%=project.getBaseUrl()+funBean.getUrl() %></a>			</p>			<h2>支持格式</h2>			<p>				<strong>JSON</strong>			</p>			<h2>Http请求方式</h2>			<p>				<strong><%=funBean.getHttpMethod() %></strong>			</p>			<h2>是否需要登录验证</h2>			<p>				<strong><%=funBean.getIsLogin()==1 ? "是" : "否" %></strong>			</p>			<h2>注意事项</h2>			<p style="color: Red;"><%=funBean.getParams() %></p>			<div style="margin: 30px 0 85px 0; height: 4px; line-height: 0;">				<span style="float: right; color: #ccc; font-size: 12px;">第					{<%=fCount %>} 页 API Doc Maker-melonlee.com</span>			</div>		</div>						<%						}		 %>	</div>	<div style="position: fixed; right: 100px; bottom: 20px;">		<a href="#top">回到目录</a>	</div></body></html>