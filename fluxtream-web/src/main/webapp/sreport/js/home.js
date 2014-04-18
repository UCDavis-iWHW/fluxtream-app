console.log("this is home.js");

var topicsList;

// Change IP address
$(document).ready(function() {
    var selfReportAPI = "/api/fluxtream_capture/getLatestObservation";
    var getAllTopics = "/api/fluxtream_capture/getAllTopics";

    $.getJSON( getAllTopics, function(data) {
        topicsList = data;
        for (var entryNum in data) {
            $('.flex-container').append("<a href='#' id='"+entryNum+"' onClick='reply_click(this.id)'><div class='flex-item' style='height: 10vh; padding: 0em;'>"+data[entryNum].name+"</div></a>");
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

        //Hide back arrow
        $('#backImage').css('visibility', 'hidden');

        //Show history button
        $('#historyImage').css('visibility', 'visible');

        //Show settings button
        $('#settingsImage').css('visibility', 'visible');

        //Show logout button
        $('#logoutImage').css('visibility', 'visible');

        //Remove all items
        $(".flex-item").remove();

        for (var entryNum in topicsList) {
            $('.flex-container').append("<a href='#' id='"+entryNum+"' onClick='reply_click(this.id)'><div class='flex-item' style='height: 10vh; padding: 0em;'>"+topicsList[entryNum].name+"</div></a>");
        }

        return false;
    });
});

function reply_click(item_id)
{
    $("#header").text(topicsList[item_id].name);

    //Show back arrow
    $('#backImage').css('visibility', 'visible');

    //Hide history button
    $('#historyImage').css('visibility', 'hidden');

    //Hide settings button
    $('#settingsImage').css('visibility', 'hidden');

    //Hide logout button
    $('#logoutImage').css('visibility', 'hidden');

    //Remove all items
    $(".flex-item").remove();

    //enterObservation();

    $('.flex-container').append("" +
        "<div class='flex-item' style='height: 10vh; padding: 0em;'>" +
        "<div style='background: white; width: 50%; height: 20vh; float:left; line-height:20vh; margin: 0em; text-align: left;'>Value</div>" +
        "<div style='background: white; width: 50%; height: 20vh; float:left; line-height:20vh; margin: 0em; text-align: left;'>" +
            "<select>" +
                "<option value='1'>1</option>"  +
                "<option value='2'>2</option>"  +
                "<option value='3'>3</option>" +
                "<option value='4'>4</option>" +
             "</select>" +
        "</div>" +
        "</div>");

    $('.flex-container').append("" +
        "<div class='flex-item' style='height: 10vh; padding: 0em;'>" +
        "<div style='background: white; width: 50%; height: 20vh; float:left; line-height:20vh; margin: 0em; text-align: left;'>Value</div>" +
        "<div style='background: white; width: 50%; height: 20vh; float:left; line-height:20vh; margin: 0em; text-align: left;'>" +
            "<select>" +
                "<option value='1'>1</option>"  +
                "<option value='2'>2</option>"  +
                "<option value='3'>3</option>" +
                "<option value='4'>4</option>" +
            "</select>" +
        "</div>" +
    "</div>");

    return false;
}

function showTopics(data){
    App.loadMustacheTemplate("selfReportTemplates.html","showTopics",function(template){
        var html = template.render(data);
        App.makeModal(html);
    });
}

function enterObservation(){
    App.loadMustacheTemplate("selfReportTemplates.html","enterObservation",function(template){
        var html = template.render();
        App.makeModal(html);
        bindDialog();
    });
}

function bindDialog(){
    $("#modal").on("hide",function(){
        App.activeApp.renderState(App.state.getState(App.activeApp.name),true);//force refresh of the current app state
    });
}