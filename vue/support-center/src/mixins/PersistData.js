export default function(id, fieldList) {
  return {
    watch: fieldList.reduce((obj, field) => {
      obj[field] = function(val) {
        localStorage.setItem(`${id}.${field}`, JSON.stringify(val));
      };

      return obj;
    }, {}),

    methods: {
      saveAllPersistantData() {
        for (const field of fieldList) {
          localStorage.setItem(
            `${id}.${field}`,
            JSON.stringify(this.$data[field])
          );
        }
      }
    },
    beforeDestroy() {
      this.saveAllPersistantData();
    },

    created() {
      for (let field of fieldList) {
        const savedVal = localStorage.getItem(`${id}.${field}`);
        console.log(savedVal);
        this.$data[field] = JSON.parse(savedVal);
      }
    }
  };
}
