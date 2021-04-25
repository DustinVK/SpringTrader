<H1>Portfolio</H1>
<table id="result-table">
<tr>
	<th>Symbol | </th>
	<th>Amount</th>
	<th>| Price </th>
	<th>| Holdings </th>
</tr>


<tr th:each="portfolio,j : ${portfolios}">
	<th th:text="${portfolio.name}"></th>
	<tr th:each="list,i : ${list}">
		<th th:text="${list.symbol}"></th>
</tr>

</table>
