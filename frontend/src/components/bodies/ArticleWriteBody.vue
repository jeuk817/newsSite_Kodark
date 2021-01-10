<template>
    <div class="writeArticle">
        <h1 class="title">Article</h1>
        <h2 class="subTitle">Write Article</h2>

        <div class="articleOptions" style="margin-top:60px;">
            <span class="optionTitle">Title</span>
            <div class="optionContent">
                <v-text-field
                ref="title"
                label="title"
                height="40px"
                outlined
                required
                dense=true
                 class="section1"
                >
                </v-text-field>
            </div>
        </div>
        <div class="articleOptions">
            <span class="optionTitle">Subtitle</span>
                <div class="optionContent Article_subTitle">
                <v-text-field
                ref="subTitle"
                label="subTitle"
                height="40px"
                outlined
                required
                dense=true
                >
                </v-text-field>
            </div>
        </div>
        <div class="articleOptions sectionOptions">
            <span class="optionTitle">Section</span>
            <div class="section1">
                <v-select
                :items="items"
                label="Section 1"
                solo
                height="40px"
                dense="true"
                ></v-select>
            </div>
            <div class="section2">
                <v-select
                :items="items"
                label="Section 2"
                solo
                height="40px"
                dense="true"
                ></v-select>
            </div>
            <select name="" id=""></select>
        </div>

        <div class="formContainer">
            
        </div>


    </div>
</template>

<script>
export default {
     data: () => ({
      items: ['Foo', 'Bar', 'Fizz', 'Buzz']

    }),
}           
</script>

<style scopre>
.title{
    font-weight: 300;
    font-size: 50px;
    line-height: 56px;
    margin-bottom: 24px;
}
.subTitle{
    border-bottom: 1px solid #bdbdbd;
    font-weight: 700;
    font-size: 17px;
    margin-bottom: 9px;
}

.articleOptions{
    display: flex;
}

.optionTitle{
    flex-basis: 10%;
    padding-top: 8px;
}
.Article_subTitle{
    width: 70%;
}

.section1{
    margin-right: 30px;
}

.formContainer{
    width: 100%;
    height: 600px;
    border: 1px solid black;
}


</style>