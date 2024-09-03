
// Include Handlebars
(function(d) {
    var s = d.createElement('script');
    s.src = 'https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.7.7/handlebars.min.js';
    s.onload = function() {
        console.log('Handlebars loaded');
    };
    d.head.appendChild(s);
}(document));

// Include custom script
document.write('<script src="/etc.clientlibs/your-project/clientlibs/bikedetails/js/bikedetails.js"></script>');
