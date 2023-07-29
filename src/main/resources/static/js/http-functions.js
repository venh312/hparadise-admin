const base = function(url, method, contentType, data, xsrfToken, callback) {
  const headers = new Headers();
  if (contentType) headers.append('Content-Type', contentType);
  if (xsrfToken) headers.append('X-XSRF-TOKEN', xsrfToken);

  fetch(url, {
    method: method,
    headers: headers,
    body: data
  })
  .then(response => {
    console.log(response);
    if (response.json().length > 0) {
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