document.addEventListener('DOMContentLoaded', function () {
    var navItems = document.querySelectorAll('.navbar-left li, .navbar-right li');
console.log("nav class has been called");
    // Function to handle hover state
    function handleHover(item) {
        var arrow = item.querySelector('.arrow img');
        if (arrow) {
            arrow.dataset.originalSrc = arrow.src; // Store the original src
            arrow.src = '/content/dam/mysite/icons/dropdown-icon.png'; // Change to up arrow image on hover
			
            arrow.style.backgroundColor = 'black'; // Change background color to red



        }
        var submenu = item.querySelector('.submenu');
        if (submenu) {
            submenu.style.display = 'block'; // Show the submenu on hover
            submenu.style.pointerEvents = 'auto'; // Allow interaction with submenu
        }
    }

    // Function to handle mouse leave state
    function handleMouseLeave(item) {
        var arrow = item.querySelector('.arrow img');
        if (arrow) {
            arrow.src = arrow.dataset.originalSrc; // Revert to original src
            arrow.style.backgroundColor = ''; // Revert background color
        }
        var submenu = item.querySelector('.submenu');
        if (submenu) {
            submenu.style.display = 'none'; // Hide the submenu on mouse leave
            submenu.style.pointerEvents = 'none'; // Prevent interaction when hidden
        }
    }

    navItems.forEach(function (item) {
        item.addEventListener('mouseenter', function () {
            handleHover(item);
        });

        item.addEventListener('mouseleave', function () {
            // Delay hiding submenu to allow interaction
            setTimeout(function () {
                if (!item.matches(':hover') && !item.querySelector('.submenu').matches(':hover')) {
                    handleMouseLeave(item);
                }
            }, 200); // Adjust delay as needed
        });

        // Ensure submenu stays visible when hovering over it
        var submenu = item.querySelector('.submenu');
        if (submenu) {
            submenu.addEventListener('mouseenter', function () {
                submenu.style.display = 'block';
                submenu.style.pointerEvents = 'auto'; // Allow interaction
            });
            submenu.addEventListener('mouseleave', function () {
                submenu.style.display = 'none';
                submenu.style.pointerEvents = 'none'; // Prevent interaction when hidden
            });
        }
    });

    // Remove hover styles and hide submenus when clicking outside the menu
    document.addEventListener('click', function (event) {
        if (!event.target.closest('.navbar')) {
            navItems.forEach(function (i) {
                handleMouseLeave(i); // Ensure to revert hover styles
            });
        }
    });
});


