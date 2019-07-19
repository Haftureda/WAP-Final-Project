function validform(){
    var firstName=document.forms["RegForm1"]["firstName"];
    var lastName=document.forms["RegForm1"]["lastName"];
    var email=document.forms["RegForm1"]["email"]
    var password=document.forms["RegForm1"]["password"]
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
    
    if(password.value().length==0){
        alert("Password can't be empty.");
        return false;
    }
}