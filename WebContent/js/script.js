function onloadingFunction(){
	$('#btn_add').click(addStudent);
   $('#btn_search').click(searchStudent);
}
	
	function addStudent(){
		var id = $('#id').val();
		var fName = $('#first_name').val();
		var lName = $('#last_name').val();
		var em = $('#email').val();
		var cr = $('#course').val();
		var student = {Id:id, firstName:fName, lastName:lName,email:em,course:cr};
		$.post('student',{stu: JSON.stringify(student)}, processData, "json")
	}
	
	function searchStudent() {
		var value=$('#searchStudent').val();
		//var searchStudent={searchStudent:value};
		$.post('search',{searchStudent:value},processSearchData,"json")
    }
  function processSearchData(data) {
      $('#tbl_students .defaultTr').hide();
        var td0 = $('<td>').text(data.id);
        var td1 = $('<td>').text(data.firstName);
		var td2 = $('<td>').text(data.lastName);
		var td3 = $('<td>').text(data.email);
		var td4 = $('<td>').text(data.course);
		var tr =  $('<tr>').append(td0).append(td1).append(td2).append(td3).append(td4);
      $('#tbl_students>tbody').append(tr);
  }

	function processData(data){
		//data = JSON.parse(data);
		    var td0 = $('<td>').text(data.id);
	        var td1 = $('<td>').text(data.firstName);
			var td2 = $('<td>').text(data.lastName);
			var td3 = $('<td>').text(data.email);
			var td4 = $('<td>').text(data.course);
			var tr =  $('<tr>').append(td0).append(td1).append(td2).append(td3).append(td4);
		$('#tbl_students>tbody').append(tr);
	}
 