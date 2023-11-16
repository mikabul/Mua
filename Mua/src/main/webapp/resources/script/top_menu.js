var str = "btn btn-outline-secondary my-2 my-sm-0 btn_blank";
function login_function(){
	document.getElementById("user_modify").className += " hidden";
	document.getElementById("logout").className += " hidden";
	document.getElementById("login").className = str;
	document.getElementById("register").className = str;
}

function logout_function(){
	document.getElementById("login").className += " hidden";
	document.getElementById("register").className += " hidden";
	document.getElementById("user_modify").className = str;
	document.getElementById("logout").className = " str";
}

function show_hide_menu(value){
	
	if(value == false){
		document.getElementById("under_menu").className = "nav nav_under";
		return true;
	}else if(value == true){
		document.getElementById("under_menu").className = "nav nav_hide nav_under";
		return false;
	}
}