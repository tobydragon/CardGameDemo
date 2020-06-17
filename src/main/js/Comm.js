export function getFromServer(apiUrl, callUrl, callBack){
    return fetch(apiUrl+callUrl)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                return Promise.reject (new Error("Comm.getFromServer: response not ok, status:"+ response.status));
            }
        });
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