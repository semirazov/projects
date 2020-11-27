let baseUrl;
import state from "../state";
import router from "../router";

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
  } else if (response.status === 403) {
    // If the session is no longer valid // We logout
    state.user = null;
    // If the route is private
    // We go to the login screen
    if (router.currentRoute.matched.some(r => r.meta.private)) {
      router.replace({
        name: 'login',
        params: {
          wantedRoute: router.currentRoute.fullPath
        }
      });
    }
  } else {
    const message = await response.text();
    const error = new Error(message);
    error.response = response;
    throw error;
  }
}
