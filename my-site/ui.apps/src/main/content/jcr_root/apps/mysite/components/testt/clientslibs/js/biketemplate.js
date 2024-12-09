document.addEventListener('DOMContentLoaded', function() {
    // Define the Handlebars template
    const bikeTemplate = `
        <div class="bike-container">
            {{#each bikes}}
            <div class="bike-item">
                <h2>{{bikeName}}</h2>
                <img src="{{bikeHeroImage}}" alt="{{bikeName}} Image">
                <p><strong>Price:</strong> {{bikePrice}}</p>
                <div>
                    <h3>Features:</h3>
                    <ul class="bike-features">
                        {{#each bikeFeatures}}
                        <li>{{this}}</li>
                        {{/each}}
                    </ul>
                </div>
                <div>
                    {{#each bikeSmallImages}}
                    <img src="{{this}}" alt="Small Image">
                    {{/each}}
                </div>
                <img src="{{forwardIcon}}" alt="Forward Icon">
            </div>
            {{/each}}
        </div>
    `;

    // Define static data
    const staticData = {
        bikes: [
            {
                bikeName: 'Mountain Bike',
                bikeHeroImage: 'https://via.placeholder.com/150',
                bikePrice: '$500',
                bikeFeatures: ['Suspension', 'Hydraulic Brakes', '21 Gears'],
                bikeSmallImages: [
                    'https://via.placeholder.com/50',
                    'https://via.placeholder.com/50'
                ],
                forwardIcon: 'https://via.placeholder.com/20'
            },
            {
                bikeName: 'Road Bike',
                bikeHeroImage: 'https://via.placeholder.com/150',
                bikePrice: '$700',
                bikeFeatures: ['Lightweight', 'Drop Bars', '18 Gears'],
                bikeSmallImages: [
                    'https://via.placeholder.com/50',
                    'https://via.placeholder.com/50'
                ],
                forwardIcon: 'https://via.placeholder.com/20'
            }
        ]
    };

    // Compile the Handlebars template
    const template = Handlebars.compile(bikeTemplate);

    // Generate the HTML
    const html = template(staticData);

    // Render the HTML into the page
    document.getElementById('content').innerHTML = html;
});
