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
