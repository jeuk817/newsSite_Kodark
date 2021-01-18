<template>
  <div>
    <div class="articleTitle">
      {{ title }}
    </div>
    <div class="articleSubTitle">
      {{ subTitle }}
    </div>
    <div class="articleReporter">
      <div class="font-weight-bold">By {{ reporter.name }}</div>
      <div>Edited at {{ editedAt }}</div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['title', 'subTitle', 'reporter', 'editedAt']
}
</script>

<style scoped>

.articleTitle {
  font-style: italic;
  font-size: 35px;
  font-weight: bold;
  word-break: break-all;
  width: 660px;
  margin: 0 auto 16px calc((100% - 600px) / 2);
}

.articleSubTitle {
  font-size: 20px;
  word-break: break-all;
  width: 600px;
  margin: 0 auto 30px calc((100% - 600px) / 2);
}

.articleReporter {
  width: 600px;
  margin: 0 auto 0 calc((100% - 600px) / 2);
  border-bottom: 1px solid rgb(215, 215, 215);
  text-align: right;
  font-size: 14px;
}
</style>
