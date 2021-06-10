escapeClass(15)
function escapeClass(studentlim){
// Select the node that will be observed for mutations
var targetNode = document.querySelector('[jscontroller="SKibOb"]');

// Options for the observer (which mutations to observe)
var config = { attributes: true, characterData: true, childList: true, subtree: true };

// Callback function to execute when mutations are observed
var callback = function(mutationsList, observer) {
    // Use traditional 'for loops' for IE 11
    for(var mutation of mutationsList) {
       
        var studentCount = parseInt(mutation.addedNodes[0].data)    
        console.error("Student count Update: " + studentCount);
        
        //change this per class
        if(studentCount < studentlim)
            window.location.href = "https://www.google.com/search?q=MRIGEL+DEGLA&oq=MRIGEL+DEGLA";

    }
};

// Create an observer instance linked to the callback function
var observer = new MutationObserver(callback);

// Start observing the target node for configured mutations
observer.observe(targetNode, config);
}

