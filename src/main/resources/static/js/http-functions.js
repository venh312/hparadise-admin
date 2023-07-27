const base = function(url, method, contentType, data, xsrfToken, callback) {
  fetch(url, {
    method: method,
    headers: {
      'Content-Type': contentType,
      'X-XSRF-TOKEN': xsrfToken
    },
    body: data
  })
  .then(response => {
    console.log(response);
    if (response.status == 200) {
      response.json().then((data) => {
        callback(data);
      }).catch((error) => {
        console.error(error);
      });
    } else {
      callback(response);
    }
  })
  .catch(error => console.error(error));
}

const get = function(url, contentType, data, xsrfToken, callback) {
  base(url, 'GET', contentType, data, xsrfToken, callback);
}

const post = function(url, contentType, data, xsrfToken, callback) {
  base(url, 'POST', contentType, data, xsrfToken, callback);
}

