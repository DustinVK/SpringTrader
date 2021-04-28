$(document).ready(function(){
	$("#nav-placeholder").load("./includes/nav.html");
	var view = getQueryStringVariable('view');
	if(view === null){
		$("#content-placeholder").load("./includes/home.html");
	}
	else if(view === "stock"){
		$("#content-placeholder").load("./includes/stock.html");
		var symbol = getQueryStringVariable('symbol');
		getStock(symbol);
	}
	else if(view === "search"){
		var searchEntry = getQueryStringVariable('entry');
		stockSearch(searchEntry);
	}
	else if(view === "portfolios"){
		getPortfolios();
	}
});


var getQueryStringVariable = function ( field, url ) {
		var href = url ? url : window.location.href;
		var reg = new RegExp( '[?&]' + field + '=([^&#]*)', 'i' );
		var string = reg.exec(href);
		return string ? string[1] : null;
	};
	
function showLoggedInMenu(){
	var name = sessionStorage.getItem("uname");
	var menu = "<a class='nav-link' href='/'>Home</a>" +
		"<a class='nav-link' href='/'>Portfolio</a>" +
		"<a class='nav-link' href='/stock'>Stocks</a>" +
		"<a class='nav-link' href='/'>"+name+"</a>";

		$("#nav-menu-placeholder").append(menu);
	
}

function login(){
		var username = document.getElementById('username').value;
		var password = document.getElementById('password').value;
		var parms = { username:username, password:password };
		$.ajax({
		url: "./authenticate",
		type: 'POST',
		data: JSON.stringify(parms),
		dataType : "text",
        contentType: "application/json",
		}).fail(function(response) {
			console.log(response);
			$("#login-message").append("Invalid username or password");
			
		}).done(function(response) {
			sessionStorage.setItem("token", JSON.parse(response).jwt);
			sessionStorage.setItem("uname", username);
			location.reload();
		});
}

function getUser(){
		var username = sessionStorage.getItem("uname");
		$.ajax({
		url: "./users/"+username,
		type: 'GET',
		headers: {
    		"Authorization": "Bearer "+sessionStorage.getItem("token")
  		},
		dataType : "text",
        contentType: "application/json",
		}).fail(function(response) {
			console.log(response);
			$("#login-message").append("Invalid xxx");
			
		}).done(function(response) {
					$("#pageBody").empty();

			$("#content-placeholder").empty();
			$("#content-placeholder").append(response);
			location.reload
		});
}




// Get the modal
var modal = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}


function useSearchBarButton(){
	var input = document.getElementById('searchbar').value;
	location.replace("./?view=search&entry="+input);
}
	
function stockSearch(){
	var input = document.getElementById("searchbar").value
	$("#content-placeholder").empty();
	$("#pageBody").empty();

	$.ajax({
		url: "./search/"+input,
		type: 'GET',
		dataType : "json",
        contentType: "application/json",
	}).fail(function(response) {
		console.log("error");
		console.log(response);
    }).done(function(response) {

		var results = "<div class='result-container' id='searchResults'>";
		$.each(response, function(key, value) {
			
				results += "<div class='card mb-4'>" +
					            "<div class='card-body'>" +
					              "<h2 class='card-title'><a class='stock-header' href='./?view=stock&symbol="+value.symbol+ "'>" + value.symbol + "</a></h2>" +
					              "<p class='card-text'>" + value.name + "</p>" +				              			              
					            "</div>" +
					          "</div>";
					    	
			
			
			});
			results += "</div>";
							$("#content-placeholder").append(results);
	});
		
}


function getStock(symbol){
	$("#pageBody").empty();
	$("#content-placeholder").empty();

	$.ajax({
		url: "./stock/"+symbol,
		type: 'GET',
		dataType : "json",
        contentType: "application/json",
	}).fail(function(response) {
		console.log("error");
		console.log(response);
    }).done(function(response) {
	
		var results = "<div class='big-stock-symbol'><h1>"+response.symbol+"</h1></div>" +
		"<h2>$"+response.price+"</h2><h3>"+response.changePercent+"</h3>" +
		"<button class='btn btn-info btnround' type='buy' onclick='buyStock("+symbol+")'>Buy</button>";   	
				$("#pageBody").append(results);
				
	
	});
		
}

