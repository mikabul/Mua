var str = "btn btn-outline-success my-2 my-sm-0 btn_blank";
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