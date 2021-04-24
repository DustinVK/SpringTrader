<H1>Portfolio</H1>

<span th:text="${test}" />
<table id="result-table">
<tr>
<tr><th></th>
	<th class="folio-table-header">Stocks</th>
	<th></th>
</tr>
	<th>Symbol | </th>
	<th>Amount</th>
	<th>| Price </th>
</tr>


<tr th:each="userStock : ${userStocks}">
	<td th:text="${userStock.symbol}">|</td>
	<td th:text="${userStock.amount}"></td>
	<td th:text="${userStock.price}"></td>
	
</tr>

</table>