function usePortfoliosButton(){
	location.replace("./?view=portfolios");
}

function getPortfolios(){
		$("#pageBody").empty();
		var username = sessionStorage.getItem("uname");
		$.ajax({
		url: "./users/"+username+"/portfolios",
		type: 'GET',
		headers: {
    		"Authorization": "Bearer "+sessionStorage.getItem("token")
  		},
		dataType : "json",
        contentType: "application/json",
		}).fail(function(response) {
			console.log(response);
			$("#login-message").append("Invalid xxx");
			
		}).done(function(response) {
			var portfolio = "<h3 id='portfolio-main-header'>"+sessionStorage.getItem("uname")+"'s Portfolios</h3>";
					$.each(response, function(key, value) {
						var pl = value.metaData.cashChange;
						var percent =value.metaData.percentChange;
						if(pl < 0) {
							pl = "<th id='pl-red'>Loss: -$"+(value.metaData.cashChange *-1).toFixed(2)+"</th>";
							percent = "<th id='pl-red'> "+value.metaData.percentChange.toFixed(2)+"%</th>";
						}
						else{
							pl = "<th id='pl-green'>Profit: +$"+value.metaData.cashChange.toFixed(2)+"</th>";
							percent = "<th id='pct-green'> +"+value.metaData.percentChange.toFixed(2)+"%</th>";
						}
					
						portfolio += " <div class='portfolio-card'><table class='p-header'><tr><th>"+
						"<button class='btn btn-info btnround' id='p-trash' type='submit' onclick='deletePortfolio("+value.metaData.id+")'><i class='fa fa-times' aria-hidden='true'></i></button></th><th colspan='2'>"+value.metaData.name+"</th><th>"+percent+"</table>"+
						"<table class='column-key'><tr><th>Symbol</th><th>Price</th><th>Amount</th><th>Holdings</th><tr>";
						$.each(value.list, function(key, value) {
							portfolio += "<table class='p-row'><tr><td><a class='stock-header' href='./?view=stock&symbol="+value.symbol+ "'>" + value.symbol + "</a></td><td>"+value.price+"</td><td>"+value.amount+"</td><td>$"+value.holdings+"</td><tr>";
						});
						portfolio += "</table><table><tr><th>Cash in: $"+value.metaData.cashIn.toFixed(2)+"</th><th>Cash out: $"+value.metaData.cashOut.toFixed(2)+"</th>"+pl+"<th>Total: $"+value.metaData.totalHoldings.toFixed(2)+"</th></table>" +
						"</table><table><tr><th></th><th></th><th></th><th><button class='btn btn-info btnround' id='add-stock' type='submit' onclick=''><i class='fa fa-plus' aria-hidden='true'></i> Add Transaction</button></th></tr></table></div>";

					});
						portfolio += "<div class='portfolio-card'><button type='button' class='btn btn-info btn-round' data-toggle='modal' data-target='#portfolio-modal'><i class='fa fa-plus' aria-hidden='true'></i> New Portfolio</button><div>" ;
						$("#pageBody").append(portfolio);
		});
}

function addPortfolio(){
	var uname = sessionStorage.getItem("uname");
	var portfolioName = document.getElementById('portfolio-name').value;	
	var parms = {portfolioName:portfolioName}
	$.ajax({
		url: "./users/"+uname+"/portfolios/",
		type: 'POST',
		headers: {
    		"Authorization": "Bearer "+sessionStorage.getItem("token")
  		},
		dataType : "text",
        contentType: "application/json",
		data: JSON.stringify(parms)
	}).fail(function(response) {
		console.log(JSON.stringify(response));

    }).done(function(response){
		location.reload();
	});
}

function deletePortfolio(id){
	var confirmation = confirm("Delete Portfolio?");
	if(confirmation){
	var uname = sessionStorage.getItem("uname");
	$.ajax({
			url: "./users/"+uname+"/portfolios/"+id,
			type: 'DELETE',
			headers: {
	    		"Authorization": "Bearer "+sessionStorage.getItem("token")
	  		},
			dataType : "text",
	        contentType: "application/json",
		}).fail(function(response) {
			console.log(JSON.stringify(response));
	
	    }).done(function(response){
			alert(response);
			location.reload();
		});
	}
}