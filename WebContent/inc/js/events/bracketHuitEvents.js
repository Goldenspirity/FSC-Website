/* Events */

jQuery(document).ready(function(){

	if ($("#resultWindowOverlay").css("display") == "none") {
		var menuHidden = true;
	} else {
		var menuHidden = false;
	}
	
	// Which result clicked on
	var resultOpened;

	// Hide result overlay
	$("#resultWindowOverlay").click(function(e) {
		if(menuHidden == false && e.target.id == "resultWindowOverlay") {
			$("#resultWindowOverlay").hide();
			menuHidden = true;
		}
	});
	
	$(document).keydown(function(e) {
		if(menuHidden == false && e.keyCode == 27) {
			$("#resultWindowOverlay").hide();
			menuHidden = true;
		}
	});

	// Click on "Result"
	$(".result").click(function(e) {
		var id = e.target.id;
		var idSplit = id.split("");
		if (idSplit.length == 2) {
			var i = idSplit[1];
		} else if (idSplit.length == 3) {
			var i = parseInt(idSplit[1].toString() + idSplit[2].toString());
		}
		resultOpened = i;
		if (i < 10) {
			if ($("#sc" + (i*2)).children().html().trim() == "" && $("#t" + (i*2+2)).children().html().trim() != "" && $("#t" + (i*2+3)).children().html().trim() != ""
				&& $("#t" + (i*2+2)).children().html().trim() != "freewin" && $("#t" + (i*2+3)).children().html().trim() != "freewin") {
				if (menuHidden == true) {
					$("#team0").html($("#t" + (i*2+2)).children().html());
					$("#team1").html($("#t" + (i*2+3)).children().html());
					$("#resultWindowOverlay").toggle();
					menuHidden = false;
					e.stopPropagation();
				}
			}
		} else if (i == 10) {
			if ($("#sc" + (i*2)).children().html().trim() == "" && $("#t" + (i*2+4)).children().html().trim() != "" && $("#t" + (i*2+5)).children().html().trim() != ""
				&& $("#t" + (i*2+4)).children().html().trim() != "freewin" && $("#t" + (i*2+5)).children().html().trim() != "freewin") {
				if (menuHidden == true) {
					$("#team0").html($("#t" + (i*2+4)).children().html());
					$("#team1").html($("#t" + (i*2+5)).children().html());
					$("#resultWindowOverlay").toggle();
					menuHidden = false;
					e.stopPropagation();
				}
			}
		} else if (i == 11) {
			if ($("#sc" + (i*2)).children().html().trim() == "" && $("#t" + (i*2+6)).children().html().trim() != "" && $("#t" + (i*2+7)).children().html().trim() != ""
				&& $("#t" + (i*2+6)).children().html().trim() != "freewin" && $("#t" + (i*2+7)).children().html().trim() != "freewin") {
				if (menuHidden == true) {
					$("#team0").html($("#t" + (i*2+6)).children().html());
					$("#team1").html($("#t" + (i*2+7)).children().html());
					$("#resultWindowOverlay").toggle();
					menuHidden = false;
					e.stopPropagation();
				}
			}
		}

	});
	
	// Submit result
	$("#submitResult").click(function(e) {
		if ($.isNumeric($("#score0").val().trim()) && $.isNumeric($("#score1").val().trim()) && $("#score0").val().trim() != $("#score1").val().trim()) {
			
			/* Add an invisible input with the match number as value */
			var newInputDiv = $(document.createElement('input'));
			newInputDiv.attr("name","matchNumber");
			newInputDiv.attr("value", resultOpened);
			newInputDiv.hide();
			$("#resultWindow form").append(newInputDiv);
			
		} else {
			e.preventDefault();
			alert("Vous devez rentrez des scores valides !");
		}
		
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