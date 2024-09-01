// function filterFullName(input) {
//     // Allow only letters and spaces
//     input.value = input.value.replace(/[^A-Za-z\s]/g, '');
// }

// function filterDigits(input) {
//     // Allow only digits
//     input.value = input.value.replace(/[^0-9]/g, '');
// }

// function validatePhoneNumber(input) {
//     const phoneError = document.getElementById('phoneError');
//     if (input.value.length !== 10) {
//         phoneError.textContent = 'Phone number must be 10 digits.';
//         input.classList.add('invalid');

//         // Remove the error message after 2 seconds
//         setTimeout(() => {
//             phoneError.textContent = '';
//             input.classList.remove('invalid');
//         }, 2000);
//     } else {
//         phoneError.textContent = '';
//         input.classList.remove('invalid');
//     }
// }

// document.getElementById('pincode').addEventListener('input', function (e) {
//     // Remove any non-digit characters from the input
//     e.target.value = e.target.value.replace(/[^0-9]/g, '');

//     // Call the validation function to check if the input is valid
//     validatePincode(e.target);
// });

// function validatePincode(input) {
//     const pincodeError = document.getElementById('pincodeError');

//     // Check if the length of the input is not 6 digits
//     if (input.value.length !== 6) {
//         pincodeError.textContent = 'Pincode must be 6 digits.';
//         input.classList.add('invalid');

//         // Remove the error message after 2 seconds
//         setTimeout(() => {
//             pincodeError.textContent = '';
//             input.classList.remove('invalid');
//         }, 2000);
//     }
//     // If everything is correct
//     else {
//         pincodeError.textContent = '';
//         input.classList.remove('invalid');
//     }
// }



// function validateEmail(input) {
//     const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
//     const emailError = document.getElementById('emailError');
//     if (!regex.test(input.value)) {
//         emailError.textContent = 'Please enter a valid email address.';
//         input.classList.add('invalid');

//         // Remove the error message after 2 seconds
//         setTimeout(() => {
//             emailError.textContent = '';
//             input.classList.remove('invalid');
//         }, 2000);
//     } else {
//         emailError.textContent = '';
//         input.classList.remove('invalid');
//     }
// }

// function validateForm() {
//     const phoneNumber = document.getElementById('phoneNumber');
//     const pincode = document.getElementById('pincode');

//     validatePhoneNumber(phoneNumber);
//     validatePincode(pincode);

//     if (phoneNumber.classList.contains('invalid') || pincode.classList.contains('invalid')) {
//         return false; // Prevent form submission
//     }
//     return true; // Allow form submission
// }
