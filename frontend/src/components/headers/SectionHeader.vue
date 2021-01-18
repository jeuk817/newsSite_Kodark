<template>
  <div class="sectionHeader">
    {{ sectionName }}
  </div>
</template>

<script>

export default {
  props: ['sectionName']
}
</script>

<style scoped>
.sectionHeader{
  padding-top: 50px;
  width: 100%;
  border-bottom: 3px solid black;
  font-size: 50px;
  font-weight: 800;
}
</style>
