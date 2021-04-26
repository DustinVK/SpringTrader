  function logout(){
		sessionStorage.clear("uname");
		sessionStorage.clear("token");
		window.location.replace("./");
	}
  
  window.onclick = function(event) {
	  if (event.target == modal) {
	    modal.style.display = "none";
	  }
	}
  
  $(".stock-search").on('keyup', function (e) {
	    if (e.key === 'Enter' || e.keyCode === 13) {
	        stockSearch();
	    }
	});
  
  var menu = "<a class='nav-link' href='./'>Home</a>";
	
  let name = sessionStorage.getItem("uname");

  if(name != null){
		menu += "<button class='nav-link-button' onclick='usePortfoliosButton()'>Portfolios</button>" +
		"<li class='dropdown'>" +
        "<a class='nav-link' data-toggle='dropdown' href='#'>"+name+" <i class='fa fa-caret-down'></i>" +
        "<span class='caret'></span></a>" +
        "<ul class='dropdown-menu'>" +
        "<li><button class='btn logout-btn' onclick=''>Balance</button></li>" +
        "<li><button class='btn logout-btn' onclick='getUser()'>Account</button></li>" + 
        "<li><a href='#'><button class='btn logout-btn' onclick='logout()'>Logout</button></a></li>" +
        "</ul>"+
      	"</li>";
 
			
  } else {
	  $("#login-placeholder").load("./includes/login-form.html");
		$('#loginModal').modal('hide');
		  $(function () {
		    $('[data-toggle="tooltip"]').tooltip()
		  })
  }
  $("#nav-menu-placeholder").append(menu);
  