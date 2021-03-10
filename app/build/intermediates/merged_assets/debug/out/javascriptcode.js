window.androidObj = function AndroidClass(){};

/*var textContainer = document.createElement("p");
var nativeText = document.createTextNode("PDF Viewer");
textContainer.appendChild(nativeText);

var inputContainer = document.createElement("p");
var input = document.createElement("INPUT");
input.setAttribute("type", "text");
inputContainer.appendChild(input);*/

var buttonContainer = document.createElement("p");
var button = document.createElement("button");
button.innerHTML = "Open PDF";
button.style.width = "150px";
button.style.height = "30px";
button.addEventListener ("click", function() {
  //window.androidObj.textToAndroid("http://www.adobe.com/devnet/acrobat/pdfs/pdf_open_parameters.pdf");
  window.androidObj.textToAndroid("https://www.adobe.com/support/products/enterprise/knowledgecenter/media/c4611_sample_explain.pdf");
});
buttonContainer.appendChild(button);

/*document.body.appendChild(textContainer);
document.body.appendChild(inputContainer);*/
document.body.appendChild(buttonContainer);

function updateFromAndroid(message){
    nativeText.nodeValue = message;
}
