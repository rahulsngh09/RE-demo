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