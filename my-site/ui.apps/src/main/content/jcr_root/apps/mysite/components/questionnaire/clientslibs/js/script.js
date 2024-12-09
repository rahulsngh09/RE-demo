document.addEventListener("DOMContentLoaded", function () {
    console.log("script file is running :");
    const nextButtons = document.querySelectorAll(".next");
    const prevButtons = document.querySelectorAll(".prev");
    const checkboxes = document.querySelectorAll('#myCheckbox');
    const nextButton12 = document.getElementById('nextButton');
    const nextButton = document.querySelector("#section5 .next");
    const mainSection = document.querySelector("#main-section");

    document.querySelectorAll('.option-item').forEach(item => {
        item.addEventListener('click', function(event) {
            // console.log("event",event);
            document.querySelectorAll('.option-item').forEach(el => {
                el.classList.remove('selectedd');
                el.querySelector('.option-checkbox').checked = false;
            });
            const checkbox = this.querySelector('.option-checkbox');
            if (checkbox) {
                checkbox.checked = !checkbox.checked; 
                this.classList.toggle('selectedd', checkbox.checked);
                updateButtonState();
            }
        });
    });

    function updateButtonState() {
        const isChecked = Array.from(checkboxes).some(checkbox => checkbox.checked);
        nextButton12.disabled = !isChecked;
        if (!isChecked) {
            nextButton.classList.add('disabled');
        } else {
            nextButton.classList.remove('disabled');
        }
    }

    function updateButtonState() {
        const isChecked = Array.from(checkboxes).some(checkbox => checkbox.checked);
        nextButton12.disabled = !isChecked;
        if (!isChecked) {
            nextButton.classList.add('disabled');
        } else {
            nextButton.classList.remove('disabled');
        }
    }

    $(document).ready(function () {
        function updateNumbers($sortable) {
            $sortable.find(".answer-container").each(function (index) {
                $(this).find(".answer-id-box").text('#' + (index + 1));
            });
            // console.log("dsgsdj");
        }

        function checkSectionVisibility() {
            if ($('#section3').hasClass('active')) {
                updateNumbers($('#section3 .sortable.answers'));
            }
        }

        checkSectionVisibility();

        $('button.next').on('click', function () {
            setTimeout(checkSectionVisibility, 10);
        });
    });

    // checkbox.addEventListener('change', function () {
    //     submitBtn.disabled = !this.checked;
    // });

    // updateButtonState();

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

    const selectedImagesState = {};

    function setupImageSelection(sectionId, minSelections, maxSelections) {
        const section = document.querySelector(sectionId);
        if (!section) return;

        const imageGrid = section.querySelector('.image-grid');
        const nextButton = section.querySelector('.next');
        const notification = section.querySelector('.notification');
        let selectedImages = selectedImagesState[sectionId] || [];
        // let selectionFinalized = false;

        function updateNextButtonState() {
            // if (selectionFinalized) return;

            if (selectedImages.length >= minSelections) {
                nextButton.classList.add('enabled');
                nextButton.disabled = false;
                console.log("update next button");;
            } else {
                nextButton.classList.remove('enabled');
                nextButton.disabled = true;
                console.log("update next button else part");
            }
        }

        function showNotification(message) {
            if (notification) {
                notification.textContent = message;
                notification.classList.add('show');
                setTimeout(() => {
                    notification.classList.remove('show');
                }, 3000);
            } else {
                // alert(message);
            }
        }

        function updateSelectedImages() {
            section.querySelectorAll('.image-card').forEach(card => {
                const imageId = card.getAttribute('data-image-id');
                if (selectedImages.includes(imageId)) {
                    card.classList.add('selected');
                } else {
                    card.classList.remove('selected');
                }
            });
            // updateNextButtonState();
        }

        function handleImageClick(event) {
            const card = event.target.closest('.image-card');
            if (!card) return;
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
            console.log(selectedImages, selectedImages.length);
            updateNextButtonState();
            handleNextButtonClick();
        }

        function handleNextButtonClick() {
            if (selectedImages.length >= minSelections) {
                selectionFinalized = true;
                selectedImagesState[sectionId] = selectedImages;
                console.log(`Selected Images for ${sectionId}:`);
                console.log("selected image is :", selectedImages);
            } else {
                // showNotification(`Please select at least ${minSelections} image(s) to proceed.`);
            }
        }

        imageGrid.addEventListener('click', handleImageClick);
        nextButton.addEventListener('click', handleNextButtonClick);

        document.querySelectorAll('.prev').forEach(button => {
            button.addEventListener('click', () => {
                const prevSectionId = button.getAttribute('data-prev-section');
                if (prevSectionId === sectionId || button.getAttribute('data-prev-section')) {
                    const currentSelectedImages = [];
                    section.querySelectorAll('.image-card.selected').forEach(card => {
                        currentSelectedImages.push(card.getAttribute('data-image-id'));
                    });

                    selectedImagesState[sectionId] = currentSelectedImages;

                    // updateSelectedImages();
                }
            });
        });
    }

    setupImageSelection('#section4', 1, 3);
    // setupImageSelection('#section5', 4, 20);


    //Set background image for first five sections.
    const bgimageForFirstFiveSection = document.querySelector('.bgimageForFirstFiveSection');
    const bgImageUrl = bgimageForFirstFiveSection?.getAttribute('bgImage');

    if (bgImageUrl) {
        // Select all sections inside the main-div
        bgimageForFirstFiveSection.style.backgroundImage = `url('${bgImageUrl}')`;
    } else {
        console.error('Background image URL not found in temp div.');
    }
});




