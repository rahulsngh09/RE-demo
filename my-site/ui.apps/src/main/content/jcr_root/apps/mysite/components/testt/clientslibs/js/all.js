console.log("hjfgjfgj");
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
        "purpose": ['Daily', 'Weekend', 'Touring', 'Adventure'],
        "pillion_riding": "Never",
        "terrain": ['Narrow Lanes', 'In City', 'Highway', 'Off-Road'],
        "riding_position": ['Agg', 'Relax'],
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
            console.log("images: ", recommendedImages);

            return fetch('http://18.141.24.29:8000/api/image_mappings/', {
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
                            // const checkmarkImg = document.createElement('img');
                            const overlayDiv = document.createElement('div');
                            // const checkmarkDiv = document.createElement('div');
                            imageGrid.classList.add('image-grid')
                            imgDiv.classList.add('img-div')
                            // checkmarkImg.classList.add('select-tick-img')
                            divElement.classList.add('image-card');
                            divElement.setAttribute('data-image-id', key);

                            overlayDiv.className = 'overlay';
                            // checkmarkDiv.className = 'checkmark';

                            imgElement.src = image.image_url;
                            imgElement.alt = image.title;
                            imgElement.title = image.title;
                            imgElement.style.width = '128px';
                            imgElement.style.height = '128px';

                            // var checkmarkImgPath = document.getElementById('select-tick-img');
                            // var imageURL = checkmarkImgPath.getAttribute('data-image-url');

                            // checkmarkImg.src = imageURL;
                            const tickImg = document.createElement('img');

                            // overlayDiv.appendChild(checkmarkDiv);
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
                        console.log("clicked");
                        const payload = {
                            "purpose": ['Daily', 'Weekend', 'Touring', 'Adventure'],
                            "pillion_riding": "Never",
                            "terrain":  ['Narrow Lanes', 'In City', 'Highway', 'Off-Road'],
                            "riding_position": ['Agg', 'Relax'],
                            "associations":  ["associations_meteor", "associations_interceptor"]
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
                                        document.getElementById('final-section').innerHTML = html;

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

    // console.log('SelectedImages:', selectedImages);
}