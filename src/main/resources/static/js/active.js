$(document).ready(function() {

    /*
    // Remove and set active class
    $("nav a.dropdown-item").on("click", function(){
        $(".navbar-nav").find(".active").removeClass("active");
        $(this).addClass("active");
        //console.log('OUT');
    });

     */

    // Set default active on current page
    let setDefaultActive = function (){
        let path = window.location.pathname;
        let element = $("a[href='" + path + "']");
        element.addClass("active");
        //console.log('IN');
        //console.log(element);
    }

    setDefaultActive();
});