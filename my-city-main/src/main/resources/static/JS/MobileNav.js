document.addEventListener('DOMContentLoaded', () => {
    const mobileHeader = document.querySelector('.mobile_header');
    const navLinks = document.querySelector('.nav_links');

    mobileHeader.addEventListener('click', () => {
        // Toggle the display property
        if (navLinks.style.display === 'flex') {
            navLinks.style.display = 'none';
        } else {
            navLinks.style.display = 'flex';
        }
    });
});
