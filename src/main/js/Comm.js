export function getFromServer(apiUrl, callUrl){
    /**
     * @return a Promise because it is in a .then. Promise will be rejected if throwing an error, or accepted if returning anything else
     */
    return fetch(apiUrl+callUrl)
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error("Comm.getFromServer: response not ok, status:"+ response.status);
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
export function putToServer(apiUrl, callUrl, bodyObject, callBack){
    const requestOptions = {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(bodyObject),
    };
    fetch(apiUrl+callUrl, requestOptions)
        .then(response => response.json())
        // .then(data => console.log(data))
        .then(responseJson => callBack(responseJson));
}
