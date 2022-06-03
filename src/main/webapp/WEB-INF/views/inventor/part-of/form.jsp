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
<%@ page import="acme.artifact.ArtifactType" %>

<acme:form>
	<jstl:if test="${!acme:anyOf(command, 'add-tool')}">
		<acme:input-integer code="any.partof.form.label.quantity"
			path="quantity" readonly="${artifact.type == ArtifactType.TOOL}" />
	</jstl:if>
	<acme:input-select code="any.partof.list.label.artifact"
		path="artifact">
		<jstl:forEach items="${artifacts}" var="art">
			<acme:input-option code="${art.code}" value="${art.id}"
				selected="${ art.id == artifact.id }" />
		</jstl:forEach>
	</acme:input-select>
	<jstl:if test="${acme:anyOf(command, 'add-tool')}">
		<acme:submit code="patron.patronage.form.button.create"
			action="/inventor/part-of/add-tool?masterId=${toolkit.id}" />
	</jstl:if>
	<jstl:if test="${acme:anyOf(command, 'add-component')}">
		<acme:submit code="patron.patronage.form.button.create"
			action="/inventor/part-of/add-component?masterId=${toolkit.id}" />
	</jstl:if>
	<jstl:if test="${acme:anyOf(command, 'show') && !toolkitIsPublish}">
		<acme:submit code="patron.patronage.form.button.update"
			action="/inventor/part-of/update?id=${id}" />
		<acme:submit code="patron.patronage.form.button.delete"
			action="/inventor/part-of/delete?id=${id}" />
	</jstl:if>
</acme:form>
