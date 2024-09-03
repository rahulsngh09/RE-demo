// document.addEventListener('DOMContentLoaded', function() {
//     async function fetchDataAndRender() {
//         try {
//             // Fetch the JSON data from your servlet
//             const response = await fetch('/bin/bikesdetails'); // Adjust the URL as needed
//             const data = await response.json();

//             // Get the Handlebars template from the HTML
//             const templateSource = document.getElementById('bike-template').innerHTML;
//             const template = Handlebars.compile(templateSource);

//             // Create a data context
//             const context = { bikes: data };

//             // Generate the HTML
//             const html = template(context);

//             // Render the HTML into the page
//             document.getElementById('content').innerHTML = html;
//         } catch (error) {
//             console.error('Error fetching or rendering data:', error);
//         }
//     }

//     // Call the function to fetch data and render the template
//     fetchDataAndRender();
// });
