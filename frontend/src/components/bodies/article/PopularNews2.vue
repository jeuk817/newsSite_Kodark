<template>
  <div class="poppularNew2">
    <div class="bestNews">
      <template v-if="newsData[0]">
        <img class="bestNewsImg" :src="newsData[0].image" alt="">
        <div class="bestNewsText">
          <div class="text-h5 font-weight-bold">
            {{cutStringLength(newsData[0].title, 50)}}
            <!-- 50자 -->
          </div>
          <div>
            {{cutStringLength(newsData[0].subTitle, 120)}}
            <!-- 120 -->
          </div>
          <div class="newsFoot">
            {{newsData[0].editedAt}}
          </div>
        </div>
      </template>
    </div>
    <div class="secondNews">
      <template v-if="newsData[1]">
        <img class="secondNewsImg" :src="newsData[1].image" alt="">
        <div class="secondNewsText">
          <div class="text-h5 font-weight-bold">
            {{cutStringLength(newsData[1].title, 50)}}
            <!-- 50 -->
          </div>
          <div>
            {{cutStringLength(newsData[1].subTitle, 120)}}
            <!-- 120 -->
          </div>
          <div class="newsFoot">
            {{newsData[1].editedAt}}
          </div>
        </div>
      </template>
    </div>
    <div class="thirdForthNews">
      <div>
        <template v-if="newsData[2]">
          <div class="text-h5 font-weight-bold">
            {{cutStringLength(newsData[2].title, 30)}}
            <!-- 30 -->
          </div>
          <div class="thirdForthNewsBody">
            <img class="thirdForthNewsImg" :src="newsData[2].image" align="right" alt="">
            <span>
              {{cutStringLength(newsData[2].subTitle, 75)}}
              <!-- 75 -->
            </span>
            <div class="newsFoot">
              {{newsData[2].editedAt}}
            </div>
          </div>
        </template>
      </div>
      <div>
        <template v-if="newsData[3]">
          <div class="text-h5 font-weight-bold">
            {{cutStringLength(newsData[3].title, 30)}}
            <!-- 30 -->
          </div>
          <div class="thirdForthNewsBody">
            <img class="thirdForthNewsImg" :src="newsData[3].image" align="right" alt="">
            <span>
              {{cutStringLength(newsData[3].subTitle, 75)}}
              <!-- 75 -->
            </span>
            <div class="newsFoot">
              {{newsData[3].editedAt}}
            </div>
          </div>
        </template>
      </div>
    </div>
  </div>
</template>

<script>
import { utils } from '../../mixins/utils'

export default {
  props:['newsData'],
  mixins: [utils],
  data: () => ({
    randomImg: 'https://picsum.photos/1920/1080?random'
  })
}
</script>

<style scoped>
.poppularNew2 {
  width: 100%;
  height: 660px;
  padding-top: 30px;
  padding-bottom: 30px;
  display: grid;
  grid-template-columns: 2fr 1.1fr 0.8fr;
}

.poppularNew2 img {
  object-fit: cover;
}

.bestNews {
  padding-right: 15px;
  border-right: 1px solid rgb(215, 215, 215);
}

.bestNewsImg {
  width: 100%;
  height: 400px;
}

.bestNewsText {
  padding-top: 25px;
}

.newsFoot {
  font-size: 10px;
  font-style: italic;
  color: black;
}

.secondNews {
  padding: 0 15px;
  border-right: 1px solid rgb(215, 215, 215);
}

.secondNewsImg {
  width: 100%;
  height: 205px;
}

.thirdForthNews {
  padding-left: 15px;
  display: grid;
  grid-template-rows: 1fr 1fr;
}

.thirdForthNewsImg {
  width: 90px;
  height: 80px;
  padding-left: 15px;
  padding-bottom: 5px;
}
</style>
