<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<div style="margin: auto;width:30%;padding-top:50px">
<form id="form1" name="form1" method="post">
<div class="form-group">
<input type="text" class="form-control" placeholder= 'Search by id...' id="roll_no"><br>
<p align="center"><button type="submit" class="btn btn-primary btn-default" id="search">Submit</button></p><br>
<label for="city">Display  name of students:</label>
<input type="email" class="form-control" placeholder= 'first name' id="city_name"><br><br>
</div>
</form>
</div>
<script>
$("#search").click(function() {
var id= $('#id').val();
first_name.value = first_name;
$.ajax({
url: "fetch.jsp",
type: 'POST',
data: {id: id},
success: function(data) {
$('#city_name').val(data);
alert(data);
var city_name = data;
}
});
});
</script>
</body>
</html>