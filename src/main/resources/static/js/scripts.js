$(document).ready(function(){
	$("#nav-placeholder").load("./includes/nav.html");
	if(sessionStorage.getItem("uname") == null){
		
	} else {
		//showLoggedInMenu();
		getBalance();
	}
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
		let searchEntry = getQueryStringVariable('entry');
		stockSearch(searchEntry);
	}
	else if(view === "portfolio"){
		let id = getQueryStringVariable('id');
		getPortfolio(id);
	}
});


var getQueryStringVariable = function ( field, url ) {
		var href = url ? url : window.location.href;
		var reg = new RegExp( '[?&]' + field + '=([^&#]*)', 'i' );
		var string = reg.exec(href);
		return string ? string[1] : null;
	};
	
function showLoggedInMenu(){
	let name = sessionStorage.getItem("uname");
	var menu = "<a class='nav-link' href='/'>Home</a>" +
		"<a class='nav-link' href='/'>Portfolio</a>" +
		"<a class='nav-link' href='/stock'>Stocks</a>" +
		"<a class='nav-link' href='/'>"+name+"</a>";

		$("#nav-menu-placeholder").append(menu);
	
}

function getBalance(){
		$.ajax({
		url: "./users/"+sessionStorage.getItem("uname")+"/balance",
		type: 'GET',
		headers: {
    		"Authorization": "Bearer "+sessionStorage.getItem("token")
  		},
		dataType : "json",
        contentType: "application/json",
		}).fail(function(response) {
			console.log("error");
			console.log(response);
		}).done(function(response) {
			sessionStorage.setItem("balance",response.balance);
		});
}

function login(){
		let username = document.getElementById('username').value;
		let password = document.getElementById('password').value;
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
		let username = sessionStorage.getItem("uname");
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
			$("#content-placeholder").empty();
			$("#content-placeholder").append(response);
			location.reload
		});
}

function logout(){
	sessionStorage.clear("uname");
	sessionStorage.clear("token");
	window.location.replace("./");
}

// Get the modal
var modal = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

function getAdmin(){
		$.ajax({
		url: "./admin",
		type: 'GET',
		headers: {
    		"Authorization": "Bearer "+sessionStorage.getItem("token")
  		},
		dataType : "text",
        contentType: "application/json",
		}).fail(function(response) {
			console.log("error");
			console.log(response);
		}).done(function(response) {
			console.log("success?");
			console.log(response);
		});
}

function useSearchBarButton(){
	let input = document.getElementById('searchbar').value;
	location.replace("./?view=search&entry="+input);
	
	
}
	
function stockSearch(input){
	$("#pageBody").empty();
	console.log(input);
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
							$("#pageBody").append(results);
	});
		
}


function getStock(symbol){
	$("#pageBody").empty();
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