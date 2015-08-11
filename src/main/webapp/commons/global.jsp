<%--标签 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page isELIgnored="false" %>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn"  uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<%--basePath --%>
<c:set var="base" value="${pageContext.request.scheme}://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}" />
<c:set var="path" value="${base}" />

<c:set var="staticPath" value="${path }" />
<%--域名 --%>
<c:set var="domain" value="${path }" />
<%--
<c:set var="domain" value="http://h5.228.cn" />
 --%>
<%--版本信息 --%>
<c:set var="v" value="?v=2014101300123121" />
<c:set var="min" value="" />
<%--
<c:set var="min" value=".min" />
 --%>
