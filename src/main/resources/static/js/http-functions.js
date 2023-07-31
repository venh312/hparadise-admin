const base = function(url, method, contentType, data, xsrfToken, callback) {
  loadingDisplay('');

  const headers = new Headers();
  if (contentType) headers.append('Content-Type', contentType);
  if (xsrfToken) headers.append('X-XSRF-TOKEN', xsrfToken);

  fetch(url, {
    method: method,
    headers: headers,
    body: data
  })
  .then(response => {
    console.log('Server response:', response);
    loadingDisplay('none');

    if (!response.ok) {
      callback(response);
      throw new Error('Network response was not ok');
    }

    return response.json();
  })
  .then(data => {
    console.log('Server Data:', data);
    callback(data);
  })
  .catch(error => console.error(error));
}

const get = function(url, contentType, data, xsrfToken, callback) {
  base(url, 'GET', contentType, data, xsrfToken, callback);
}

const post = function(url, contentType, data, xsrfToken, callback) {
  base(url, 'POST', contentType, data, xsrfToken, callback);
}

const put = function(url, contentType, data, xsrfToken, callback) {
  base(url, 'PUT', contentType, data, xsrfToken, callback);
}

const del = function(url, contentType, data, xsrfToken, callback) {
  base(url, 'DELETE', contentType, data, xsrfToken, callback);
}