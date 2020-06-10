export function getFromServer(apiUrl, callUrl, callBack){
    fetch(apiUrl+callUrl)
        .then(response => response.json())
        // .then(data => console.log(data));
        .then(responseJson => callBack(responseJson));
}

export function postToServer(apiUrl, callUrl, bodyText, callBack){
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'text/plain' },
        body: bodyText,
    };
    fetch(apiUrl+callUrl, requestOptions)
        .then(response => response.json())
        // .then(data => console.log(data))
        .then(responseJson => callBack(responseJson));
}