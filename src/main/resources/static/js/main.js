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

function searchLuceneQueryLanguage() {

    var value = $('#luceneQueryLanguage input[name=value]').val();
    var data = JSON.stringify({"value":value});
    $("#btnSubmitLuceneQueryLanguage").prop("disabled", true);

    $.ajax({
        type: "POST",
        url: "/search/queryParser",
        data: data,
        contentType: 'application/json',
        success: function (data) {
        	$('#result').empty();
            for(index = 0; index < data.length; index++){
                var result = data[index]
                $.each(result, function(key, value) {
                  $('#result').append('<li>' + key + ': ' + value + '</li>');
                });
            }
            console.log("SUCCESS : ", data);
            $("#btnSubmitLuceneQueryLanguage").prop("disabled", false);

        },
        error: function (e) {
        	$('#result').empty();
            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmitLuceneQueryLanguage").prop("disabled", false);

        }
    });

}

function searchLuceneTermQuery() {

    var field = $('#luceneTermQuery input[name=field]').val();
    var value = $('#luceneTermQuery input[name=value]').val();
    var data = JSON.stringify({"field":field, "value":value});
   
    $("#btnSubmitLuceneTermQuery").prop("disabled", true);

    $.ajax({
        type: "POST",
        url: "/search/term",
        data: data,
        contentType: 'application/json',
        success: function (data) {

        	$('#result').empty();
            for(index = 0; index < data.length; index++){
                var result = data[index]
                $.each(result, function(key, value) {
                  $('#result').append('<li>' + key + ': ' + value + '</li>');
                });
            }
            console.log("SUCCESS : ", data);
            $("#btnSubmitLuceneTermQuery").prop("disabled", false);

        },
        error: function (e) {
        	$('#result').empty();
            $("#result").text(e.responseText);
            console.log("ERROR : ", e);
            $("#btnSubmitLuceneTermQuery").prop("disabled", false);

        }
    });

}