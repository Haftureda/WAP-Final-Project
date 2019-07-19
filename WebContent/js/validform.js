function validform(){
    var firstName=document.forms["RegForm"]["firstName"];
    var lastName=document.forms["RegForm"]["lastName"];
    var email=document.forms["RegForm"]["email"]
    if(firstName.value==""){
    	alert("First name can't be empty. Please enter your first name");
        return false;
    }
    if(lastName.value==""){
    	alert("Last name can't be empty. Please enter your last name");
        return false;
    }
    if(email.value==""){
        alert("Email can't be empty. Please enter your email");
        return false;
    }
    if(email.value.indexOf("@",0)<0){
        alert("Enter valid email");
        return false;
    }
    if(email.value.indexOf(".",0)<0){
        alert("Enter valid email");
        return false;
    }
}