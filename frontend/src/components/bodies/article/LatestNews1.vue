<template>
  <div class="container">
    <div class="articleRow">
      <div class="sectionTop">
        <h3 class="sectionTitle">{{latestNews.category}}</h3>
        <div class="sectionLink">
          <router-link :to="'/en/section/' + latestNews.category.toLowerCase()">
            More in The {{latestNews.category}}
          </router-link>
        </div>
      </div>
      <ul class="articles">
        <li v-for="(news, i) in latestNews.data" :key="i"
        class="item"
        >
          <router-link :to="'/en' + news._link.href">
            <img :src="news.image" alt="articleImg" class="articleImg">
            <p class="articleTitle">
              {{news.title.length > 40 ? news.title.substring(0, 40) + '...' : news.title}}
              <!-- 40자 -->
            </p>
          </router-link>
        </li>
      </ul>
    </div>
    
</div>
</template>

<script>
export default {
  props: ['latestNews']
}
</script>

<style scoped>
ul{
  padding-left: 0;
}
li{
  list-style: none;
}
body{
  box-sizing: border-box;
}
img{
  width: 200px;
  height: 143px;

}
.sectionTop{
  width: 100%;
  border-top: 1px solid black;
  display: grid;
  grid-template-columns: 1fr 1fr;
  margin-bottom: 15px;
  /* padding: 5px; */
}
.sectionTitle,
.sectionLink{
  margin-top: 10px;
}
.sectionLink{
  text-align: right;
  font-size: 14px;
  text-decoration: none;
}

.sectionLink >  a, .item > a {
  color: black;
}

.sectionLink >  a:hover, .item > a:hover {
  text-decoration: underline;
}

.articles{
  /* width: 100%; */
  margin: 10px 0 30px 0;
  display: grid;
  grid-template-columns: repeat(5, 1fr);
  column-gap: 3px;
  margin-bottom: 30px;
}
.item{
  height: 230px;
  padding: 0 15px 10px 15px;
  display:grid;
  grid-template-rows: 1fr 1fr;
  border-right: 1px solid #e2e2e2;
}
.articles > li:nth-child(5){
  border: none;

}
.item {
  font-size: 16px;
  color: #121212;
}
.articleTitle{
  margin-top: 10px;
}

</style>