const HTTPMethods = {
  GET: 'GET',
  POST: 'POST',
  PUT: 'PUT',
  PATCH: 'PATCH',
  DELETE: 'DELETE',
} as const;

const handleError = (status: number, message: string) => {
  throw new Error(`${status}: ${message}`);
};

const request = async <T>(
  url: string,
  config: RequestInit,
  body?: BodyInit,
): Promise<T> => {
  const needToken = !(url.includes(`login`) || url.includes(`signup`));
  const options = { ...config, body };

  if (needToken) {
    // const token = await getToken();
    options.headers = {
      'Content-Type': 'application/json',
      // Authorization: `Bearer ${token}`,
    };
  } else {
    options.headers = { 'Content-Type': 'application/json' };
  }

  const response = await fetch(`${import.meta.env.VITE_SERVER_URL}/api${url}`, {
    ...options,
  });
  if (!response.ok) {
    response.json().then((res) => console.log(res));
    handleError(response.status, response.statusText);
  }
  return response.json();
};

export const api = {
  get: <T>(url: string): Promise<T> =>
    request<T>(url, { method: HTTPMethods.GET }),

  post: <T, U>(url: string, bodyObject?: U): Promise<T> => {
    const body = JSON.stringify(bodyObject);
    return request<T>(url, { method: HTTPMethods.POST }, body);
  },

  delete: <T>(url: string): Promise<T> =>
    request<T>(url, { method: HTTPMethods.DELETE }),

  put: <T, U>(url: string, bodyObject?: U): Promise<T> => {
    const body = JSON.stringify(bodyObject);
    return request<T>(url, { method: HTTPMethods.PUT }, body);
  },
};
