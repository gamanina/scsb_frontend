var scrollToByClick2 = function(e, t, s) {
    t = t || $(".header").first().outerHeight(!0),
    t = $(e).offset().top - t - 30,
    s = s || 600;
    $("html, body").animate({
        scrollTop: t
    }, s)
}
  , switchToId = function(e) {
    "undefined" != typeof $ && $("#" + e).collapse("show")
}
  , switchToIdTab = function(e, t, s) {
    "undefined" != typeof $ && "undefined" != typeof ResponsiveBootstrapToolkit && (ResponsiveBootstrapToolkit.is(">=md") ? ($("#" + e).tab("show"),
    "function" == typeof s && $("#" + e).on("shown.bs.tab", function(e) {
        s()
    })) : ($("#" + t).collapse("show"),
    "function" == typeof s && $("#" + t).on("shown.bs.collapse", function(e) {
        s()
    })))
}
  , dig05a_callback_01 = function() {
    
    switchTabBySelector("#accordion_qa"),
    setTimeout(function() {
        
        $("#collapseAOne").collapse("show")
    }, 350)
}
  , dig05a_callback_01_ref = document.getElementById("dig05a_callback_01_btn");
null != dig05a_callback_01_ref && dig05a_callback_01_ref.addEventListener("click", function(e) {
    switchToIdTab("tab-C", "collapse-C", dig05a_callback_01)
});
var dig05a_callback_02 = function() {
    
    switchTabBySelector("#deal_qa"),
    setTimeout(function() {
        
        $("#collapseBSix").collapse("show")
    }, 350)
}
  , dig05a_callback_02_ref = document.getElementById("dig05a_callback_02_btn");
