export default function(resources) {
  return {
    data() {
      const data = { remoteDataLoading: 0, remoteErrors: {} };

      for (let key in resources) {
        data[key] = null;
        data.remoteErrors[key] = null;
      }

      return data;
    },

    created() {
      for (let key in resources) {
        let url = resources[key];
        this.fetchResource(key, url);
      }
    },

    computed: {
      remoteDataBusy() {
        this.$data.remoteDataLoading !== 0;
      },
      hasRemoteErrors() {
        Object.keys(this.$data.remoteErrors).some(
          key => this.$data.remoteErrors[key]
        );
      }
    },

    methods: {
      async fetchResource(key, url) {
        this.$data.remoteDataLoading++;
        this.$data.remoteErrors[key] = null;
        try {
          this.$data[key] = await this.$fetch(url);
        } catch (e) {
          console.log(e);
          this.$data.remoteErrors[key] = e;
        } finally {
          this.$data.remoteDataLoading--;
        }
      }
    }
  };
}
