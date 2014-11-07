(function(){
  $(".mobile-header-menu").click(function() {
    $(".mobile-link-list").slideToggle()

    var arrow = $(".menu-arrow");
    if (arrow.text() == "▼") arrow.text("▲");
    else arrow.text("▼");
  });
})()