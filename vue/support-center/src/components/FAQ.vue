<template>
  <main class="faq">
    <h1>Frenquently Asked Questions</h1>

    <div class="error" v-if="hasRemoteErrors">Can't load data</div>

    <Loader v-if="remoteDataBusy" />

    <section class="list">
      <article v-for="question in questionList" :key="question.id">
        <h2 v-html="question.title"></h2>
        <p v-html="question.content"></p>
      </article>
    </section>
  </main>
</template>

<script>
import RemoteData from '../mixins/RemoteData';

export default {
  mixins: [
    RemoteData({
      questionList: "questions"
    })
  ]
};
</script>

<style lang="stylus" scoped></style>