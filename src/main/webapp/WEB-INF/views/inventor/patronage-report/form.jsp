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

<jstl:if test="${isNew == null}">
	<jstl:set var="isNew" value="false" />
</jstl:if>

<acme:form>
	<acme:input-textbox
		code="inventor.patronageReport.form.label.sequenceNumber"
		path="sequenceNumber" />
	<jstl:if test="${!isNew}">
		<acme:input-textbox
			code="inventor.patronageReport.form.label.creationMoment"
			path="creationMoment" />
	</jstl:if>
	<acme:input-textbox
		code="inventor.patronageReport.form.label.memorandum"
		path="memorandum" />
	<acme:input-textbox code="inventor.patronageReport.form.label.link"
		path="link" />
	<jstl:if test="${!isNew}">
		<acme:input-checkbox
			code="inventor.patronage.report.form.checkbox.confirm" path="confirm" />
	</jstl:if>
	<acme:submit test="${isNew}"
		code="inventor.patronage.report.form.button.create"
		action="/inventor/patronage-report/create?masterId=${masterId}" />
</acme:form>
