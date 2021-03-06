$(document).ready(function () {

    $("#btnSubmit").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        uploadData();

    });
    
    $("#btnCvByNameAndSurname").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        searchCvByNameAndSurname();

    });
        
    $("#btnCvByEducation").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        searchCvByEducation();

    });
    
    $("#btnCvByTerms").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        searchCvByTerms();

    });
    
    $("#btnCvByTermsHighlight").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        searchCvByTermsHighlight();

    });
    
    $("#btnComplexQuery").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        searchComplexQuery();

    });

    $("#btnGeoSearch").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        searchGeoSearch();

    });
    
    $("#btnPhrase").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        searchPhraseSearch();

    });
    
    $("#btnPhraseHighlight").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        searchPhraseHighlightSearch();

    });
    
    $("#btnCityStat").click(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        searchCityStatSearch();

    });
});

function uploadData() {

    // Get form
    var form = $('#fileUploadForm')[0];

    var data = new FormData(form);

    $("#btnSubmit").prop("disabled", true);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: "/index/add",
        data: data,
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        cache: false,
        timeout: 600000,
        success: function (data) {
        	$('#result').empty();
            $("#result").text(data);
            console.log("SUCCESS : ", data);
            $("#btnSubmit").prop("disabled", false);

        },
        error: function (e) {
        	$('#result').empty();
            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmit").prop("disabled", false);

        }
    });

}


function searchCvByNameAndSurname() {
	var name = $('#cvByNameAndSurname input[name=name]').val();
	var surname = $('#cvByNameAndSurname input[name=surname]').val();
    var data = JSON.stringify({"name":name, "surname":surname});
    $("#btnCvByNameAndSurname").prop("disabled", true);

    $.ajax({
        type: "POST",
        url: "/search/cv/name",
        data: data,
        contentType: 'application/json',
        success: function (data) {
        	$('#result').empty();
            for(index = 0; index < data.length; index++){
                var result = data[index]
                $('#result').append('<li>' + result + '</li>');
            }
            console.log("SUCCESS : ", data);
            $("#btnCvByNameAndSurname").prop("disabled", false);

        },
        error: function (e) {
        	$('#result').empty();
            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnCvByNameAndSurname").prop("disabled", false);

        }
    });
}

function searchCvByEducation() {
	var educationlevel = $('#cvByEducation input[name=educationlevel]').val();
	var educationgrade = $('#cvByEducation input[name=educationgrade]').val();
    var data = JSON.stringify({"educationlevel":educationlevel, "educationgrade":educationgrade});
    $("#btnCvByEducation").prop("disabled", true);

    $.ajax({
        type: "POST",
        url: "/search/cv/education",
        data: data,
        contentType: 'application/json',
        success: function (data) {
        	$('#result').empty();
            for(index = 0; index < data.length; index++){
                var result = data[index]
                $('#result').append('<li>' + result + '</li>');
            }
            console.log("SUCCESS : ", data);
            $("#btnCvByEducation").prop("disabled", false);

        },
        error: function (e) {
        	$('#result').empty();
            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnCvByEducation").prop("disabled", false);

        }
    });
}

function searchCvByTerms() {
	var data = $('#cvByTerms input[name=terms]').val();
    $("#btnCvByTerms").prop("disabled", true);

    $.ajax({
        type: "POST",
        url: "/search/cv/terms",
        data: data,
        contentType: 'application/json',
        success: function (data) {
        	$('#result').empty();
            for(index = 0; index < data.length; index++){
                var result = data[index]
                $('#result').append('<li>' + result + '</li>');
            }
            console.log("SUCCESS : ", data);
            $("#btnCvByTerms").prop("disabled", false);

        },
        error: function (e) {
        	$('#result').empty();
            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnCvByTerms").prop("disabled", false);

        }
    });
}

