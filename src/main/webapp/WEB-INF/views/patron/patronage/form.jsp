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


	
	<jstl:choose>
<jstl:when test="${isNew}">
<acme:form >
	<acme:input-textbox code="patron.patronage.form.label.status" path="status"/>	
	<acme:input-textbox code="patron.patronage.form.label.code" path="code" placeholder="AAA-000-A"/>	
	<acme:input-textarea code="patron.patronage.form.label.legalStuff" path="legalStuff"/>
	<acme:input-textbox code="patron.patronage.form.label.budget" path="budget"/>
	<acme:input-textbox code="patron.patronage.form.label.startDate" path="startDate"/>
	<acme:input-textbox code="patron.patronage.form.label.finishDate" path="finishDate"/>
	<acme:input-textbox code="patron.patronage.form.label.link" path="link"/>
	<acme:input-select code="patron.patronage.form.label.inventor" path="inventorId">
	   			<jstl:forEach items="${inventor}" var="inventor">
					<acme:input-option code="${inventor.getUserAccount().getUsername()}" value="${inventor.getId()}" selected="${ inventor.getId() == inventId }"/>
				</jstl:forEach>
			</acme:input-select>
<acme:submit code="patron.patronage.form.button.create" action="/patron/patronage/create"/>

</acme:form>
</jstl:when>


<jstl:otherwise>	
<acme:form >
	<acme:input-textbox code="patron.patronage.form.label.status" path="status"/>	
	<acme:input-textbox code="patron.patronage.form.label.code" path="code"/>	
	<acme:input-textarea code="patron.patronage.form.label.legalStuff" path="legalStuff"/>
	<acme:input-textbox code="patron.patronage.form.label.budget" path="budget"/>
	<acme:input-textbox code="patron.patronage.form.label.startDate" path="startDate"/>
	<acme:input-textbox code="patron.patronage.form.label.finishDate" path="finishDate"/>
	<acme:input-textbox code="patron.patronage.form.label.link" path="link"/>

<jstl:if test="${isPublish == false}">
<acme:submit code="patron.patronage.form.button.update" action="/patron/patronage/update"/>
<acme:submit code="patron.patronage.form.button.delete" action="/patron/patronage/delete"/>
<acme:submit code="patron.patronage.form.button.publish" action="/patron/patronage/publish" />
</jstl:if>
</acme:form>
	</jstl:otherwise>
</jstl:choose>