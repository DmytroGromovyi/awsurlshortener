function validate() {
  let site = "https://d3eevte86p6kmv.cloudfront.net/";
  var x, text;
  x = document.getElementById("inputUrl").value;
  if(validateUrl(x))
  {
	var xhr = new XMLHttpRequest();
	xhr.open("POST", "https://edib9h6jg3.execute-api.eu-west-2.amazonaws.com/default", true);
	xhr.setRequestHeader('Content-Type', 'application/json');
	xhr.send(JSON.stringify({
		url:x
	}));
	xhr.onload = function() {
	console.log(this.responseText);
	var data = JSON.parse(this.responseText);
	var result = data.shortUrl;
	console.log(data);
	console.log(result);
	}
	var link =  site + result;
	text = "Your short url: <a href=" + x + ">" + link + "</a>";
  }
  else
  {
  text = "Bad url!";
  }
  document.getElementById("result").innerHTML = text;
}
function validateUrl(value) {
  return /^(?:(?:(?:https?|ftp):)?\/\/)(?:\S+(?::\S*)?@)?(?:(?!(?:10|127)(?:\.\d{1,3}){3})(?!(?:169\.254|192\.168)(?:\.\d{1,3}){2})(?!172\.(?:1[6-9]|2\d|3[0-1])(?:\.\d{1,3}){2})(?:[1-9]\d?|1\d\d|2[01]\d|22[0-3])(?:\.(?:1?\d{1,2}|2[0-4]\d|25[0-5])){2}(?:\.(?:[1-9]\d?|1\d\d|2[0-4]\d|25[0-4]))|(?:(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)(?:\.(?:[a-z\u00a1-\uffff0-9]-*)*[a-z\u00a1-\uffff0-9]+)*(?:\.(?:[a-z\u00a1-\uffff]{2,})))(?::\d{2,5})?(?:[/?#]\S*)?$/i.test(value);
}