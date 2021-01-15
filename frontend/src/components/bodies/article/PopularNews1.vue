<template>
  <div class="poppularNew1">
    <div class="pop1Best">
      <div>
        <img src="https://picsum.photos/1920/1080?random">
      </div>
      <div class="pop1BestText">
        <div class="text-h5 font-weight-bold">
          가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다
          <!-- 45자 -->
        </div>
        <div class="pop1BestSubTitle">
          가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하가나다라마바사아자차카타파하
        </div>
        <!-- 100자 or 60자 -->
      </div>
    </div>
    <div class="pop1Right">
      <div class="pop1RightSecond">
        <div class="pop1RightTop">
          <div class="pop1RightTitle text-h5 font-weight-bold">
            ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJKOPD
            <!-- 40자 -->
          </div>          
          <div class="pop1RightImg">
            <img src="https://picsum.photos/1920/1080?random">
          </div>          
        </div>
        <div>
          abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz
          <!-- 130자 -->
        </div>
        <div class="pop1RightFoot">
          10h ago - By Jack
        </div>
      </div>
      <div class="pop1RightThird">
        <div class="pop1RightTop">
          <div class="pop1RightTitle text-h5 font-weight-bold">
            ABCDEFGHIJKLMNOPQRSTUVWXYZ
          </div>          
          <div class="pop1RightImg">
            <img src="https://picsum.photos/1920/1080?random">
          </div>          
        </div>
        <div>
          abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz
        </div>
        <div class="pop1RightFoot">
          10h ago - By Jack
        </div>
      </div>
      <div class="pop1RightForth">
        <div class="pop1RightTop">
          <div class="pop1RightTitle text-h5 font-weight-bold">
            ABCDEFGHIJKLMNOPQRSTUVWXYZABCDEFGHIJK
          </div>          
          <div class="pop1RightImg">
            <img src="https://picsum.photos/1920/1080?random">
          </div>          
        </div>
        <div>
          abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrst
        </div>
        <div class="pop1RightFoot">
          10h ago - By Jack
        </div>
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
}

.pop1RightThird{
  border-bottom: 1px solid rgb(195, 195, 195);
}

.pop1RightFoot {
  font-size: 10px;
  font-style: italic;
}
</style>
