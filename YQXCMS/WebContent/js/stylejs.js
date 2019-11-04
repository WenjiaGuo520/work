$(function () {

    $("tr:gt(0)").mouseover(function () {
        $(this).addClass("mt-hover")
    });

    $("tr:gt(0)").mouseout(function () {
        $(this).removeClass("mt-hover")
    });

});