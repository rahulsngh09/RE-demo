document.addEventListener("DOMContentLoaded", function() {
    // Function to show a specific section and hide others
    function showSection(sectionId) {
        const sections = document.querySelectorAll('.questionnaire-section, .final-section');
        sections.forEach(section => {
            if (section.id === sectionId) {
                section.style.display = 'block';
            } else {
                section.style.display = 'none';
            }
        });
    }

    // Initial setup: Show only the first section
    showSection('section1');

    // Event listeners for navigation buttons
    document.querySelectorAll('.next').forEach(button => {
        button.addEventListener('click', function() {
            const nextSection = this.getAttribute('data-next-section');
            showSection(nextSection.substring(1)); // Remove '#' from ID
        });
    });

    document.querySelectorAll('.prev').forEach(button => {
        button.addEventListener('click', function() {
            const prevSection = this.getAttribute('data-prev-section');
            showSection(prevSection.substring(1)); // Remove '#' from ID
        });
    });
});
