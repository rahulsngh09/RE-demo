const testBtn = document.getElementById('book-a-test-ride')
// console.log('pref file testride', testBtn);
var preferences = [];
var placePreferences = [];
var selectedImages = [];
var selectedOption;
var selectedImageIds = [];
let currentStep = 1;

$(document).ready(function () {
    $(".sortable").sortable({
        update: function (event, ui) {
            updateNumber($(this));
        }
    });
    $(".sortable").disableSelection();
});

function updateNumber($sortable) {
    $sortable.find(".answer-container").each(function (index) {
        $(this).find(".answer-id-box").text('#' + (index + 1));
    });
}

function storePreferences() {
    console.log('Storing preferences...');
    preferences = [];

    const items = document.querySelectorAll('.sortable .section1');
    items.forEach(function (item) {
        const value = item.getAttribute('data-value');
        const singleWord = value.split(' ')[0]; // Split by space and take the first word
        preferences.push(singleWord);
    });

    console.log('Preferences:', preferences);
}

function placeToRide() {
    placePreferences = [];

    const items = document.querySelectorAll('.sortable .section3');
    items.forEach(function (item) {
        const value = item.getAttribute('data-value');
        console.log("value is:", value);
        placePreferences.push(value);
    });

    console.log('PlacePreferences:', placePreferences);
}

function checkSelection() {
    var selectedValue = document.querySelector('input[name="pillion"]:checked');
    var nextButton = document.querySelector("#section2 .next");

    var atLeastOneSelected = false;
    selectedOption = "";

    if (selectedValue) {
        selectedOption = selectedValue.value.trim();
        if (selectedOption === 'Often / Always') {
            selectedOption = 'Always';
        }

        atLeastOneSelected = true;
    }
    console.log("selectedOption", selectedOption);
    nextButton.disabled = !atLeastOneSelected;
}

function initializeEventListeners() {
    const nextButton = document.getElementById('next-button');
    const backButton = document.getElementById('back-button');

    if (nextButton) {
        nextButton.removeEventListener('click', handleNextButtonClick);
        nextButton.addEventListener('click', handleNextButtonClick);
    }

    if (backButton) {
        backButton.removeEventListener('click', handleBackButtonClick);
        backButton.addEventListener('click', handleBackButtonClick);
    }
}

function handleNextButtonClick() {
    storePreferences();
    console.log('Navigating to next step...');
    currentStep++;
}

function handleBackButtonClick() {
    console.log('Navigating back...');
    currentStep--;
}

initializeEventListeners();

//*******************************************************************************

