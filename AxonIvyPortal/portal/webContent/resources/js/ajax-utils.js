async function callFetchApi(uri, content) {
  try {
    const response = await fetch(uri, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
        'X-Requested-By': 'ivy',
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': 'GET, POST',
      },
      body: content
    });
    return response;
  } catch (error) {
    console.error('Error sending AJAX request:', error);
  }
}

async function callJQueryAjaxToIvy(uri, content) {
try {
    const response = await fetch(uri, {
      method: 'POST',
      async: true,
      headers: {
        'Content-Type': 'application/json',
        'X-Requested-By': 'ivy',
        'Access-Control-Allow-Origin': '*',
        'Access-Control-Allow-Methods': 'GET, POST',
      },
      body: content
    });
    return response;
  } catch (error) {
    console.error('Error sending AJAX request:', error);
  }
}