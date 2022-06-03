<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<jstl:if test="${isTool == null}">
	<jstl:set var="isTool" value="false"/>
</jstl:if>

<acme:form>
	<acme:input-textbox code="any.artifact.form.label.name" path="name" />
	<acme:input-textbox code="any.artifact.form.label.code" path="code" placeholder="AAA-000-A" />
	<acme:input-textbox code="any.artifact.form.label.technology"
		path="technology" />
	<acme:input-textarea code="any.artifact.form.label.description"
		path="description" />
	<acme:input-money code="any.artifact.form.label.retail-price"
		path="retailPrice" />
	<acme:input-url code="any.artifact.form.label.link" path="link" />
	<jstl:choose>
		<jstl:when test="${isNew == true}">
			<jstl:choose>
				<jstl:when test="${isTool == true}">
					<acme:submit code="any.tool.form.button.create"
						action="/inventor/artifact/create-tool" />
				</jstl:when>
				<jstl:otherwise>
					<acme:submit code="any.tool.form.button.create"
					action="/inventor/artifact/create-component" />
				</jstl:otherwise>
			</jstl:choose>
		</jstl:when>
		<jstl:otherwise>
			<jstl:if test="${isPublish == false}">
				<acme:submit code="any.artifact.form.button.update"
					action="/inventor/artifact/update" />
				<acme:submit code="any.artifact.form.button.delete"
					action="/inventor/artifact/delete" />
				<acme:submit code="any.artifact.form.button.publish"
					action="/inventor/artifact/publish" />
			</jstl:if>
		</jstl:otherwise>
	</jstl:choose>
</acme:form>



