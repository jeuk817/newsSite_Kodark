<template>
  <div>
    <div class="articleTitle">
      {{ title }}
    </div>
    <div class="articleSubTitle">
      {{ subTitle }}
    </div>
  </div>
</template>

<script>
export default {
  props: ['title', 'subTitle']
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
</style>
