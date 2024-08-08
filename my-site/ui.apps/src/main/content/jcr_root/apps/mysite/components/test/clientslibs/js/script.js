console.log("test component is running :");
document.addEventListener("DOMContentLoaded", function() {

    const imageCards = document.querySelectorAll(".image-card");
    const maxSelections = 3;
    let selectedCount = 0;

    imageCards.forEach(card => {
        card.addEventListener("click", function() {
            if (card.classList.contains("selected")) {
                card.classList.remove("selected");
                selectedCount--;
            } else if (selectedCount < maxSelections) {
                card.classList.add("selected");
                selectedCount++;
                
            }
        });
    });
});
// const maxSelections = 3;
//         let selectedImages = [];

//         function selectImage(card) {
//             if (card.classList.contains('selected')) {
//                 card.classList.remove('selected');
//                 selectedImages = selectedImages.filter(item => item !== card);
//             } else {
//                 if (selectedImages.length < maxSelections) {
//                     card.classList.add('selected');
//                     selectedImages.push(card);
//                 }
//             }
//         }