null != dig05a_callback_02_ref && dig05a_callback_02_ref.addEventListener("click", function(e) {
    dig05a_callback_02()
});
var filterTabBySelector = function(s) {
    
    
    var e = document.querySelectorAll("#downloadTypeListTarget > div");
    
    e.forEach(function(e, t) {
		var e1 = e.children;
		e1 = e1.length > 0 ? e1[0] : e;
        "" == s.value ? e.classList.remove("d-none") : -1 != String(e1.dataset.typeTag).split("|").indexOf(s.value) ? (e.classList.remove("d-none"),
        e.classList.add("d-block")) : (e.classList.add("d-none"),
        e.classList.remove("d-block"))
    })
};
$(".tab-content-box .collapse").on("shown.bs.collapse", function(e) {
    e.stopPropagation(),
    
    
    e = e.target.id;
    justForCollapse05 || scrollToByClick2("#" + e, $(".header").first().outerHeight(!0) + $('[href="#' + e + '"]').first().outerHeight(!0), 350)
});
var _setEqualHeight, slidebar_controller, justForCollapse05 = !1, gotoWhatIsTrack = function(e) {
    $("#collapse-05-other").one("shown.bs.collapse", function(e) {
        e.stopPropagation(),
        
        justForCollapse05 = !1,
        $("#collapse-E-05").collapse("show")
    }),
    justForCollapse05 = !0,
    $("#collapse-05-other").collapse("show")
};
function openNav() {
    document.getElementById("overlayMask").style.backgroundColor = "rgba(0,0,0,0.8)",
    document.getElementById("overlayMask").style.display = "block",
    document.body.style.overflow = "hidden"
}
function closeNav() {
    document.getElementById("overlayMask").style.backgroundColor = "rgba(0,0,0,0)",
    document.getElementById("overlayMask").style.display = "none",
    document.body.style.overflow = "auto"
}
$(".favorite").click(function() {
    $(this).toggleClass("beating")
}),
$(".favorite-mb").click(function() {
    $(this).toggleClass("beating")
}),
$(function() {
    window._setEqualHeight = function() {
        $(".cardItem .card-title").matchHeight({
            property: "min-height"
        }),
        $(".testheight .box").matchHeight({
            property: "min-height"
        })
    }
}),
$(function() {
    $(".JQellipsis").each(function(e) {
        var t;
        30 < $(this).text().length && ($(this).attr("title", $(this).text()),
        t = $(this).text().substring(0, 29) + "...",
        $(this).text(t))
    })
}),
$(".hamburger").on("click", function() {
    $(this).toggleClass("open"),
    $(this).hasClass("open") ? document.body.style.overflow = "hidden" : document.body.style.overflow = "auto"
}),
$(function() {
    var e = ResponsiveBootstrapToolkit;
    $(window).scroll(function() {
        e.is("<=md") || (70 <= (window.pageYOffset || document.documentElement.scrollTop) ? ($(".header").addClass("shrink"),
        $(".roofbar").addClass("shrink")) : ($(".header").removeClass("shrink"),
        $(".roofbar").removeClass("shrink")))
    })
}),
$(function() {
    $(".uselist_cont").matchHeight({
        property: "min-height"
    })
}),
$(function() {
    $("[data-col-height]").matchHeight({
        property: "min-height"
    })
}),
$(function() {
    void 0 !== window.slidebars && ((slidebar_controller = new slidebars).init(),
    $(".js-open-left-slidebar").on("click", function(e) {
        e.stopPropagation(),
        slidebar_controller.open("slidebar-1")
    }),
    $(".js-close-left-slidebar").on("click", function(e) {
        e.stopPropagation(),
        slidebar_controller.close("slidebar-1")
    }),
    $(".js-toggle-left-slidebar").on("click", function(e) {
        e.stopPropagation(),
        slidebar_controller.toggle("slidebar-1")
    }),
    $(slidebar_controller.events).on("opened", function() {
        $('[canvas="container"]').addClass("js-close-any-slidebar")
    }),
    $(slidebar_controller.events).on("closed", function() {
        $('[canvas="container"]').removeClass("js-close-any-slidebar")
    }),
    $("body").on("click", ".js-close-any-slidebar", function(e) {
        e.stopPropagation(),
        slidebar_controller.close()
    }))
}),
$(function() {
    var e;
    new Swiper("#kv-slide",{
        pagination: {
            el: ".swiper-pagination",
            type: "fraction"
        },
        navigation: {
            nextEl: "#data-activity-kv-next",
            prevEl: "#data-activity-kv-prev"
        }
    });
    0 < $("#servicelist-per-slide").length && ((e = new Swiper("#servicelist-per-slide",{
        loop: !1,
        pagination: {
            el: ".swiper-pagination",
            type: "fraction"
        },
        navigation: {
            nextEl: "#data-personal-next",
            prevEl: "#data-personal-prev"
        },
        breakpoints: {
            1024: {
                centeredSlides: !1,
                slidesPerView: 4,
                spaceBetween: 15,
                slidesOffsetBefore: 0,
                allowTouchMove: !1
            },
            768: {
                centeredSlides: !1,
                slidesPerView: 3.2,
                spaceBetween: 10,
                slidesOffsetBefore: 10
            },
            475: {
                centeredSlides: !1,
                slidesPerView: 2.3,
                spaceBetween: 5,
                slidesOffsetBefore: 10
            },
            320: {
                centeredSlides: !1,
                slidesPerView: 1.3,
                spaceBetween: 5,
                slidesOffsetBefore: 10,
                allowTouchMove: !0
            }
        },
        init: !1
    })).on("init", function() {
        window._setEqualHeight()
    }),
    e.init()),
    0 < $("#servicelist-com-slide").length && ((e = new Swiper("#servicelist-com-slide",{
        loop: !1,
        pagination: {
            el: ".swiper-pagination",
            type: "fraction"
        },
        navigation: {
            nextEl: "#data-personal-next",
            prevEl: "#data-personal-prev"
        },
        breakpoints: {
            1024: {
                centeredSlides: !1,
                slidesPerView: 4,
                spaceBetween: 15,
                slidesOffsetBefore: 0,
                allowTouchMove: !1
            },
            768: {
                centeredSlides: !1,
                slidesPerView: 3.2,
                spaceBetween: 10,
                slidesOffsetBefore: 10
            },
            475: {
                centeredSlides: !1,
                slidesPerView: 2.3,
                spaceBetween: 5,
                slidesOffsetBefore: 10
            },
            320: {
                centeredSlides: !1,
                slidesPerView: 1.3,
                spaceBetween: 5,
                slidesOffsetBefore: 10,
                allowTouchMove: !0
            }
        },
        init: !1
    })).on("init", function() {
        window._setEqualHeight()
    }),
    e.init()),
    0 < $("#servicelist-hotevent-slide").length && ((t = new Swiper("#servicelist-hotevent-slide",{
        loop: !1,
        pagination: {
            el: ".swiper-pagination",
            type: "fraction"
        },
        navigation: {
            nextEl: "#data-hoteven-next",
            prevEl: "#data-hoteven-prev"
        },
        breakpoints: {
            1024: {
                centeredSlides: !1,
                slidesPerView: 3,
                spaceBetween: 15,
                slidesOffsetBefore: 0,
                allowTouchMove: !1
            },
            768: {
                centeredSlides: !1,
                slidesPerView: 3.2,
                spaceBetween: 10,
                slidesOffsetBefore: 10
            },
            475: {
                centeredSlides: !1,
                slidesPerView: 2.3,
                spaceBetween: 5,
                slidesOffsetBefore: 10
            },
            320: {
                centeredSlides: !1,
                slidesPerView: 1.3,
                spaceBetween: 5,
                slidesOffsetBefore: 10,
                allowTouchMove: !0
            }
        },
        init: !1
    })).on("init", function() {
        window._setEqualHeight()
    }),
    t.init());
    var t = new Swiper("#event-slide",{
        loop: !1,
        pagination: {
            el: ".swiper-pagination",
            type: "fraction"
        },
        navigation: {
            nextEl: "#data-event-next",
            prevEl: "#data-event-prev"
        },
        breakpoints: {
            1024: {
                centeredSlides: !1,
                slidesPerView: 1,
                spaceBetween: 10,
                allowTouchMove: !1,
                slidesOffsetBefore: 0
            },
            768: {
                centeredSlides: !1,
                slidesPerView: 3.2,
                spaceBetween: 10,
                slidesOffsetBefore: 10,
                allowTouchMove: !0
            },
            475: {
                centeredSlides: !1,
                slidesPerView: 2.3,
                spaceBetween: 5,
                slidesOffsetBefore: 10,
                allowTouchMove: !0
            },
            320: {
                centeredSlides: !1,
                slidesPerView: 1.3,
                spaceBetween: 5,
                slidesOffsetBefore: 10,
                allowTouchMove: !0
            }
        }
    });
    new Swiper(".scrollbox",{
        direction: "vertical",
        slidesPerView: "auto",
        freeMode: !0,
        scrollbar: {
            el: ".swiper-scrollbar"
        },
        mousewheel: !0
    })
}),
$(function() {});
var switchNav = function(e) {
    if (ResponsiveBootstrapToolkit.is(">md"))
        return !1;
    var t = document.getElementById("mmenuButton");
    (t.classList.contains("collapsed") ? openNav : closeNav)(),
    "mmenuButton" != e.target.id && t.dispatchEvent(new CustomEvent("click",{
        view: window,
        bubbles: !0,
        cancelable: !0
    }))
}
  , removeUselessActiveClass = {}
  , initSelector = {};
