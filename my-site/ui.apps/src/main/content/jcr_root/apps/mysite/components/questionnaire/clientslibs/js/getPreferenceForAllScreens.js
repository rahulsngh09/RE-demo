console.log("get pref js is running ");
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

var preferences = [];
var placePreferences = [];
var selectedImages = [];
var selectedOption;

function storePreferences() {

    var items = document.querySelectorAll('.sortable .section1');

    if (items.length === 0) {
     //   console.log('No sortable items found.');
    } else {
       // console.log('Sortable items found:');
    }
    items.forEach(function(item) {
        preferences.push(item.getAttribute('data-value'));
    });

    console.log('Preferences:', preferences);
}


//*****************************************************************************


//******************************************************************************

// Array to store selected checkbox values
let selectedValues = [];


function placeToRide() {

    var items = document.querySelectorAll('.sortable .section3');

    if (items.length === 0) {
      //  console.log('No sortable items found.');
    } else {
       //s console.log('Sortable items found:');
    }
    items.forEach(function(item) {
        placePreferences.push(item.getAttribute('data-value'));
    });

    console.log('Preferences:', placePreferences);
}

//*******************************************************************************


function checkSelection() {

    var selectedValue = document.querySelector('input[name="pillion"]:checked');
    var nextButton = document.querySelector("#section2 .next");

    var atLeastOneSelected = false;
    if(selectedValue){
        selectedOption = selectedValue.value;
        atLeastOneSelected = true;
        console.log("selectedValues",selectedOption);
    }

   if (atLeastOneSelected) {
       nextButton.disabled = false;
   } else {
       nextButton.disabled = true;
   }
}

// Event listener for checkbox change
//document.querySelectorAll('#section2 input[type="checkbox"]').forEach(checkbox => {
   // checkbox.addEventListener('change', checkSelection);
//});

// Function to handle next button click
//document.querySelector('#section2 .next').addEventListener('click', function() {
    // Store selected values in formData or wherever needed
    //formData.section2 = selectedValues;

    // Proceed to next section
    //const currentSection = this.closest('.questionnaire-section');
    //const nextSection = document.querySelector(this.getAttribute('data-next-section'));
    //currentSection.classList.remove('active');
  //  nextSection.classList.add('active');
//});

//********************************************************************************************************















