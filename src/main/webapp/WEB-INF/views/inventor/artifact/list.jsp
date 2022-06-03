<%--
- list.jsp
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

<acme:list>
	<acme:list-column code="any.artifact.list.label.name" path="name" width="20%"/>
	<acme:list-column code="any.artifact.list.label.code" path="code" width="20%"/>
	<acme:list-column code="any.artifact.list.label.description" path="description" width="50%"/>
	<acme:list-column code="any.artifact.form.label.retail-price" path="retailPrice" width="10%"/>
	<acme:list-column code="any.artifact.form.label.price-converted" path="convertedPrice" width="10%"/>
</acme:list>


	<!-- comprobar por qué no me hace el isTool -->
<jstl:choose>
	<jstl:when test="${isTool == true}">
		<acme:button code="any.tool.list.button.create" action="/inventor/artifact/create-tool"/>
	</jstl:when>
	<jstl:otherwise>
		<acme:button code="any.tool.list.button.create-component" action="/inventor/artifact/create-component"/>
	</jstl:otherwise>
</jstl:choose>
