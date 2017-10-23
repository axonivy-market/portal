// FNC for detecting for click outside of any elements ============== 
$.fn.clickOff = function(callback, selfDestroy) {
		var clicked = false;
		var parent = this;
		var destroy = selfDestroy || true;
		
		parent.click(function() {
			clicked = true;
		});
		
		$(document).click(function(event) { 
			if (!clicked) {
				callback(parent, event);
			}
			if (destroy) {
			};
			clicked = false;
		});
	};
	
/** 
 * PrimeFaces Modena Layout
 */
var Modena = {
    
    init: function() {
        this.menuWrapper = $('#layout-menu-cover');
        this.menu = this.menuWrapper.find('ul.modena-menu');
        this.menulinks = this.menu.find('a.menulink');
        this.topMenu = $('#top-menu');
        this.topMenuButton = $('#show-top-menu');
        this.mobileMenuButton = $('#mobile-menu-button');
        this.expandedMenuitems = this.expandedMenuitems||[];
        this.mobile = this.isMobile();
        
        //remove transform on Firefox Mobile 
        if(this.mobile && $.browser.mozilla) {
            this.mobileMenuButton.addClass('no-transform');
            this.menu.addClass('no-transform');
        }
        
        this.bindEvents();
    },
    
    bindEvents: function() {
        var $this = this;
        
        if(this.mobile) {
            this.menuWrapper.css('overflow-y', 'auto');
        }
        else {
            this.menuWrapper.perfectScrollbar({suppressScrollX: true});
        }
	
        this.menulinks.on('click',function(e) {
            var menuitemLink = $(this),
            menuitem = menuitemLink.parent();

            if(menuitem.hasClass('active-menu-parent')) {
                menuitem.removeClass('active-menu-parent');
                menuitemLink.removeClass('active-menu active-menu-restore').next('ul').removeClass('active-menu active-menu-restore');
                $this.removeMenuitem(menuitem.attr('id'));
            }
            else {
                var activeSibling = menuitem.siblings('.active-menu-parent');
                if(activeSibling.length) {
                    activeSibling.removeClass('active-menu-parent');
                    $this.removeMenuitem(activeSibling.attr('id'));

                    activeSibling.find('ul.active-menu,a.active-menu').removeClass('active-menu active-menu-restore');
                    activeSibling.find('li.active-menu-parent').each(function() {
                        var menuitem = $(this);
                        menuitem.removeClass('active-menu-parent');
                        $this.removeMenuitem(menuitem.attr('id'));
                    });
                }

                menuitem.addClass('active-menu-parent');
                menuitemLink.addClass('active-menu').next('ul').addClass('active-menu');
                $this.addMenuitem(menuitem.attr('id'));
            }

            if(menuitemLink.next().is('ul')) {
                e.preventDefault();
            }
            else {
                $this.menuWrapper.removeClass('showmenu');
                $this.mobileMenuButton.removeClass('MenuClose');
            }

            $this.saveMenuState();
            
            if($this.mobile) {
                $this.menuWrapper.perfectScrollbar("update");
            }
        });
        
        this.mobileMenuButton.on('click', function() {
            if(parseInt($this.menuWrapper.css('marginLeft')) < 0) {
                $(this).addClass('MenuClose');
                $this.menuWrapper.addClass('showmenu');
                $this.topMenu.removeClass('showmenu');
                $this.topMenuButton.removeClass('showmenu');
            }
            else {
                $(this).removeClass('MenuClose');
                $this.menuWrapper.removeClass('showmenu');
            }
        });

        this.topMenuButton.on('click',function(){
            if($this.topMenu.is(':hidden')) {
                $(this).addClass('MenuClose');
                $this.topMenu.addClass('showmenu');
                $this.mobileMenuButton.removeClass('MenuClose');
                $this.menuWrapper.removeClass('showmenu');
            }
            else {
                $(this).removeClass('MenuClose');
                $this.topMenu.removeClass('showmenu');
            }
        });
        
        //topbar
        this.topMenu.find('a').click(function(e) {
            var link = $(this),
            submenu = link.next('ul');
            
            if(submenu.length) {
                if(submenu.hasClass('active-menu')) {
                    submenu.removeClass('active-menu');
                    link.removeClass('active-menu');
                    $this.topMenuActive = false;
                }
                else {
                    $this.topMenu.find('> li > ul.active-menu').removeClass('active-menu').prev('a').removeClass('active-menu');
                    link.addClass('active-menu').next('ul').addClass('active-menu');
                    $this.topMenuActive = true;
                }
            }
            else {
                if($(e.target).is(':not(:input)')) {
                    $this.topMenu.find('.active-menu').removeClass('active-menu');
                    $this.topMenuActive = false;
                }
            }
        })
        .on('mouseenter', function() {
            var link = $(this);
    
            if(link.parent().parent().is($this.topMenu)&&$this.topMenuActive&&document.documentElement.clientWidth > 960) {
                var submenu = link.next('ul');
                
                $this.topMenu.find('.active-menu').removeClass('active-menu');
                link.addClass('active-menu');
                
                if(submenu.length) {
                    submenu.addClass('active-menu');
                }
            }
        });

        this.topMenu.find('li').clickOff(function() {
            $this.topMenu.find('.active-menu').removeClass('active-menu');
            $this.topMenuActive = false;
        });
    },
    
    removeMenuitem: function(id) {        
        this.expandedMenuitems = $.grep(this.expandedMenuitems, function(value) {
            return value !== id;
        });
    },
    
    addMenuitem: function(id) {
        if($.inArray(id, this.expandedMenuitems) === -1) {
            this.expandedMenuitems.push(id);
        }
    },
    
    saveMenuState: function() {
        $.cookie('modena_expandeditems', this.expandedMenuitems.join(','), {path:'/'});
    },
    
    clearMenuState: function() {
        $.removeCookie('modena_expandeditems', {path:'/'});
    },
    
    restoreMenuState: function() {
        var menucookie = $.cookie('modena_expandeditems');
        if(menucookie) {
            this.expandedMenuitems = menucookie.split(',');
            for(var i = 0; i < this.expandedMenuitems.length; i++) {
                var id = this.expandedMenuitems[i];
                if(id) {
                    var menuitem = $("#" + this.expandedMenuitems[i].replace(/:/g,"\\:"));
                    menuitem.addClass('active-menu-parent');
                    menuitem.children('a,ul').addClass('active-menu active-menu-restore');
                }             
            }
        }
    },
    
    isMobile: function() {
        return (/Android|webOS|iPhone|iPad|iPod|BlackBerry|IEMobile|Opera Mini/i.test(window.navigator.userAgent));
    }
};


