export function getFromServer(apiUrl, callUrl, callBack){
    fetch(apiUrl+callUrl)
        .then(response => response.json())
        // .then(data => console.log(data));
        .then(responseJson => callBack(responseJson));
}

export function postToServer(apiUrl, callUrl, bodyObject, callBack){
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(bodyObject),
    };
    fetch(apiUrl+callUrl, requestOptions)
        .then(response => response.json())
        // .then(data => console.log(data))
        .then(responseJson => callBack(responseJson));
}