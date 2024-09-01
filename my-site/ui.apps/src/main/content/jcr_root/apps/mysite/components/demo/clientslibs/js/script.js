console.log("heyy");
$(document).ready(function () {
    $.ajax({
        url: "/bin/bikesdetails",
        type: "GET",
        success: function (response) {
            console.log(response); // Log the response to check data structure
            renderBikeDetails(response);
            console.log("heyy")
        },
        error: function () {
            console.error("Error fetching bike details.");
        }
    });

    function renderBikeDetails(bikeDetailsList) {
        console.log("Rendering bike details:", bikeDetailsList); // Log the data being processed

        const container = $('#motorcycle-container');
        container.empty(); // Clear any existing content

        bikeDetailsList.forEach(bikeDetails => {
            console.log("Rendering bike card:", bikeDetails); // Log each bike's details

            const bikeCard = `
                <div class="motorcycle-card background-image dark-background" style="background-image: linear-gradient(to bottom, transparent -42%, #181818 38%), url('${bikeDetails.bikeHeroImage}');">
                    <div class="motorcycle-image background-image light-background" style="background-size:100%;">
                        <h2>${bikeDetails.bikeName}</h2>
                        <img src="${bikeDetails.bikeHeroImage}" alt="${bikeDetails.bikeName}" width="100%" height="auto">
                        <div class="image-thumbnails">
                            ${bikeDetails.bikeSmallImages.map((thumbnail, index) => `
                                <img src="${thumbnail}" alt="Thumbnail" class="small-bike-image ${index === 1 ? 'middle-image' : ''}">
                            `).join('')}
                        </div>
                        <p class="price">Starts from <strong style="font-family: Montserrat; font-size: 18px; font-weight: bold;">₹ ${bikeDetails.bikePrice}</strong></p>
                        <ul class="features">
                            ${bikeDetails.bikeFeatures.map(feature => `
                                <li><span class="tick">✔</span> ${feature}</li>
                            `).join('')}
                        </ul>
                        <div class="actions">
                            <button class="configure-btn prev">CONFIGURE NOW</button>
                            <button class="test-ride-btn next">BOOK A TEST RIDE</button>
                        </div>
                    </div>
                </div>
            `;
            console.log("dahdhbaub");
            

            container.append(bikeCard);
        });
    }
});
