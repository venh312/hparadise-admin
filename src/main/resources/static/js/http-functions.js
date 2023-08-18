const send = function(method, url, contentType, data, xsrfToken, callback) {
  loadingDisplay('');

  const headers = new Headers();
  if (contentType) headers.append('Content-Type', contentType);
  if (xsrfToken) headers.append('X-XSRF-TOKEN', xsrfToken);

  const requestOptions = {
    method: method,
    headers: headers
  };

  if (data) requestOptions.body = data;

  fetch(url, requestOptions)
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