$(function() {
    (initSelector = function() {
        var e = document.getElementById("generalQaList");
        null != e && (e.options[0].selected = !0)
    }
    )(),
    switchTabBySelector = function(e) {
        var t = document.getElementById("generalQaList");
        if (null != t) {
            var s, o, i = document.getElementById("generalQaTarget"), l = t.querySelectorAll("option"), n = "string" == typeof e ? e : e.value;
            for (s in 
            
            
            l)
                l[s].value == n && (l[s].selected = !0,
                l[s].setAttribute("selected", !0));
            for (o in i.children)
                void 0 !== i.children[o].classList && (
                i.children[o].classList.add("d-none"));
            e = i.querySelector(n);
            
            e.classList.remove("d-none")
        }
    }
});
var initIndexer, setMenuActive, menuIdxData = {};
$(function() {
    (initIndexer = function() {
        var e, t, s, o = document.querySelector("[data-menu-indexer]");
        
        null != o && (e = o.getAttribute("data-ly-a"),
        t = o.getAttribute("data-ly-b"),
        s = o.getAttribute("data-ly-c"),
        o = o.getAttribute("data-ly-d"),
        menuIdxData = {
            a: e,
            b: t,
            c: s,
            d: o
        })
    }
    )(),
    (setMenuActive = function() {
        if (null != document.querySelector(".leftCol_menu")) {
            var e, t = document.querySelector(".leftCol_menu").querySelectorAll('[data-toggle="collapse"]');
            for (e in 
            t)
                "A" == t[e].nodeName && t[e].setAttribute("aria-expanded", "false");
            var s = document.querySelector('[data-ly-a="' + menuIdxData.a + '"]');
            if (
            null != s) {
                var o = s.querySelector('[data-ly-b="' + menuIdxData.b + '"]');
                
                var i = s.querySelector('[data-ly-c="' + menuIdxData.c + '"]');
                
                s = s.querySelector('[data-ly-d="' + menuIdxData.d + '"]');
                if (
                null != o) {
                    if (o.querySelector('[data-toggle="collapse"]').setAttribute("aria-expanded", "true"),
                    null == o.querySelector(".collapse"))
                        return;
                    o.querySelector(".collapse").classList.add("show")
                }
                null != i && (i.setAttribute("aria-expanded", "true"),
                
                null != i.parentElement.querySelector(".collapse") && i.parentElement.querySelector(".collapse").classList.add("show")),
                null != s && (s.setAttribute("aria-expanded", "true"))
            }
        }
    }
    )()
});
var shareThisPage = function(e) {
    var t, s = window.location.href;
    "line" == e ? t = "https://social-plugins.line.me/lineit/share?url=" : "fb" == e && (t = "https://www.facebook.com/sharer/sharer.php?u="),
    window.open(t + encodeURIComponent(s))
};
function scrollToByClick(e) {
    e = $(e).offset().top - 70;
    $("html, body").animate({
        scrollTop: e
    }, 600)
}
function SideMenu() {
    this._template = ['<div class="d-flex flex-row flex-xl-column sideWrap">', '    <a href="https://www.coolpapa.com.tw/scsb_official_website/eservice/ecounter.html" class="btnLink flex-fill" target="_blank" title="開新視窗-線上櫃檯">', '        <div class="iconBlk-txt icon001"></div>', "        線上櫃檯", "    </a>", '    <a href="https://smartrobot.scsb.com.tw/index.php/chat/chat/556041/5be04247aaf114ea5b556bff03029e4a9e91d8f9?ICSPID=&lat=&lng=&qaCategory=&eservice=mainweb" class="btnLink flex-fill" target="_blank" title="開新視窗-智能客服">', '        <div class="iconBlk-txt icon002"></div>', "        智能客服", "    </a>", '    <a href="javascript:;" class="btnLink flex-fill" id="addPageToFavorite">', '        <div class="iconBlk-txt icon003"></div>', "        收藏清單", "    </a>", '    <a href="https://www.scsb.com.tw/newscsbweb/content/about/about04_b_01.jsp" class="btnLink flex-fill" target="_blank" title="開新視窗-法定揭露">', '        <div class="iconBlk-txt icon004"></div>', "        法定揭露", "    </a>", "</div>"],
    this.init = function() {
        var e = document.createElement("div");
        e.classList.add("r-sideMenu"),
        e.innerHTML = this._template.join(""),
        document.querySelector("body").prepend(e)
    }
}
$(function() {
    wow = new WOW({
        boxClass: "wow",
        animateClass: "animated",
        offset: 150,
        mobile: !0,
        live: !0
    }),
    wow.init(),
    $("#demo").on("shown.bs.collapse", function(e) {
        $('button[data-target="#demo"]').html('收合<span class="icon-minus ml-2"></span>'),
        scrollToByClick("#demo")
    }),
    $("#demo").on("hidden.bs.collapse", function(e) {
        $('button[data-target="#demo"]').html('展開<span class="icon-plus ml-2"></span>'),
        scrollToByClick("#st02")
    }),
    0 < $("#pagenav").length && $("#pagenav").rPage()
}),
function(e, t) {
    t.breakpoints = {
        xs: e('<div class="d-xs-block d-sm-none d-md-none d-lg-none d-xl-none"></div>'),
        sm: e('<div class="d-none d-sm-block d-md-none d-lg-none d-xl-none"></div>'),
        md: e('<div class="d-none d-md-block d-sm-none d-lg-none d-xl-none"></div>'),
        lg: e('<div class="d-none d-lg-block d-sm-none d-md-none d-xl-none"></div>'),
        xl: e('<div class="d-none d-xl-block d-sm-none d-md-none d-lg-none"></div>')
    },
    e(document).ready(function() {
        
        e(window).resize(t.changed(function() {
            t.is(">=md") && slidebar_controller.isActive() && (slidebar_controller.close(),
            e(".hamburger").removeClass("open"))
        }))
    })
}(jQuery, ResponsiveBootstrapToolkit);
var sideMenu = new SideMenu;
function FavoriteList() {
    this._favorSwitch = null,
    this._rootTarget = null,
    this._listTarget = null,
    this._list = [],
    this.maxLength = 5,
    this._template = ['<a href="javascript:;" class="closeBtn icon-close-b"></a>', '<div class="container searchFormWrap">', '    <div class="h1 uniTitle">收藏清單</div>', '    <ul class="favorite_list"></ul>', "</div>"];
    var s = this;
    this.init = function() {
        
        var t, e = document.createElement("div");
        e.classList.add("searchOverlay"),
        e.id = "favoriteOverlay",
        e.style.display = "none",
        e.innerHTML = this._template.join(""),
        document.querySelector("body").insertBefore(e, document.querySelector(".overlayMask")),
        this._favorSwitch = document.querySelector("#addPageToFavorite"),
        this._rootTarget = document.querySelector("#favoriteOverlay"),
        this._listTarget = this._rootTarget.querySelector(".favorite_list"),
        null != localStorage.getItem("favorlist") && (this._list = JSON.parse(localStorage.getItem("favorlist"))),
        null != document.querySelector(".shareNav .item .favorite") && ((t = document.querySelector(".shareNav .item .favorite")).addEventListener("click", function(e) {
            
            t.classList.contains("beating") ? s.add() || t.classList.remove("beating") : s.remove()
        }, !1),
        this.updateBtnStatus()),
        null != this._favorSwitch && this._favorSwitch.addEventListener("click", function(e) {
            "none" == s._rootTarget.style.display ? s.open() : s.close()
        }),
        this._rootTarget.querySelector(".closeBtn").addEventListener("click", function() {
            favoriteList.close()
        }),
        this.render()
    }
    ,
    this.updateBtnStatus = function() {
        var e;
        null != document.querySelector(".shareNav .item .favorite") && (e = document.querySelector(".shareNav .item .favorite"),
        this.isCurrentPageIn() ? e.classList.add("beating") : e.classList.remove("beating"))
    }
    ,
    this.add = function() {
        
        var e = document.querySelector(".inner-kv-slogan .title")
          , t = ""
          , t = null == e ? document.title : e.innerHTML;
        return this._list.length < this.maxLength ? this.checkDup(location.pathname) ? (
        !1) : (this._list.push([location.pathname, t]),
        localStorage.setItem("favorlist", JSON.stringify(this._list)),
        this.render(),
        !0) : (
        alert("收藏清單的項目數量上限為 " + this.maxLength + " 筆，請先刪減項目後再試！"),
        !1)
    }
    ,
    this.remove = function() {
        this._list.forEach(function(e, t) {
            e[0] == location.pathname && s.removeByIdx(t)
        })
    }
    ,
    this.removeByIdx = function(e) {
        
        e <= this._list.length - 1 ? (this._list.splice(e, 1),
        
        localStorage.setItem("favorlist", JSON.stringify(this._list))) : 
        this.render(),
        this.updateBtnStatus()
    }
    ,
    this.checkDup = function(s) {
        var o = !1;
        return this._list.forEach(function(e, t) {
            e[0] == s && (o = !0)
        }),
        o
    }
    ,
    this.isCurrentPageIn = function() {
        var s = !1;
        return this._list.forEach(function(e, t) {
            e[0] == location.pathname && (s = !0)
        }),
        s
    }
    ,
    this.render = function() {
        var s = "";
        this._list.forEach(function(e, t) {
            s += '<li class="item d-flex">',
            s += '<a href="' + e[0] + '" title="' + e[1] + '" class="flex-grow-1">' + e[1] + "</a>",
            s += '<a href="javascript:void(0);" class="trash" onclick="favoriteList.removeByIdx(\'' + t + "')\"></a>",
            s += "</li>"
        }),
        
        this._listTarget.innerHTML = s
    }
    ,
    this.close = function() {
        this._rootTarget.style.display = "none"
    }
    ,
    this.open = function() {
        this._rootTarget.style.display = "block",
        this.render()
    }
}
sideMenu.init();
var favoriteList = new FavoriteList;
function SearchTextInput() {
    this._inputSwitch = null,
    this._submitInputSwitch = null,
    this._rootTarget = null,
    this._inputTarget = null;
    var t = this;
    this._template = ['<a href="javascript:;" class="closeBtn icon-close-b"></a>', '<div class="container searchFormWrap">', '    <div class="h1 uniTitle">我要搜尋</div>', '    <div class="form_wrap">', '        <div class="form-group form-row">', '            <div class="col-12 col-md-8 offset-md-2">', '                <div class="input-group">', '                    <input type="text" id="searchTextInputField" class="form-control border-right-0" placeholder="請輸入關鍵字" name="" id="" value="">', '                    <div class="input-group-append">', '                        <a href="javascript:void(0);" class="input-group-text"><span class="searchBtn icon-sh" id="submitSearchBtn"></span></a>', "                    </div>", "                </div>", "            </div>", "        </div>", "    </div>", "</div>"],
    this.init = function() {
        var e = document.createElement("div");
        e.classList.add("searchOverlay"),
        e.id = "searchTextInputOverlay",
        e.style.display = "none",
        e.innerHTML = this._template.join(""),
        document.querySelector("body").insertBefore(e, document.querySelector(".overlayMask")),
        this._inputSwitch = document.querySelector(".roofbar-nav .searchBtn"),
        this._rootTarget = document.querySelector("#searchTextInputOverlay"),
        this._inputTarget = this._rootTarget.querySelector("#searchTextInputField"),
        this._submitInputSwitch = document.querySelector("#submitSearchBtn"),
        document.querySelector(".header").addEventListener("click", function(e) {
            
            e.target.classList.contains("searchBtn") && (t._submitInputSwitch.addEventListener("click", function() {
                t.doSearch()
            }),
            "none" == t._rootTarget.style.display ? t.open() : t.close())
        }),
        this._inputTarget.addEventListener("keyup", function(e) {
            13 == e.keyCode && t.doSearch()
        }),
        this._rootTarget.querySelector(".closeBtn").addEventListener("click", function() {
            t.close()
        })
    }
    ,
    this.doSearch = function() {
        window.location.href = "../search/search_result.jsp?q=" + this._inputTarget.value
    }
    ,
    this.close = function() {
        this._rootTarget.style.display = "none"
    }
    ,
    this.open = function() {
        this._rootTarget.style.display = "block"
    }
}
favoriteList.init();
var searchTextInput = new SearchTextInput;
function SearchResultPage() {
    this._inputSwitch = null,
    this._submitInputSwitch = null,
    this._rootTarget = null,
    this._inputTarget = null,
    this._resultCount = null,
    this._resultTarget = null,
    this._getMoreResult = null,
    this._apiSearchUrl = "www.scsb.com.tw",
    this._googleSearchEngineId = "002359105016726879906:j9ybokl8zxo",
    this._googleSearchApiKey = "AIzaSyDQFgjzmbStQIcoC8W3uKSp1AsARq7bYBc",
    this._googleSearchApiUrl = "https://www.googleapis.com/customsearch/v1",
    this._urlQueryPair = [],
    this._searchResultJsonData = null,
    this._searchResultJsonItems = [],
    this._searchResultTotalCount = 0,
    this._searchResultStartIdx = 0,
    this._searchResultPagePerNum = 10,
    this._searchResultCurrentPage = 0,
    this._searchString = "";
    var s = this;
    this.init = function() {
        this._inputSwitch = document.querySelector(".roofbar-nav .searchBtn"),
        this._rootTarget = document.querySelector("#searchResultListBlk"),
        this._inputTarget = this._rootTarget.querySelector("#searchResultInputField"),
        this._submitInputSwitch = document.querySelector("#searchResultInputBlk .searchBtn"),
        this._resultCount = document.querySelector("#searchResultCount"),
        this._resultTarget = document.querySelector("#searchResultLister"),
        this._getMoreResult = document.querySelector("#getMoreResult"),
        this._searchResultJsonItems = [],
        this._submitInputSwitch.addEventListener("click", function() {
            s.doingSearch()
        }),
        this._inputTarget.addEventListener("keyup", function(e) {
            13 == e.keyCode && s.doingSearch()
        }),
        this._getMoreResult.addEventListener("click", function() {
            s.doNextPageApiSearch()
        }),
        this.parseUrlQueryString()
    }
    ,
    this.doingSearch = function() {
        s._searchResultJsonData = null,
        s._searchResultJsonItems = [],
        s._searchResultTotalCount = 0,
        s._searchResultStartIdx = 0,
        s._searchResultPagePerNum = 10,
        s._searchResultCurrentPage = 0,
        s._resultCount.innerHTML = 0,
        s._resultTarget.innerHTML = "",
        s._searchString = s._inputTarget.value,
        s.doApiSearch(s._apiSearchUrl, s._searchString)
    }
    ,
    this.parseUrlQueryString = function() {
        "" != location.search && (location.search.replace("?", "").split("&").forEach(function(e, t) {
            e = e.split("=");
            s._urlQueryPair.push([e[0], e[1]])
        }),
        this._inputTarget.value = decodeURIComponent(this._urlQueryPair[0][1]),
        this._searchString = decodeURIComponent(this._urlQueryPair[0][1]),
        this.doApiSearch(this._apiSearchUrl, this._searchString))
    }
    ,
    this.doNextPageApiSearch = function() {
        this._searchResultTotalCount > this._searchResultPagePerNum * (this._searchResultCurrentPage + 1) && (this._searchResultCurrentPage += 1,
        this._searchResultStartIdx = this._searchResultPagePerNum * this._searchResultCurrentPage,
        this.doApiSearch(this._apiSearchUrl, this._searchString))
    }
    ,
    this.doApiSearch = function(e, t) {
        
        
        $.get(this._googleSearchApiUrl, {
            cx: this._googleSearchEngineId,
            key: this._googleSearchApiKey,
            q: decodeURIComponent(t),
            siteSearch: this._apiSearchUrl,
            num: this._searchResultPagePerNum,
            start: this._searchResultStartIdx
        }, function(e) {
            
            s._searchResultJsonData = e,
            0 == s._searchResultTotalCount && (s._searchResultTotalCount = e.searchInformation.totalResults),
            
            
            e.items.length < s._searchResultPagePerNum ? s._getMoreResult.style.display = "none" : s._getMoreResult.style.display = "inline",
            s._searchResultJsonItems = s._searchResultJsonItems.concat(e.items),
            s.parseApiResult()
        }, "json")
    }
    ,
    this.parseApiResult = function() {
        var e, s;
        null != this._searchResultJsonData && (e = this._searchResultJsonData,
        s = [],
        this._resultCount.innerHTML = this._searchResultTotalCount,
        e.items.forEach(function(e, t) {
            s.push(['<li class="list-group-item">', '    <a href="' + e.formattedUrl + '" class="urlTitle">' + e.htmlTitle + "</a>", '    <div class="urlink">' + e.formattedUrl + "</div>", '    <div class="urlCont">', '        <div class="item"><span class="urlInt">' + e.htmlSnippet + "</span></div>", "    </div>", "</li>"].join(""))
        }),
        
        this._resultTarget.innerHTML = this._resultTarget.innerHTML + s.join(""))
    }
}
searchTextInput.init();
var searchResultPage = new SearchResultPage;
searchResultPage.init();