//Api Call
function imageTagAttribute() {
    const items = document.querySelectorAll('.selected');
    const currentSelections = new Set();

    items.forEach(item => {
        const customAttr = item.getAttribute('customAttr');
        if (customAttr) {
            currentSelections.add(customAttr);
        }
    });

    // Convert the set to an array and limit to the max selections
    selectedImages = Array.from(currentSelections).slice(0, 3);

    console.log("Selected images are:", selectedImages);


    function toggleSelection(imageId, imageCard) {
        const index = selectedImageIds.indexOf(imageId);
        if (index > -1) {
            // Deselect the image
            selectedImageIds.splice(index, 1);
            imageCard.classList.remove('selected');
            console.log(`Deselected image ID: ${imageId}`)
            console.log(selectedImageIds, "size of array is ", selectedImageIds.length);
        } else {
            // Select the image
            selectedImageIds.push(imageId);
            imageCard.classList.add('selected');
            console.log(`Selected image ID: ${imageId}`);
            console.log(selectedImageIds, "size of array is ", selectedImageIds.length);
        }

        const nextButton = document.getElementById('section5Btn');
        if (selectedImageIds.length >= 4) {
            console.log("finally user selcetd more tha")
            nextButton.disabled = false;
            nextButton.classList.remove('disabled');
        } else {
            nextButton.disabled = true;
            nextButton.classList.add('disabled');
        }

    }

    document.getElementById('section5Btn').disabled = true;

    const payload = {
        "purpose": preferences,
        "pillion_riding": selectedOption,
        "terrain": placePreferences,
        "riding_position": selectedImages,
    };

    let recommendedImages = {};

    // First POST API call
    fetch('http://18.141.24.29:8001/api/recommendations/get-images', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(payload),
    })
        .then(response => response.json())
        .then(data => {
            recommendedImages = data;
            console.log("images: ", recommendedImages);

            return fetch('http://18.141.24.29:8001/api/image_mappings/', {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                },
            }).then(response => response.json())
                .then(data => {
                    console.log('GET Success:', data);

                    const imageMappings = data.image_mappings;
                    // console.log("image-mapping",data.image_mappings);
                    const imageGallery = document.getElementById('img-div');

                    imageGallery.innerHTML = '';
                    for (const key in recommendedImages.associations) {
                        if (imageMappings.hasOwnProperty(key)) {
                            const image = imageMappings[key];

                            const imageGrid = document.createElement('div');
                            const divElement = document.createElement('div');
                            const imgDiv = document.createElement('div');
                            const imgElement = document.createElement('img');
                            const checkmarkImg = document.createElement('img');
                            const overlayDiv = document.createElement('div');
                            const checkmarkDiv = document.createElement('div');
                            imageGrid.classList.add('image-grid')
                            imgDiv.classList.add('img-div')
                            checkmarkImg.classList.add('select-tick-img')
                            divElement.classList.add('image-card');
                            divElement.setAttribute('data-image-id', key);

                            overlayDiv.className = 'overlay';
                            checkmarkDiv.className = 'checkmark';

                            imgElement.className = 'img-info';

                            imgElement.src = image.image_url;
                            imgElement.alt = image.title;
                            imgElement.title = image.title;
                            // imgElement.style.width = '128px';
                            // imgElement.style.height = '128px';

                            var checkmarkImgPath = document.getElementById('select-tick-img');
                            var imageURL = checkmarkImgPath.getAttribute('data-image-url');

                            checkmarkImg.src = imageURL;

                            divElement.onclick = function () {
                                toggleSelection(key, divElement)
                            };
                            const tickImg = document.createElement('img');

                            overlayDiv.appendChild(checkmarkDiv);
                            checkmarkDiv.appendChild(checkmarkImg);
                            divElement.appendChild(imgElement);
                            divElement.appendChild(overlayDiv)
                            if (divElement.classList.contains('selected')) {
                                divElement.appendChild(tickImg);
                            }
                            imageGallery.appendChild(divElement);
                        }
                    }

                    document.getElementById('section5Btn').addEventListener('click', function () {
                        const payload = {
                            "purpose": preferences,
                            "pillion_riding": selectedOption,
                            "terrain": placePreferences,
                            "riding_position": selectedImages,
                            "associations": selectedImageIds
                        };

                        fetch('http://18.141.24.29:8001/api/recommendations/v2', {
                            method: 'POST',
                            headers: {
                                'Content-Type': 'application/json',
                            },
                            body: JSON.stringify(payload),
                        })
                            .then(response => response.json())
                            .then(data => {
                                console.log('POST Success:', data);

                                const modelNames = data.recommendations.map(item => item.model_name);
                                const params = new URLSearchParams();
                                modelNames.forEach((modelName, index) => {
                                    params.append(`model_name_${index}`, modelName);
                                });
                                console.log("params", params.toString());

                                // After receiving the response, send it to your AEM servlet
                                fetch(`/bin/captureBikeNames?${params.toString()}`, {
                                    method: 'GET',

                                })
                                    .then(servletResponse => servletResponse.json())
                                    .then(servletData => {
                                        console.log('Servlet Success:', servletData);
                                        //handlebar template
                                        const templateSource = document.getElementById('bike-template').innerHTML;
                                        const template = Handlebars.compile(templateSource);
                                        const context = {
                                            bikes: servletData.map(bike => ({
                                                bikeName: bike.bikeName,
                                                bikeHeroImage: bike.bikeHeroImage || 'defaultHeroImageUrl',
                                                bikePrice: bike.bikePrice || 'Price Not Available',
                                                bikeSmallImages: bike.bikeSmallImages || ['defaultSmallImageUrl'],
                                                bikeFeatures: bike.bikeFeatures || ['Feature Not Available'],
                                                forwardIcon: bike.forwardIcon || 'defaultForwardIconUrl'
                                            }))
                                        };



                                        const html = template(context);
                                        document.getElementById('main-section').innerHTML = html;
                                        // console.log("context is :",context);

                                        const testRideButtons = document.querySelectorAll(".test-ride-btn");
                                        const formOverlay = document.getElementById("test-ride-form");
                                        const closeButton = document.querySelector(".close-btn");
                                        const checkbox = document.getElementById('agree');
                                        const submitBtn = document.querySelector('.submit-btn');




                                        const tabs = document.querySelectorAll('.bike-tab');
                                        const cards = document.querySelectorAll('.bike-inner-card');

                                        if (tabs.length === 0 || cards.length === 0) {
                                            console.error("No elements found for tabs or cards.");
                                            return; // Exit the script if elements are not found
                                        }

                                        // Update Active Tab (highlight the selected tab)
                                        function updateActiveTab(cardId) {
                                            console.log("inside method");
                                            if (window.innerWidth <= 500) {
                                                // Remove 'highlighted' class from all tabs
                                                tabs.forEach(tab => tab.classList.remove('highlighted'));

                                                // Find and highlight the tab corresponding to the cardId
                                                const activeTab = Array.from(tabs).find(tab => tab.getAttribute('data-bike-id') === cardId);
                                                if (activeTab) {
                                                    activeTab.classList.add('highlighted');
                                                }
                                            }
                                        }

                                        // Observer for each card to update active tab when visible
                                        const observer = new IntersectionObserver((entries) => {
                                            console.log("Observer triggered", entries);
                                            entries.forEach(entry => {
                                                if (entry.isIntersecting) {
                                                    const cardId = entry.target.id;
                                                    updateActiveTab(cardId);
                                                }
                                            });
                                        }, {
                                            threshold: 0.5  // Trigger when 50% of the card is visible
                                        });

                                        // Observe each bike card (only when needed, triggered by section5)
                                        cards.forEach(card => {
                                            console.log("observing card", card.id);
                                            observer.observe(card);
                                        });



                                        function userForm() {
                                            testRideButtons.forEach(button => {

                                                const bikeDetails = button.getAttribute('bike-details');

                                                button.addEventListener("click", function () {
                                                    formOverlay.style.display = "flex";

                                                    console.log('Bike details for form:', bikeDetails);
                                                });
                                            });

                                        }

                                        // userForm();
                                    })
                                    .catch(servletError => {
                                        console.error('Servlet Error:', servletError);
                                    });
                            })
                            .catch(error => {
                                console.error('Error:', error);
                            });
                    });

                })
                .catch(error => {
                    console.error('Error:', error);
                });
        });

    //******************************************** */

    console.log('SelectedImages:', selectedImages);
}

