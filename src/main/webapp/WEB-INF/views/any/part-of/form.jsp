<%--
- form.jsp
-
- Copyright (C) 2012-2022 Rafael Corchuelo.
-
- In keeping with the traditional purpose of furthering education and research, it is
- the policy of the copyright owner to permit non-commercial use and redistribution of
- this software. It has been tested carefully, but it is not guaranteed for any particular
- purposes.  The copyright owner does not offer any warranties or representations, nor do
- they accept any liabilities with respect to them.
--%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="true">
	<acme:input-textbox code="any.partof.form.label.quantity" path="quantity"/>	
	<acme:input-textbox code="any.partof.form.label.name" path="artifact.name"/>
	<acme:input-textbox code="any.partof.form.label.technology" path="artifact.technology"/>
	<acme:input-textbox code="any.partof.form.label.description" path="artifact.description"/>
	<acme:input-textbox code="any.partof.form.label.retailPrice" path="artifact.retailPrice"/>
	<acme:input-textbox code="any.partof.form.label.link" path="artifact.link"/>	
	<acme:input-textbox code="any.partof.form.label.type" path="artifact.type"/>
</acme:form>
