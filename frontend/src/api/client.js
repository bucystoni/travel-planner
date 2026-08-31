const BASE_URL = "http://localhost:8080/api";

 async function request(url, options = {}) {
    const token = localStorage.getItem("token");

    const headers = {
        "Content-Type": "application/json",
        ...options.headers,
    };

    if (token) {
        headers.Authorization = `Bearer ${token}`;
    }


    const response = await fetch(`${BASE_URL}${url}`, {
        method: options.method,
        headers : headers,
        body : JSON.stringify(options.body)
    });

    if (!response.ok) {
        const message = await response.text();

        throw new Error(message || `Request failed with status ${response.status}`);
    }



    return response;
}

function get(endpoint, params = {}) {
    const query = new URLSearchParams(params).toString();

    const url = query
        ? `${endpoint}?${query}`
        : endpoint;

    return request(url, {
        method: "GET"
    });
}

function post(endpoint, options) {
    return request(endpoint, {
        method: "POST",
        body: options.body
    });
}

export { get, post };

//TODO: implement delete and patch functions when the backend is ready for admin operations