$(document).ready(function () {
    //     $('#callbackForm').on('submit', function (event) {
    //         event.preventDefault();
    //         $('#form-content').hide();
    //         $('#success-message').show();
    //     });

    //     $('#close-popup').on('click', function () {
    //         $('#test-ride-form').hide();
    //     });

    $('#agree').on('change', function () {
        $('.submit-btn').prop('disabled', !this.checked);
    });

    $('.close-btn').on('click', function () {
        $('#test-ride-form').hide();
        $('#callbackForm')[0].reset();
    });

});
function validateFullName(input) {
    const nameError = document.getElementById('nameError');

    // Check if input contains invalid characters
    if (/[0-9]/.test(input.value)) {
        nameError.textContent = 'Please enter only letters. Digits are not allowed.';
        input.setCustomValidity('Invalid full name.');
    } else {
        nameError.textContent = '';
        input.setCustomValidity('');
    }

    // Filter the input value to allow only letters and spaces
    input.value = input.value.replace(/[^A-Za-z\s]/g, '');
}


function validatePhoneNumber(input) {
    const phoneError = document.getElementById('phoneError');

    // Filter the input value
    input.value = input.value.replace(/[^0-9]/g, '');

    if (input.value.length > 10) {
        phoneError.textContent = 'Phone number cannot exceed 10 digits.';
        input.setCustomValidity('Invalid phone number.');
    } else {
        phoneError.textContent = '';
        input.setCustomValidity('');
    }
}

function validateEmail(input) {
    const emailError = document.getElementById('emailError');
    const regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!regex.test(input.value)) {
        emailError.textContent = 'Please enter a valid email address.';
        input.setCustomValidity('Invalid email address.');
    } else {
        emailError.textContent = ''; // Clear error if input is valid
        input.setCustomValidity('');
    }
}

function validatePincode(input) {
    const pincodeError = document.getElementById('pincodeError');

    // Filter the input value
    input.value = input.value.replace(/[^0-9]/g, '');

    if (input.value.length !== 6) {
        pincodeError.textContent = 'Please enter a valid 6-digit pincode.';
        input.setCustomValidity('Invalid pincode.');
    } else {
        pincodeError.textContent = ''; // Clear error if input is valid
        input.setCustomValidity('');
    }
}


// Function to update background image based on screen size
function updateBackgroundImage() {
    var mainSection = document.getElementById('main-section');
    var mobileImageURL = mainSection.getAttribute('mobileImageURL'); // Get mobile background URL from the data attribute

    if (window.innerWidth <= 500) {
        // Change to mobile background image
        mainSection.style.backgroundImage = 'url(' + mobileImageURL + ')';
    } else {
        // Change to default desktop background image (already set inline)
        var desktopImageURL = mainSection.style.backgroundImage; // Default desktop image from inline style
        mainSection.style.backgroundImage = desktopImageURL; // Keep the same desktop background
    }
}

// Run the function when the window is resized or loaded
window.addEventListener('resize', updateBackgroundImage);
window.addEventListener('load', updateBackgroundImage);









































