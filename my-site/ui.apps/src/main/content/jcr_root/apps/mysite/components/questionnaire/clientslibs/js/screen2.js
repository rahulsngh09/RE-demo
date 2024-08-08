// let selectedValues = [];

// function checkSelection() {
//     const checkboxes = document.querySelectorAll('input[name="pillion"]');
//     const nextButton = document.getElementById('nextButton');
    
//     selectedValues = []; // Reset selectedValues array
    
//     let anyChecked = false;
//     checkboxes.forEach(checkbox => {
//         if (checkbox.checked) {
//             anyChecked = true;
//             selectedValues.push(checkbox.value); // Store the selected value
//         }
//     });

//     nextButton.disabled = !anyChecked;
// }

// // Optionally, you can define a function to proceed to the next section, storing values as needed
// function nextSection(sectionId) {
//     if (selectedValues.length > 0) {
//         console.log("Selected values:", selectedValues); // Example: Log the selected values
//         // Add any additional logic to proceed to the next section
//         // For example, you could use window.location.hash to navigate to the next section
//         window.location.hash = sectionId;
//     }
// }
