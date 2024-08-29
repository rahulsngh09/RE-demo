console.log("script file is running :");
document.addEventListener("DOMContentLoaded", function () {
    const nextButtons = document.querySelectorAll(".next");
    const prevButtons = document.querySelectorAll(".prev");
    // // ***********************************

    const checkboxes = document.querySelectorAll('#myCheckbox');
    const nextButton12 = document.getElementById('nextButton');
    // // *****************************************

    const nextButton = document.querySelector("#section5 .next");
    const mainSection = document.querySelector("#main-section");

    // **********************
    checkboxes.forEach(checkbox => {
        checkbox.addEventListener('change', function () {
            updateButtonState();
        });
    });

    function updateButtonState() {
        const isChecked = Array.from(checkboxes).some(checkbox => checkbox.checked);
        nextButton12.disabled = !isChecked;
        if(!isChecked){
            nextButton.classList.add('disabled');
        }else{
            nextButton.classList.remove('disabled');
        }
    }


    // form 
    const testRideButtons = document.querySelectorAll(".test-ride-btn");
    const formOverlay = document.getElementById("test-ride-form");
    const closeButton = document.querySelector(".close-btn");
    const checkbox = document.getElementById('agree');
    const submitBtn = document.querySelector('.submit-btn');

    testRideButtons.forEach(button => {
        button.addEventListener("click", function () {
            formOverlay.style.display = "flex";
        });
    });

    closeButton.addEventListener("click", function () {
        formOverlay.style.display = "none";
    });

    checkbox.addEventListener('change', function () {
        submitBtn.disabled = !this.checked;
    });


    //************** */

    // Initial check in case any checkboxes are pre-checked
    updateButtonState();
    // ***********************************************

    nextButton.addEventListener("click", function () {
        mainSection.style.display = mainSection.style.display === "block" ? "none" : "block";
    });

    nextButtons.forEach(button => {
        button.addEventListener("click", function () {
            const currentSection = this.closest(".questionnaire-section");
            const nextSectionSelector = this.getAttribute("data-next-section");
            const nextSection = document.querySelector(nextSectionSelector);

            if (nextSection) {
                currentSection.classList.remove("active");
                nextSection.classList.add("active");
            }
        });
    });

    prevButtons.forEach(button => {
        button.addEventListener("click", function () {
            const currentSection = this.closest(".questionnaire-section");
            const prevSectionSelector = this.getAttribute("data-prev-section");
            const prevSection = document.querySelector(prevSectionSelector);

            if (prevSection) {
                currentSection.classList.remove("active");
                prevSection.classList.add("active");
            }
        });
    });
});


document.addEventListener("DOMContentLoaded", function () {

});



//******************* */


// $(function() {
//     // Make the list sortable
//     $(".sortable").sortable({
//         update: function(event, ui) {
//             console.log("sortablee.....");
//             updateNumbers();
//         }
//     });

//     // Function to update numbers dynamically
//     function updateNumbers() {
//         $(".sortable .sortable-item").each(function(index) {
//             $(this).find(".answer-id-box").text("#" + (index + 1));
//         });
//     }
// });




//************************************************************************ */

console.log("test component is running :");

document.addEventListener('DOMContentLoaded', () => {
    function setupImageSelection(sectionId, minSelections, maxSelections) {
        const section = document.querySelector(sectionId);
        const imageCards = section.querySelectorAll('.image-card');
        const nextButton = section.querySelector('.next.riding');
        const notification = section.querySelector('.notification'); // Notification element
        let selectedImages = [];
        let selectionFinalized = false; // Track if selection is finalized

        function updateNextButtonState() {
            if (selectionFinalized) return; // Prevent changes if selection is finalized

            // Enable button if the number of selected images meets the minimum requirement
            if (selectedImages.length >= minSelections) {
                nextButton.classList.add('enabled');
                nextButton.disabled = false;
            } else {
                nextButton.classList.remove('enabled');
                nextButton.disabled = true;
            }
        }

        function showNotification(message) {
            if (notification) {
                notification.textContent = message;
                notification.classList.add('show');
                setTimeout(() => {
                    notification.classList.remove('show');
                }, 3000); // Hide notification after 3 seconds
            } else {
                alert(message);
            }
        }

        imageCards.forEach(card => {
            card.addEventListener('click', () => {
                if (selectionFinalized) return; // Prevent changes if selection is finalized

                const imageId = card.getAttribute('data-image-id');
                if (card.classList.contains('selected')) {
                    card.classList.remove('selected');
                    selectedImages = selectedImages.filter(id => id !== imageId);
                } else if (selectedImages.length < maxSelections) {
                    card.classList.add('selected');
                    selectedImages.push(imageId);
                } else {
                    showNotification(`You can only select up to ${maxSelections} images.`);
                }
                updateNextButtonState();
            });
        });

        nextButton.addEventListener('click', () => {
            if (selectedImages.length >= minSelections) {
                selectionFinalized = true; // Finalize selection
                // You can send the selectedImages array to your server or handle it as needed
                console.log(`Selected Images for ${sectionId}:`, selectedImages);
                // Here you can redirect to the next section or perform any further actions
            } else {
                showNotification(`Please select at least ${minSelections} image(s) to proceed.`);
            }
        });

        // Initial check to set the correct state of the Next button
        updateNextButtonState();
    }

    // Setup image selection for each section
    setupImageSelection('#section4', 1, 3); // Section 4 with minimum 1 and maximum 3 selections
    setupImageSelection('#section5', 4, 6); // Section 5 with minimum 4 and maximum 6 selections
});

$(document).ready(function () {
    $('#callbackForm').on('submit', function (event) {
        event.preventDefault(); // Prevent the default form submission

        $('#form-content').hide(); // Hide the form content
        $('#success-message').show(); // Show the success message
    });

    $('#close-popup').on('click', function () {
        $('#test-ride-form').hide(); // Hide the form overlay
    });

    $('#agree').on('change', function () {
        $('.submit-btn').prop('disabled', !this.checked);
    });

    $('.close-btn').on('click', function () {
        $('#test-ride-form').hide();
    });
});





  
    









