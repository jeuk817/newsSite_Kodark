<template>
  <div class="container">
    <header class="header">
      <v-btn text>
        <p class="latest">Latest</p>
      </v-btn>
       <v-btn text>
        <span class="material-icons">
        search
        </span>
        Search
      </v-btn>
    </header>
    <div class="content__container">
      <div class="articleItemList">
        <router-link
        v-for="(news, i) in latestNews" :key="i"
        :to="'/en' + news._link.href"
        class="articleItem"
        >
          <div class="articleDate">
            <p>{{ news.editedAt }}</p>
            <!-- <p>Dec 22, 2020</p> -->
          </div>
          <div class="articleItem__content">
            <div class="articleTitle">
              <p>
                {{ cutStringLength(news.title, 40) }}
                <!-- 40 -->
              </p>
            </div>
            <div class="articleSubTitle">
              <p>
                {{ cutStringLength(news.subTitle, 85) }}
                <!-- 85 -->
              </p>
            </div>
            <div class="reportName">
              <p>report name</p>
            </div>
          </div>
          <div class="articleImg">
            <img :src="news.image" alt="articleImg">
          </div>
        </router-link>
      </div>
      <template v-if="latestNews">
        <div class="adItem">
          <img src="../../../assets/img/ad.png" alt="">
        </div>
      </template>
    </div>
  </div>
</template>

<script>
import { utils } from '../../mixins/utils'

export default {
  props: ['latestNews'],
  mixins: [utils]
}
</script>

<style scoped>
body{
  box-sizing: border-box;
}
.container{
  margin-bottom: 100px;
}
.header{
  height: 38px;
  border-top: 1px solid black;
  border-bottom: 1px solid #e2e2e2;
}
.articleImg img{
  width: 215px;
  height: 143px;
}
.latest{
  padding-left: 45px;
  padding-right: 45px;
  margin: 0;
}
.content__container{
  margin-top: 10px;
  display: grid;
  grid-template-columns: 6fr 1fr;
}

.articleItemList a {
  color: black;
}

.articleItemList a:hover {
  text-decoration: underline;
}

.articleItem{
  margin: 15px 20px 0 0 ;
  max-height: 178px;
  display: grid;
  grid-template-columns: 1fr 3fr 2fr;
  border-bottom:  1px solid #e2e2e2;
}
.articleItem__content{
  padding-right: 26px;
}

.articleDate p{
  margin-top: 20px;
  font-size: 11px;
  color: rgb(153, 153, 153);
}

.articleTitle p{
  font-size: 19px;
  line-height: 23px;
  color: #000;
  margin: 0;
}
.articleSubTitle p{
  margin-top: 10px;
  font-size: 14px;
  color: #333;
}
.reportName p{
  color: #999;
  font-size: 11px;
}
</style>