$(function() {
   Modena.init();
});


/*!
 * jQuery Cookie Plugin v1.4.1
 * https://github.com/carhartl/jquery-cookie
 *
 * Copyright 2006, 2014 Klaus Hartl
 * Released under the MIT license
 */
(function (factory) {
	if (typeof define === 'function' && define.amd) {
		// AMD (Register as an anonymous module)
		define(['jquery'], factory);
	} else if (typeof exports === 'object') {
		// Node/CommonJS
		module.exports = factory(require('jquery'));
	} else {
		// Browser globals
		factory(jQuery);
	}
}(function ($) {

	var pluses = /\+/g;

	function encode(s) {
		return config.raw ? s : encodeURIComponent(s);
	}

	function decode(s) {
		return config.raw ? s : decodeURIComponent(s);
	}

	function stringifyCookieValue(value) {
		return encode(config.json ? JSON.stringify(value) : String(value));
	}

	function parseCookieValue(s) {
		if (s.indexOf('"') === 0) {
			// This is a quoted cookie as according to RFC2068, unescape...
			s = s.slice(1, -1).replace(/\\"/g, '"').replace(/\\\\/g, '\\');
		}

		try {
			// Replace server-side written pluses with spaces.
			// If we can't decode the cookie, ignore it, it's unusable.
			// If we can't parse the cookie, ignore it, it's unusable.
			s = decodeURIComponent(s.replace(pluses, ' '));
			return config.json ? JSON.parse(s) : s;
		} catch(e) {}
	}

	function read(s, converter) {
		var value = config.raw ? s : parseCookieValue(s);
		return $.isFunction(converter) ? converter(value) : value;
	}

	var config = $.cookie = function (key, value, options) {

		// Write

		if (arguments.length > 1 && !$.isFunction(value)) {
			options = $.extend({}, config.defaults, options);

			if (typeof options.expires === 'number') {
				var days = options.expires, t = options.expires = new Date();
				t.setMilliseconds(t.getMilliseconds() + days * 864e+5);
			}

			return (document.cookie = [
				encode(key), '=', stringifyCookieValue(value),
				options.expires ? '; expires=' + options.expires.toUTCString() : '', // use expires attribute, max-age is not supported by IE
				options.path    ? '; path=' + options.path : '',
				options.domain  ? '; domain=' + options.domain : '',
				options.secure  ? '; secure' : ''
			].join(''));
		}

		// Read

		var result = key ? undefined : {},
			// To prevent the for loop in the first place assign an empty array
			// in case there are no cookies at all. Also prevents odd result when
			// calling $.cookie().
			cookies = document.cookie ? document.cookie.split('; ') : [],
			i = 0,
			l = cookies.length;

		for (; i < l; i++) {
			var parts = cookies[i].split('='),
				name = decode(parts.shift()),
				cookie = parts.join('=');

			if (key === name) {
				// If second argument (value) is a function it's a converter...
				result = read(cookie, value);
				break;
			}

			// Prevent storing a cookie that we couldn't decode.
			if (!key && (cookie = read(cookie)) !== undefined) {
				result[name] = cookie;
			}
		}

		return result;
	};

	config.defaults = {};

	$.removeCookie = function (key, options) {
		// Must not alter options, thus extending a fresh object...
		$.cookie(key, '', $.extend({}, options, { expires: -1 }));
		return !$.cookie(key);
	};

}));