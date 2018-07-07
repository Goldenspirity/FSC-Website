var sizeBracket = 8, numberOfTeamDivs, numberOfScoreDivs, numberOfMainDiv, numberOfSecundaryDiv, numberOftsDiv, numberOfResults;
var arrayOfTeamDivs = [], arrayOfScoreDivs = [], arrayOfMainDivs = [], arrayOfSecundaryDivs = [], arrayOftsDivs = [], arrayOfResults = [], arrayOfMatchDivs = [];

if (sizeBracket == 8) {
	numberOfTeamDivs = 32;
	numberOfScoreDivs = 24;
	numberOfMainDiv = 3;
	numberOfSecundaryDiv = 11;
	numberOftsDiv = 24;
	numberOfResults = 12;
} else {
	numberOfTeamDivs = 0;
	numberOfScoreDivs = 0;
	numberOfMainDiv = 0;
	numberOfSecundaryDiv = 0;
	numberOftsDiv = 0;
	numberOfResults = 0;
}

/* Creation of all needed elements */

// Create wrapping div around m1 and m2
var newWrappingDiv = $(document.createElement('div'));
newWrappingDiv.addClass('wrappingDiv');
newWrappingDiv.attr("id",'wrappingDiv');

// Create all main divs
for (var i = 0; i < numberOfMainDiv; i++) {
	var newMainDiv = $(document.createElement('div'));
	newMainDiv.addClass('mainDiv');
	newMainDiv.attr("id",'m'+i);
	// newMainDiv.html("<p>Main div n°" + i + "</p>");
	arrayOfMainDivs.push(newMainDiv);
}

// Create all secundary divs
for (var i = 0; i < numberOfSecundaryDiv; i++) {
	var newSecundaryDiv = $(document.createElement('div'));
	newSecundaryDiv.addClass('secundaryDiv');
	newSecundaryDiv.attr("id",'se'+i);;
	arrayOfSecundaryDivs.push(newSecundaryDiv);
}

// Create all match divs
for (var i = 0; u < numberOfResults; i++) {
	var newMatchDiv = $(document.createElement('div'));
	newMatchDiv.addClass('matchDiv');
	newMatchDiv.attr("id",'match'+i);
	arrayOfMatchDivs.push(newMatchDiv);
}

// Create all ts divs
for (var i = 0; i < numberOftsDiv; i++) {
	var newtsDiv = $(document.createElement('div'));
	newtsDiv.addClass('tsDiv');
	newtsDiv.attr("id",'ts'+i);
	arrayOftsDivs.push(newtsDiv);
}

// Create all <span>result</span>
for (var i = 0; i < numberOfResults; i++) {
	var newMatch = $(document.createElement('span'));
	newMatch.addClass('result');
	newMatch.attr("id",'r'+i);
	newMatch.html("Résultat");
	arrayOfResults.push(newMatch);
}

// Create all score divs
for (var i = 0; i < numberOfScoreDivs; i++) {
	var newScoreDiv = $(document.createElement('div'));
	newScoreDiv.addClass('score');
	newScoreDiv.attr("id",'sc'+i);
	newScoreDiv.html("<span></span>");
	arrayOfScoreDivs.push(newScoreDiv);
}

// Create all team divs
for (var i = 0; i < numberOfTeamDivs; i++) {
	var newTeamDiv = $(document.createElement('div'));
	newTeamDiv.addClass('team');
	newTeamDiv.attr("id",'t'+i);
	//newTeamDiv.html("La div n°" + i + " a pour id " + newTeamDiv.attr("id") + " et est dans la classe " + newTeamDiv.attr("class"));
	if (i > 7 && i < 16) {
		newTeamDiv.html("<input type='text' value='team "+i+"'/>");
	} else {
		newTeamDiv.html("");//"<span>team "+i+"</span>");
	}
	arrayOfTeamDivs.push(newTeamDiv);
}

jQuery(document).ready(function(){
	// Add the global div
	// $("#blocPage").append(newGlobalDiv);

	// Add the main divs to the DOM
	$("#globalDiv").append(arrayOfMainDivs[0]);
	$("#globalDiv").append(newWrappingDiv);
	$("#wrappingDiv").append(arrayOfMainDivs[1]);
	$("#wrappingDiv").append(arrayOfMainDivs[2]);

	// Add the secundary divs
	for (var i = 0; i < numberOfSecundaryDiv; i++) {
		if (i < 7) {
			$("#m0").append(arrayOfSecundaryDivs[i]);
		} else if (i < 9) {
			$("#m1").append(arrayOfSecundaryDivs[i]);
		} else {
			$("#m2").append(arrayOfSecundaryDivs[i]);
		}
	}
	
	// Add the match divs
	

	// Add the ts divs
	for (var i = 0; i < numberOftsDiv; i++) {
		if (i < 2) {
			$("#se1").append(arrayOftsDivs[i]);
		} else if (i < 6) {
			$("#se2").append(arrayOftsDivs[i]);
		} else if (i < 14) {
			$("#se3").append(arrayOftsDivs[i]);
		} else if (i < 18) {
			$("#se4").append(arrayOftsDivs[i]);
		} else if (i < 20) {
			$("#se5").append(arrayOftsDivs[i]);
		} else if (i < 22) {
			$("#se7").append(arrayOftsDivs[i]);
		} else if (i < 24) {
			$("#se9").append(arrayOftsDivs[i]);
		}
	}

	// Add the team divs
	for (var i = 0; i < numberOfTeamDivs; i++) {
		if (i < 2) {
			$("#se0").append(arrayOfTeamDivs[i]);
		} else if (i < 22) {
			$("#ts" + (i-2)).append(arrayOfTeamDivs[i]);
		} else if (i < 24) {
			$("#se6").append(arrayOfTeamDivs[i]);
		} else if (i < 26) {
			$("#ts" + (i-4)).append(arrayOfTeamDivs[i]);
		} else if (i < 28) {
			$("#se8").append(arrayOfTeamDivs[i]);
		} else if (i < 30) {
			$("#ts" + (i-6)).append(arrayOfTeamDivs[i]);
		} else {
			$("#se10").append(arrayOfTeamDivs[i]);
		}
	}

	// Add the score divs
	for (var i = 0; i < numberOfScoreDivs; i++) {
		$("#ts" + i).append(arrayOfScoreDivs[i]);
	}

	// Add the results spans
	for (var i = 0; i < numberOfResults; i++) {
		$("#ts"+(i*2)).after(arrayOfResults[i]);
	}
});