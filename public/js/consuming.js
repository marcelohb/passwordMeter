$(document).ready(function() {
	$('#score').html("0%");
	$('#complexity').html("Too Short");
    $('#password').change(
		function() {
			consult();
		});
});

function consult() {
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: "http://localhost:8787/validade",
        dataType: "json",
        data: $('#password').val(),
        success: function(data, textStatus, jqXHR){
            $('#score').html(data.score + "%");
			$('#complexity').html(data.complexity);
        },
        error: function(jqXHR, textStatus, errorThrown){
        	$('#score').html("0%");
        	$('#complexity').html("Too Short");
        }
    });
}
