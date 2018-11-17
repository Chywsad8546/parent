
(function($){

    History.Adapter.bind(window,'statechange',function(){
        try {
            var State = History.getState();
            var defaults = {
                url: State.hashedUrl
            };
            // console.log(State);
            //History.getFullUrl(State.hash)
            var event = $.Event('route:change')
            $(document).trigger(event, History.getShortUrl(defaults.url));
            var options = optionsFor($.router.container, {});
            var opts = $.extend({}, defaults, options);
            routerGo(opts);

        }
        catch (e){console.error(e);}
    });

    function bindRouter(selector, container) {
        $.router.container=container;
        return this.on('click.router', selector, function(event) {
            var link = event.currentTarget;
            var $link = $(link);
            var url = $.trim($link.attr('href'))
            // console.log($.trim($(this).attr('href')))
            // var u = new URI($.trim($(this).attr('href')));
            handleClick(event, url,link.hostname)
        })
    }

    function go(url) {
        var u = new URI($.trim(url));
        var hostname = $.trim(u.hostname())==''?location.hostname:$.trim(u.hostname());
        handleClick(jQuery.Event( "router:ignore",{}),u.toString(),hostname);
    }

    function handleClick(event, gourl,hostname) {

        var validurl = gourl.toLowerCase();
        if (validurl == '') {
            event.preventDefault()
            return
        }
        if(validurl.indexOf('#')>-1) {
            event.preventDefault()
            console.error('前端请注意，本系统不支持a标签的href中带有#号');
            return
        }
        if(validurl.indexOf('javascrit:')>-1) {
            event.preventDefault()
            return
        }
        var link= parseURL(gourl);
        // Ignore cross origin links
        if ( location.hostname !== hostname )
            return
        // Ignore event with default prevented
        if (event.isDefaultPrevented())
            return

        var url = stripHash(link.href);

        event.preventDefault();
        try {
            var u = new URI(url);
            u.addQuery("__suid", uniqueId())
            History.pushState('', '', u.toString());
        }catch (e){
            console.error(e);
        }


    }


    function routerGo(options) {
        options = $.extend(true, {}, $.ajaxSettings, $.router.defaults, options)

        var context = options.context = $(options.container)

        if (!options.data) options.data = {}


        function fire(type, args, props) {
            if (!props) props = {}
            props.relatedTarget = options.target
            var event = $.Event(type, props)
            context.trigger(event, args)
            return !event.isDefaultPrevented()
        }


        options.beforeSend = function(xhr, settings) {
            xhr.setRequestHeader('X-PJAX', 'true')
            xhr.setRequestHeader('X-PJAX-Container', options.container)

            if (!fire('router:beforeSend', [xhr, settings]))
                return false
        }

        options.complete = function(xhr, textStatus) {
            fire('router:complete', [xhr, textStatus, options])
        }

        options.error = function(xhr, textStatus, errorThrown) {
            var container = extractContainer("", xhr, options)
            var allowed = fire('router:error', [xhr, textStatus, errorThrown, options])
        }

        options.success = function(data, status, xhr) {
            data=$.trim(data);
            if (!fire('router:presuccess', [data, status, xhr]))
                return false

            var container = extractContainer(data, xhr, options)

            if (!container.contents) {
                console.log('没有内容')
                if (!fire('router:content-empty', [data, status, xhr]))
                    return false
                //todo 替换成一个小提示
                return
            }

            // Only blur the focus if the focused element is within the container.
            var blurFocus = $.contains(context, document.activeElement)

            // Clear out any focused controls before inserting new page contents.
            if (blurFocus) {
                try {
                    document.activeElement.blur()
                } catch (e) { /* ignore */ }
            }



            context.html(container.contents)

            // FF bug: Won't autofocus fields that are inserted via JS.
            // This behavior is incorrect. So if theres no current focus, autofocus
            // the last field.
            //
            // http://www.w3.org/html/wg/drafts/html/master/forms.html
            var autofocusEl = context.find('input[autofocus], textarea[autofocus]').last()[0]
            if (autofocusEl && document.activeElement !== autofocusEl) {
                autofocusEl.focus()
            }

            var scrollTo = options.scrollTo
            if (typeof scrollTo == 'number') $(window).scrollTop(scrollTo)

            fire('router:success', [data, status, xhr, options])
        }



        // Cancel the current request if we're already pjaxing
        abortXHR(routerGo.xhr)

        routerGo.options = options
        var xhr = routerGo.xhr = $.ajax(options)

        if (xhr.readyState > 0) {
            fire('router:start', [xhr, options])
            fire('router:send', [xhr, options])
        }

        return routerGo.xhr
    }





    function abortXHR(xhr) {
        if ( xhr && xhr.readyState < 4) {
            xhr.onreadystatechange = $.noop
            xhr.abort()
        }
    }

    function uniqueId() {
        return (new Date).getTime()
    }

// Internal: Parse URL components and returns a Locationish object.
//
// url - String URL
//
// Returns HTMLAnchorElement that acts like Location.
    function parseURL(url) {
        var a = document.createElement('a')
        a.href = url
        return a
    }

// Internal: Return the `href` component of given URL object with the hash
// portion removed.
//
// location - Location or HTMLAnchorElement
//
// Returns String
    function stripHash(url) {
        return url.replace(/#.*/, '')
    }

// Internal: Build options Object for arguments.
//
// For convenience the first parameter can be either the container or
// the options object.
//
// Examples
//
//   optionsFor('#container')
//   // => {container: '#container'}
//
//   optionsFor('#container', {push: true})
//   // => {container: '#container', push: true}
//
//   optionsFor({container: '#container', push: true})
//   // => {container: '#container', push: true}
//
// Returns options Object.
    function optionsFor(container, options) {
        if (container && options) {
            options = $.extend({}, options)
            options.container = container
            return options
        } else if ($.isPlainObject(container)) {
            return container
        } else {
            return {container: container}
        }
    }


    function findAll(elems, selector) {
        return elems.filter(selector).add(elems.find(selector))
    }

    function parseHTML(html) {
        return $.parseHTML(html, document, true)
    }


    function extractContainer(data, xhr, options) {
        var obj = {}, fullDocument = /<html/i.test(data)


        var $head, $body
        // Attempt to parse response html into elements
        if (fullDocument) {
            $body = $(parseHTML(data.match(/<body[^>]*>([\s\S.]*)<\/body>/i)[0]))
            var head = data.match(/<head[^>]*>([\s\S.]*)<\/head>/i)
            $head = head != null ? $(parseHTML(head[0])) : $body
        } else {
            $head = $body = $(parseHTML(data))
        }

        // If response data is empty, return fast
        if ($body.length === 0)
            return obj

        // If there's a <title> tag in the header, use it as
        // the page's title.
        obj.title = findAll($head, 'title').last().text()

        if (options.fragment) {
            var $fragment = $body
            // If they specified a fragment, look for it in the response
            // and pull it out.
            if (options.fragment !== 'body') {
                $fragment = findAll($fragment, options.fragment).first()
            }

            if ($fragment.length) {
                obj.contents = options.fragment === 'body' ? $fragment : $fragment.contents()

                // If there's no title, look for data-title and title attributes
                // on the fragment
                if (!obj.title)
                    obj.title = $fragment.attr('title') || $fragment.data('title')
            }

        } else if (!fullDocument) {
            obj.contents = $body
        }

        // Clean up any <title> tags
        if (obj.contents) {
            // Remove any parent title elements
            obj.contents = obj.contents.not(function() { return $(this).is('title') })

            // Then scrub any titles from their descendants
            obj.contents.find('title').remove()

            // Gather all script[src] elements
            // obj.scripts = findAll(obj.contents, 'script[src]').remove()
            // obj.contents = obj.contents.not(obj.scripts)
        }

        // Trim any whitespace off the title
        if (obj.title) obj.title = $.trim(obj.title)

        return obj
    }





    function enable() {
        $.fn.bindRouter = bindRouter
        $.router = {}

        $.router.go = go
        $.router.defaults = {
            timeout: 5000,
            type: 'GET',
            dataType: 'html',
            scrollTo: 0,
            maxCacheLength: 20
        }
    }
    enable();
})(jQuery)