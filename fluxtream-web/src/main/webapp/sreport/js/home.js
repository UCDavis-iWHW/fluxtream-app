console.log("this is home.js");


// Change IP address
$(document).ready(function() {
    var selfReportAPI = "/api/fluxtream_capture/getLatestObservation";
    var getAllTopics = "/api/fluxtream_capture/getAllTopics";

    $.getJSON( getAllTopics, function(data) {
        for (var entryNum in data) {
            $('.flex-container').append("<div class='flex-item' style='height: 10vh; padding: 0em;'>"+data[entryNum].name+"</div>");
        }

        /*for (var entryNum in data) {
            $('.flex-container').append("<div class='flex-item' style='height: 10vh; padding: 0em;'>"+data[entryNum].name+"</div>");
        } */
    });


    $('a#historyLink').click( function() {

        //Change header
        $("#header").text("History");

       //Show back arrow
        $('#backImage').css('visibility', 'visible');

        //Remove all items
        $(".flex-item").remove();


        //Put new items
        $.getJSON( selfReportAPI, function(data) {
            for (var entryNum in data) {
                $('.flex-container').append("<div class='flex-item' style='height: 10vh; padding: 0em;'>"+data[entryNum].topic+"</div>");
            }
        });

        return false;
    });
    $('a#settingsLink').click( function() {

        //Change header
        $("#header").text("Settings");

        //Show back arrow
        $('#backImage').css('visibility', 'visible');

        //Remove all items
        $(".flex-item").remove();


        return false;
    });

    $('a#backLink').click( function() {

        //Change header
        $("#header").text("Home");

        //Show back arrow
        $('#backImage').css('visibility', 'hidden');

        //Remove all items
        $(".flex-item").remove();

        return false;
    });


});


function showTopics(data){
    App.loadMustacheTemplate("selfReportTemplates.html","showTopics",function(template){
        var html = template.render(data);
        App.makeModal(html);
    });
}