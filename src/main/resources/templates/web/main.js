function accordion() {
    const acc = document.getElementsByClassName("accordion");

    Array.from(acc).map(x => {
        x.addEventListener("click", function() {
            x.classList.toggle("active");
            let panel = x.nextElementSibling;
            
            if (panel.style.display === "block") {
                panel.style.display = "none";
            } else {
                panel.style.display = "block";
            }
        });
    });
