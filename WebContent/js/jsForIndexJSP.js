function validateLogin ( sEmail, sPassword ) {
	
	var emailInput = document.getElementById(sEmail);
	var passwordInput = document.getElementById(sPassword);
	if ( !emailInput.value ||  !passwordInput.value ) {
		switch ( "" ) {
		case emailInput.value:
			emailInput.value = "MUST";
			break;
		case passwordInput.value:
			passwordInput.style.backgroundColor = "red";
			break;
		default:break;
		}
	return false;
	}
	return true;
	
	
}

function validateRegistration ( fName, lName, email, rPassword ) {
	
	var fNameInput = document.getElementById(fName);
	var lNameInput = document.getElementById(lName);
	var emailInput = document.getElementById(email);
	var rPasswordInput = document.getElementById(rPassword);
	if ( !fNameInput.value || !lNameInput.value || !emailInput.value || !rPasswordInput.value ) {
		switch ( "" ) {
		case fNameInput.value:
			fNameInput.value = "MUST";
			break;
		case lNameInput.value:
			lNameInput.value = "MUST";
			break;
		case emailInput.value:
			emailInput.value = "MUST";
			break;
		case rPasswordInput.value:
			rPasswordInput.style.backgroundColor = "red";
		default:break;
		}
	return false;
	}
	return true;
}