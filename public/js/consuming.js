$(document).ready(function() {
//    $.ajax({
//        url: "http://localhost:8787/validade"
//    }).then(function(data, status, jqxhr) {
//       $('#score').append(data.score + "%");
//       $('#complexity').append(data.complexity);
//       console.log(jqxhr);
//    });
    consult();
});

function consult() {
    console.log('addWine');
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: "http://localhost:8787/validade",
        dataType: "json",
        data: $('#password').val(),
        success: function(data, textStatus, jqXHR){
            $('#score').append(data.score + "%");
			$('#complexity').append(data.complexity);
        },
        error: function(jqXHR, textStatus, errorThrown){
            alert('addWine error: ' + textStatus);
        }
    });
}
