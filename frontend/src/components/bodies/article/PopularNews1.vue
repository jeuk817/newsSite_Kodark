<template>
  <div class="poppularNew1">
    <div class="pop1Best">
      <template v-if="formatedNews[0]">
        <router-link :to="'/en' + formatedNews[0]._link.href">
          <div>
            <img :src="formatedNews[0].image">
          </div>
          <div class="pop1BestText">
            <div class="text-h5 font-weight-bold">
              {{formatedNews[0].title}}
            </div>
            <div class="pop1BestSubTitle">
              {{formatedNews[0].subTitle}}
            </div>
          </div>
        </router-link>
      </template>
    </div>
    <div class="pop1Right">
      <div class="pop1RightSecond">
        <template v-if="formatedNews[1]">
          <router-link :to="'/en' + formatedNews[1]._link.href">
            <div class="pop1RightTop">
              <div class="pop1RightTitle text-h5 font-weight-bold">
                {{formatedNews[1].title}}
                <!-- 25자 -->
              </div>          
              <div class="pop1RightImg">
                <img :src="formatedNews[1].image">
              </div>          
            </div>
            <div class="pop1RignthBody">
              {{formatedNews[1].subTitle}}
              <!-- 70자 -->
            </div>
            <div class="pop1RightFoot">
              {{formatedNews[1].editedAt}}
            </div>
          </router-link>
        </template>
      </div>
      <div class="pop1RightThird">
        <template v-if="formatedNews[2]">
          <router-link :to="'/en' + formatedNews[2]._link.href">
            <div class="pop1RightTop">
              <div class="pop1RightTitle text-h5 font-weight-bold">
                {{formatedNews[2].title}}
              </div>          
              <div class="pop1RightImg">
                <img :src="formatedNews[2].image">
              </div>          
            </div>
            <div class="pop1RignthBody">
              {{formatedNews[2].subTitle}}
            </div>
            <div class="pop1RightFoot">
              {{formatedNews[1].editedAt}}
            </div>
          </router-link>
        </template>
      </div>
      <div class="pop1RightForth">
        <template v-if="formatedNews[3]">
          <router-link :to="'/en' + formatedNews[3]._link.href">
            <div class="pop1RightTop">
              <div class="pop1RightTitle text-h5 font-weight-bold">
                {{formatedNews[3].title}}
              </div>          
              <div class="pop1RightImg">
                <img :src="formatedNews[3].image">
              </div>          
            </div>
            <div class="pop1RignthBody">
              {{formatedNews[3].subTitle}}
            </div>
            <div class="pop1RightFoot">
              {{formatedNews[3].editedAt}}
            </div>
          </router-link>
        </template>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  props:['newsData'],
  computed: {
    formatedNews() {
      return this.newsData.map((news, i) => {
        if(i === 0) {
          news.title = news.title.length > 45 ? news.title.substring(0, 46) + '...' : news.title
          news.subTitle = news.subTitle.length > 150 ? news.subTitle.substring(0, 150) + '...' : news.subTitle
        }
        else {
          news.title = news.title.length > 25 ? news.title.substring(0, 26) + '...' : news.title
          news.subTitle = news.subTitle.length > 100 ? news.subTitle.substring(0, 100) + '...' : news.subTitle
        }
        return news
      })
    }
  }
}
</script>

<style scoped>
.poppularNew1 {
  width: 100%;
  height: 750px;
  display: grid;
  grid-template-columns: 2.5fr 1fr;
  padding-top: 30px;
}

.pop1Best {
  padding-right: 30px;
  color: #000;
}

.pop1BestText {
  padding: 3% 15% 0 15%;
  text-align: center;
  color: #000;
}

.pop1BestSubTitle {
  padding: 5px 10px;
}

.pop1Right {
  border-left: 1px solid rgb(195, 195, 195);
  padding-left: 30px;
  display: grid;
  grid-template-rows: 1fr 1fr 1fr;
  row-gap: 15px;
  /* word-break: break-all; */
}

.poppularNew1 img {
  object-fit: cover;
  width: 100%;
  height: 100%;
}

.pop1RightTop {
  display: grid;
  grid-template-columns: 1fr 90px;
  color: black;
}

.pop1RightTitle {
  padding-bottom: 3px;
}

.pop1RightImg {
  width: 90px;
  height: 80px;
  padding-left: 15px;
  padding-bottom: 5px;
}

.pop1RightSecond{
  border-bottom: 1px solid rgb(195, 195, 195);
  color: black;
}

.pop1RightThird{
  border-bottom: 1px solid rgb(195, 195, 195);
}

.pop1RignthBody {
  color: black;
}

.pop1RightFoot {
  font-size: 10px;
  font-style: italic;
  color: black;
}
</style>
