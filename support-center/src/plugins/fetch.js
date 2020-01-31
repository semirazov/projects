let baseUrl;

export default {
  install(Vue, options) {
    console.log(`Installing fetch with options: ${options}`);

    baseUrl = options.baseUrl;
    Vue.prototype.$fetch = $fetch;
  }
};

export async function $fetch(url, options) {
  const mergedOptions = Object.assign(
    {},
    {
      headers: {
        'Content-Type': 'application/json'
      },
      credentials: 'include'
    },
    options
  );

  const response = await fetch(`${baseUrl}/${url}`, mergedOptions);

  if (response.ok) {
    return await response.json();
  } else {
    const message = await response.text();
    const error = new Error(message);
    error.response = response;
    throw error;
  }
}
