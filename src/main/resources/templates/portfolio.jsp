<H1>Portfolio</H1>


<table id="stocks-table">
<tr>
<tr>
	<th class="folio-table-header">Stocks</th>
</tr>
	<th>Symbol</th>
	<th>Amount</th>
	<th>Price</th>
</tr>


<tr th:each="userStock : ${userStocks}">
	<td th:text="${userStock.symbol}"></td>
	<td th:text="${userStock.amount}"></td>
	<td th:text="${userStock.price}"></td>
</tr>

</table>
