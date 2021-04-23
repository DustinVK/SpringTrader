<div class="result-container">

<tr>
	<th>Symbol</th>
	<th>Name</th>
	<th>Currency</th>
</tr>
<table id="results">

<tr th:each="result : ${results}">
	<td th:text="${result.currency}"></td>
</tr>
</div>
</table>