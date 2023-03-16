// The exact pattern for the konami code
var pattern = ['ArrowUp', 'ArrowUp', 'ArrowDown', 'ArrowDown', 'ArrowLeft', 'ArrowRight', 'ArrowLeft', 'ArrowRight', 'b', 'a'];
var current = 0;

var keyHandler = function (event) {

    // If the key isn't in the pattern, or isn't the current key in the pattern, reset
    if (pattern.indexOf(event.key) < 0 || event.key !== pattern[current]) {
        current = 0;
        return;
    }

    // Update how much of the pattern is complete
    current++;

    // If complete, alert and reset
    if (pattern.length === current) {
        current = 0;
        $('#secret').append(
            '<h1 style="font-weight:bold;color:beige;font-size:4.5rem;position:absolute;top:45rem;left:32rem;">Hi 👋🦝</h1>' +
            '<img style="width:25%;position:absolute;top:40rem;left:0;" src="../img/cartoon-raccoon.png" alt="Cartoon raccoon"/>'
        );
        document.getElementById("secret").style.display = "block";
    }

};

// Listen for keydown events
document.addEventListener('keydown', keyHandler, false);