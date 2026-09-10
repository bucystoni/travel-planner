const BASE_URL = import.meta.env.VITE_API_URL;

 async function request(url, options = {}) {
    const token = localStorage.getItem("jwt");

    const headers = {
        "Content-Type": "application/json",
        ...options.headers,
    };

    if (token) {
        headers.Authorization = `Bearer ${token}`;
    }

    let response = null;

    if (options.body) {
        response = await fetch(`${BASE_URL}${url}`, {
                method: options.method,
                headers : headers,
                body : JSON.stringify(options.body)
            });
    } else {
        response = await fetch(`${BASE_URL}${url}`, {
                        method: options.method,
                        headers : headers,
                    });
    }

    if (response.status === 204) return null;
    if (!response.ok) {
        const message = await response.text();

        throw new Error(message || `Request failed with status ${response.status}`);
    }

    return await response.json();
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

function put(endpoint, options) {
    return request(endpoint, {
        method: "PUT",
        body: options.body
    });
}

export { get, post, put };

//TODO: implement delete and patch functions when the backend is ready for admin operations