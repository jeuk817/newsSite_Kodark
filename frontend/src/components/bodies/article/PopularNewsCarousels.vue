<template>
  <div>
    <v-slide-group
      v-model="model"
      :show-arrows="false"
      class="slideContainer"
    >
      <v-slide-item
        v-for="n in 6"
        :key="n"
      >
        <div class="slideNewsContainer">
          <div class="text-h5 font-weight-bold" style="word-break: break-all;">
            ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKDFS
            <!-- 40자 -->
          </div>
          <div class="slideNewsBody">
            <div>
              abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghij
              <!-- 140자 -->
            </div>
            <div class="slideNewsImg">
              <img src="https://picsum.photos/1920/1080?random">
            </div>
          </div>
          <div class="slideNewsFoot">
            10h ago - By Jack
          </div>
        </div>
      </v-slide-item>
    </v-slide-group>
  </div>
</template>

<script>
export default {
  
}
</script>

<style scoped>
.slideContainer {
  border-top: 1px solid black;
  margin-top: 30px;
  padding: 0;
  position: relative;
}

.slideNewsContainer {
  margin-top: 15px;
  border-left: 1px solid rgb(195, 195, 195);
  height: 200px;
  width: 350px;
  padding: 0 10px;
  /* white-space: normal; */

}

.slideNewsBody {
  display: grid;
  grid-template-columns: 1fr 90px;
}

.slideNewsBody img {
  object-fit: cover;
  width: 100%;
  height: 100%;
}

.slideNewsImg {
  width: 90px;
  height: 80px;
  padding-left: 15px;
  padding-bottom: 5px;
}

.slideNewsFoot {
  font-size: 10px;
  font-style: italic;
}

</style>
