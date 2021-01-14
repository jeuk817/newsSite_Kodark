<template>
  <div class="poppularNew1">
    <div class="pop1Best">
      <div>
        <img src="https://picsum.photos/1920/1080?random">
      </div>
      <div class="pop1BestText">
        <div class="text-h5 font-weight-bold">
          가나다라마바사아자차카타파하가나다라마바사아자차카타파하
        </div>
        <div class="pop1BestSubTitle">
          가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하
        </div>
        <!-- 100자 or 60자 -->
      </div>
    </div>
    <div class="pop1Right">
      <div>
        second news
      </div>
      <div>
        third news
      </div>
      <div>
        forth news
      </div>
    </div>
  </div>
</template>

<script>
export default {
  
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
}

.pop1BestText {
  padding: 3% 15% 0 15%;
  text-align: center;
}

.pop1BestSubTitle {
  padding: 5px 10px;
}

.pop1Right {
  border-left: 1px solid gray;
  padding-left: 30px;
  display: grid;
  grid-template-rows: 1fr 1fr 1fr;
}

.poppularNew1 img {
  object-fit: cover;
  width: 100%;
}
</style>
