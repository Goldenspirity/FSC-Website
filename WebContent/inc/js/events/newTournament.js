var inputNumber = 1;


$(document).ready(function() {
	$("#AddOneTeamButton").click(function() {
		var colNumber = Math.ceil(inputNumber/8);
		console.log(inputNumber);
		console.log(colNumber);
		var newDiv = $(document.createElement('div'));
		newDiv.addClass("labelInput");
		newDiv.attr("id","labelInput" + inputNumber);
		var newInput = $(document.createElement('input'));
		newInput.attr("name","team" + inputNumber);
		newInput.attr("id","team" + inputNumber);
		var newLabel = $(document.createElement('label'));
		newLabel.attr("for","team" + inputNumber);
		newLabel.html("Equipe " + (inputNumber + 1));

		if (inputNumber/8 == colNumber) {
			var newTeamsDiv = $(document.createElement('div'));
			newTeamsDiv.attr("id","teams" + (colNumber + 1));
			$("#allTeams").append(newTeamsDiv);
			$("#teams" + (colNumber + 1)).append(newDiv);
		} else {
			$("#teams" + colNumber).append(newDiv);
		}
		$("#labelInput" + inputNumber).append(newLabel);
		$("#labelInput" + inputNumber).append(newInput);
		inputNumber = inputNumber + 1;
	});
	
	// Start tournament
	$("#startTournament").click(function(e){
		if ($("#title").val().trim() == "") {
			e.preventDefault();
			if ($("#date").val().trim() == "") {
				alert("Il faut saisir un titre et une date !")
			} else {
				alert("Il faut saisir un titre !");
			}
		} else {
			if ($("#date").val().trim() == "") {
				e.preventDefault();
				alert("Il faut saisir une date !")
			} 
		}
	});
	
	// Back button
	$("#backLink").mouseenter(function() {
		$("#backLink").css("background-color", "rgb(40, 40, 40)");
	});
	
	$("#backLink").mouseleave(function() {
		$("#backLink").css("background-color", "rgba(21, 0, 0, 0.95)");
	});
	
	$("#backLink").click(function (){
		$(location).attr('href', 'events/tournoisInternes')
	});
});