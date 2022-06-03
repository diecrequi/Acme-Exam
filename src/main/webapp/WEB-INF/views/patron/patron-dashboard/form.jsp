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

<h1>
	<acme:message code="patron.dashboard.form.title.patron-dashboard" />
</h1>

<h2>
	<acme:message code="patron.dashboard.form.title.patronages" />
</h2>

<table class="table table-sm">
	<jstl:forEach var="entry" items="${patronDashboard.totalNumberOfPatronages.entrySet()}">
		<tr>
			<th scope="row"><acme:message
					code="patron.dashboard.form.label.total-number-for" /> <acme:print
					value="${entry.key}" /></th>
			<td><acme:print value="${entry.value}" /></td>
		</tr>
	</jstl:forEach>
</table>

<h3>
	<acme:message code="patron.dashboard.form.title.currencies" />
</h3>
<table class="table table-sm">
	<jstl:forEach var="entry" items="${patronDashboard.avgBudget.entrySet()}">
		<tr>
			<th scope="row"><acme:message
					code="patron.dashboard.form.label.average-budget-for" /> <acme:print
					value="${entry.key.first}" /> <acme:message
					code="patron.dashboard.form.label.in" /> <acme:print
					value="${entry.key.second}" /></th>
			<td><acme:print value="${entry.value}" /> <acme:print
					value="${entry.key.second}" /></td>
		</tr>
	</jstl:forEach>
	<jstl:forEach var="entry" items="${patronDashboard.deviationBudget.entrySet()}">
		<tr>
			<th scope="row"><acme:message
					code="patron.dashboard.form.label.deviation-budget-for" /> <acme:print
					value="${entry.key.first}" /> <acme:message
					code="patron.dashboard.form.label.in" /> <acme:print
					value="${entry.key.second}" /></th>
			<td><acme:print value="${entry.value}" /> <acme:print
					value="${entry.key.second}" /></td>
		</tr>
	</jstl:forEach>
	<jstl:forEach var="entry" items="${patronDashboard.maxBudget.entrySet()}">
		<tr>
			<th scope="row"><acme:message
					code="patron.dashboard.form.label.max-budget-for" /> <acme:print
					value="${entry.key.first}" /> <acme:message
					code="patron.dashboard.form.label.in" /> <acme:print
					value="${entry.key.second}" /></th>
			<td><acme:print value="${entry.value}" /> <acme:print
					value="${entry.key.second}" /></td>
		</tr>
	</jstl:forEach>
	<jstl:forEach var="entry" items="${patronDashboard.minBudget.entrySet()}">
		<tr>
			<th scope="row"><acme:message
					code="patron.dashboard.form.label.min-budget-for" /> <acme:print
					value="${entry.key.first}" /> <acme:message
					code="patron.dashboard.form.label.in" /> <acme:print
					value="${entry.key.second}" /></th>
			<td><acme:print value="${entry.value}" /> <acme:print
					value="${entry.key.second}" /></td>
		</tr>
	</jstl:forEach>
</table>

