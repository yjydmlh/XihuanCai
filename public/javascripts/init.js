// Bind init events.
$(function(){ 
    $(".cover").css({"opacity":0,top:"300px",height:"10px"});
    $(".person-img").hover(function(){
    	$(this).find(".cover")
    			.stop()
    			.animate({top:"10px",
						 "width":$(this).css("width"),
						 "height":$(this).css("height"),
						 "opacity":.8
						 },{queue:false,duration:160});
    },function(){
    	$(this).find(".cover")
    			.stop()
    			.animate({top:"10px",
						 "width":$(this).css("width"),
						 "height":$(this).css("height"),
						 "opacity":0
						 },{queue:false,duration:160});
    });
    //TODO need to be changed.
    $(".not-like-it").click(function(){
    	$(this).attr("class","like-it");
    });
});

// Google analytics start
var _gaq = _gaq || [];
_gaq.push(['_setAccount', 'UA-29153903-1']);
_gaq.push(['_trackPageview']);

(function() {
 var ga = document.createElement('script'); 
 ga.type = 'text/javascript'; ga.async = true;
 ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
 var s = document.getElementsByTagName('script')[0]; 
 s.parentNode.insertBefore(ga, s);})();
// Google analytics end