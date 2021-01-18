<template>
  <div class="articleTitleContainer">
    <div class="articleTitle">
      {{ title }}
    </div>
    <div class="articleSubTitle">
      {{ subTitle }}
    </div>
    <div class="articleReporter">
      <div class="font-weight-bold">By {{ reporter.name }}</div>
      <div>last edit : {{ editedAt }}</div>
    </div>
  </div>
</template>

<script>
export default {
  props: ['title', 'subTitle', 'reporter', 'editedAt']
}
</script>

<style scoped>

.articleTitleContainer {
  margin-left: calc((100% - 600px) / 2);
  margin-right: auto;
}

.articleTitle {
  font-style: italic;
  font-size: 35px;
  font-weight: bold;
  word-break: break-all;
  width: 660px;
  margin-bottom: 16px;
}

.articleSubTitle {
  font-size: 20px;
  word-break: break-all;
  width: 600px;
  margin-bottom: 30px;
}

.articleReporter {
  width: 600px;
  border-bottom: 1px solid rgb(215, 215, 215);
  text-align: right;
  font-size: 14px;
}
</style>
