!function(o){function e(e,n){setTimeout(function(){console.log("on-image-load"),console.log(e),e&&o(n).addClass("imageLoaded")})}var t=o(document.body),n=o("div[class='overlayMask open']"),a=o(".wrapper"),l=0,i=0,d=o("img[on-image-load]");d.one("load",function(){e(!0,o(this))}).each(function(){this.complete&&e(!0,o(this))});var c=0;t.find("img[on-image-load]").each(function(){"none"!=o(this).css("display")&&c++});function g(){var e;a.addClass("show"),n.removeClass("open"),void 0!==window.loadingCompleteCallback&&window.loadingCompleteCallback(),"function"==typeof Event?e=new Event("loading.completed"):(e=document.createEvent("Event")).initEvent("loading.completed",!0,!0),document.dispatchEvent(e)}console.log("totalImgNum:"+c);var s=setInterval(function(){var e=t.find('img[class$="imageLoaded"]').length;if(console.log("okImgNum:"+e),e==c?(g(),clearInterval(s)):i!=e?i=e:l++,10<l&&(g(),clearInterval(s)),e!=c&&0!=e){var n=Math.floor(e/c*100);console.log(n)}o("#loadingText").length&&(document.getElementById("loadingText").innerHTML=String(n)),o("#loadingPic").length&&(document.getElementById("loadingPic").style.height=String(100-n)+"%")},100)}(jQuery);