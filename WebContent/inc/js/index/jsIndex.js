$(document).ready(function(){
	$('.owl-carousel').owlCarousel({
	    items:1,
	    loop:true,
	    nav:false,
	    autoplay:true,
		autoplayTimeout:5000,
		mouseDrag:false
	})
	
	$(".nextBtn").click(function() {
		$('.owl-carousel').trigger('next.owl.carousel');
		$('.owl-carousel').trigger('stop.owl.autoplay');
		$('.owl-carousel').trigger('play.owl.autoplay', [5000]);
	});
	
	$(".prevBtn").click(function() {
		$('.owl-carousel').trigger('prev.owl.carousel');
		$('.owl-carousel').trigger('stop.owl.autoplay');
		$('.owl-carousel').trigger('play.owl.autoplay', [5000]);
	});
});

var a = 0.85*window.innerWidth;
var b = 0.75*window.innerHeight;

new Twitch.Embed("twitch-embed", {
  width: a,
  height: b,
  channel: "frenchskylinecorporation"
});