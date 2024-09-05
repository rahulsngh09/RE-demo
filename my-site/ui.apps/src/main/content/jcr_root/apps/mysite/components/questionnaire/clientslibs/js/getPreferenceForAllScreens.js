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

$(document).ready(function () {
    $('#callbackForm').on('submit', function (event) {
        event.preventDefault();
        $('#form-content').hide();
        $('#success-message').show();
    });

    $('#close-popup').on('click', function () {
        $('#test-ride-form').hide();
    });

    $('#agree').on('change', function () {
        $('.submit-btn').prop('disabled', !this.checked);
    });

    $('.close-btn').on('click', function () {
        $('#test-ride-form').hide();
    });

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

    const payload = {
        "purpose": preferences,
        "pillion_riding": selectedOption,
        "terrain": placePreferences,
        "riding_position": selectedImages,
    };

    let recommendedImages = {};

    // First POST API call
    fetch('http://18.141.24.29:8000/api/recommendations/get-images', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(payload),
    })
        .then(response => response.json())
        .then(data => {
            recommendedImages = data;
            // console.log("images: ", recommendedImages);

            return fetch('http://18.141.24.29:8000/api/image_mappings/', {
                method: 'GET',
                headers: {
                    'Content-Type': 'application/json',
                },
            }).then(response => response.json())
                .then(data => {
                    console.log('GET Success:', data);

                    const imageMappings = data.image_mappings;
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

                            imgElement.src = image.image_url;
                            imgElement.alt = image.title;
                            imgElement.title = image.title;
                            imgElement.style.width = '128px';
                            imgElement.style.height = '128px';

                            var checkmarkImgPath = document.getElementById('select-tick-img');
                            var imageURL = checkmarkImgPath.getAttribute('data-image-url');

                            checkmarkImg.src = imageURL;
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

                        fetch('http://18.141.24.29:8000/api/recommendations/v2', {
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
                                fetch(`/bin/captureBikeNames?${params.toString()}`, { // Replace with your actual servlet path
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

                                        const testRideButtons = document.querySelectorAll(".test-ride-btn");
                                        const formOverlay = document.getElementById("test-ride-form");
                                        const closeButton = document.querySelector(".close-btn");
                                        const checkbox = document.getElementById('agree');
                                        const submitBtn = document.querySelector('.submit-btn');
                                        function userForm() {
                                            testRideButtons.forEach(button => {
                                                button.addEventListener("click", function () {
                                                    formOverlay.style.display = "flex";
                                                });
                                            });

                                        }

                                        userForm();
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

    console.log('SelectedImages:', selectedImages);
}


function validateFullName(input) {
    const regex = /^[A-Za-z\s]+$/;
    const nameError = document.getElementById('nameError');
    if (!regex.test(input.value)) {
        nameError.textContent = 'Please enter only letters.';
        input.setCustomValidity('Invalid full name.');
    } else {
        nameError.textContent = '';
        input.setCustomValidity('');
    }
    toggleSubmitButton();
}


function validatePhoneNumber(input) {
    const regex = /^\d{10}$/;
    const phoneError = document.getElementById('phoneError');
    if (!regex.test(input.value)) {
        phoneError.textContent = 'Please enter a valid 10-digit phone number.';
        input.setCustomValidity('Invalid phone number.');
    } else {
        phoneError.textContent = '';
        input.setCustomValidity('');
    }
    toggleSubmitButton();
}

function validateEmail(input) {
    const emailError = document.getElementById('emailError');
    const isValid = input.checkValidity();
    if (!isValid) {
        emailError.textContent = 'Please enter a valid email address.';
        input.setCustomValidity('Invalid email.');
    } else {
        emailError.textContent = '';
        input.setCustomValidity('');
    }
    toggleSubmitButton();
}

function validatePincode(input) {
    const regex = /^\d{6}$/;
    const pincodeError = document.getElementById('pincodeError');
    if (!regex.test(input.value)) {
        pincodeError.textContent = 'Please enter a valid 6-digit pincode.';
        input.setCustomValidity('Invalid pincode.');
    } else {
        pincodeError.textContent = '';
        input.setCustomValidity('');
    }
    toggleSubmitButton();
}























