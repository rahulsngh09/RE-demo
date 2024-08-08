// console.log("screen2 is running");
// $(document).ready(function() {
//     // Event handler for image click to toggle selection
//     $('.image-card img').click(function() {
//         $(this).toggleClass('selected'); 
//     });


//     $('.riding').click(function() {
//         let selectedImageIds = [];
//         $('.image-card.selected').each(function() {
//             selectedImageIds.push($(this).attr('data-image-id'));
          
//         });

//         if (selectedImageIds.length > 0) {
       
//             let postData = {
//                 imageIds: selectedImageIds,
//                 prefernces : preferences,
//                 placePreferences : placePreferences,
//                 checkBoxValues : selectedOption
//             };
//             console.log("postData : ", postData);
//             $.ajax({
//                 url: 'https://fakestoreapi.com/products/11',
//                 type: 'GET',
//                 contentType: 'application/json',
//                 data: JSON.stringify(postData),
//                 success: function(response) {
                
//                     console.log('API Response:', response);
                  
//                     console.log("api response : ",response.image);
//                    //  renderImage(response.image);
//                     const nextSectionId = $('.next').attr('data-next-section');
//                     $(nextSectionId).get(0).scrollIntoView({ behavior: 'smooth' });
//                 },
//                 error: function(xhr, status, error) {
//                     // Handle error
//                     console.error('Error during API request:', error);
//                     alert('Error: ' + error); // Alert user of error
//                 }
//             });
//         } else {
//             // Handle case where no images are selected
//             alert('Please select at least one image.');
//         }
//     });


//       function renderImage(imageUrl) {
//         let imageContainer = $('#section5 #image-container');
//         // Clear previous content if any
//         imageContainer.empty();

//         // Create an <img> element
//         let imgElement = $('<img>');
//         imgElement.attr('src', imageUrl);
//         imgElement.attr('alt', 'Image from API'); // Optional: Add alt text for accessibility

//         // Append the image to imageContainer
//         imageContainer.append(imgElement);

//         preloadImage(imageUrl);
//     }

//     function preloadImage(url) {
//         let img = new Image();
//         img.src = url;
//     }
// });

//     function toggleNextButton() {
//         var checkboxes = document.querySelectorAll('input[type="checkbox"]');
//         var nextButton = document.getElementById('nextButton');
//         var checked = false;
//         checkboxes.forEach(function(checkbox) {
//             if (checkbox.checked) {
//                 checked = true;
//             }
//         });
//         if (checked) {
//             nextButton.disabled = false;
//         } else {
//             nextButton.disabled = true;
//         }
//     }

