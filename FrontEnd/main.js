function validate() {
  let site = "https://d3eevte86p6kmv.cloudfront.net/";
  var longUrl, text, data;
  longUrl = document.getElementById("inputUrl").value;
  if(validateUrl(longUrl))
  {
	var resultElement = document.getElementById("result");
	resultElement.innerHTML = "";
	resultElement.classList.toggle('spinner');
  foo(longUrl, function parseResult(txt){
  	data = JSON.parse(txt);
  	console.log(data.shortUrl);
  	text = "Your short url: <a href=" + longUrl + ">" + site + data.shortUrl + "</a>";
	resultElement.classList.toggle('spinner');
  	document.getElementById("result").innerHTML = text;
  });
  }
  else
  {
  text = "Bad url!";
  document.getElementById("result").innerHTML = text;
  }
}
function parseResult(txt)
{
	console.log(txt);
	return JSON.parse(txt);
}
function validateUrl(value) {
  return /^(?:(?:(?:https?|ftp):)?\/\/)(?:\S+(?::\S*)?@)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})))(?::\d{2,5})?(?:[/?#]\S*)?$/i.test(value);
}
function foo(longUrl, callback) {
    xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) { // request is done
            if (xhr.status === 200) { // successfully
                callback(xhr.responseText); // we're calling our method
            }
        }
    };
    xhr.open("POST", "https://edib9h6jg3.execute-api.eu-west-2.amazonaws.com/default", true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    	xhr.send(JSON.stringify({
		url:longUrl
		}));
}