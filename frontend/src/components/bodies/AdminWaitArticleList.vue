<template>
  <div class="waitArticleListComponent">
    <h1 class="title">Waiting Article Lists</h1>
    <ul class="articleItemList">
      <li class="articleItem">
        <div class="article">
          <img src="../../assets/img/article.png" alt="" class="articleImg">
          <div class="articleDetail">
            <h3 class="articleTitle">Title of Article</h3>
            <h5 class="articleContent">Lorem ipsum dolor sit amet consectetur a
              dipisicing elit. Voluptates, quibusdam, perspiciatis expli
              rum quae aliquam autem?</h5>
            <h6 class="reporterName">이종현기자</h6>
          </div>
        </div>
        <div class="btns">
          <span class="material-icons">
            check
          </span>
          <span class="material-icons">
            clear
          </span>
        </div>
      </li>
      <li class="articleItem">
        <div class="article">
          <img src="../../assets/img/article.png" alt="" class="articleImg">
          <div class="articleDetail">
            <h3 class="articleTitle">Title of Article</h3>
            <h5 class="articleContent">Lorem ipsum dolor sit amet consectetur a
              dipisicing elit. Voluptates, quibusdam, perspiciatis expli
              rum quae aliquam autem?</h5>
            <h6 class="reporterName">이종현기자</h6>
          </div>
        </div>
        <div class="btns">
          <span class="material-icons">
            check
          </span>
          <span class="material-icons">
            clear
          </span>
        </div>
      </li>
    </ul>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped>
ul{
  padding-left: 0;
}
li{
  list-style: none;
}
img{
  width: 80px;
  height: 80px;
}

.title{
  font-weight: 300;
  font-size: 50px;
  line-height: 56px;
  margin-bottom: 24px;
  border-bottom: 1px solid black;
}

.articleItem{
  padding: 4px 0 10px 4px;
  margin-bottom: 10px;
  display: grid;
  grid-template-columns: 3fr 1fr;
  border-bottom: 1px solid black;
}

.article{
  display: grid;
  grid-template-columns: 1fr 4fr;
}

.btns{
  display: grid;
  grid-template-columns: 1fr 1fr;
  justify-items: center;
  align-content: center;
}
</style>