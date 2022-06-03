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
		<acme:form>
			<acme:input-textbox code="inventor.toolkit.form.label.code" path="code"/>	
			<acme:input-textbox code="inventor.toolkit.form.label.title" path="title"/>	
			<acme:input-textarea code="inventor.toolkit.form.label.description" path="description"/>
			<acme:input-textbox code="inventor.toolkit.form.label.assemblyNotes" path="assemblyNotes"/>
			<acme:input-textbox code="inventor.toolkit.form.label.link" path="link"/>
			
			<acme:submit code="inventor.toolkit.form.button.create" action="/inventor/tool-kit/create"/>
		
		</acme:form>
	</jstl:when>
	<jstl:otherwise>
		<acme:form>
			<acme:input-textbox code="inventor.toolkit.form.label.code" path="code"/>
			<acme:input-textbox code="inventor.toolkit.form.label.title" path="title"/>	
			<acme:input-textarea code="inventor.toolkit.form.label.description" path="description"/>
			<acme:input-textbox code="inventor.toolkit.form.label.assemblyNotes" path="assemblyNotes"/>
			<acme:input-textbox code="inventor.toolkit.form.label.link" path="link"/>
			<jstl:if test="${isPublish==false}">
				<acme:submit code="inventor.toolkit.form.button.update" action="/inventor/tool-kit/update"/>
				<acme:submit code="inventor.toolkit.form.button.delete" action="/inventor/tool-kit/delete"/>
				<acme:submit code="inventor.toolkit.form.button.publish" action="/inventor/tool-kit/publish"/>
			</jstl:if>
		</acme:form>
		<acme:button code="any.toolkit.form.button.partOf.component" action="/inventor/part-of/list-component?masterId=${id}"/>
		<acme:button code="any.toolkit.form.button.partOf.tool" action="/inventor/part-of/list-tool?masterId=${id}"/>
	</jstl:otherwise>
</jstl:choose>
