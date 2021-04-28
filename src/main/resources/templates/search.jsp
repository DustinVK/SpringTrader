
			    	
<div class='card mb-4'>
<div class='card-body'>
<tr th:each="result : ${results}">
	<td th:text="${result.symbol}"></td>
	<td th:text="${result.name}"></td>
</tr>		              			              
</div>
</div>
		
