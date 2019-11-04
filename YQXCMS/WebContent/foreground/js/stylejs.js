$(function () {

    $("img").addClass("width-img");

    $(".nav li a").click(function () {
        $(".nav li").removeClass("active");
        $(this).addClass("active");
    });
});