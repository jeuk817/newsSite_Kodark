<template>
  <div class="introduceContent">
    <div class="introduce__title">
      <h1>Team Kodark's NewsSite</h1>
      <p>
        Lorega necessitatibus dolores.orem ipsum dolor sit amet orem ipsum dolor
        sit amet !
      </p>
    </div>
    <div class="contentContainer">
      <div class="contentItem">
        <h4>
          <div class="contentTitle__container">
            About Project
          </div>
        </h4>
        <!-- <div class="border"></div> -->
        <p>
          Lorem ipsum dolor sit amet efsefsregsfedgarweforis fuga saepe error
          nisi obcaecati! Porro, assumenda repudiandae minus optio harum culpa
          molestiae dolorem dolores amet ipsum id ratione.
        </p>
      </div>
      <div class="contentItem">
        <h4>
          <div class="contentTitle__container">
            Introduce Team
          </div>
        </h4>
        <p>
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Alias, ipsa
          corporis fuga saepe error nisi obcaecati! Porro, assumenda repudiandae
          minus optio harum culpa molestiae dolorem dolores amet ipsum id
          ratione.
        </p>
      </div>
      <div class="contentItem">
        <h4>
          <div class="contentTitle__container">
            Skills
          </div>
        </h4>
        <p>
          Lorem ipsum dolor sit amet consectetur adipisicing elit. Ut suscipit
          atque exercitationem odio repellendus quasi placeat quibusdam id.
          Laudantium debitis suscipit atque voluptatem tempore accusantium
          provident laboriosam minima aliquid similique?
        </p>
      </div>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.introduceContent {
  margin: 0 auto;
  padding-top: 100px;
}
.introduce__title {
  text-align: center;
  padding: 10px;
  border-bottom: 1px solid black;
  margin-bottom: 80px;
}
.border {
  border: black;
  width: 10px;
  height: 48px;
  color: black;
}
.contentItem {
  display: grid;
  grid-template-columns: 1fr 3fr;
  padding: 10px;
}
.contentTitle__container {
  width: 250px;
  height: 70px;
  margin: 10px auto;
  border-right: 1px solid rgb(206, 206, 206);
}
h4 {
  padding: 5px;
  width: 300px;
  line-height: 60px;
  text-align: center;
}
.contentItem p {
  width: 700px;
  padding-left: 3px;
  margin-top: 10px;
  margin-left: 10px;
}
.introduce__title h1 {
  font-weight: 500;
  font-size: 45px;
  margin: 0;
}
.contentContainer {
  margin: 0 auto;
}
</style>