function searchComplexQuery() {
	var data = $('#complexQuery input[name=query]').val();
    $("#btnComplexQuery").prop("disabled", true);

    $.ajax({
        type: "POST",
        url: "/search/bool",
        data: data,
        contentType: 'application/json',
        success: function (data) {
        	$('#result').empty();
            for(index = 0; index < data.length; index++){
                var result = data[index]
                $('#result').append('<li>' + result + '</li>');
            }
            console.log("SUCCESS : ", data);
            $("#btnComplexQuery").prop("disabled", false);

        },
        error: function (e) {
        	$('#result').empty();
            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnComplexQuery").prop("disabled", false);

        }
    });
}

function searchGeoSearch() {
	var city = $('#geoSearch input[name=city]').val();
	var radius = $('#geoSearch input[name=radius]').val();
    var data = JSON.stringify({"city":city, "radius":radius});
    $("#btnGeoSearch").prop("disabled", true);

    $.ajax({
        type: "POST",
        url: "/search/nearby",
        data: data,
        contentType: 'application/json',
        success: function (data) {
        	$('#result').empty();
            for(index = 0; index < data.length; index++){
                var result = data[index]
                $('#result').append('<li>' + result + '</li>');
            }
            console.log("SUCCESS : ", data);
            $("#btnGeoSearch").prop("disabled", false);

        },
        error: function (e) {
        	$('#result').empty();
            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnGeoSearch").prop("disabled", false);

        }
    });
}

function searchPhraseSearch() {
	var field = $('#phrase input[name=field]').val();
	var value = $('#phrase input[name=value]').val();
    var data = JSON.stringify({"field":field, "value":value});
    $("#btnPhrase").prop("disabled", true);

    $.ajax({
        type: "POST",
        url: "/search/phrase",
        data: data,
        contentType: 'application/json',
        success: function (data) {
        	$('#result').empty();
            for(index = 0; index < data.length; index++){
                var result = data[index]
                $('#result').append('<li>' + result + '</li>');
            }
            console.log("SUCCESS : ", data);
            $("#btnPhrase").prop("disabled", false);

        },
        error: function (e) {
        	$('#result').empty();
            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnPhrase").prop("disabled", false);

        }
    });
}

function searchCvByTermsHighlight() {
	var data = $('#cvByTermsHighlight input[name=terms]').val();
    $("#btnCvByTermsHighlight").prop("disabled", true);

    $.ajax({
        type: "POST",
        url: "/search/cv/terms/highlight",
        data: data,
        contentType: 'application/json',
        success: function (data) {
        	$('#result').empty();
            for(index = 0; index < data.length; index++){
                var result = data[index]
                $('#result').append('<li>' + result + '</li>');
            }
            console.log("SUCCESS : ", data);
            $("#btnCvByTermsHighlight").prop("disabled", false);

        },
        error: function (e) {
        	$('#result').empty();
            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnCvByTermsHighlight").prop("disabled", false);

        }
    });
}

function searchPhraseHighlightSearch() {
	var field = $('#phraseHighlight input[name=field]').val();
	var value = $('#phraseHighlight input[name=value]').val();
    var data = JSON.stringify({"field":field, "value":value});
    $("#btnPhraseHighlight").prop("disabled", true);

    $.ajax({
        type: "POST",
        url: "/search/phrase/highlight",
        data: data,
        contentType: 'application/json',
        success: function (data) {
        	$('#result').empty();
            for(index = 0; index < data.length; index++){
                var result = data[index]
                $('#result').append('<li>' + result + '</li>');
            }
            console.log("SUCCESS : ", data);
            $("#btnPhraseHighlight").prop("disabled", false);

        },
        error: function (e) {
        	$('#result').empty();
            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnPhraseHighlight").prop("disabled", false);

        }
    });
}

function searchCityStatSearch() {
    $("#btnCityStat").prop("disabled", true);

    $.ajax({
        type: "POST",
        url: "/stat/city",
        data: "",
        contentType: 'application/json',
        success: function (data) {
        	$('#result').empty();
            for(index = 0; index < data.length; index++){
                var result = data[index]
                $('#result').append('<li>' + result + '</li>');
            }
            console.log("SUCCESS : ", data);
            $("#btnCityStat").prop("disabled", false);

        },
        error: function (e) {
        	$('#result').empty();
            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnCityStat").prop("disabled", false);

        }
    });
}