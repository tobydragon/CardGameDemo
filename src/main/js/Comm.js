export function getFromServer(baseApiUrl, gameId, callUrl, callBack){
    fetch(baseApiUrl+gameId+callUrl)
        .then(response => response.json())
        // .then(data => console.log(data));
        .then(responseJson => callBack(responseJson));
}

export function postToServer(baseApiUrl, gameId, callUrl, callBack){
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ title: 'Hit' })
    };
    fetch(baseApiUrl+gameId+callUrl, requestOptions)
        .then(response => response.json())
        // .then(data => console.log(data))
        .then(responseJson => callBack(responseJson));
}