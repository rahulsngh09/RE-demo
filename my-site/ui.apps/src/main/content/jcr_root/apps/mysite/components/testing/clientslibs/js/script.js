const nextButtons = document.querySelectorAll(".next");
const prevButtons = document.querySelectorAll(".prev");
const checkboxes = document.querySelectorAll('#myCheckbox');
const nextButton12 = document.getElementById('nextButton');
const nextButton = document.querySelector("#section5 .next");
const mainSection = document.querySelector("#main-section");
const testRideButtons = document.querySelectorAll(".test-ride-btn");
const formOverlay = document.getElementById("test-ride-form");
const closeButton = document.querySelector(".close-btn");
const checkbox = document.getElementById('agree');
const submitBtn = document.querySelector('.submit-btn');



// ***************************************for draggable options:
$(document).ready(function() {
    // Initialize sortable for both sections
    $(".sortable").sortable({
        update: function(event, ui) {
            // Update numbering after sorting
            updateNumbers($(this));
        }
    });
    $(".sortable").disableSelection();
});

function updateNumbers($sortable) {
    // Reset numbering for the specific sortable list
    $sortable.find(".answer-container").each(function(index) {
        $(this).find(".answer-id-box").text('#' + index);
    });
}

function showSection(sectionId) {
    document.querySelectorAll('.questionnaire-section').forEach(section => {
        section.classList.remove('active');
    });
    document.querySelector(sectionId).classList.add('active');
}

// Attach event listeners to "Next" buttons
document.querySelectorAll('.next').forEach(button => {
    console.log("jgfjgejfgehj");
    button.addEventListener('click', function () {
        const nextSection = this.getAttribute('data-next-section');
        showSection(nextSection);
    });
});

// Attach event listeners to "Back" buttons
document.querySelectorAll('.prev').forEach(button => {
    button.addEventListener('click', function () {
        const prevSection = this.getAttribute('data-prev-section');
        showSection(prevSection);
    });
});


// ******************Section 2***************************






