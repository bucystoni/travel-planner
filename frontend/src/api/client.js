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

    const fetchOptions = {
        method: options.method,
        headers: headers,
    };

    if (options.body) {
        fetchOptions.body = JSON.stringify(options.body);
    }

    const response = await fetch(`${BASE_URL}${url}`, fetchOptions);

    if (!response.ok) {
        const message = await response.text();
        throw new Error(message || `Request failed with status ${response.status}`);
    }

    const text = await response.text();
    return text ? JSON.parse(text) : null;
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